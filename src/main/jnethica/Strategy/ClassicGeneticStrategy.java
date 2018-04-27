package main.jnethica.Strategy;

import main.jnethica.Fitness.Contracts.FitnessCalculator;
import main.jnethica.Mutator.Contracts.Mutator;
import main.jnethica.Population.Population;
import main.jnethica.StopCondition.Contracts.StopCondition;
import main.jnethica.Strategy.Contracts.Strategy;

public class ClassicGeneticStrategy extends Strategy {
    Double crossoverRate;

    public ClassicGeneticStrategy(Population initialPopulation, FitnessCalculator fitnessCalculator, StopCondition stopCondition, Mutator mutator, Double crossoverRate) {
        super(initialPopulation, fitnessCalculator, stopCondition, mutator);
        this.crossoverRate = crossoverRate;
    }

    @Override
    public void run() throws IllegalArgumentException {

    }
}
