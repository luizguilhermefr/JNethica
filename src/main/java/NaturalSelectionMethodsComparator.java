package main.java;

import main.java.Contracts.FitnessCalculator;
import main.java.Contracts.Individual;
import main.java.Contracts.Strategy;
import main.java.Exceptions.EmptyPopulationException;
import main.java.Factories.QuadriGaussianValueFactory;
import main.java.Fitness.MaximumValueFitnessCalculator;
import main.java.Individuals.QuadriGaussianValue;
import main.java.Population.Population;
import main.java.Strategies.MetaEvolutionary;
import main.java.Strategies.MiPlusMi;
import main.java.Strategies.OnePlusOne;
import main.java.Strategies.QuickIteratedLocalSearch;

class NaturalSelectionMethodsComparator {

    private Integer populationSize;

    private Integer generations;

    private Population<QuadriGaussianValue> initialPopulation;

    private FitnessCalculator fitnessCalculator;

    private Individual bestOfOnePlusOne;

    private Individual bestOfMiPlusMi;

    private Individual bestOfMetaEvolutionary;

    private Individual bestOfQuickIterated;

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
        System.out.println("1+1\t" + bestOfOnePlusOne);
        System.out.println("µ+µ\t" + bestOfMiPlusMi);
        System.out.println("MEv\t" + bestOfMetaEvolutionary);
        System.out.println("ILC\t" + bestOfQuickIterated);
    }

    void compare () throws EmptyPopulationException {
        printInitializer();

        Strategy onePlusOne = new OnePlusOne(initialPopulation, fitnessCalculator);
        onePlusOne.run(generations);
        bestOfOnePlusOne = onePlusOne.getGlobalOptimum();

        Strategy miPlusMi = new MiPlusMi(initialPopulation, fitnessCalculator);
        miPlusMi.run(generations);
        bestOfMiPlusMi = miPlusMi.getGlobalOptimum();

        Strategy metaEvolutionary = new MetaEvolutionary(initialPopulation, fitnessCalculator);
        metaEvolutionary.run(generations);
        bestOfMetaEvolutionary = metaEvolutionary.getGlobalOptimum();

        Strategy quickIterated = new QuickIteratedLocalSearch(initialPopulation, fitnessCalculator);
        quickIterated.run(generations);
        bestOfQuickIterated = quickIterated.getGlobalOptimum();

        printResults();
    }
}
