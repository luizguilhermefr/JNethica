package main.java.Individuals.Contracts;

import main.java.Fitness.Contracts.FitnessCalculator;
import main.java.Mutators.Contracts.Mutator;

public interface Individual {
    Individual mutate (Mutator mutator);

    Double getValue ();

    Boolean isBetterThan (Individual individual, FitnessCalculator fitnessFunction);

    Individual clone ();

    String toString ();
}
