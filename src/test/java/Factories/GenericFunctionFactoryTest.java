package test.java.Factories;

import main.jnethica.Factory.GenericFunctionFactory;
import main.jnethica.Individual.GenericFunction;
import main.jnethica.Util.CommonFunctions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertTrue;

class GenericFunctionFactoryTest {

    @Test
    void testGenerate () {
        ArrayList<String> variables = new ArrayList<>();
        HashMap<String, Double> minimumValues = new HashMap<>();
        HashMap<String, Double> maximumValues = new HashMap<>();
        variables.add("x");
        variables.add("y");
        minimumValues.put("x", -5.0);
        minimumValues.put("y", -5.0);
        maximumValues.put("x", 5.0);
        maximumValues.put("y", 5.0);
        GenericFunctionFactory genericFunctionFactory = new GenericFunctionFactory(CommonFunctions.QUADRI_GAUSSIAN, variables, minimumValues, maximumValues);
        GenericFunction generated = genericFunctionFactory.generate();
        Boolean belowMaximumBoundaries = generated.getArgument("x") <= 5.0 && generated.getArgument("y") <= 5.0;
        Boolean aboveMinimumBoundaries = generated.getArgument("x") >= -5.0 && generated.getArgument("y") >= -5.0;
        assertTrue(belowMaximumBoundaries);
        assertTrue(aboveMinimumBoundaries);
    }
}