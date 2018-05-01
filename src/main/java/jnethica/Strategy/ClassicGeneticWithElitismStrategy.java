package jnethica.Strategy;

import jnethica.Crossover.Contracts.Crossover;
import jnethica.Fitness.Contracts.FitnessCalculator;
import jnethica.Individual.Contracts.Individual;
import jnethica.Mutator.Contracts.Mutator;
import jnethica.Population.Population;
import jnethica.StopCondition.Contracts.StopCondition;

public class ClassicGeneticWithElitismStrategy<T extends Individual> extends ClassicGeneticStrategy<T> {
    public ClassicGeneticWithElitismStrategy (Population initialPopulation, FitnessCalculator fitnessCalculator, StopCondition stopCondition, Mutator mutator, Crossover crosser, Double crossoverRate) {
        super(initialPopulation, fitnessCalculator, stopCondition, mutator, crosser, crossoverRate);
    }

    @Override
    protected void execute () {
        Population toCross = selectIndividualsToCross();
        Population crossed = crossIndividuals(toCross);
        currentPopulation = crossed.mutateAllWithElitism(mutator, fitnessCalculator);
    }
}
