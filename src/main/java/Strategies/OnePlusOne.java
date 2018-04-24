package main.java.Strategies;

import main.java.Exceptions.EmptyPopulationException;
import main.java.Fitness.Contracts.FitnessCalculator;
import main.java.Individuals.Contracts.Individual;
import main.java.Mutators.Contracts.Mutator;
import main.java.Mutators.CreepMutator;
import main.java.Population.Population;
import main.java.StopConditions.Contracts.StopCondition;
import main.java.Strategies.Contracts.Strategy;

public class OnePlusOne extends Strategy {

    public OnePlusOne (Population initialPopulation, FitnessCalculator fitnessCalculator) {
        super(initialPopulation, fitnessCalculator);
    }

    private Individual tournament (Individual a, Individual b) {
        if (a.isBetterThan(b, fitnessCalculator)) {
            return a;
        } else {
            return b;
        }
    }

    @Override
    public void run (StopCondition stopCondition) throws EmptyPopulationException {
        globalOptimum = initialPopulation.getBetter(fitnessCalculator);
        globalGeneration = 0;

        Individual localOptimum = null;
        Population nextGeneration = null;

        Mutator mutator = new CreepMutator(2.0);

        Integer currentGenerationNumber = 1;

        do {
            nextGeneration = initialPopulation.cloneEmpty();
            for (Integer j = 0; j < fixedSize; j++) {
                Individual firstSelected = this.initialPopulation.getRandomIndividual();
                Individual secondSelected = this.initialPopulation.getRandomIndividual();
                Individual betterOfSelecteds = tournament(firstSelected, secondSelected);
                nextGeneration.pushIndividual(betterOfSelecteds.mutate(mutator));
            }
            localOptimum = nextGeneration.getBetter(fitnessCalculator);
            if (localOptimum.isBetterThan(globalOptimum, fitnessCalculator)) {
                globalOptimum = localOptimum;
                globalGeneration = currentGenerationNumber;
            }
            stopCondition.report(currentGenerationNumber, fitnessCalculator.getFitness(globalOptimum));
            currentGenerationNumber++;
        } while (!stopCondition.mustStop());
    }
}
