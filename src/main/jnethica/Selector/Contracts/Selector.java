package main.jnethica.Selector.Contracts;

import main.jnethica.Individual.Contracts.Individual;
import main.jnethica.Population.Population;

public interface Selector<T extends Individual> {
    T select (Population<T> population);
}
