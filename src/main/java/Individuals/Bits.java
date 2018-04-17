package main.java.Individuals;

import main.java.Fitness.Contracts.FitnessCalculator;
import main.java.Individuals.Contracts.Individual;
import main.java.Mutators.Contracts.Mutator;

import java.util.ArrayList;

public class Bits implements Individual {

    private ArrayList<Boolean> set;

    public Bits (ArrayList<Boolean> set) {
        this.set = set;
    }

    public Integer size () {
        return set.size();
    }

    @Override
    public Individual mutate (Mutator mutator) {
        ArrayList<Boolean> nextSet = (ArrayList<Boolean>) mutator.mutate(set);
        return new Bits(nextSet);
    }

    @Override
    public Double getValue () {
        Long value = 0L;
        for (Integer i = 0; i < set.size(); ++i) {
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
        ArrayList<Boolean> nextSet = new ArrayList<>(set);
        return new Bits(nextSet);
    }

    public Boolean get (Integer index) {
        return set.get(index);
    }

    public Bits splice (Integer fromIndex, Integer toIndex) {
        ArrayList<Boolean> nextSet = new ArrayList<>();
        for (Integer i = fromIndex; i <= toIndex; i++) {
            nextSet.add(set.get(i));
        }
        return new Bits(nextSet);
    }

    public void append (Bits bits) {
        ArrayList<Boolean> nextSet = new ArrayList<>();
        for (Integer i = 0; i < this.size(); i++) {
            nextSet.add(this.get(i));
        }
        for (Integer i = 0; i < bits.size(); i++) {
            nextSet.add(bits.get(i));
        }
        this.set = nextSet;
    }

    public String toString () {
        StringBuilder s = new StringBuilder();
        for (Boolean aSet : set) {
            s.append(aSet ? '1' : '0');
        }
        return s.toString();
    }
}
