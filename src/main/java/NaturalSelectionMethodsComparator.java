package main.java;

import main.java.Factories.Contracts.IndividualFactory;
import main.java.Factories.GenericFunctionFactory;
import main.java.Fitness.Contracts.FitnessCalculator;
import main.java.Fitness.MaximumValueFitnessCalculator;
import main.java.Individuals.Bits;
import main.java.Individuals.Contracts.Individual;
import main.java.Population.Population;
import main.java.StopConditions.MaximumGenerationsStopCondition;
import main.java.Strategies.Contracts.Strategy;
import main.java.Strategies.MiPlusMi;
import main.java.Util.CommonFunctions;

import java.util.ArrayList;
import java.util.HashMap;

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
        this.fitnessCalculator = new MaximumValueFitnessCalculator();
    }

    private void printInitializer () {
        System.out.println("--- Comparing methods using " + populationSize + " pop. size and " + generations + " generations ---");
    }

    private void printResults () {
        System.out.println(best + "\t" + generation);
    }

    private void generateInitialPopulation () {
        ArrayList<String> variables = new ArrayList<>();
        HashMap<String, Double> minimumValues = new HashMap<>();
        HashMap<String, Double> maximumValues = new HashMap<>();
        variables.add("x");
        variables.add("y");
        minimumValues.put("x", -5.0);
        minimumValues.put("y", -5.0);
        maximumValues.put("x", 5.0);
        maximumValues.put("y", 5.0);
        IndividualFactory genericFunctionFactory = new GenericFunctionFactory(CommonFunctions.QUADRI_GAUSSIAN, variables, minimumValues, maximumValues);
        this.initialPopulation.generateInitialPopulation(populationSize, genericFunctionFactory);

    }

    void compare () {
        printInitializer();
        generateInitialPopulation();

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
