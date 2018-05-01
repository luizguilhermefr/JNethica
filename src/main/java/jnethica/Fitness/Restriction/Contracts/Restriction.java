package jnethica.Fitness.Restriction.Contracts;

import jnethica.Individual.Contracts.Individual;

@FunctionalInterface
public interface Restriction {
    Double apply (Individual individual);
}
