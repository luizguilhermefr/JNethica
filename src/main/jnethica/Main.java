package main.jnethica;

import main.jnethica.Factory.Contracts.IndividualFactory;
import main.jnethica.Factory.GenericFunctionFactory;
import main.jnethica.Fitness.Contracts.FitnessCalculator;
import main.jnethica.Fitness.MaximumValueFitnessCalculator;
import main.jnethica.Individual.Contracts.Individual;
import main.jnethica.Individual.GenericFunction;
import main.jnethica.Mutator.Contracts.Mutator;
import main.jnethica.Mutator.CreepMutator;
import main.jnethica.Population.Population;
import main.jnethica.StopCondition.Contracts.StopCondition;
import main.jnethica.StopCondition.MaximumGenerationsStopCondition;
import main.jnethica.Strategy.ClassicGeneticStrategy;
import main.jnethica.Strategy.Contracts.Strategy;

import java.util.ArrayList;
import java.util.HashMap;

public class Main {
    private static Strategy generateFirstProblemThread() {
        // Define variables
        ArrayList<String> variables = new ArrayList<>();
        variables.add("x1");
        variables.add("x2");
        variables.add("x3");

        // Define minimum values for variables
        HashMap<String, Double> minimumValues = new HashMap<>();
        minimumValues.put("x1", -8.0);
        minimumValues.put("x2", -8.0);
        minimumValues.put("x3", -8.0);

        // Define maximum values for variables
        HashMap<String, Double> maximumValues = new HashMap<>();
        maximumValues.put("x1", 8.0);
        maximumValues.put("x2", 8.0);
        maximumValues.put("x3", 8.0);

        // Define function
        String function = "x1 * x2 + x2 * x3";

        // Define individual factory
        IndividualFactory individualFactory;
        individualFactory = new GenericFunctionFactory(function, variables, minimumValues, maximumValues);

        // Generate initial population
        Population<GenericFunction> initialPopulation = new Population<>();
        initialPopulation.generateInitialPopulation(30, individualFactory);

        // Define a fitness calculator
        FitnessCalculator fitnessCalculator = new MaximumValueFitnessCalculator();

        // Define a stop condition
        StopCondition stopCondition = new MaximumGenerationsStopCondition(500);

        // Define a mutator
        Mutator mutator = new CreepMutator(2.0);

        // Generate strategy
        Double crossoverRate = 70.0;

        return new ClassicGeneticStrategy(initialPopulation, fitnessCalculator, stopCondition, mutator, crossoverRate);
    }

    public static void main(String[] args) throws InterruptedException {
        Strategy firstProblem = generateFirstProblemThread();
        firstProblem.start();
        firstProblem.join();
        Individual bestOfFirstProblem = firstProblem.getGlobalOptimum();
        Integer bestOfFirstProblemGeneration = firstProblem.getGlobalGeneration();
        System.out.println(bestOfFirstProblem + "\t" + bestOfFirstProblemGeneration);
        System.exit(0);
    }

    private Strategy generateSecondProblemThread() {
        return null;
    }
}
