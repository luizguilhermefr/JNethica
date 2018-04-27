package test.java.Fitness;

import main.jnethica.Fitness.MaximumValueFitnessCalculator;
import main.jnethica.Individual.GenericFunction;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MaximumValueFitnessCalculatorTest {

    private GenericFunction firstIndividual() {
        Map<String, Object> values = new HashMap<>();
        values.put("x", 2.0);
        return new GenericFunction("x^2", values);
    }

    private GenericFunction secondIndividual() {
        Map<String, Object> values = new HashMap<>();
        values.put("x", 1.0);
        return new GenericFunction("x^2", values);
    }

    @Test
    void testGetFitness() {
        MaximumValueFitnessCalculator fitnessCalculator = new MaximumValueFitnessCalculator();
        Double fitness = fitnessCalculator.getFitness(firstIndividual());
        assertEquals(4.0, (double) fitness);
    }

    @Test
    void testComparation() {
        MaximumValueFitnessCalculator fitnessCalculator = new MaximumValueFitnessCalculator();
        Integer compareResult = fitnessCalculator.compare(firstIndividual(), secondIndividual());
        assertEquals(3.0, (int) compareResult);
    }
}