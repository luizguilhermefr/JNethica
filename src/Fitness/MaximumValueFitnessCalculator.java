package Fitness;

import Contracts.FitnessCalculator;
import Contracts.Individual;

public class MaximumValueFitnessCalculator implements FitnessCalculator {
    @Override
    public Double getFitness (Individual individual) {
        return individual.getValue();
    }
}
