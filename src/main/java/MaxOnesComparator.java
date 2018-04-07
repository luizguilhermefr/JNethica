package main.java;

import main.java.Contracts.FitnessCalculator;
import main.java.Contracts.Individual;
import main.java.Contracts.IndividualFactory;
import main.java.Contracts.Strategy;
import main.java.Exceptions.EmptyPopulationException;
import main.java.Fitness.MaximumValueFitnessCalculator;
import main.java.Individuals.BitMap;
import main.java.Population.Population;
import main.java.Strategies.Crossover;

// Refere-se a 1a aula de geneticos...
class MaxOnesComparator {

    private Integer generations;

    private Population<BitMap> initialPopulation;

    private FitnessCalculator fitnessCalculator;

    MaxOnesComparator (Integer populationSize, Integer generations) {
        this.generations = generations;
        this.initialPopulation = new Population<BitMap>();
        this.initialPopulation.generateInitialPopulation(populationSize, new IndividualFactory() {
            @Override
            public Individual generate () {
                return null;
            }
        });
        this.fitnessCalculator = new MaximumValueFitnessCalculator();
    }

    void compare () throws EmptyPopulationException {
        Strategy crossover = new Crossover(initialPopulation, fitnessCalculator);
        Individual best = crossover.run(generations);
        System.out.println(best);
    }
}
