package Fitness.Penalizer;

import jnethica.Fitness.Penalizer.SquarePenalizer;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SquarePenalizerTest extends PenalizerTest {
    @Test
    void testPenalization () {
        SquarePenalizer penalizer = new SquarePenalizer();
        Double value = penalizer.penalize(individual(), restrictions());
        assertEquals(-100.0, (double) value);
    }
}