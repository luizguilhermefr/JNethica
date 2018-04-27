package main.jnethica.Individual.Contracts;

import main.jnethica.Fitness.Contracts.FitnessCalculator;
import main.jnethica.Mutator.Contracts.Mutator;

public abstract class Individual {
    public abstract Individual mutate (Mutator mutator);

    public abstract Double getValue ();

    protected abstract Individual clone ();

    public abstract String toString ();

    public Boolean isBetterThan (Individual individual, FitnessCalculator fitnessFunction) {
        return fitnessFunction.getFitness(this) > fitnessFunction.getFitness(individual);
    }
}
