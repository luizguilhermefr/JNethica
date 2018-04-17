package main.java.Fitness.Contracts;

import main.java.Individuals.Contracts.Individual;

import java.util.Comparator;

public interface FitnessCalculator extends Comparator<Individual> {
    Double getFitness (Individual individual);
}
