package main.java.Individuals;

import main.java.Fitness.Contracts.FitnessCalculator;
import main.java.Individuals.Contracts.Function;
import main.java.Individuals.Contracts.Individual;
import main.java.Util.RandomUtilities;

public class QuadriGaussianValue implements Function {
    public static final Double MIN_X = -5.0;

    public static final Double MAX_X = 5.0;

    public static final Double MIN_Y = -5.0;

    public static final Double MAX_Y = 5.0;

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
        Double exp1 = Math.exp(-(Math.pow((x + 3), 2) + Math.pow((y + 3), 2)) / 5);
        Double exp2 = Math.exp(-(Math.pow((x + 3), 2) + Math.pow((y - 3), 2)) / 5);
        Double exp3 = Math.exp(-(Math.pow((x - 3), 2) + Math.pow((y + 3), 2)) / 5);
        Double exp4 = Math.exp(-(Math.pow((x - 3), 2) + Math.pow((y - 3), 2)) / 5);
        this.value = .97 * exp1 + .98 * exp2 + .99 * exp3 + 1 * exp4;
    }

    public Double getX () {
        return x;
    }

    public Double getY () {
        return y;
    }

    @Override
    public QuadriGaussianValue mutate (final Double mutationRate) {
        Double nextX = this.x + RandomUtilities.doubleBetween(-mutationRate, mutationRate);
        Double nextY = this.y + RandomUtilities.doubleBetween(-mutationRate, mutationRate);
        nextX = nextX > MAX_X ? MAX_X : nextX;
        nextY = nextY > MAX_Y ? MAX_Y : nextY;
        nextX = nextX < MIN_X ? MIN_X : nextX;
        nextY = nextY < MIN_Y ? MIN_Y : nextY;
        return new QuadriGaussianValue(nextX, nextY);
    }

    @Override
    public QuadriGaussianValue mutate (Double xRate, Double yRate) {
        Double nextX = this.x + RandomUtilities.doubleBetween(-xRate, xRate);
        Double nextY = this.y + RandomUtilities.doubleBetween(-yRate, yRate);
        nextX = nextX > MAX_X ? MAX_X : nextX;
        nextY = nextY > MAX_Y ? MAX_Y : nextY;
        nextX = nextX < MIN_X ? MIN_X : nextX;
        nextY = nextY < MIN_Y ? MIN_Y : nextY;
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
    public QuadriGaussianValue clone () {
        return new QuadriGaussianValue(this.x, this.y);
    }

    @Override
    public String toString () {
        return this.x + "\t" + this.y + "\t" + this.value;
    }
}
