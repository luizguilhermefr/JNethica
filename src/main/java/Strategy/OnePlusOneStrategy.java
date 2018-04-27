package main.java.Strategy;

import main.java.Exception.EmptyPopulationException;
import main.java.Fitness.Contracts.FitnessCalculator;
import main.java.Individual.Contracts.Individual;
import main.java.Mutator.Contracts.Mutator;
import main.java.Mutator.CreepMutator;
import main.java.Population.Population;
import main.java.StopCondition.Contracts.StopCondition;
import main.java.Strategy.Contracts.Strategy;

public class OnePlusOneStrategy extends Strategy {

    public OnePlusOneStrategy(Population initialPopulation, FitnessCalculator fitnessCalculator, StopCondition stopCondition, Mutator mutator) {
        super(initialPopulation, fitnessCalculator, stopCondition, mutator);
    }

    private Individual tournament (Individual a, Individual b) {
        if (a.isBetterThan(b, fitnessCalculator)) {
            return a;
        } else {
            return b;
        }
    }

    @Override
    public void run () {
        try {
            globalOptimum = initialPopulation.getBetter(fitnessCalculator);
        } catch (EmptyPopulationException e) {
            e.printStackTrace();
            return;
        }
        globalGeneration = 0;

        Individual localOptimum = null;
        Population nextGeneration = null;

        Mutator mutator = new CreepMutator(2.0);

        Integer currentGenerationNumber = 1;

        do {
            nextGeneration = initialPopulation.cloneEmpty();
            for (Integer j = 0; j < fixedSize; j++) {
                Individual firstSelected = null;
                Individual secondSelected = null;
                try {
                    firstSelected = this.initialPopulation.getRandomIndividual();
                    secondSelected = this.initialPopulation.getRandomIndividual();
                } catch (EmptyPopulationException e) {
                    e.printStackTrace();
                    return;
                }
                Individual betterOfSelecteds = tournament(firstSelected, secondSelected);
                nextGeneration.pushIndividual(betterOfSelecteds.mutate(mutator));
            }
            try {
                localOptimum = nextGeneration.getBetter(fitnessCalculator);
            } catch (EmptyPopulationException e) {
                e.printStackTrace();
                return;
            }
            if (localOptimum.isBetterThan(globalOptimum, fitnessCalculator)) {
                globalOptimum = localOptimum;
                globalGeneration = currentGenerationNumber;
            }
            stopCondition.report(currentGenerationNumber, fitnessCalculator.getFitness(globalOptimum));
            currentGenerationNumber++;
        } while (!stopCondition.mustStop());
    }
}
