package main.java.Factories;

import main.java.Factories.Contracts.IndividualFactory;
import main.java.Individuals.QuadriGaussianValue;
import main.java.Util.RandomUtilities;

public class QuadriGaussianValueFactory implements IndividualFactory {
    @Override
    public QuadriGaussianValue generate () {
        Double x = RandomUtilities.doubleBetween(QuadriGaussianValue.MIN_X, QuadriGaussianValue.MAX_X);
        Double y = RandomUtilities.doubleBetween(QuadriGaussianValue.MIN_Y, QuadriGaussianValue.MAX_Y);
        return new QuadriGaussianValue(x, y);
    }
}
