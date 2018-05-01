package jnethica.Crossover.Contracts;

import jnethica.Individual.Contracts.Individual;

@FunctionalInterface
public interface Crossover {
    Individual cross (Individual i1, Individual i2);
}
