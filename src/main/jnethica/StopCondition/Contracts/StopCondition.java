package main.jnethica.StopCondition.Contracts;

public interface StopCondition {

    void report (Integer currentGeneration, Double currentMaximumFitness);

    Boolean mustStop ();
}
