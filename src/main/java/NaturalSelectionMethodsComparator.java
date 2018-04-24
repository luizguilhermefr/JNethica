package main.java;

import main.java.Factories.QuadriGaussianValueFactory;
import main.java.Fitness.Contracts.FitnessCalculator;
import main.java.Fitness.MaximumValueFitnessCalculator;
import main.java.Individuals.Bits;
import main.java.Individuals.Contracts.Individual;
import main.java.Population.Population;
import main.java.StopConditions.MaximumGenerationsStopCondition;
import main.java.Strategies.Contracts.Strategy;
import main.java.Strategies.MiPlusMi;

class NaturalSelectionMethodsComparator {

    private Integer populationSize;

    private Integer generations;

    private Population<Bits> initialPopulation;

    private FitnessCalculator fitnessCalculator;

    private Individual best = null;

    private Integer generation;

    NaturalSelectionMethodsComparator (Integer populationSize, Integer generations) {
        this.populationSize = populationSize;
        this.generations = generations;
        this.initialPopulation = new Population<>();
        this.initialPopulation.generateInitialPopulation(populationSize, new QuadriGaussianValueFactory());
        this.fitnessCalculator = new MaximumValueFitnessCalculator();
    }

    private void printInitializer () {
        System.out.println("--- Comparing methods using " + populationSize + " pop. size and " + generations + " generations ---");
    }

    private void printResults () {
        System.out.println(best + "\t" + generation);
    }

    void compare () {
        printInitializer();

        Strategy miPlusMi = new MiPlusMi(initialPopulation, fitnessCalculator, new MaximumGenerationsStopCondition(generations));
        miPlusMi.start();

        try {
            miPlusMi.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        best = miPlusMi.getGlobalOptimum();
        generation = miPlusMi.getGlobalGeneration();

        printResults();
    }
}
