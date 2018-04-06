package main.java.Contracts;

import main.java.Exceptions.EmptyPopulationException;
import main.java.Util.RandomUtilities;

import java.util.ArrayList;

abstract public class Population {
    protected ArrayList<Individual> individuals = null;

    public Population () {
        individuals = new ArrayList<>();
    }

    public Integer size () {
        return this.individuals.size();
    }

    public void insertIndividual (Individual individual) {
        individuals.add(individual);
    }

    public void generateInitialPopulation (Integer size) {
        individuals.clear();
        for (Integer i = 0; i < size; i++) {
            Individual individual = generateIndividual();
            individuals.add(individual);
        }
    }

    private void checkNonEmptyPopulation () throws EmptyPopulationException {
        Integer popSize = individuals.size();
        if (popSize < 1) {
            throw new EmptyPopulationException();
        }
    }

    public Individual getRandomIndividual () throws EmptyPopulationException {
        checkNonEmptyPopulation();
        Integer popSize = individuals.size();
        Integer randomIndex = RandomUtilities.integerBetween(0, popSize - 1);
        return individuals.get(randomIndex);
    }

    public Individual getBetter (FitnessCalculator fitnessCalculator) throws EmptyPopulationException {
        checkNonEmptyPopulation();
        Double maxFitnessValue = Double.NEGATIVE_INFINITY;
        Individual maxFitnessIndividual = null;
        for (Individual individual : individuals) {
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
        for (Individual individual : individuals) {
            count += fitnessCalculator.getFitness(individual);
        }
        return count;
    }

    public void reset () {
        individuals.clear();
    }

    public void pushIndividual (Individual individual) {
        individuals.add(individual);
    }

    public void sort (FitnessCalculator fitnessCalculator) {
        individuals.sort((o1, o2) -> o1.isBetterThan(o2, fitnessCalculator) ? 1 : 0);
    }

    public ArrayList<Individual> getIndividuals () {
        return individuals;
    }

    public abstract Population cloneEmpty ();

    public abstract Population clone ();

    public abstract Individual generateIndividual ();
}
