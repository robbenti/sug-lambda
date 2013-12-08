package sug.lambdatest;

import sug.lambdatest.db.*;
import sug.lambdatest.model.*;

import java.util.*;

import static java.util.stream.Collectors.*;

public class RouteService {

    private static final RouteService INSTANCE = new RouteService();

    private DataBase db;

    private RouteService() {
        db = DataBase.connect();
    }

    public static RouteService get() {
        return INSTANCE;
    }

    public List<Route> getAllRoutesPassingFromAStation(String station) {
        return db.getAllRoutes().stream()
                .filter( route -> route.getAllStations().stream()
                        .map(Station::getName)
                        .anyMatch(sName -> sName.equals(station)) )
                .collect(toList());
    }

    public Optional<Route> getRouteById(long id) {
        return db.getAllRoutes().stream().filter( route -> route.getId() == id ).findFirst();
    }

    public Map<Facility, Long> getFacilitiesInRoute(long routeId) {
        return getRouteById(routeId)
                .map(route -> route.getAllStations().stream()
                        .flatMap(s -> s.getFacilities().stream())
                        .collect(groupingBy(i -> i, counting())))
                .orElse(new HashMap<Facility, Long>());
    }
}