package Individuals;

import Contracts.FitnessCalculator;
import Contracts.Individual;
import Util.RandomUtilities;

import java.util.BitSet;

public class BitMap implements Individual {

    private BitSet set;

    BitMap (BitSet set) {
        this.set = set;
    }

    @Override
    public Individual mutate (Double mutationRate) {
        BitSet nextSet = (BitSet) set.clone();
        for (Integer i = 0; i < set.length(); ++i) {
            Boolean mustMutateThisBit = RandomUtilities.floatBetween(0.0, 100.0) <= mutationRate;
            if (mustMutateThisBit) {
                nextSet.flip(i);
            }
        }
        return new BitMap(nextSet);
    }

    @Override
    public Double getValue () {
        Long value = 0L;
        for (Integer i = 0; i < set.length(); ++i) {
            value += set.get(i) ? (1L << i) : 0L;
        }
        return value.doubleValue();
    }

    @Override
    public Boolean isBetterThan (Individual individual, FitnessCalculator fitnessFunction) {
        return fitnessFunction.getFitness(this) > fitnessFunction.getFitness(individual);
    }

    @Override
    public Individual clone () {
        BitSet nextSet = (BitSet) set.clone();
        return new BitMap(nextSet);
    }
}
