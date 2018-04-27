package main.jnethica.Factory.Contracts;

import main.jnethica.Individual.Contracts.Individual;

@FunctionalInterface
public interface IndividualFactory {
    Individual generate ();
}
