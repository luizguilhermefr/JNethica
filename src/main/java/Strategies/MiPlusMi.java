package main.java.Strategies;

import main.java.Contracts.FitnessCalculator;
import main.java.Contracts.Individual;
import main.java.Contracts.Population;
import main.java.Contracts.Strategy;
import main.java.Exceptions.EmptyPopulationException;

public class MiPlusMi extends Strategy {

    private static final double MUTATION_RATE = 0.2;

    public MiPlusMi (Population initialPopulation, FitnessCalculator fitnessCalculator) {
        super(initialPopulation, fitnessCalculator);
    }

    @Override
    public Individual run (Integer maxGenerations) throws EmptyPopulationException {
        Population currentGeneration = initialPopulation.clone();

        for (Integer i = 0; i < maxGenerations; i++) {
            currentGeneration.mutateAll(MUTATION_RATE);
            currentGeneration.sort(fitnessCalculator);
        }

        return currentGeneration.getBetter(fitnessCalculator);
    }

}
