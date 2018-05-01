package jnethica.StopCondition;

import jnethica.Util.RandomSingleton;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class MaximumGenerationsStopConditionTest {

    private Double randomFitness () {
        return RandomSingleton.getInstance().doubleBetween(.0, 100.0);
    }

    @Test
    void testReset () {
        MaximumGenerationsStopCondition stopCondition = new MaximumGenerationsStopCondition(10);
        for (Integer i = 0; i <= 10; i++) {
            stopCondition.report(randomFitness());
        }
        assertFalse(stopCondition.mustContinue());
        stopCondition.reset();
        assertTrue(stopCondition.mustContinue());
    }

    @Test
    void restMustContinue () {
        MaximumGenerationsStopCondition stopCondition = new MaximumGenerationsStopCondition(10);
        assertTrue(stopCondition.mustContinue());
        for (Integer i = 0; i <= 5; i++) {
            stopCondition.report(randomFitness());
        }
        assertTrue(stopCondition.mustContinue());
        for (Integer i = 0; i <= 5; i++) {
            Double randomFitness = RandomSingleton.getInstance().doubleBetween(.0, 100.0);
            stopCondition.report(randomFitness);
        }
        assertFalse(stopCondition.mustContinue());
    }

    @Test
    void testMustStop () {
        MaximumGenerationsStopCondition stopCondition = new MaximumGenerationsStopCondition(10);
        assertFalse(stopCondition.mustStop());
        for (Integer i = 0; i <= 9; i++) {
            stopCondition.report(randomFitness());
        }
        assertTrue(stopCondition.mustStop());
    }
}