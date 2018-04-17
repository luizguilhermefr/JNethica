package main.java.Individuals;

import main.java.Fitness.Contracts.FitnessCalculator;
import main.java.Individuals.Contracts.Function;
import main.java.Individuals.Contracts.Individual;
import main.java.Mutators.Contracts.Mutator;

import java.util.List;

public class GenericFunction extends Function {
    @Override
    public Double[] getArguments () {
        return new Double[0];
    }

    @Override
    public Double getArgument (Integer index) throws IndexOutOfBoundsException {
        return null;
    }

    @Override
    public Integer countArguments () {
        return null;
    }

    @Override
    public Individual mutate (Mutator mutator) {
        return null;
    }

    @Override
    public Double getValue () {
        return null;
    }

    @Override
    public Boolean isBetterThan (Individual individual, FitnessCalculator fitnessFunction) {
        return null;
    }

    @Override
    public Individual clone () {
        return null;
    }

    @Override
    public String toString () {
        return null;
    }

    @Override
    public Function mutate (List<Mutator> mutators) {
        return null;
    }
}
