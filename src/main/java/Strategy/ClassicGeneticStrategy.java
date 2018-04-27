package main.java.Strategy;

import main.java.Fitness.Contracts.FitnessCalculator;
import main.java.Mutator.Contracts.Mutator;
import main.java.Population.Population;
import main.java.StopCondition.Contracts.StopCondition;
import main.java.Strategy.Contracts.Strategy;

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
