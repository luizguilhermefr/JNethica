package main.java.Selector;

import main.java.Fitness.Contracts.FitnessCalculator;
import main.java.Individual.Contracts.Individual;
import main.java.Population.Population;
import main.java.Selector.Contracts.Selector;
import main.java.Util.RandomUtilities;

import java.util.ArrayList;

public class RouletteSelection<T extends Individual> implements Selector {

    private FitnessCalculator fitnessCalculator;

    public RouletteSelection (FitnessCalculator fitnessCalculator) {
        this.fitnessCalculator = fitnessCalculator;
    }

    @Override
    public T select (Population population) {
        Double countFitness = population.sumOfFitness(fitnessCalculator);
        Double fitness = RandomUtilities.doubleBetween(0.0, countFitness);
        ArrayList<T> individuals = population.getIndividuals();

        Double accumulatedFitness = 0.0;

        for (T individual : individuals) {
            accumulatedFitness += fitnessCalculator.getFitness(individual);
            if (accumulatedFitness >= fitness) {
                return individual;
            }
        }

        return individuals.get(individuals.size() - 1);
    }
}
