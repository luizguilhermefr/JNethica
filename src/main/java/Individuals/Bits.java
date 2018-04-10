package main.java.Individuals;

import main.java.Contracts.FitnessCalculator;
import main.java.Contracts.Individual;
import main.java.Util.RandomUtilities;

import java.util.BitSet;

public class Bits implements Individual {

    private BitSet set;

    public Bits (BitSet set) {
        this.set = set;
    }

    public Integer size () {
        return set.size();
    }

    @Override
    public Individual mutate (Double mutationRate) {
        BitSet nextSet = (BitSet) set.clone();
        for (Integer i = 0; i < set.length(); ++i) {
            Boolean mustMutateThisBit = RandomUtilities.doubleBetween(0.0, 100.0) <= mutationRate;
            if (mustMutateThisBit) {
                nextSet.flip(i);
            }
        }
        return new Bits(nextSet);
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
        return new Bits(nextSet);
    }

    public Boolean get (Integer index) {
        return set.get(index);
    }

    public Bits splice (Integer fromIndex, Integer toIndex) {
        BitSet nextSet = new BitSet(toIndex - fromIndex);
        for (Integer i = fromIndex; i <= toIndex; i++) {
            nextSet.set(i, set.get(i));
        }
        return new Bits(nextSet);
    }

    public void append (Bits bits) {
        BitSet nextSet = new BitSet(this.size() + bits.size());
        for (Integer i = 0; i < this.size(); i++) {
            nextSet.set(i, this.get(i));
        }
        Integer aux = 0;
        for (Integer i = this.size(); i < this.size() + bits.size(); i++) {
            nextSet.set(i, bits.get(aux));
            aux++;
        }
        this.set = nextSet;
    }

    public String toString () {
        StringBuilder s = new StringBuilder();
        for (int i = 0; i < set.length(); i++) {
            s.append(set.get(i) ? '1' : '0');
        }
        return s.toString();
    }
}
