package main.java.Populations;

import main.java.Contracts.Individual;
import main.java.Contracts.Population;
import main.java.Individuals.BitMap;
import main.java.Util.RandomUtilities;

import java.util.BitSet;

// Refere-se a 1a aula de geneticos...
public class BitMapPopulation extends Population {
    private static int MAX_SIZE = 20;

    @Override
    public Population cloneEmpty () {
        return new BitMapPopulation();
    }

    @Override
    public Population clone () {
        BitMapPopulation nextPopulation = new BitMapPopulation();
        for (Individual individual : individuals) {
            nextPopulation.pushIndividual(individual.clone());
        }
        return nextPopulation;
    }

    @Override
    public Individual generateIndividual () {
        BitSet set = new BitSet(MAX_SIZE);
        for (Integer i = 0; i < MAX_SIZE; i++) {
            set.set(i, RandomUtilities.randomBoolean());
        }
        return new BitMap(set);
    }
}
