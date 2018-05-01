package jnethica.Selector.Contracts;

import jnethica.Individual.Contracts.Individual;
import jnethica.Population.Population;

public interface Selector<T extends Individual> {
    T select (Population<T> population);
}
