package main.java.Individuals.Contracts;

import java.util.List;

public interface Function extends Individual {

    Double[] getArguments ();

    Double getArgument (Integer index) throws IndexOutOfBoundsException;

    Integer countArguments ();

    Double getValue ();

    Function mutate (final List<Double> rates);
}
