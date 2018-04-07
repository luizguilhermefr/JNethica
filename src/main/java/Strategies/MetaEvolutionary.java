package main.java.Strategies;

import main.java.Contracts.FitnessCalculator;
import main.java.Contracts.Individual;
import main.java.Contracts.Strategy;
import main.java.Population.Population;

public class MetaEvolutionary extends Strategy {
    public MetaEvolutionary (Population initialPopulation, FitnessCalculator fitnessCalculator) {
        super(initialPopulation, fitnessCalculator);
    }

    @Override
    public Individual run (Integer maxGenerations) throws IllegalArgumentException {
        // TODO
        return null;
    }
}
