package jnethica.Factory.Contracts;

import jnethica.Individual.Contracts.Individual;

@FunctionalInterface
public interface IndividualFactory {
    Individual generate ();
}
