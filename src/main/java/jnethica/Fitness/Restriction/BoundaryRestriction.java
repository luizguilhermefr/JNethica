package jnethica.Fitness.Restriction;

import jnethica.Fitness.Restriction.Contracts.Restriction;
import jnethica.Individual.Contracts.Individual;
import jnethica.Individual.GenericFunction;
import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;

import java.util.Set;

public class BoundaryRestriction implements Restriction {
    private Expression engine;
    private String pattern;
    private Set<String> variables;
    private Double value;
    private Modes mode;

    public BoundaryRestriction (String pattern, Set<String> variables, Double value, Modes mode) {
        this.pattern = pattern;
        this.variables = variables;
        this.value = value;
        this.mode = mode;
        makeEvaluator();
    }

    private void makeEvaluator () {
        ExpressionBuilder builder = new ExpressionBuilder(pattern).variables(variables);
        engine = builder.build();
    }

    private Double lessThan (Double result) {
        if (result < value) {
            return .0;
        } else if (result.equals(value)) {
            return .01;
        } else {
            return result - value;
        }
    }

    private Double higherThan (Double result) {
        if (result > value) {
            return .0;
        } else if (result.equals(value)) {
            return .01;
        } else {
            return value - result;
        }
    }

    private Double lessOrEqualThan (Double result) {
        if (result <= value) {
            return .0;
        }

        return result - value;
    }

    private Double higherOrEqualThan (Double result) {
        if (result >= value) {
            return .0;
        }

        return value - result;
    }

    private Double getDifference (Double result) {
        switch (mode) {
            case LESS_THAN:
                return lessThan(result);
            case HIGHER_THAN:
                return higherThan(result);
            case LESS_OR_EQUAL_THAN:
                return lessOrEqualThan(result);
            case HIGHER_OR_EQUAL_THAN:
                return higherOrEqualThan(result);
        }
        return .0;
    }

    @Override
    public Double apply (Individual individual) {
        GenericFunction gf = (GenericFunction) individual;
        for (String key : variables) {
            engine = engine.setVariable(key, gf.getArgument(key));
        }
        Double result = engine.evaluate();
        return getDifference(result);
    }

    public enum Modes {
        HIGHER_THAN, HIGHER_OR_EQUAL_THAN, LESS_OR_EQUAL_THAN, LESS_THAN
    }
}
