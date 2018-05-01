package Individuals;

import jnethica.Individual.GenericFunction;
import jnethica.Util.CommonFunctions;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;

class QuadriGaussianValueTest {

    @Test
    void testMaximumValue () {
        HashMap<String, Object> keys = new HashMap<>();
        keys.put("x", -2.99538);
        keys.put("y", -2.99543);
        GenericFunction quadriGaussian = new GenericFunction(CommonFunctions.QUADRI_GAUSSIAN, keys);
        double result = quadriGaussian.getValue();
        assertEquals(0.971479, result, 0.0001);
    }
}