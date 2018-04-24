package main.java.StopConditions;

import main.java.StopConditions.Contracts.StopCondition;

public class MaximumGenerationsStopCondition implements StopCondition {

    private Integer maximumGenerations;

    private Integer currentGeneration;

    public MaximumGenerationsStopCondition (Integer maximumGenerations) {
        this.maximumGenerations = maximumGenerations;
        this.currentGeneration = 0;
    }

    @Override
    public void report (Integer currentGeneration, Double currentMaximumFitness) {
        this.currentGeneration = currentGeneration;
    }

    @Override
    public Boolean mustStop () {
        return currentGeneration >= maximumGenerations;
    }
}
