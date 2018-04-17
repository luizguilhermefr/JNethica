package main.java.Individuals.Contracts;

import main.java.Fitness.Contracts.FitnessCalculator;

public interface Individual {
    Individual mutate (Double mutationRate);

    Double getValue ();

    Boolean isBetterThan (Individual individual, FitnessCalculator fitnessFunction);

    Individual clone ();

    String toString ();
}
