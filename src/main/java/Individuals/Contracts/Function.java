package main.java.Individuals.Contracts;

import main.java.Mutators.Contracts.Mutator;

import java.util.List;

public abstract class Function extends Individual {

    public abstract String[] getArgumentsKeys ();

    public abstract Double getArgument (String name);

    public abstract Integer countArguments ();

    public abstract Double getValue ();

    public abstract Function mutate (final List<Mutator> mutators);
}
