package main.java;

import main.java.Exceptions.EmptyPopulationException;
import main.java.Factories.BitsFactory;
import main.java.Fitness.Contracts.FitnessCalculator;
import main.java.Fitness.MaxPositiveBitsFitnessCalculator;
import main.java.Individuals.Bits;
import main.java.Population.Population;
import main.java.Strategies.Contracts.Strategy;
import main.java.Strategies.Crossover;

class NaturalSelectionMethodsComparator {

    private Integer populationSize;

    private Integer generations;

    private Population<Bits> initialPopulation;

    private FitnessCalculator fitnessCalculator;

    private Bits best = null;

    private Integer generation;

    NaturalSelectionMethodsComparator (Integer populationSize, Integer generations) {
        this.populationSize = populationSize;
        this.generations = generations;
        this.initialPopulation = new Population<>();
        this.initialPopulation.generateInitialPopulation(populationSize, new BitsFactory(20));
        this.fitnessCalculator = new MaxPositiveBitsFitnessCalculator();
    }

    private void printInitializer () {
        System.out.println("--- Comparing methods using " + populationSize + " pop. size and " + generations + " generations ---");
    }

    private void printResults () {
        System.out.println(best + "\t" + generation);
    }

    void compare () throws EmptyPopulationException {
        printInitializer();

        Strategy crossOver = new Crossover(initialPopulation, fitnessCalculator);
        crossOver.run(generations);
        best = (Bits) crossOver.getGlobalOptimum();
        generation = crossOver.getGlobalGeneration();

        printResults();
    }
}
