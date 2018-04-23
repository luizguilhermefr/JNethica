package test.java.Mutators;

import main.java.Individuals.GenericFunction;
import main.java.Mutators.CreepMutator;
import main.java.Util.CommonFunctions;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertNotSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

class CreepMutatorTest {
    @Test
    void testBasicMutation () {
        HashMap<String, Object> keys = new HashMap<>();
        keys.put("x", 1.0);
        keys.put("y", 2.0);
        CreepMutator mutator = new CreepMutator(1.4);
        GenericFunction quadriGaussian = new GenericFunction(CommonFunctions.QUADRI_GAUSSIAN, keys);
        GenericFunction mutated = quadriGaussian.mutate(mutator);
        Boolean xBetweenMutationRate = mutated.getArgument("x") <= 1.0 + 1.4 && mutated.getArgument("x") >= 1.0 - 1.4;
        Boolean yBetweenMutationRate = mutated.getArgument("y") <= 2.0 + 1.4 && mutated.getArgument("y") >= 2.0 - 1.4;
        assertNotSame(quadriGaussian, mutated);
        assertTrue(xBetweenMutationRate);
        assertTrue(yBetweenMutationRate);
    }

    @Test
    void testLowerBoundaryMutation () {
        Double minimum = -5.0;
        Double maximum = 5.0;
        HashMap<String, Object> keys = new HashMap<>();
        keys.put("x", minimum);
        keys.put("y", minimum);
        CreepMutator mutator = new CreepMutator(1.4, minimum, maximum);
        GenericFunction quadriGaussian = new GenericFunction(CommonFunctions.QUADRI_GAUSSIAN, keys);
        GenericFunction mutated = quadriGaussian.mutate(mutator);
        Boolean xHigherOrEqualThanMinimumX = mutated.getArgument("x") >= minimum;
        Boolean yHigherOrEqualThanMinimumY = mutated.getArgument("y") >= minimum;
        assertTrue(xHigherOrEqualThanMinimumX);
        assertTrue(yHigherOrEqualThanMinimumY);
    }

    @Test
    void testUpperBoundaryMutation () {
        Double minimum = -5.0;
        Double maximum = 5.0;
        HashMap<String, Object> keys = new HashMap<>();
        keys.put("x", maximum);
        keys.put("y", maximum);
        CreepMutator mutator = new CreepMutator(1.4, minimum, maximum);
        GenericFunction quadriGaussian = new GenericFunction(CommonFunctions.QUADRI_GAUSSIAN, keys);
        GenericFunction mutated = quadriGaussian.mutate(mutator);
        Boolean xLessOrEqualThanMinimumX = mutated.getArgument("x") <= maximum;
        Boolean yLessOrEqualThanMinimumY = mutated.getArgument("y") <= maximum;
        assertTrue(xLessOrEqualThanMinimumX);
        assertTrue(yLessOrEqualThanMinimumY);
    }
}