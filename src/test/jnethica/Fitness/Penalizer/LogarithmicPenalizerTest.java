package test.jnethica.Fitness.Penalizer;

import main.jnethica.Fitness.Penalizer.LogarithmicPenalizer;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class LogarithmicPenalizerTest extends PenalizerTest {
    @Test
    void testPenalization () {
        LogarithmicPenalizer penalizer = new LogarithmicPenalizer();
        Double value = penalizer.penalize(individual(), restrictions());
        assertEquals(-2.302585092994046, (double) value);
    }
}