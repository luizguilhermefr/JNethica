package main.jnethica.Fitness.Restriction.Contracts;

import main.jnethica.Individual.Contracts.Individual;

@FunctionalInterface
public interface Restriction {
    Double apply (Individual individual);
}
