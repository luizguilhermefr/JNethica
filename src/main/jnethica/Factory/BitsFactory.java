package main.jnethica.Factory;

import main.jnethica.Factory.Contracts.IndividualFactory;
import main.jnethica.Individual.Bits;
import main.jnethica.Util.RandomSingleton;

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
            set.add(RandomSingleton.getInstance().randomBoolean());
        }
        return new Bits(set);
    }
}
