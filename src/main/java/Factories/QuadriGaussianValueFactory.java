package main.java.Factories;

import main.java.Factories.Contracts.IndividualFactory;
import main.java.Individuals.QuadriGaussianValue;
import main.java.Util.RandomUtilities;

public class QuadriGaussianValueFactory implements IndividualFactory {
    private static final Double MIN_X = -5.0;

    private static final Double MAX_X = 5.0;

    private static final Double MIN_Y = -5.0;

    private static final Double MAX_Y = 5.0;

    @Override
    public QuadriGaussianValue generate () {
        Double x = RandomUtilities.doubleBetween(MIN_X, MAX_X);
        Double y = RandomUtilities.doubleBetween(MIN_Y, MAX_Y);
        return new QuadriGaussianValue(x, y);
    }
}
