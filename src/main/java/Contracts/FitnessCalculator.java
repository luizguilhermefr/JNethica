package main.java.Contracts;

import java.util.Comparator;

public interface FitnessCalculator extends Comparator<Individual> {
    Double getFitness (Individual individual);
}
