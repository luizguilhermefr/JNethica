package main.jnethica.Fitness.Restriction;

import main.jnethica.Fitness.Restriction.Contracts.Restriction;
import main.jnethica.Individual.Contracts.Individual;
import main.jnethica.Individual.GenericFunction;
import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;

import java.util.Set;

public class BoundaryRestriction implements Restriction {
    private Expression engine;
    private String pattern;
    private Set<String> variables;
    private Double value;
    private Modes mode;

    BoundaryRestriction (String pattern, Set<String> variables, Double value, Modes mode) {
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

    private Double getDifference (Double result) {
        switch (mode) {
            case LESS_THAN:
                return (result < value) ? .0 : result - value + .01;
            case HIGHER_THAN:
                return (result > value) ? .0 : value - result + .01;
            case LESS_OR_EQUAL_THAN:
                return (result <= value) ? .0 : result - value;
            case HIGHER_OR_EQUAL_THAN:
                return (result >= value) ? .0 : value - result;
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
