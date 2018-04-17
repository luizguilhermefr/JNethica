package main.java.Individuals.Contracts;

import main.java.Mutators.Contracts.Mutator;

import java.util.List;

public interface Function extends Individual {

    Double[] getArguments ();

    Double getArgument (Integer index) throws IndexOutOfBoundsException;

    Integer countArguments ();

    Double getValue ();

    Function mutate (final List<Mutator> mutators);
}
