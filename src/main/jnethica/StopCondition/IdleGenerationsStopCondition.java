package main.jnethica.StopCondition;

import main.jnethica.StopCondition.Contracts.StopCondition;

public class IdleGenerationsStopCondition implements StopCondition {

    private Integer maximumIdleGenerations;

    private Integer idleGenerations;

    private Double bestFitness;

    public IdleGenerationsStopCondition (Integer maximumIdleGenerations) {
        this.maximumIdleGenerations = maximumIdleGenerations;
        this.idleGenerations = 0;
        this.bestFitness = Double.NEGATIVE_INFINITY;
    }

    @Override
    public void reset () {
        this.idleGenerations = 0;
    }

    @Override
    public void report (Double currentMaximumFitness) {
        if (currentMaximumFitness > bestFitness) {
            bestFitness = currentMaximumFitness;
            idleGenerations = 0;
        } else {
            idleGenerations++;
        }
    }

    @Override
    public Boolean mustContinue () {
        return !mustStop();
    }

    @Override
    public Boolean mustStop () {
        return idleGenerations >= maximumIdleGenerations;
    }
}
