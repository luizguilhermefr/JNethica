package main.java.Factory.Contracts;

import main.java.Individual.Contracts.Individual;

@FunctionalInterface
public interface IndividualFactory {
    Individual generate ();
}
