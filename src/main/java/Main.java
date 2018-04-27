package main.java;

import main.java.Factory.Contracts.IndividualFactory;
import main.java.Factory.GenericFunctionFactory;
import main.java.Individual.GenericFunction;
import main.java.Population.Population;

import java.util.ArrayList;
import java.util.HashMap;

public class Main {
    private static void testFirstProblem () {
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

        // Submit strategies

    }

    private static void testSecondProblem () {

    }


    public static void main (String[] args) {
        testFirstProblem();
        testSecondProblem();
        System.exit(0);
    }
}
