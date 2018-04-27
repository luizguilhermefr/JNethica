package main.jnethica.Strategy.Contracts;

import main.jnethica.Exception.EmptyPopulationException;
import main.jnethica.Fitness.Contracts.FitnessCalculator;
import main.jnethica.Individual.Contracts.Individual;
import main.jnethica.Mutator.Contracts.Mutator;
import main.jnethica.Population.Population;
import main.jnethica.Selector.Contracts.Selector;
import main.jnethica.StopCondition.Contracts.StopCondition;

public abstract class Strategy extends Thread {
    private final Population initialPopulation;
    protected Selector selector;

    protected FitnessCalculator fitnessCalculator;
    private Population currentPopulation;
    private Individual globalOptimum;
    private Individual localOptimum;
    private Integer globalGeneration;
    private StopCondition stopCondition;

    protected Mutator mutator;
    private Integer currentGenerationNumber;

    public Strategy(final Population initialPopulation, FitnessCalculator fitnessCalculator, StopCondition stopCondition, Mutator mutator) {
        this.initialPopulation = initialPopulation;
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

    private void beforeAll() throws EmptyPopulationException {
        setInitialCurrentPopulation();
        setInitialBest();
        initializeStopCondition();
        generateSelector();
    }

    private void afterEach(Individual localOptimum) {
        setLocalOptimum(localOptimum);
        reportStopConditionAndIncrementGeneration();
    }

    private void setInitialCurrentPopulation() {
        currentPopulation = initialPopulation.clone();
    }

    private void setInitialBest() throws EmptyPopulationException {
        globalOptimum = currentPopulation.getBetter(fitnessCalculator);
        globalGeneration = 0;
    }

    private void setLocalOptimum(Individual individual) {
        localOptimum = individual;
        if (localOptimum.isBetterThan(globalOptimum, fitnessCalculator)) {
            globalOptimum = localOptimum;
            globalGeneration = currentGenerationNumber;
        }
    }

    private void initializeStopCondition() {
        currentGenerationNumber = 1;
        stopCondition.report(currentGenerationNumber, fitnessCalculator.getFitness(globalOptimum));
    }

    private void reportStopConditionAndIncrementGeneration() {
        stopCondition.report(currentGenerationNumber, fitnessCalculator.getFitness(globalOptimum));
        currentGenerationNumber++;
    }

    @Override
    public void run() throws IllegalArgumentException {
        try {
            beforeAll();
            while (stopCondition.mustContinue()) {
                execute();
                afterEach(localOptimum);
            }
        } catch (EmptyPopulationException e) {
            e.printStackTrace();
        }
    }

    protected abstract void generateSelector();

    protected abstract void execute();
}
