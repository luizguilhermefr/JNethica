package main.java.StopConditions.Contracts;

public interface StopCondition {

    void report (Integer currentGeneration, Double currentMaximumFitness);

    Boolean mustStop ();
}
