package main.java.Strategies;

import main.java.Contracts.FitnessCalculator;
import main.java.Contracts.Individual;
import main.java.Contracts.Strategy;
import main.java.Exceptions.EmptyPopulationException;
import main.java.Population.Population;

import java.util.ArrayList;

public class MiPlusMi extends Strategy {

    private static final double MUTATION_RATE = 0.2;

    public MiPlusMi (Population initialPopulation, FitnessCalculator fitnessCalculator) {
        super(initialPopulation, fitnessCalculator);
    }

    Population combine (Population ascendent, Population descendent) throws IllegalArgumentException {
        if (!ascendent.size().equals(descendent.size())) {
            throw new IllegalArgumentException("Different population sizes.");
        }
        ArrayList<Individual> ascendentIndividuals = ascendent.getIndividuals();
        ArrayList<Individual> descendentIndividuals = descendent.getIndividuals();
        Population nextPopulation = ascendent.cloneEmpty();
        for (Integer i = 0; i < (int) Math.floor(ascendent.size() / 2); i++) {
            nextPopulation.pushIndividual(ascendentIndividuals.get(i));
        }
        for (Integer i = (int) Math.floor(ascendentIndividuals.size() / 2); i < descendent.size(); i++) {
            nextPopulation.pushIndividual(descendentIndividuals.get(i));
        }
        return nextPopulation;
    }

    @Override
    public void run (Integer maxGenerations) throws IllegalArgumentException, EmptyPopulationException {
        globalOptimum = initialPopulation.getBetter(fitnessCalculator);
        globalGeneration = 0;

        Individual localOptimum = null;
        Population currentGeneration = initialPopulation.clone();

        for (Integer i = 1; i <= maxGenerations; i++) {
            currentGeneration.sort(fitnessCalculator);
            Population descendents = currentGeneration.clone().mutateAll(MUTATION_RATE).sort(fitnessCalculator);
            currentGeneration = combine(currentGeneration, descendents);
            localOptimum = currentGeneration.getBetter(fitnessCalculator);
            if (localOptimum.isBetterThan(globalOptimum, fitnessCalculator)) {
                globalOptimum = localOptimum;
                globalGeneration = i;
            }
        }
    }

}
