package main.java.Individuals.Contracts;

public interface Function extends Individual {
    Double getX ();

    Double getY ();

    Double getValue ();

    Function mutate (final Double xRate, final Double yRate);
}
