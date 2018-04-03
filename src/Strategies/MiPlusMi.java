package Strategies;

import Contracts.FitnessCalculator;
import Contracts.Individual;
import Contracts.Population;
import Contracts.Strategy;

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
