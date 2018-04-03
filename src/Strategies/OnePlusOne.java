package Strategies;

import Contracts.FitnessCalculator;
import Contracts.Individual;
import Contracts.Population;
import Contracts.Strategy;
import Exceptions.EmptyPopulationException;

public class OnePlusOne extends Strategy {

    private static final double MUTATION_RATE = 0.2;

    public OnePlusOne (Population initialPopulation, FitnessCalculator fitnessCalculator) {
        super(initialPopulation, fitnessCalculator);
    }

    @Override
    public Individual run (Integer maxGenerations) throws EmptyPopulationException {
        Population nextGeneration = null;
        for (Integer i = 0; i < maxGenerations; i++) {
            nextGeneration = initialPopulation.cloneEmpty();
            for (Integer j = 0; j < fixedSize; j++) {
                Individual firstSelected = this.initialPopulation.getRandomIndividual();
                Individual secondSelected = this.initialPopulation.generateIndividual();
                Individual betterOfSelecteds = tournament(firstSelected, secondSelected);
                nextGeneration.pushIndividual(betterOfSelecteds.mutate(MUTATION_RATE));
            }
        }
        return nextGeneration.getBetter(fitnessCalculator);
    }

    private Individual tournament (Individual a, Individual b) {
        if (a.isBetterThan(b, fitnessCalculator)) {
            return a;
        } else {
            return b;
        }
    }
}
