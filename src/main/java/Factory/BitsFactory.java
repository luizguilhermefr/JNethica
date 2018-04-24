package main.java.Factory;

import main.java.Factory.Contracts.IndividualFactory;
import main.java.Individual.Bits;
import main.java.Util.RandomUtilities;

import java.util.ArrayList;

public class BitsFactory implements IndividualFactory {

    private Integer size;

    public BitsFactory (Integer size) {
        this.size = size;
    }

    @Override
    public Bits generate () {
        ArrayList<Boolean> set = new ArrayList<>();
        for (Integer i = 0; i < size; i++) {
            set.add(RandomUtilities.randomBoolean());
        }
        return new Bits(set);
    }
}
