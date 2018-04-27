package test.java.Selectors;

import main.jnethica.Factory.GenericFunctionFactory;
import main.jnethica.Fitness.Contracts.FitnessCalculator;
import main.jnethica.Fitness.MaximumValueFitnessCalculator;
import main.jnethica.Individual.GenericFunction;
import main.jnethica.Population.Population;
import main.jnethica.Selector.RouletteSelection;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class RouletteSelectionTest {

    @Test
    void testSelect () {
        FitnessCalculator fitnessCalculator = new MaximumValueFitnessCalculator();
        RouletteSelection<GenericFunction> selector = new RouletteSelection<>(fitnessCalculator);
        Population<GenericFunction> population = new Population<>();
        ArrayList<String> variables = new ArrayList<>();
        variables.add("x");
        population.generateInitialPopulation(100, new GenericFunctionFactory("2*x", variables));
        GenericFunction selectedIndividual = selector.select(population);
        assertNotNull(selectedIndividual);
    }
}