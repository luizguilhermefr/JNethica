package main.jnethica.Crossover.Contracts;

import main.jnethica.Individual.Contracts.Individual;

public interface Crossover {
    Individual cross (Individual i1, Individual i2);
}
