package Strategies;

import Contracts.FitnessCalculator;
import Contracts.Individual;
import Contracts.Strategy;
import Exceptions.EmptyPopulationException;
import Individuals.BitMap;
import Populations.BitMapPopulation;
import Util.RandomUtilities;

import java.util.ArrayList;

public class Crossover extends Strategy {

    private static final double MUTATION_RATE = 2.0;

    public Crossover (BitMapPopulation initialPopulation, FitnessCalculator fitnessCalculator) {
        super(initialPopulation, fitnessCalculator);
    }

    private Individual roulette (BitMapPopulation currentPopulation) {
        Double countFitness = currentPopulation.sumOfFitness(fitnessCalculator);
        Double fitness = RandomUtilities.floatBetween(0.0, countFitness);
        Double accumulatedFitness = 0.0;
        ArrayList<Individual> individuals = currentPopulation.getIndividuals();
        for (Individual individual : individuals) {
            accumulatedFitness += fitnessCalculator.getFitness(individual);
            if (accumulatedFitness >= fitness) {
                return individual;
            }
        }
        return individuals.get(individuals.size() - 1);
    }

    private BitMap cross (BitMap first, BitMap second) {
        Integer minSize = Integer.min(first.size(), second.size());
        Integer crossPoint = RandomUtilities.integerBetween(0, minSize);
        BitMap partOfFirst = first.cutBefore(crossPoint);
        BitMap partOfSecond = second.cutAfter(crossPoint);
        partOfFirst.append(partOfSecond);
        return partOfFirst;
    }

    @Override
    public Individual run (Integer maxGenerations) throws EmptyPopulationException {
        BitMapPopulation nextPopulation = (BitMapPopulation) initialPopulation;
        for (Integer i = 0; i < maxGenerations; i++) {
            BitMapPopulation currentPopulation = (BitMapPopulation) nextPopulation.clone();
            for (Integer j = 0; j < fixedSize; j++) {
                BitMap firstDrawn = (BitMap) roulette(currentPopulation);
                BitMap secondDrawn = null;
                do {
                    secondDrawn = (BitMap) roulette(currentPopulation);
                } while (secondDrawn == firstDrawn);
                BitMap crossed = cross(firstDrawn, secondDrawn);
                crossed.mutate(MUTATION_RATE);
                nextPopulation.pushIndividual(crossed);
            }
        }
        return nextPopulation.getBetter(fitnessCalculator);
    }
}
