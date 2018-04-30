package test.java.Fitness.Penalizer;

import main.jnethica.Fitness.Penalizer.ExponentialPenalizer;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ExponentialPenalizerTest extends PenalizerTest {
    @Test
    void testPenalization () {
        ExponentialPenalizer penalizer = new ExponentialPenalizer();
        Double value = penalizer.penalize(individual(), restrictions());
        assertEquals(-22026.4657948, value, 0.001);
    }
}