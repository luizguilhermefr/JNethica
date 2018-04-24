package main.java.Fitness.Contracts;

import main.java.Individual.Contracts.Individual;

import java.util.Comparator;

public interface FitnessCalculator extends Comparator<Individual> {
    Double getFitness (Individual individual);
}
