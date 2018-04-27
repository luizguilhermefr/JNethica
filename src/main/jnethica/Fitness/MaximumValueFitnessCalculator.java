package main.jnethica.Fitness;

import main.jnethica.Fitness.Contracts.FitnessCalculator;
import main.jnethica.Individual.Contracts.Individual;

public class MaximumValueFitnessCalculator implements FitnessCalculator {
    @Override
    public Double getFitness (Individual individual) {
        return individual.getValue();
    }

    @Override
    public int compare (Individual o1, Individual o2) {
        return (int) Math.floor(o1.getValue() - o2.getValue());
    }
}
