package main.java.Exception;

public class EmptyPopulationException extends Exception {
    public EmptyPopulationException () {
        super("The population must be initialized before action.");
    }
}
