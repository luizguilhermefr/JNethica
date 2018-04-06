package main.java.Fitness;

import main.java.Contracts.FitnessCalculator;
import main.java.Contracts.Individual;

public class MaximumValueFitnessCalculator implements FitnessCalculator {
    @Override
    public Double getFitness (Individual individual) {
        return individual.getValue();
    }
}
