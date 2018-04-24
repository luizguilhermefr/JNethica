package main.java.Crossover.Contracts;

import main.java.Individual.Contracts.Individual;

public interface Crossover {
    Individual cross (Individual i1, Individual i2);
}
