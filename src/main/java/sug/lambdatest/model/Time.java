package sug.lambdatest.model;

public class Time implements Comparable<Time> {
    private final int hour;
    private final int min;

    public Time(int hour, int min) {
        this.hour = hour;
        this.min = min;
    }

    public int getHour() {
        return hour;
    }

    public int getMin() {
        return min;
    }

    private int minInDay() {
        return hour * 60 + min;
    }

    public int compareTo(Time o) {
        return minInDay() - o.minInDay();
    }

    public Time after(int duration) {
        int mins = minInDay() + duration;
        return new Time(mins / 60, mins % 60);
    }
}
