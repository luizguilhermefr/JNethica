package main.java.Strategies;

import main.java.Exceptions.EmptyPopulationException;
import main.java.Fitness.Contracts.FitnessCalculator;
import main.java.Individuals.Contracts.Individual;
import main.java.Mutators.Contracts.Mutator;
import main.java.Mutators.CreepMutator;
import main.java.Population.Population;
import main.java.StopConditions.Contracts.StopCondition;
import main.java.Strategies.Contracts.Strategy;

public class QuickIteratedLocalSearch extends Strategy {
    public QuickIteratedLocalSearch (Population initialPopulation, FitnessCalculator fitnessCalculator) {
        super(initialPopulation, fitnessCalculator);
    }

    @Override
    public void run (StopCondition stopCondition) throws IllegalArgumentException, EmptyPopulationException {
        Individual starter = this.initialPopulation.getBetter(fitnessCalculator);

        globalOptimum = starter;
        globalGeneration = 0;

        Mutator smallMutator = new CreepMutator(0.125);
        Mutator bigMutator = new CreepMutator(1.0);

        Individual localBetter = starter;

        Integer currentGenerationNumber = 1;

        do {
            do {
                Individual neighbor = localBetter.mutate(smallMutator);
                if (neighbor.isBetterThan(globalOptimum, fitnessCalculator)) {
                    localBetter = neighbor;
                } else {
                    break;
                }
            } while (true);
            localBetter = localBetter.mutate(bigMutator);
            if (localBetter.isBetterThan(globalOptimum, fitnessCalculator)) {
                globalOptimum = localBetter;
                globalGeneration = currentGenerationNumber;
            }
            stopCondition.report(currentGenerationNumber, fitnessCalculator.getFitness(globalOptimum));
            currentGenerationNumber++;
        } while (!stopCondition.mustStop());
    }
}
