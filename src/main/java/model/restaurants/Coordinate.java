package model.restaurants;

import javafx.util.Pair;

public class Coordinate {

    private Pair<Double, Double> coordinate;

    public Coordinate(Pair<Double, Double> coordinate) {
        this.coordinate = coordinate;
    }

    public void setCoordinate(Pair<Double, Double> coordinate) {
        this.coordinate = coordinate;
    }

    public Pair<Double, Double> getCoordinate() {
        return coordinate;
    }
}
