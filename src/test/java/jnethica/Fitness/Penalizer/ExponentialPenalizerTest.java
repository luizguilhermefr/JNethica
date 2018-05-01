package jnethica.Fitness.Penalizer;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ExponentialPenalizerTest extends PenalizerTestGenerator {
    @Test
    void testPenalization () {
        ExponentialPenalizer penalizer = new ExponentialPenalizer();
        Double value = penalizer.penalize(individual(), restrictions());
        assertEquals(-22026.4657948, value, 0.001);
    }
}