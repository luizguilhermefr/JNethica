package main.java.Strategies;

import main.java.Exceptions.EmptyPopulationException;
import main.java.Fitness.Contracts.FitnessCalculator;
import main.java.Individuals.Bits;
import main.java.Mutators.Contracts.BitFlipperMutator;
import main.java.Mutators.Contracts.Mutator;
import main.java.Population.Population;
import main.java.Strategies.Contracts.Strategy;
import main.java.Util.RandomUtilities;

import java.util.ArrayList;

public class Crossover extends Strategy {

    private static final double MUTATION_RATE = 2.0;

    public Crossover (Population<Bits> initialPopulation, FitnessCalculator fitnessCalculator) {
        super(initialPopulation, fitnessCalculator);
    }

    private Bits roulette (Population<Bits> currentPopulation) {
        Double countFitness = currentPopulation.sumOfFitness(fitnessCalculator);
        Double fitness = RandomUtilities.doubleBetween(0.0, countFitness);
        Double accumulatedFitness = 0.0;
        ArrayList<Bits> individuals = currentPopulation.getIndividuals();
        for (Bits individual : individuals) {
            accumulatedFitness += fitnessCalculator.getFitness(individual);
            if (accumulatedFitness >= fitness) {
                return individual;
            }
        }
        return individuals.get(individuals.size() - 1);
    }

    private Bits cross (Bits first, Bits second) {
        Integer crossPoint = RandomUtilities.integerBetween(0, second.size() - 2); // -1 of index, -1 to avoid copy
        Bits partOfFirst = first.splice(0, crossPoint);
        Bits partOfSecond = second.splice(crossPoint + 1, second.size() - 1);
        partOfFirst.append(partOfSecond);
        return partOfFirst;
    }

    @Override
    public void run (Integer maxGenerations) throws EmptyPopulationException {
        globalOptimum = initialPopulation.getBetter(fitnessCalculator);
        globalGeneration = 0;

        Population<Bits> nextPopulation = initialPopulation;

        Mutator mutator = new BitFlipperMutator(2.0);

        for (Integer i = 1; i <= maxGenerations; i++) {
            Population<Bits> currentPopulation = nextPopulation.clone();
            for (Integer j = 0; j < fixedSize; j++) {
                Bits firstDrawn = roulette(currentPopulation);
                Bits secondDrawn;
                do {
                    secondDrawn = roulette(currentPopulation);
                } while (secondDrawn == firstDrawn);
                Bits crossed = cross(firstDrawn, secondDrawn);
                crossed.mutate(mutator);
                nextPopulation.pushIndividual(crossed);
            }
            Bits bestOfGeneration = nextPopulation.getBetter(fitnessCalculator);
            if (bestOfGeneration.isBetterThan(globalOptimum, fitnessCalculator)) {
                globalOptimum = bestOfGeneration;
                globalGeneration = i;
            }
        }
    }
}