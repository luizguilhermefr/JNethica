package main.java.Mutator;

import main.java.Mutator.Contracts.Mutator;
import main.java.Util.RandomSingleton;

public class CreepMutator implements Mutator {

    private Double mutationRate;

    private Double maxValue = null;

    private Double minValue = null;

    public CreepMutator (final Double mutationRate) {
        this.mutationRate = mutationRate;
    }

    public CreepMutator (final Double mutationRate, final Double minValue, final Double maxValue) {
        this.mutationRate = mutationRate;
        this.minValue = minValue;
        this.maxValue = maxValue;
    }

    @Override
    public Double mutate (final Object early) {
        if (maxValue != null && minValue != null) {
            return limitedMutation((Double) early);
        } else {
            return simpleMutation((Double) early);
        }
    }

    private Double simpleMutation (final Double early) {
        return early + RandomSingleton.getInstance().doubleBetween(-mutationRate, mutationRate);
    }

    private Double limitedMutation (final Double early) {
        Double value = early + RandomSingleton.getInstance().doubleBetween(-mutationRate, mutationRate);
        value = value > maxValue ? maxValue : value;
        value = value < minValue ? minValue : value;
        return value;
    }
}
