package main.java.Contracts;

import main.java.Exceptions.EmptyPopulationException;

public abstract class Strategy {
    protected Population initialPopulation;

    protected Integer fixedSize;

    protected FitnessCalculator fitnessCalculator;

    public Strategy (Population initialPopulation, FitnessCalculator fitnessCalculator) {
        this.initialPopulation = initialPopulation;
        this.fixedSize = initialPopulation.size();
        this.fitnessCalculator = fitnessCalculator;
    }

    public abstract Individual run (Integer maxGenerations) throws IllegalArgumentException, EmptyPopulationException;
}
