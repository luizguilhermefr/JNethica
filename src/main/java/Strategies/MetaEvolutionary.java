package main.java.Strategies;

import main.java.Exceptions.EmptyPopulationException;
import main.java.Fitness.Contracts.FitnessCalculator;
import main.java.Individuals.Contracts.Function;
import main.java.Individuals.Contracts.Individual;
import main.java.Population.Population;
import main.java.Util.Statistics;

import java.util.ArrayList;

public class MetaEvolutionary extends MiPlusMi {
    public MetaEvolutionary (Population initialPopulation, FitnessCalculator fitnessCalculator) {
        super(initialPopulation, fitnessCalculator);
    }

    private ArrayList<Double> argumentsArrayList (ArrayList<Function> individuals, Integer argumentIndex) {
        ArrayList<Double> values = new ArrayList<>();
        for (Function individual : individuals) {
            values.add(individual.getArgument(argumentIndex));
        }
        return values;
    }

    @Override
    public void run (Integer maxGenerations) throws IllegalArgumentException, EmptyPopulationException {
        globalOptimum = initialPopulation.getBetter(fitnessCalculator);
        globalGeneration = 0;

        Individual localOptimum;
        Population currentGeneration = initialPopulation.clone();

        for (Integer i = 1; i <= maxGenerations; i++) {
            currentGeneration.sort(fitnessCalculator);
            Population descendents = currentGeneration.clone();
            ArrayList<Function> individuals = descendents.getIndividuals();
            ArrayList<Double> variances = new ArrayList<>();
            for (Integer j = 0; j < individuals.get(0).countArguments(); j++) {
                ArrayList<Double> values = argumentsArrayList(individuals, j);
                variances.add(Statistics.variance(values));
            }
            for (Integer j = 0; j < individuals.size(); j++) {
                Function individual = individuals.get(j);
                individuals.set(j, individual.mutate(variances));
            }
            descendents.sort(fitnessCalculator);
            currentGeneration = combine(currentGeneration, descendents);
            localOptimum = currentGeneration.getBetter(fitnessCalculator);
            if (localOptimum.isBetterThan(globalOptimum, fitnessCalculator)) {
                globalOptimum = localOptimum;
                globalGeneration = i;
            }
        }
    }
}
