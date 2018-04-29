package main.jnethica.Crossover;

import main.jnethica.Crossover.Contracts.Crossover;
import main.jnethica.Individual.Contracts.Function;
import main.jnethica.Individual.Contracts.Individual;
import main.jnethica.Individual.GenericFunction;
import main.jnethica.Util.RandomSingleton;

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
            Double formulaResult = applyFormula(v1, v2);
            formulaResult = f1.forceMinimum(key, formulaResult);
            formulaResult = f1.forceMaximum(key, formulaResult);
            nextValues.put(key, formulaResult);
        }

        return new GenericFunction(f1.getFormat(), nextValues, f1.getBoundaries());
    }
}
