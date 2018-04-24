package main.java.Strategy.Contracts;

import main.java.Fitness.Contracts.FitnessCalculator;
import main.java.Individual.Contracts.Individual;
import main.java.Population.Population;
import main.java.StopCondition.Contracts.StopCondition;

public abstract class Strategy extends Thread {
    protected final Population initialPopulation;

    protected Integer fixedSize;

    protected FitnessCalculator fitnessCalculator;

    protected Individual globalOptimum;

    protected Integer globalGeneration;

    protected StopCondition stopCondition;

    public Strategy (final Population initialPopulation, FitnessCalculator fitnessCalculator, StopCondition stopCondition) {
        this.initialPopulation = initialPopulation;
        this.fixedSize = initialPopulation.size();
        this.fitnessCalculator = fitnessCalculator;
        this.stopCondition = stopCondition;
    }

    public Individual getGlobalOptimum () {
        return globalOptimum;
    }

    public Integer getGlobalGeneration () {
        return globalGeneration;
    }

    @Override
    public abstract void run () throws IllegalArgumentException;
}
