package main.java.StopCondition.Contracts;

public interface StopCondition {

    void report (Integer currentGeneration, Double currentMaximumFitness);

    Boolean mustStop ();
}
