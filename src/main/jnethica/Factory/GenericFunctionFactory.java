package main.jnethica.Factory;

import main.jnethica.Factory.Contracts.IndividualFactory;
import main.jnethica.Individual.GenericFunction;
import main.jnethica.Util.RandomSingleton;
import main.jnethica.Util.Tuple;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GenericFunctionFactory implements IndividualFactory {

    private String function;

    private List<String> variables;

    private Map<String, Tuple<Double>> boundaries;

    public GenericFunctionFactory (String function, List<String> variables) {
        this.function = function;
        this.variables = variables;
        this.boundaries = null;
    }

    public GenericFunctionFactory (String function, List<String> variables, Map<String, Tuple<Double>> boundaries) {
        this.function = function;
        this.variables = variables;
        this.boundaries = boundaries;
    }

    @Override
    public GenericFunction generate () {
        HashMap<String, Object> values = new HashMap<>();
        for (String variable : variables) {
            Double minimumValue = boundaries != null ? boundaries.get(variable).x : Double.NEGATIVE_INFINITY;
            Double maximumValue = boundaries != null ? boundaries.get(variable).y : Double.POSITIVE_INFINITY;
            Double generatedValue = RandomSingleton.getInstance().doubleBetween(minimumValue, maximumValue);
            values.put(variable, generatedValue);
        }
        return new GenericFunction(function, values);
    }
}
