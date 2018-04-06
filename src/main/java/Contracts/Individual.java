package main.java.Contracts;

public interface Individual {
    Individual mutate (Double mutationRate);

    Double getValue ();

    Boolean isBetterThan (Individual individual, FitnessCalculator fitnessFunction);

    Individual clone ();

    String toString ();
}
