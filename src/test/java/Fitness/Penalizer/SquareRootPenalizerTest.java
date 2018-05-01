package Fitness.Penalizer;

import jnethica.Fitness.Penalizer.SquareRootPenalizer;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SquareRootPenalizerTest extends PenalizerTest {
    @Test
    void testPenalization () {
        SquareRootPenalizer penalizer = new SquareRootPenalizer();
        Double value = penalizer.penalize(individual(), restrictions());
        assertEquals(-3.16227766017, value, 0.001);
    }
}