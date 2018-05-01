package jnethica.StopCondition.Contracts;

public interface StopCondition {

    void reset ();

    void report (Double currentMaximumFitness);

    Boolean mustStop ();

    Boolean mustContinue ();
}
