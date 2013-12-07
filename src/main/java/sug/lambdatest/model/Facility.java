package sug.lambdatest.model;

public class Facility {
    private final String name;

    public Facility(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return name;
    }
}
