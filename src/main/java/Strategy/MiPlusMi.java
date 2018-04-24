package main.java.Strategy;

import main.java.Exception.EmptyPopulationException;
import main.java.Fitness.Contracts.FitnessCalculator;
import main.java.Individual.Contracts.Individual;
import main.java.Mutator.Contracts.Mutator;
import main.java.Mutator.CreepMutator;
import main.java.Population.Population;
import main.java.StopCondition.Contracts.StopCondition;
import main.java.Strategy.Contracts.Strategy;

import java.util.ArrayList;

public class MiPlusMi extends Strategy {

    public MiPlusMi (Population initialPopulation, FitnessCalculator fitnessCalculator, StopCondition stopCondition) {
        super(initialPopulation, fitnessCalculator, stopCondition);
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
    public void run () {
        try {
            globalOptimum = initialPopulation.getBetter(fitnessCalculator);
        } catch (EmptyPopulationException e) {
            e.printStackTrace();
            return;
        }
        globalGeneration = 0;

        Individual localOptimum;
        Population currentGeneration = initialPopulation.clone();

        Mutator mutator = new CreepMutator(2.0);

        Integer currentGenerationNumber = 1;

        do {
            currentGeneration.sort(fitnessCalculator);
            Population descendents = currentGeneration.clone().mutateAll(mutator).sort(fitnessCalculator);
            currentGeneration = combine(currentGeneration, descendents);
            try {
                localOptimum = currentGeneration.getBetter(fitnessCalculator);
            } catch (EmptyPopulationException e) {
                e.printStackTrace();
                return;
            }
            if (localOptimum.isBetterThan(globalOptimum, fitnessCalculator)) {
                globalOptimum = localOptimum;
                globalGeneration = currentGenerationNumber;
            }
            stopCondition.report(currentGenerationNumber, fitnessCalculator.getFitness(globalOptimum));
            currentGenerationNumber++;
        } while (!stopCondition.mustStop());
    }

}
