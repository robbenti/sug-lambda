package sug.lambdatest.model;

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
}
