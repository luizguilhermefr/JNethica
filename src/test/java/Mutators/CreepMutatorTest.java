package test.java.Mutators;

import main.java.Individuals.QuadriGaussianValue;
import main.java.Mutators.CreepMutator;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

class CreepMutatorTest {
    @Test
    void testBasicMutation () {
        CreepMutator mutator = new CreepMutator(1.4);
        QuadriGaussianValue quadriGaussian = new QuadriGaussianValue(1.0, 2.0);
        QuadriGaussianValue mutated = quadriGaussian.mutate(mutator);
        Boolean xBetweenMutationRate = mutated.getArgument(0) <= 1.0 + 1.4 && mutated.getArgument(0) >= 1.0 - 1.4;
        Boolean yBetweenMutationRate = mutated.getArgument(1) <= 2.0 + 1.4 && mutated.getArgument(1) >= 2.0 - 1.4;
        assertNotSame(quadriGaussian, mutated);
        assertTrue(xBetweenMutationRate);
        assertTrue(yBetweenMutationRate);
    }

    @Test
    void testLowerBoundaryMutation () {
        Double minimum = -5.0;
        Double maximum = 5.0;
        CreepMutator mutator = new CreepMutator(1.4, minimum, maximum);
        QuadriGaussianValue quadriGaussian = new QuadriGaussianValue(minimum, maximum);
        QuadriGaussianValue mutated = quadriGaussian.mutate(mutator);
        Boolean xHigherOrEqualThanMinimumX = mutated.getArgument(QuadriGaussianValue.X_INDEX) >= minimum;
        Boolean yHigherOrEqualThanMinimumY = mutated.getArgument(QuadriGaussianValue.Y_INDEX) >= minimum;
        assertTrue(xHigherOrEqualThanMinimumX);
        assertTrue(yHigherOrEqualThanMinimumY);
    }

    @Test
    void testUpperBoundaryMutation () {
        Double minimum = -5.0;
        Double maximum = 5.0;
        CreepMutator mutator = new CreepMutator(1.4, minimum, maximum);
        QuadriGaussianValue quadriGaussian = new QuadriGaussianValue(minimum, maximum);
        QuadriGaussianValue mutated = quadriGaussian.mutate(mutator);
        Boolean xLessOrEqualThanMinimumX = mutated.getArgument(QuadriGaussianValue.X_INDEX) <= maximum;
        Boolean yLessOrEqualThanMinimumY = mutated.getArgument(QuadriGaussianValue.Y_INDEX) <= maximum;
        assertTrue(xLessOrEqualThanMinimumX);
        assertTrue(yLessOrEqualThanMinimumY);
    }
}