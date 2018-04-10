package main.java.Factories;

import main.java.Contracts.IndividualFactory;
import main.java.Individuals.Bits;
import main.java.Util.RandomUtilities;

import java.util.BitSet;

public class BitsFactory implements IndividualFactory {

    private Integer size;

    public BitsFactory (Integer size) {
        this.size = size;
    }

    @Override
    public Bits generate () {
        BitSet set = new BitSet(size);
        for (Integer i = 0; i < size; i++) {
            set.set(i, RandomUtilities.randomBoolean());
        }
        return new Bits(set);
    }
}
