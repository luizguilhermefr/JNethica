package main.java.Strategies;

import main.java.Contracts.FitnessCalculator;
import main.java.Contracts.Individual;
import main.java.Contracts.Strategy;
import main.java.Exceptions.EmptyPopulationException;
import main.java.Population.Population;

public class OnePlusOne extends Strategy {

    private static final double MUTATION_RATE = 0.2;

    public OnePlusOne (Population initialPopulation, FitnessCalculator fitnessCalculator) {
        super(initialPopulation, fitnessCalculator);
    }

    private Individual tournament (Individual a, Individual b) {
        if (a.isBetterThan(b, fitnessCalculator)) {
            return a;
        } else {
            return b;
        }
    }

    @Override
    public void run (Integer maxGenerations) throws EmptyPopulationException {
        Individual localOptimum = null;
        Population nextGeneration = null;

        globalOptimum = initialPopulation.getBetter(fitnessCalculator);

        for (Integer i = 0; i < maxGenerations; i++) {
            nextGeneration = initialPopulation.cloneEmpty();
            for (Integer j = 0; j < fixedSize; j++) {
                Individual firstSelected = this.initialPopulation.getRandomIndividual();
                Individual secondSelected = this.initialPopulation.getRandomIndividual();
                Individual betterOfSelecteds = tournament(firstSelected, secondSelected);
                nextGeneration.pushIndividual(betterOfSelecteds.mutate(MUTATION_RATE));
            }
            localOptimum = nextGeneration.getBetter(fitnessCalculator);
            if (localOptimum.isBetterThan(globalOptimum, fitnessCalculator)) {
                globalOptimum = localOptimum;
            }
        }
    }
}
