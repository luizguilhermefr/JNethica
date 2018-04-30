package test.jnethica.Fitness.Penalizer;

import main.jnethica.Fitness.Penalizer.LinearPenalizer;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class LinearPenalizerTest extends PenalizerTest {
    @Test
    void testPenalization () {
        LinearPenalizer penalizer = new LinearPenalizer();
        Double value = penalizer.penalize(individual(), restrictions());
        assertEquals(-10.0, (double) value);
    }
}