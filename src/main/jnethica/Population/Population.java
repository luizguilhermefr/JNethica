package main.jnethica.Population;

import main.jnethica.Exception.EmptyPopulationException;
import main.jnethica.Factory.Contracts.IndividualFactory;
import main.jnethica.Fitness.Contracts.FitnessCalculator;
import main.jnethica.Individual.Contracts.Individual;
import main.jnethica.Mutator.Contracts.Mutator;
import main.jnethica.Util.RandomSingleton;

import java.util.ArrayList;

public class Population<T extends Individual> {
    protected ArrayList<T> individuals = null;

    public Population () {
        individuals = new ArrayList<>();
    }

    public Integer size () {
        return this.individuals.size();
    }

    public Population<T> generateInitialPopulation (Integer size, IndividualFactory factory) {
        individuals.clear();
        for (Integer i = 0; i < size; i++) {
            T individual = (T) factory.generate();
            individuals.add(individual);
        }
        return this;
    }

    private Population<T> checkNonEmptyPopulation () throws EmptyPopulationException {
        Integer popSize = individuals.size();
        if (popSize < 1) {
            throw new EmptyPopulationException();
        }
        return this;
    }

    public T getRandomIndividual () throws EmptyPopulationException {
        checkNonEmptyPopulation();
        Integer popSize = individuals.size();
        Integer randomIndex = RandomSingleton.getInstance().integerBetween(0, popSize - 1);
        return individuals.get(randomIndex);
    }

    public T getBetter (FitnessCalculator fitnessCalculator) throws EmptyPopulationException {
        checkNonEmptyPopulation();
        Double maxFitnessValue = Double.NEGATIVE_INFINITY;
        T maxFitnessIndividual = null;
        for (T individual : individuals) {
            Double fitnessOfThisElement = fitnessCalculator.getFitness(individual);
            if (fitnessOfThisElement > maxFitnessValue) {
                maxFitnessValue = fitnessOfThisElement;
                maxFitnessIndividual = individual;
            }
        }
        return maxFitnessIndividual;
    }

    public Double sumOfFitness (FitnessCalculator fitnessCalculator) {
        Double count = 0.0;
        for (T individual : individuals) {
            count += fitnessCalculator.getFitness(individual);
        }
        return count;
    }

    public Population<T> reset () {
        individuals.clear();
        return this;
    }

    public Population<T> pushIndividual (T individual) {
        individuals.add(individual);
        return this;
    }

    public Population<T> sort (FitnessCalculator fitnessCalculator) {
        individuals.sort(fitnessCalculator);
        return this;
    }

    public T getIndividualByIndex (Integer i) {
        return individuals.get(i);
    }

    public ArrayList<T> getIndividuals () {
        return individuals;
    }

    public T getLastIndexIndividual () {
        return individuals.get(individuals.size() - 1);
    }

    public Population<T> mutateAll (Mutator mutator) {
        for (Integer i = 0; i < individuals.size(); i++) {
            individuals.set(i, (T) individuals.get(i).mutate(mutator));
        }
        return this;
    }

    public Population<T> clone () {
        Population<T> next = new Population<T>();
        for (T individual : individuals) {
            next.pushIndividual(individual);
        }
        return next;
    }
}
