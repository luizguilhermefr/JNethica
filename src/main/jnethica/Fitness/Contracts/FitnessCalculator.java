package main.jnethica.Fitness.Contracts;

import main.jnethica.Individual.Contracts.Individual;

import java.util.Comparator;

public interface FitnessCalculator extends Comparator<Individual> {
    Double getFitness (Individual individual);
}
