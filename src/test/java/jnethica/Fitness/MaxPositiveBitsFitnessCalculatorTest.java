package jnethica.Fitness;

import jnethica.Individual.Bits;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MaxPositiveBitsFitnessCalculatorTest {

    private ArrayList<Boolean> bitSet () {
        ArrayList<Boolean> bitSet = new ArrayList<>(5);
        bitSet.add(true);
        bitSet.add(false);
        bitSet.add(true);
        bitSet.add(true);
        bitSet.add(false);
        return bitSet;
    }

    @Test
    void getFitness () {
        Bits bits = new Bits(bitSet());
        MaxPositiveBitsFitnessCalculator fitnessCalculator = new MaxPositiveBitsFitnessCalculator();
        Double fitness = fitnessCalculator.getFitness(bits);
        assertEquals(3.0, (double) fitness);
    }
}