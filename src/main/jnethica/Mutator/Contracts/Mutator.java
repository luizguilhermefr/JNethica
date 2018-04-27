package main.jnethica.Mutator.Contracts;

@FunctionalInterface
public interface Mutator {
    Object mutate (final Object early);
}
