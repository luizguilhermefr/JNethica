package main.java.Util;

import java.util.ArrayList;

public class Statistics {
    public static Double variance (ArrayList<Double> values) {
        Double mean = mean(values);
        ArrayList<Double> squares = new ArrayList<>();
        for (Double value : values) {
            squares.add(Math.pow(value - mean, 2));
        }
        return mean(squares);
    }

    public static Double mean (ArrayList<Double> values) {
        Double sum = sum(values);
        return sum / values.size();
    }

    public static Double sum (ArrayList<Double> values) {
        Double sum = 0.0;
        for (Double value : values) {
            sum += value;
        }
        return sum;
    }
}
