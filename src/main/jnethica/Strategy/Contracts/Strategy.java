package main.jnethica.Strategy.Contracts;

import main.jnethica.Crossover.Contracts.Crossover;
import main.jnethica.Exception.EmptyPopulationException;
import main.jnethica.Fitness.Contracts.FitnessCalculator;
import main.jnethica.Individual.Contracts.Individual;
import main.jnethica.Mutator.Contracts.Mutator;
import main.jnethica.Population.Population;
import main.jnethica.Selector.Contracts.Selector;
import main.jnethica.StopCondition.Contracts.StopCondition;

public abstract class Strategy<T extends Individual> extends Thread {
    private final Population<T> initialPopulation;
    protected Selector selector;
    protected Crossover crosser;
    protected FitnessCalculator fitnessCalculator;
    protected Population<T> currentPopulation;
    private T globalOptimum;
    private T localOptimum;
    private Integer globalGeneration;
    private StopCondition stopCondition;
    protected Mutator mutator;
    private Integer currentGenerationNumber;

    public Strategy (final Population initialPopulation, FitnessCalculator fitnessCalculator, StopCondition stopCondition, Mutator mutator, Crossover crosser) {
        this.initialPopulation = initialPopulation;
        this.fitnessCalculator = fitnessCalculator;
        this.stopCondition = stopCondition;
        this.mutator = mutator;
        this.crosser = crosser;
    }

    public Double getGlobalOptimumFitness () {
        return fitnessCalculator.getFitness(globalOptimum);
    }

    public T getGlobalOptimum () {
        return globalOptimum;
    }

    public Integer getGlobalOptimumGeneration () {
        return globalGeneration;
    }

    private void beforeAll () throws EmptyPopulationException {
        setInitialCurrentPopulation();
        setInitialBest();
        initializeStopCondition();
        generateSelector();
    }

    private void afterEach () throws EmptyPopulationException {
        updateLocalOptimum();
        updateGlobalOptimumIfNecessary();
        reportStopConditionAndIncrementGeneration();
    }

    private void setInitialCurrentPopulation () {
        currentPopulation = initialPopulation.clone();
        currentGenerationNumber = 0;
    }

    private void setInitialBest () throws EmptyPopulationException {
        globalOptimum = currentPopulation.getBetter(fitnessCalculator);
        globalGeneration = 0;
    }

    private void updateLocalOptimum () throws EmptyPopulationException {
        localOptimum = currentPopulation.getBetter(fitnessCalculator);
    }

    private void updateGlobalOptimumIfNecessary () {
        if (localOptimum.isBetterThan(globalOptimum, fitnessCalculator)) {
            globalOptimum = localOptimum;
            globalGeneration = currentGenerationNumber;
        }
    }

    private void initializeStopCondition () {
        stopCondition.reset();
    }

    private void reportStopConditionAndIncrementGeneration () {
        stopCondition.report(fitnessCalculator.getFitness(globalOptimum));
        currentGenerationNumber++;
    }

    @Override
    public void run () throws IllegalArgumentException {
        try {
            beforeAll();
            while (stopCondition.mustContinue()) {
                execute();
                afterEach();
            }
        } catch (EmptyPopulationException e) {
            e.printStackTrace();
        }
    }

    protected abstract void generateSelector ();

    protected abstract void execute ();
}
