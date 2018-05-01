package jnethica.StopCondition;

import jnethica.StopCondition.Contracts.StopCondition;

public class MaximumGenerationsStopCondition implements StopCondition {

    private Integer maximumGenerations;

    private Integer currentGeneration;

    public MaximumGenerationsStopCondition (Integer maximumGenerations) {
        this.maximumGenerations = maximumGenerations;
        this.currentGeneration = 0;
    }

    @Override
    public void reset () {
        this.currentGeneration = 0;
    }

    @Override
    public void report (Double currentMaximumFitness) {
        this.currentGeneration++;
    }

    @Override
    public Boolean mustContinue() {
        return !mustStop();
    }

    @Override
    public Boolean mustStop () {
        return currentGeneration >= maximumGenerations;
    }
}
