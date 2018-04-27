package main.jnethica.Strategy;

import main.jnethica.Crossover.Contracts.Crossover;
import main.jnethica.Fitness.Contracts.FitnessCalculator;
import main.jnethica.Individual.Contracts.Individual;
import main.jnethica.Mutator.Contracts.Mutator;
import main.jnethica.Population.Population;
import main.jnethica.Selector.RouletteSelection;
import main.jnethica.StopCondition.Contracts.StopCondition;
import main.jnethica.Strategy.Contracts.Strategy;
import main.jnethica.Util.RandomSingleton;

public class ClassicGeneticStrategy<T extends Individual> extends Strategy<T> {
    private Double crossoverRate;

    public ClassicGeneticStrategy(Population initialPopulation, FitnessCalculator fitnessCalculator, StopCondition stopCondition, Mutator mutator, Crossover crosser, Double crossoverRate) {
        super(initialPopulation, fitnessCalculator, stopCondition, mutator, crosser);
        this.crossoverRate = crossoverRate;
    }

    private Population selectIndividualsToCross() {
        Population<T> selectedToCross = new Population<>();
        for (Integer i = 0; i < currentPopulation.size(); i++) {
            T selected = (T) selector.select(currentPopulation);
            selectedToCross.pushIndividual(selected);
        }
        return selectedToCross;
    }

    private Population crossIndividuals(Population toCross) {
        Population<T> crossedOrCopiedIndividuals = new Population<>();
        for (Integer i = 0; i < toCross.size(); i += 2) {
            T i1 = (T) toCross.getIndividualByIndex(i);
            T i2 = (T) toCross.getIndividualByIndex(i + 1);
            if (shouldCross()) {
                crossedOrCopiedIndividuals.pushIndividual((T) crosser.cross(i1, i2));
                crossedOrCopiedIndividuals.pushIndividual((T) crosser.cross(i1, i2));
            } else {
                crossedOrCopiedIndividuals.pushIndividual(i1);
                crossedOrCopiedIndividuals.pushIndividual(i2);
            }
        }
        return crossedOrCopiedIndividuals;
    }

    private Boolean shouldCross() {
        Double randDrawn = RandomSingleton.getInstance().doubleBetween(0.0, 100.0);
        return randDrawn <= crossoverRate;
    }

    @Override
    protected void generateSelector() {
        selector = new RouletteSelection(fitnessCalculator);
    }

    @Override
    protected void execute() {
        Population toCross = selectIndividualsToCross();
        Population crossed = crossIndividuals(toCross);
        currentPopulation = crossed.mutateAll(mutator);
    }
}
