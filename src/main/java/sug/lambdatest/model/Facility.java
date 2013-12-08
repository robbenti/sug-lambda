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

    @Override
    public int hashCode() {
        return name.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof Facility && name.equals(((Facility)obj).getName());
    }
}
