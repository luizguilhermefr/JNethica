package Individuals;

import Contracts.FitnessCalculator;
import Contracts.Individual;
import Util.RandomUtilities;

public class QuadriGaussianValue implements Individual {
    private Double x;

    private Double y;

    private Double value;

    public QuadriGaussianValue (final Double x, final Double y) {
        this.x = x;
        this.y = y;
        calculate();
    }

    private void calculate () {
        // 0.97 * e^(-((x+3)^2 + (y+3)^2) / 5) + 0.98 * e^(-((x+3)^2 + (y-3)^2) / 5) + 0.99 * e^(-((x-3)^2 + (y+3)^2) / 5) + 1 * e^(-((x-3)^2 + (y-3)^2) / 5)
        // Max 1 between -5 and 5: x = -2.99538, y = -2.99543, z = 0.971479
        // Max 2 between -5 and 5: x = -2.99557, y = -2.99520, z = 1.00148
        Double exp1 = Math.exp(-(Math.pow((x + 3), 2) + Math.pow((y + 3), 2)) / 5);
        Double exp2 = Math.exp(-(Math.pow((x + 3), 2) + Math.pow((y - 3), 2)) / 5);
        Double exp3 = Math.exp(-(Math.pow((x - 3), 2) + Math.pow((y + 3), 2)) / 5);
        Double exp4 = Math.exp(-(Math.pow((x - 3), 2) + Math.pow((y - 3), 2)) / 5);
        this.value = .97 * exp1 + .98 * exp2 + .99 * exp3 + 1 * exp4;
    }

    @Override
    public Individual mutate (final Double mutationRate) {
        Double nextX = this.x + RandomUtilities.floatBetween(-mutationRate, mutationRate);
        Double nextY = this.y + RandomUtilities.floatBetween(-mutationRate, mutationRate);
        return new QuadriGaussianValue(nextX, nextY);
    }

    @Override
    public Double getValue () {
        return this.value;
    }

    @Override
    public Boolean isBetterThan (Individual individual, FitnessCalculator fitnessFunction) {
        return fitnessFunction.getFitness(this) > fitnessFunction.getFitness(individual);
    }

    @Override
    public Individual clone () {
        return new QuadriGaussianValue(this.x, this.y);
    }

    @Override
    public String toString () {
        return "{ \"x\":" + this.x + ", \"y\":" + this.y + ", \"z\":" + this.value + " }";
    }
}
