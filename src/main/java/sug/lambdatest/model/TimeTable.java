package sug.lambdatest.model;

import java.util.*;

import static java.util.Optional.*;
import static java.util.stream.Collectors.toList;

public class TimeTable {
    private final long id;
    private final Route route;
    private final Time depTime;
    private final Time arrTime;

    public TimeTable(long id, Route route, Time depTime, Time arrTime) {
        this.id = id;
        this.route = route;
        this.depTime = depTime;
        this.arrTime = arrTime;
    }

    public long getId() {
        return id;
    }

    public Route getRoute() {
        return route;
    }

    public Time getDepTime() {
        return depTime;
    }

    public Time getArrTime() {
        return arrTime;
    }

    public Optional<Time> getDepartureTimeFromStation(String station) {
        return station.equals(route.getDeparture().getName()) ? of(getDepTime()) : getTransitionTime(station);
    }

    public Optional<Time> getArrivalTimeToStation(String station) {
        return station.equals(route.getArrival().getName()) ? of(getArrTime()) : getTransitionTime(station);
    }

    private Optional<Time> getTransitionTime(String station) {
        int index = route.getStops().stream().map(Station::getName).collect(toList()).indexOf(station);
        if (index < 0) {
            return empty();
        }
        int totalDuration = arrTime.minInDay() - depTime.minInDay();
        int duration = totalDuration / (route.getStops().size()) * index;
        return of(depTime.after(duration));
    }

    @Override
    public String toString() {
        return "TimeTable for " + route + ", starting at: " + depTime + " and arriving at: " + arrTime;
    }
}
