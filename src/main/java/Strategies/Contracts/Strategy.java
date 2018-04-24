package main.java.Strategies.Contracts;

import main.java.Exceptions.EmptyPopulationException;
import main.java.Fitness.Contracts.FitnessCalculator;
import main.java.Individuals.Contracts.Individual;
import main.java.Population.Population;
import main.java.StopConditions.Contracts.StopCondition;

public abstract class Strategy {
    protected final Population initialPopulation;

    protected Integer fixedSize;

    protected FitnessCalculator fitnessCalculator;

    protected Individual globalOptimum;

    protected Integer globalGeneration;

    public Strategy (final Population initialPopulation, FitnessCalculator fitnessCalculator) {
        this.initialPopulation = initialPopulation;
        this.fixedSize = initialPopulation.size();
        this.fitnessCalculator = fitnessCalculator;
    }

    public Individual getGlobalOptimum () {
        return globalOptimum;
    }

    public Integer getGlobalGeneration () {
        return globalGeneration;
    }

    public abstract void run (StopCondition stopCondition) throws IllegalArgumentException, EmptyPopulationException;
}
