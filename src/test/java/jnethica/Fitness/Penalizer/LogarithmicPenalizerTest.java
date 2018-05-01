package jnethica.Fitness.Penalizer;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class LogarithmicPenalizerTest extends PenalizerTestGenerator {
    @Test
    void testPenalization () {
        LogarithmicPenalizer penalizer = new LogarithmicPenalizer();
        Double value = penalizer.penalize(individual(), restrictions());
        assertEquals(-2.302585092994046, (double) value);
    }
}