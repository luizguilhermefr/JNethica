package jnethica.Fitness.Penalizer;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class LinearPenalizerTest extends PenalizerTestGenerator {
    @Test
    void testPenalization () {
        LinearPenalizer penalizer = new LinearPenalizer();
        Double value = penalizer.penalize(individual(), restrictions());
        assertEquals(-10.0, (double) value);
    }
}