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
}
