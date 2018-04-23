package main.java.Factories;

import main.java.Factories.Contracts.IndividualFactory;
import main.java.Individuals.GenericFunction;
import main.java.Util.CommonFunctions;
import main.java.Util.RandomUtilities;

import java.util.HashMap;

public class QuadriGaussianValueFactory implements IndividualFactory {
    private static final Double MIN_X = -5.0;

    private static final Double MAX_X = 5.0;

    private static final Double MIN_Y = -5.0;

    private static final Double MAX_Y = 5.0;

    @Override
    public GenericFunction generate () {
        Double x = RandomUtilities.doubleBetween(MIN_X, MAX_X);
        Double y = RandomUtilities.doubleBetween(MIN_Y, MAX_Y);
        HashMap<String, Object> keys = new HashMap<>();
        keys.put("x", x);
        keys.put("y", y);
        return new GenericFunction(CommonFunctions.QUADRI_GAUSSIAN, keys);
    }
}
