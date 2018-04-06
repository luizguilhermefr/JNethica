package main.java.Fitness;

import main.java.Contracts.FitnessCalculator;
import main.java.Contracts.Individual;

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
