package main.jnethica.Strategy.Contracts;

import main.jnethica.Exception.EmptyPopulationException;
import main.jnethica.Fitness.Contracts.FitnessCalculator;
import main.jnethica.Individual.Contracts.Individual;
import main.jnethica.Mutator.Contracts.Mutator;
import main.jnethica.Population.Population;
import main.jnethica.StopCondition.Contracts.StopCondition;

public abstract class Strategy extends Thread {
    protected final Population initialPopulation;

    protected Integer fixedSize;

    protected FitnessCalculator fitnessCalculator;

    protected Individual globalOptimum;

    protected Integer globalGeneration;

    protected StopCondition stopCondition;

    protected Integer currentGenerationNumber;

    protected Mutator mutator;

    public Strategy(final Population initialPopulation, FitnessCalculator fitnessCalculator, StopCondition stopCondition, Mutator mutator) {
        this.initialPopulation = initialPopulation;
        this.fixedSize = initialPopulation.size();
        this.fitnessCalculator = fitnessCalculator;
        this.stopCondition = stopCondition;
        this.mutator = mutator;
    }

    public Individual getGlobalOptimum() {
        return globalOptimum;
    }

    public Integer getGlobalGeneration() {
        return globalGeneration;
    }

    protected void setInitialBest() throws EmptyPopulationException {
        globalOptimum = initialPopulation.getBetter(fitnessCalculator);
        globalGeneration = 0;
    }

    protected void setLocalOptimum(Individual individual) {
        if (individual.isBetterThan(globalOptimum, fitnessCalculator)) {
            globalOptimum = individual;
            globalGeneration = currentGenerationNumber;
        }
    }

    protected void initializeStopCondition() {
        currentGenerationNumber = 1;
        stopCondition.report(currentGenerationNumber, fitnessCalculator.getFitness(globalOptimum));
    }

    protected void reportStopConditionAndIncrementGeneration() {
        stopCondition.report(currentGenerationNumber, fitnessCalculator.getFitness(globalOptimum));
        currentGenerationNumber++;
    }

    @Override
    public abstract void run() throws IllegalArgumentException;
}
