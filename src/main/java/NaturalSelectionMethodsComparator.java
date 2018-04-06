package main.java;

import main.java.Contracts.FitnessCalculator;
import main.java.Contracts.Individual;
import main.java.Contracts.Population;
import main.java.Contracts.Strategy;
import main.java.Exceptions.EmptyPopulationException;
import main.java.Fitness.MaximumValueFitnessCalculator;
import main.java.Populations.QuadriGaussianPopulation;
import main.java.Strategies.MiPlusMi;
import main.java.Strategies.OnePlusOne;

class NaturalSelectionMethodsComparator {

    private Integer populationSize;

    private Integer generations;

    private Population initialPopulation;

    private FitnessCalculator fitnessCalculator;

    private Individual bestOfOnePlusOne;

    private Individual bestOfMiPlusMi;

    NaturalSelectionMethodsComparator (Integer populationSize, Integer generations) {
        this.populationSize = populationSize;
        this.generations = generations;
        this.initialPopulation = new QuadriGaussianPopulation();
        this.initialPopulation.generateInitialPopulation(populationSize);
        this.fitnessCalculator = new MaximumValueFitnessCalculator();
    }

    private void printInitializer () {
        System.out.println("<-<-< Comparing methods using " + populationSize + " pop. size and " + generations + " generations >->->");
    }

    private void printResults () {
        System.out.println("1+1:\t" + bestOfOnePlusOne);
        System.out.println("µ+µ:\t" + bestOfMiPlusMi);
        System.out.println("<< -- END -- >>");
    }

    void compare () throws EmptyPopulationException {
        printInitializer();
        Strategy onePlusOne = new OnePlusOne(initialPopulation, fitnessCalculator);
        bestOfOnePlusOne = onePlusOne.run(generations);
        Strategy miPlusMi = new MiPlusMi(initialPopulation, fitnessCalculator);
        bestOfMiPlusMi = miPlusMi.run(generations);
        printResults();
    }
}
