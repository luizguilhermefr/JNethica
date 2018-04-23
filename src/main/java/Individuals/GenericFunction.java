package main.java.Individuals;

import main.java.Individuals.Contracts.Function;
import main.java.Mutators.Contracts.Mutator;
import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;

import java.util.HashMap;
import java.util.Map;

public class GenericFunction extends Function {

    private Map<String, Object> arguments;

    private String format;

    private Double value;

    private Expression engine;

    public GenericFunction (String format, Map<String, Object> arguments) {
        this.arguments = arguments;
        this.format = format;
        makeEvaluator();
        calculate();
    }

    private void calculate () {
        try {
            value = engine.evaluate();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
            value = null;
        }
    }

    private void makeEvaluator () {
        ExpressionBuilder builder = new ExpressionBuilder(format).variables(arguments.keySet());
        engine = builder.build();
        for (String key : arguments.keySet()) {
            engine = engine.setVariable(key, (Double) arguments.get(key));
        }
    }

    @Override
    public String[] getArgumentsKeys () {
        return (String[]) arguments.keySet().toArray();
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
            nextArguments.replace(key, mutator.mutate(value));
        }
        return new GenericFunction(format, nextArguments);
    }

    @Override
    public GenericFunction mutate (Map<String, Mutator> mutators) {
        Map<String, Object> nextArguments = new HashMap<>(arguments);
        for (String key : mutators.keySet()) {
            Double value = (Double) nextArguments.get(key);
            Mutator mutator = mutators.get(key);
            nextArguments.replace(key, mutator.mutate(value));
        }
        return new GenericFunction(format, nextArguments);
    }

    @Override
    public Double getValue () {
        return value;
    }

    @Override
    public GenericFunction clone () {
        Map<String, Object> nextArguments = new HashMap<>(arguments);
        return new GenericFunction(format, nextArguments);
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
