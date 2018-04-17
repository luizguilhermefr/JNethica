package main.java.Strategies;

import main.java.Exceptions.EmptyPopulationException;
import main.java.Fitness.Contracts.FitnessCalculator;
import main.java.Individuals.Contracts.Individual;
import main.java.Population.Population;
import main.java.Strategies.Contracts.Strategy;

public class QuickIteratedLocalSearch extends Strategy {

    private static final double BIG_MUTATION_RATE = 1;

    private static final double SMALL_MUTATION_RATE = 0.125;

    public QuickIteratedLocalSearch (Population initialPopulation, FitnessCalculator fitnessCalculator) {
        super(initialPopulation, fitnessCalculator);
    }

    @Override
    public void run (Integer maxGenerations) throws IllegalArgumentException, EmptyPopulationException {
        Individual starter = this.initialPopulation.getBetter(fitnessCalculator);

        globalOptimum = starter;
        globalGeneration = 0;

        Individual localBetter = starter;

        for (Integer i = 1; i <= maxGenerations; i++) {
            do {
                Individual neighbor = localBetter.mutate(SMALL_MUTATION_RATE);
                if (neighbor.isBetterThan(globalOptimum, fitnessCalculator)) {
                    localBetter = neighbor;
                } else {
                    break;
                }
            } while (true);
            localBetter = localBetter.mutate(BIG_MUTATION_RATE);
            if (localBetter.isBetterThan(globalOptimum, fitnessCalculator)) {
                globalOptimum = localBetter;
                globalGeneration = i;
            }
        }
    }
}
