package jnethica.Selectors;

import jnethica.Factory.GenericFunctionFactory;
import jnethica.Fitness.Contracts.FitnessCalculator;
import jnethica.Fitness.MaximumValueFitnessCalculator;
import jnethica.Individual.GenericFunction;
import jnethica.Population.Population;
import jnethica.Selector.RouletteSelection;
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