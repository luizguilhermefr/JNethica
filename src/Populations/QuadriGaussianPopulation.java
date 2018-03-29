package Populations;

import Contracts.Individual;
import Contracts.Population;
import Individuals.QuadriGaussianValue;
import Util.RandomUtilities;

public class QuadriGaussianPopulation extends Population {
    private static final Double MIN_X = -5.0;
    private static final Double MAX_X = 5.0;
    private static final Double MIN_Y = -5.0;
    private static final Double MAX_Y = 5.0;

    @Override
    public Population cloneEmpty () {
        return new QuadriGaussianPopulation();
    }

    @Override
    public Population clone () {
        QuadriGaussianPopulation next = new QuadriGaussianPopulation();
        for (Individual individual : individuals) {
            next.pushIndividual(individual);
        }
        return next;
    }

    @Override
    public Individual generateIndividual () {
        Double x = RandomUtilities.floatBetween(MIN_X, MAX_X);
        Double y = RandomUtilities.floatBetween(MIN_Y, MAX_Y);
        return new QuadriGaussianValue(x, y);
    }
}
