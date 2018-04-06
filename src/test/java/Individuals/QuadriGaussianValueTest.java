package test.java.Individuals;

import main.java.Individuals.QuadriGaussianValue;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class QuadriGaussianValueTest {

    @Test
    void testBasicMutation () {
        QuadriGaussianValue quadriGaussian = new QuadriGaussianValue(1.0, 2.0);
        QuadriGaussianValue mutated = (QuadriGaussianValue) quadriGaussian.mutate(1.4);
        Boolean xBetweenMutationRate = mutated.getX() <= 1.0 + 1.4 && mutated.getX() >= 1.0 - 1.4;
        Boolean yBetweenMutationRate = mutated.getY() <= 2.0 + 1.4 && mutated.getY() >= 2.0 - 1.4;
        assertNotSame(quadriGaussian, mutated);
        assertTrue(xBetweenMutationRate);
        assertTrue(yBetweenMutationRate);

    }

    @Test
    void testLowerBoundaryMutation () {
        Double minimumX = -5.0;
        Double minimumY = -5.0;
        QuadriGaussianValue quadriGaussian = new QuadriGaussianValue(minimumX, minimumY);
        QuadriGaussianValue mutated = (QuadriGaussianValue) quadriGaussian.mutate(1.4);
        Boolean xHigherOrEqualThanMinimumX = mutated.getX() >= minimumX;
        Boolean yHigherOrEqualThanMinimumY = mutated.getY() >= minimumY;
        assertTrue(xHigherOrEqualThanMinimumX);
        assertTrue(yHigherOrEqualThanMinimumY);
    }

    @Test
    void testUpperBoundaryMutation () {
        Double maximumX = 5.0;
        Double maximumY = 5.0;
        QuadriGaussianValue quadriGaussian = new QuadriGaussianValue(maximumX, maximumY);
        QuadriGaussianValue mutated = (QuadriGaussianValue) quadriGaussian.mutate(1.4);
        Boolean xLessOrEqualThanMinimumX = mutated.getX() <= maximumX;
        Boolean yLessOrEqualThanMinimumY = mutated.getY() <= maximumY;
        assertTrue(xLessOrEqualThanMinimumX);
        assertTrue(yLessOrEqualThanMinimumY);
    }

    @Test
    void testMaximumValue () {
        QuadriGaussianValue quadriGaussian = new QuadriGaussianValue(-2.99538, -2.99543);
        double result = quadriGaussian.getValue();
        assertEquals(0.971479, result, 0.0001);
    }
}