package sug.lambdatest.model;

import java.util.*;

public class Station {
    private final String name;
    private final String city;
    private final List<Facility> facilities;

    public Station(String name, String city, List<Facility> facilities) {
        this.name = name;
        this.city = city;
        this.facilities = facilities;
    }

    public String getName() {
        return name;
    }

    public String getCity() {
        return city;
    }

    public List<Facility> getFacilities() {
        return facilities;
    }

    @Override
    public String toString() {
        return "Station: " +  name + " in " + city;
    }
}
