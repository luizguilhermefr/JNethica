package main.java.Strategies;

import main.java.Contracts.FitnessCalculator;
import main.java.Contracts.Individual;
import main.java.Contracts.Population;
import main.java.Contracts.Strategy;

public class MiPlusMi extends Strategy {

    public MiPlusMi (Population initialPopulation, FitnessCalculator fitnessCalculator) {
        super(initialPopulation, fitnessCalculator);
    }

    @Override
    public Individual run (Integer maxGenerations) {
//        Population nextGeneration = null;
//        Population currentBestHalf;
//
//        for (Integer i = 0; i < maxGenerations; i++) {
//            nextGeneration = initialPopulation.clone();
//            nextGeneration.mutateAll();
//            nextGeneration.sort(fitnessCalculator);
//            currentBestHalf = initialPopulation.clone();
//            currentBestHalf.sort(fitnessCalculator);
//
//        }
//
//        return nextGeneration.getBetter(fitnessCalculator);
        return null;
    }

}
