package jnethica.Individual;

import jnethica.Individual.Contracts.Function;
import jnethica.Mutator.Contracts.Mutator;
import jnethica.Util.Tuple;
import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class GenericFunction extends Function {

    private Map<String, Object> arguments;

    private Map<String, Tuple<Double>> boundaries;

    private String format;

    private Double value;

    private Expression engine;

    public GenericFunction (String format, Map<String, Object> arguments) {
        this.arguments = arguments;
        this.format = format;
        this.boundaries = null;
        makeEvaluator();
        calculate();
    }

    public GenericFunction (String format, Map<String, Object> arguments, Map<String, Tuple<Double>> boundaries) {
        this.arguments = arguments;
        this.format = format;
        this.boundaries = boundaries;
        makeEvaluator();
        calculate();
    }

    public String getFormat () {
        return format;
    }

    public Map<String, Tuple<Double>> getBoundaries () {
        return boundaries;
    }

    private void calculate () {
        value = engine.evaluate();
    }

    private void makeEvaluator () {
        ExpressionBuilder builder = new ExpressionBuilder(format).variables(arguments.keySet());
        engine = builder.build();
        for (String key : arguments.keySet()) {
            engine = engine.setVariable(key, (Double) arguments.get(key));
        }
    }

    public Double forceMinimum (String key, Double nextValue) {
        Double minimum = boundaries.get(key).x;
        if (nextValue < minimum) {
            return minimum;
        }
        return nextValue;
    }

    public Double forceMaximum (String key, Double nextValue) {
        Double maximum = boundaries.get(key).y;
        if (nextValue > maximum) {
            return maximum;
        }
        return nextValue;
    }

    @Override
    public String[] getArgumentsKeys () {
        Set<String> keys = arguments.keySet();
        return keys.toArray(new String[keys.size()]);
    }

    @Override
    public Double getArgument (String name) {
        return (Double) arguments.get(name);
    }

    @Override
    public Integer countArguments () {
        return arguments.size();
    }

    @Override
    public GenericFunction mutate (Mutator mutator) {
        Map<String, Object> nextArguments = new HashMap<>(arguments);
        for (String key : nextArguments.keySet()) {
            Double value = (Double) nextArguments.get(key);
            Double valueMutated = (Double) mutator.mutate(value);
            if (boundaries != null) {
                valueMutated = forceMinimum(key, valueMutated);
                valueMutated = forceMaximum(key, valueMutated);
            }
            nextArguments.replace(key, valueMutated);
        }
        return new GenericFunction(format, nextArguments, boundaries);
    }

    @Override
    public GenericFunction mutate (Map<String, Mutator> mutators) {
        Map<String, Object> nextArguments = new HashMap<>(arguments);
        for (String key : mutators.keySet()) {
            Double value = (Double) nextArguments.get(key);
            Mutator mutator = mutators.get(key);
            Double valueMutated = (Double) mutator.mutate(value);
            if (boundaries != null) {
                valueMutated = forceMinimum(key, valueMutated);
                valueMutated = forceMaximum(key, valueMutated);
            }
            nextArguments.replace(key, valueMutated);
        }
        return new GenericFunction(format, nextArguments, boundaries);
    }

    @Override
    public Double getValue () {
        return value;
    }

    @Override
    public GenericFunction clone () {
        Map<String, Object> nextArguments = new HashMap<>(arguments);
        return new GenericFunction(format, nextArguments, boundaries);
    }

    @Override
    public String toString () {
        StringBuilder builder = new StringBuilder();
        for (Map.Entry<String, Object> argument : arguments.entrySet()) {
            builder.append(argument.getKey()).append("=").append(argument.getValue()).append("\t");
        }
        builder.append("f(args)=").append(getValue());
        return builder.toString();
    }
}
