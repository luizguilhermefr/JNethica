package main.jnethica.Individual.Contracts;

import main.jnethica.Mutator.Contracts.Mutator;

import java.util.Map;

public abstract class Function extends Individual {

    public abstract String[] getArgumentsKeys ();

    public abstract Double getArgument (String name);

    public abstract Integer countArguments ();

    public abstract Double getValue ();

    public abstract Function mutate (final Map<String, Mutator> mutators);
}
