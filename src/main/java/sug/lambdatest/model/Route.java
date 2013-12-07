package sug.lambdatest.model;

import java.util.*;

public class Route {
    private final long id;
    private final Station departure;
    private final Station arrival;
    private final List<Station> stops;

    public Route(long id, Station departure, Station arrival, List<Station> stops) {
        this.id = id;
        this.departure = departure;
        this.arrival = arrival;
        this.stops = stops;
    }

    public long getId() {
        return id;
    }

    public Station getDeparture() {
        return departure;
    }

    public Station getArrival() {
        return arrival;
    }

    public List<Station> getStops() {
        return stops;
    }
}
