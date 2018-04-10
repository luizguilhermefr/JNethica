package main.java.Strategies;

import main.java.Contracts.FitnessCalculator;
import main.java.Contracts.Fxy;
import main.java.Contracts.Individual;
import main.java.Exceptions.EmptyPopulationException;
import main.java.Population.Population;
import main.java.Util.Statistics;

import java.util.ArrayList;

public class MetaEvolutionary extends MiPlusMi {
    public MetaEvolutionary (Population initialPopulation, FitnessCalculator fitnessCalculator) {
        super(initialPopulation, fitnessCalculator);
    }

    private ArrayList<Double> xArray (ArrayList<Fxy> individuals) {
        ArrayList<Double> values = new ArrayList<>();
        for (Fxy individual : individuals) {
            values.add(individual.getX());
        }
        return values;
    }

    private ArrayList<Double> yArray (ArrayList<Fxy> individuals) {
        ArrayList<Double> values = new ArrayList<>();
        for (Fxy individual : individuals) {
            values.add(individual.getY());
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
            ArrayList<Fxy> individuals = descendents.getIndividuals();
            Double varX = Statistics.variance(xArray(individuals));
            Double varY = Statistics.variance(yArray(individuals));
            for (Integer j = 0; j < individuals.size(); j++) {
                Fxy individual = individuals.get(j);
                individuals.set(j, individual.mutate(varX, varY));
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
