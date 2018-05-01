package Factories;

import jnethica.Factory.GenericFunctionFactory;
import jnethica.Individual.GenericFunction;
import jnethica.Util.CommonFunctions;
import jnethica.Util.Tuple;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertTrue;

class GenericFunctionFactoryTest {

    private HashMap<String, Tuple<Double>> boundaries () {
        HashMap<String, Tuple<Double>> boundaries = new HashMap<>();
        boundaries.put("x", new Tuple<>(-5.0, 5.0));
        boundaries.put("y", new Tuple<>(-5.0, 5.0));
        return boundaries;
    }

    @Test
    void testGenerate () {
        ArrayList<String> variables = new ArrayList<>();
        variables.add("x");
        variables.add("y");
        GenericFunctionFactory genericFunctionFactory = new GenericFunctionFactory(CommonFunctions.QUADRI_GAUSSIAN, variables, boundaries());
        GenericFunction generated = genericFunctionFactory.generate();
        Boolean belowMaximumBoundaries = generated.getArgument("x") <= 5.0 && generated.getArgument("y") <= 5.0;
        Boolean aboveMinimumBoundaries = generated.getArgument("x") >= -5.0 && generated.getArgument("y") >= -5.0;
        assertTrue(belowMaximumBoundaries);
        assertTrue(aboveMinimumBoundaries);
    }
}