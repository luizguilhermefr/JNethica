package main.java;

import main.java.Contracts.FitnessCalculator;
import main.java.Contracts.Individual;
import main.java.Contracts.Strategy;
import main.java.Exceptions.EmptyPopulationException;
import main.java.Fitness.MaximumValueFitnessCalculator;
import main.java.Populations.BitMapPopulation;
import main.java.Strategies.Crossover;

// Refere-se a 1a aula de geneticos...
class MaxOnesComparator {

    private Integer generations;

    private BitMapPopulation initialPopulation;

    private FitnessCalculator fitnessCalculator;

    MaxOnesComparator (Integer populationSize, Integer generations) {
        this.generations = generations;
        this.initialPopulation = new BitMapPopulation();
        this.initialPopulation.generateInitialPopulation(populationSize);
        this.fitnessCalculator = new MaximumValueFitnessCalculator();
    }

    void compare () throws EmptyPopulationException {
        Strategy crossover = new Crossover(initialPopulation, fitnessCalculator);
        Individual best = crossover.run(generations);
        System.out.println(best);
    }
}
