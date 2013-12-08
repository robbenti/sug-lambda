package sug.lambdatest;

import sug.lambdatest.db.*;
import sug.lambdatest.model.*;

import java.util.*;
import java.util.stream.*;

import static java.util.Arrays.asList;
import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.*;

public class TimeTableService {

    private static final TimeTableService INSTANCE = new TimeTableService();

    private DataBase db;

    private TimeTableService() {
        db = DataBase.connect();
    }

    public static TimeTableService get() {
        return INSTANCE;
    }

    public List<TimeTable> getAllTimeTablesForAStation(String station) {
        return db.getAllTimeTables().stream()
                .filter(tt -> tt.getRoute().getAllStations().stream().anyMatch(s -> s.getName().equals(station)))
                .collect(toList());
    }

    public Station getStationWithMaxNumberOfTrains() {
        return db.getAllTimeTables().stream()
                .map(TimeTable::getRoute)
                .flatMap(route -> route.getAllStations().stream())
                .collect(groupingBy(i -> i, counting()))
                .entrySet().stream()
                .max(comparing(Map.Entry<Station, Long>::getValue))
                .map(Map.Entry::getKey)
                .get();
    }

    public List<List<TimeTable>> getTrainsPathWithConnection(String from, String to) {
        return new ArrayList<List<TimeTable>>() {{
            addAll(
                    db.getAllTimeTables().stream()
                    .filter(tt -> {
                        List<String> stations = tt.getRoute().getAllStations().stream().map(Station::getName).collect(toList());
                        int fromIndex = stations.indexOf(from);
                        return fromIndex >= 0 && fromIndex < stations.indexOf(to);
                    }).map( tt -> new ArrayList<TimeTable>() {{ add(tt); }}).collect(toList())
            );
            addAll(
                    db.getAllTimeTables().stream()
                    .filter(tt -> tt.getRoute().getAllStations().stream()
                            .map(Station::getName).anyMatch(s -> s.equals(from)))
                    .flatMap(tt -> getConnectionsFor(tt, from, to)
                            .map(tt2 -> asList(tt, tt2))).collect(toList())

            );
        }};
    }

    private Stream<TimeTable> getConnectionsFor(TimeTable timeTable, String from, String to) {
        return db.getAllTimeTables().stream()
                .filter(tt -> tt.getRoute().getAllStations().stream()
                        .map(Station::getName).anyMatch(s -> s.equals(to)))
                .filter(tt -> tt.getRoute().getAllStations().stream()
                        .anyMatch(s -> timeTable.getArrivalTimeToStation(s.getName()).flatMap(t1 -> tt.getDepartureTimeFromStation(s.getName()).map(t2 -> t1.compareTo(t2) < 0)).orElse(false) &&
                                timeTable.getRoute().getAllStations().indexOf(s) > timeTable.getRoute().getAllStations().stream().map(Station::getName).collect(toList()).indexOf(from) &&
                                tt.getRoute().getAllStations().indexOf(s) < tt.getRoute().getAllStations().stream().map(Station::getName).collect(toList()).indexOf(to)));
    }

    public List<TimeTable> getTrainsAfter(String from, Time after) {
        return db.getAllTimeTables().stream()
                .filter(tt -> tt.getDepartureTimeFromStation(from).map(time -> time.compareTo(after) > 0).orElse(false))
                .collect(toList());
    }
}
