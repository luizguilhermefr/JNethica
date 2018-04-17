package test.java.Individuals;

import main.java.Individuals.QuadriGaussianValue;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class QuadriGaussianValueTest {

    @Test
    void testMaximumValue () {
        QuadriGaussianValue quadriGaussian = new QuadriGaussianValue(-2.99538, -2.99543);
        double result = quadriGaussian.getValue();
        assertEquals(0.971479, result, 0.0001);
    }
}