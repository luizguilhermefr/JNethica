package main.java.Individual.Contracts;

import main.java.Fitness.Contracts.FitnessCalculator;
import main.java.Mutator.Contracts.Mutator;

public abstract class Individual {
    public abstract Individual mutate (Mutator mutator);

    public abstract Double getValue ();

    protected abstract Individual clone ();

    public abstract String toString ();

    public Boolean isBetterThan (Individual individual, FitnessCalculator fitnessFunction) {
        return fitnessFunction.getFitness(this) > fitnessFunction.getFitness(individual);
    }
}
