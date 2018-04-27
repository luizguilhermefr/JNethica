package main.java.Crossover;

import main.java.Crossover.Contracts.Crossover;
import main.java.Individual.Contracts.Function;
import main.java.Individual.Contracts.Individual;
import main.java.Individual.GenericFunction;
import main.java.Util.RandomSingleton;

import java.util.HashMap;

public class ArithmeticCrossover implements Crossover {
    private Double applyFormula (Double v1, Double v2) {
        Double beta = RandomSingleton.getInstance().doubleBetween(0.0, 1.0);
        return beta * v1 + (1 - beta) * v2;
    }

    @Override
    public Function cross (Individual i1, Individual i2) {
        GenericFunction f1 = (GenericFunction) i1;
        GenericFunction f2 = (GenericFunction) i2;

        HashMap<String, Object> nextValues = new HashMap<>();

        String[] keysFirst = f1.getArgumentsKeys();

        for (String key : keysFirst) {
            Double v1 = f1.getArgument(key);
            Double v2 = f2.getArgument(key);
            nextValues.put(key, applyFormula(v1, v2));
        }

        return new GenericFunction(f1.getFormat(), nextValues);
    }
}
