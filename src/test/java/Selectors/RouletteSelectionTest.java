package test.java.Selectors;

import main.java.Factory.GenericFunctionFactory;
import main.java.Fitness.Contracts.FitnessCalculator;
import main.java.Fitness.MaximumValueFitnessCalculator;
import main.java.Individual.GenericFunction;
import main.java.Population.Population;
import main.java.Selector.RouletteSelection;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

class RouletteSelectionTest {

    private FitnessCalculator fitnessCalculator;

    @BeforeAll
    void prepare () {
        fitnessCalculator = new MaximumValueFitnessCalculator();
    }

    @Test
    void testSelect () {
        RouletteSelection<GenericFunction> selector = new RouletteSelection<>(fitnessCalculator);
        Population<GenericFunction> population = new Population<>();
        ArrayList<String> variables = new ArrayList<>();
        variables.add("x");
        population.generateInitialPopulation(100, new GenericFunctionFactory("x", variables));
        GenericFunction selectedIndividual = selector.select(population);
    }
}