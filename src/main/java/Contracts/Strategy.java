package main.java.Contracts;

import main.java.Exceptions.EmptyPopulationException;
import main.java.Population.Population;

public abstract class Strategy {
    protected final Population initialPopulation;

    protected Integer fixedSize;

    protected FitnessCalculator fitnessCalculator;

    public Strategy (final Population initialPopulation, FitnessCalculator fitnessCalculator) {
        this.initialPopulation = initialPopulation;
        this.fixedSize = initialPopulation.size();
        this.fitnessCalculator = fitnessCalculator;
    }

    public abstract Individual run (Integer maxGenerations) throws IllegalArgumentException, EmptyPopulationException;
}
