package main.java.Factory;

import main.java.Factory.Contracts.IndividualFactory;
import main.java.Individual.GenericFunction;
import main.java.Util.RandomUtilities;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GenericFunctionFactory implements IndividualFactory {

    private String function;

    private List<String> variables;

    private Map<String, Double> minimumValues = null;

    private Map<String, Double> maximumValues = null;

    public GenericFunctionFactory (String function, List<String> variables) {
        this.function = function;
        this.variables = variables;
    }

    public GenericFunctionFactory (String function, List<String> variables, Map<String, Double> minimumValues, Map<String, Double> maximumValues) {
        this.function = function;
        this.variables = variables;
        this.minimumValues = minimumValues;
        this.maximumValues = maximumValues;
    }

    @Override
    public GenericFunction generate () {
        HashMap<String, Object> values = new HashMap<>();
        for (String variable : variables) {
            Double minimumValue = minimumValues != null ? minimumValues.get(variable) : Double.NEGATIVE_INFINITY;
            Double maximumValue = maximumValues != null ? maximumValues.get(variable) : Double.POSITIVE_INFINITY;
            Double generatedValue = RandomUtilities.doubleBetween(minimumValue, maximumValue);
            values.put(variable, generatedValue);
        }
        return new GenericFunction(function, values);
    }
}
