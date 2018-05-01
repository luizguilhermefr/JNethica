package StopCondition;

import jnethica.StopCondition.IdleGenerationsStopCondition;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class IdleGenerationsStopConditionTest {

    @Test
    void testReset () {
        IdleGenerationsStopCondition stopCondition = new IdleGenerationsStopCondition(10);
        for (Integer i = 0; i <= 10; i++) {
            stopCondition.report(100.0);
        }
        assertFalse(stopCondition.mustContinue());
        stopCondition.reset();
        assertTrue(stopCondition.mustContinue());
    }

    @Test
    void restMustContinue () {
        IdleGenerationsStopCondition stopCondition = new IdleGenerationsStopCondition(10);
        assertTrue(stopCondition.mustContinue());
        for (Integer i = 0; i <= 5; i++) {
            stopCondition.report(100.0);
        }
        assertTrue(stopCondition.mustContinue());
        for (Integer i = 0; i <= 5; i++) {
            stopCondition.report(100.0);
        }
        assertFalse(stopCondition.mustContinue());
    }

    @Test
    void testMustStop () {
        IdleGenerationsStopCondition stopCondition = new IdleGenerationsStopCondition(10);
        assertFalse(stopCondition.mustStop());
        for (Integer i = 0; i <= 9; i++) {
            stopCondition.report(100.0);
        }
        assertFalse(stopCondition.mustStop());
        stopCondition.report(101.0);
        assertFalse(stopCondition.mustStop());
        stopCondition.report(101.0);
        assertFalse(stopCondition.mustStop());
    }
}