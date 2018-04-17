package main.java.Individuals.Contracts;

import main.java.Mutators.Contracts.Mutator;

import java.util.List;

public abstract class Function extends Individual {

    public abstract Double[] getArguments ();

    public abstract Double getArgument (Integer index) throws IndexOutOfBoundsException;

    public abstract Integer countArguments ();

    public abstract Double getValue ();

    public abstract Function mutate (final List<Mutator> mutators);
}
