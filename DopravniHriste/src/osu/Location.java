package osu;

import java.util.ArrayList;
import java.util.List;

public class Location {
    private String name;
    private List<Sign> sign;

    public Location(String name) {
        this.name = name;
        this.sign = new ArrayList<>();
    }

    public void addSign(Sign newSign) {
        this.sign.add(newSign);
    }

    public List<Sign> getSigns() {
        return sign;
    }

    public String getName() {
        return name;
    }
}