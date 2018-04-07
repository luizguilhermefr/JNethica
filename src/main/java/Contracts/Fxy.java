package main.java.Contracts;

public interface Fxy extends Individual {
    Double getX ();

    Double getY ();

    Double getValue ();

    Fxy mutate (final Double xRate, final Double yRate);
}
