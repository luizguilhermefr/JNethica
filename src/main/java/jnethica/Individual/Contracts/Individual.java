package jnethica.Individual.Contracts;

import jnethica.Fitness.Contracts.FitnessCalculator;
import jnethica.Mutator.Contracts.Mutator;

public abstract class Individual {
    public abstract Individual mutate (Mutator mutator);

    public abstract Individual mutateWithElitism (Mutator mutator, FitnessCalculator fitnessCalculator);

    public abstract Double getValue ();

    protected abstract Individual clone ();

    public abstract String toString ();

    public Boolean isBetterThan (Individual individual, FitnessCalculator fitnessFunction) {
        return fitnessFunction.getFitness(this) > fitnessFunction.getFitness(individual);
    }
}
