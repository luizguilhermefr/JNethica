package main.java.Selector.Contracts;

import main.java.Individual.Contracts.Individual;
import main.java.Population.Population;

public interface Selector<T extends Individual> {
    T select (Population<T> population);
}
