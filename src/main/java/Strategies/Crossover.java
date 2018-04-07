package main.java.Strategies;

import main.java.Contracts.FitnessCalculator;
import main.java.Contracts.Individual;
import main.java.Contracts.Strategy;
import main.java.Exceptions.EmptyPopulationException;
import main.java.Individuals.BitMap;
import main.java.Population.Population;
import main.java.Util.RandomUtilities;

import java.util.ArrayList;

// Refere-se a 1a aula de geneticos...
public class Crossover extends Strategy {

    private static final double MUTATION_RATE = 2.0;

    public Crossover (Population<BitMap> initialPopulation, FitnessCalculator fitnessCalculator) {
        super(initialPopulation, fitnessCalculator);
    }

    private Individual roulette (Population<BitMap> currentPopulation) {
        Double countFitness = currentPopulation.sumOfFitness(fitnessCalculator);
        Double fitness = RandomUtilities.doubleBetween(0.0, countFitness);
        Double accumulatedFitness = 0.0;
        ArrayList<BitMap> individuals = currentPopulation.getIndividuals();
        for (Individual individual : individuals) {
            accumulatedFitness += fitnessCalculator.getFitness(individual);
            if (accumulatedFitness >= fitness) {
                return individual;
            }
        }
        return individuals.get(individuals.size() - 1);
    }

    private BitMap cross (BitMap first, BitMap second) {
        Integer crossPoint = RandomUtilities.integerBetween(0, second.size() - 2); // -1 of index, -1 to avoid copy
        BitMap partOfFirst = first.splice(0, crossPoint);
        BitMap partOfSecond = second.splice(crossPoint + 1, second.size() - 1);
        partOfFirst.append(partOfSecond);
        return partOfFirst;
    }

    @Override
    public Individual run (Integer maxGenerations) throws EmptyPopulationException {
        Population<BitMap> nextPopulation = initialPopulation;
        for (Integer i = 0; i < maxGenerations; i++) {
            Population<BitMap> currentPopulation = nextPopulation.clone();
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
