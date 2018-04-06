package main.java.Populations;

import main.java.Contracts.Individual;
import main.java.Contracts.Population;
import main.java.Individuals.QuadriGaussianValue;

public class QuadriGaussianPopulation extends Population {

    @Override
    public Population cloneEmpty () {
        return new QuadriGaussianPopulation();
    }

    @Override
    public Population clone () {
        QuadriGaussianPopulation next = new QuadriGaussianPopulation();
        for (Individual individual : individuals) {
            next.pushIndividual(individual);
        }
        return next;
    }

    @Override
    public Individual generateIndividual () {
        return QuadriGaussianValue.random();
    }
}
