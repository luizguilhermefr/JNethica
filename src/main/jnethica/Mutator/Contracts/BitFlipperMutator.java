package main.jnethica.Mutator.Contracts;

import main.jnethica.Util.RandomSingleton;

import java.util.ArrayList;

public class BitFlipperMutator implements Mutator {
    private Double mutationRate;

    public BitFlipperMutator (final Double mutationRate) {
        this.mutationRate = mutationRate;
    }

    @Override
    public Object mutate (Object early) {
        ArrayList<Boolean> currentSet = (ArrayList<Boolean>) early;
        ArrayList<Boolean> nextSet = new ArrayList<>();
        for (Boolean aSet : currentSet) {
            Boolean mustMutateThisBit = RandomSingleton.getInstance().doubleBetween(0.0, 100.0) <= mutationRate;
            if (mustMutateThisBit) {
                nextSet.add(!aSet);
            } else {
                nextSet.add(aSet);
            }
        }
        return nextSet;
    }
}
