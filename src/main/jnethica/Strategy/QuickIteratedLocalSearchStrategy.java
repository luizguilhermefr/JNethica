package main.jnethica.Strategy;

import main.jnethica.Exception.EmptyPopulationException;
import main.jnethica.Fitness.Contracts.FitnessCalculator;
import main.jnethica.Individual.Contracts.Individual;
import main.jnethica.Mutator.Contracts.Mutator;
import main.jnethica.Mutator.CreepMutator;
import main.jnethica.Population.Population;
import main.jnethica.StopCondition.Contracts.StopCondition;
import main.jnethica.Strategy.Contracts.Strategy;

public class QuickIteratedLocalSearchStrategy extends Strategy {
    public QuickIteratedLocalSearchStrategy(Population initialPopulation, FitnessCalculator fitnessCalculator, StopCondition stopCondition) {
        super(initialPopulation, fitnessCalculator, stopCondition, null);
    }

    @Override
    public void run () {
        try {
            setInitialBest();
        } catch (EmptyPopulationException e) {
            e.printStackTrace();
            return;
        }

        Mutator smallMutator = new CreepMutator(0.125);
        Mutator bigMutator = new CreepMutator(1.0);

        Individual localBetter = globalOptimum;

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
            setLocalOptimum(localBetter);
            reportStopConditionAndIncrementGeneration();
        } while (!stopCondition.mustStop());
    }
}
