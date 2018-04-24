package main.java.Strategy;

import main.java.Exception.EmptyPopulationException;
import main.java.Fitness.Contracts.FitnessCalculator;
import main.java.Individual.Contracts.Function;
import main.java.Individual.Contracts.Individual;
import main.java.Mutator.Contracts.Mutator;
import main.java.Mutator.CreepMutator;
import main.java.Population.Population;
import main.java.StopCondition.Contracts.StopCondition;
import main.java.Util.Statistics;

import java.util.ArrayList;
import java.util.HashMap;

public class MetaEvolutionary extends MiPlusMi {
    public MetaEvolutionary (Population initialPopulation, FitnessCalculator fitnessCalculator, StopCondition stopCondition) {
        super(initialPopulation, fitnessCalculator, stopCondition);
    }

    private ArrayList<Double> argumentsArrayList (ArrayList<Function> individuals, String argumentIndex) {
        ArrayList<Double> values = new ArrayList<>();
        for (Function individual : individuals) {
            values.add(individual.getArgument(argumentIndex));
        }
        return values;
    }

    @Override
    public void run () throws IllegalArgumentException {
        try {
            globalOptimum = initialPopulation.getBetter(fitnessCalculator);
        } catch (EmptyPopulationException e) {
            e.printStackTrace();
            return;
        }
        globalGeneration = 0;

        Individual localOptimum;
        Population currentGeneration = initialPopulation.clone();

        Integer currentGenerationNumber = 1;

        do {
            currentGeneration.sort(fitnessCalculator);
            Population descendents = currentGeneration.clone();
            ArrayList<Function> individuals = descendents.getIndividuals();
            HashMap<String, Mutator> variances = new HashMap<>();
            for (String key : individuals.get(0).getArgumentsKeys()) {
                ArrayList<Double> values = argumentsArrayList(individuals, key);
                variances.put(key, new CreepMutator(Statistics.variance(values)));
            }
            for (Integer j = 0; j < individuals.size(); j++) {
                Function individual = individuals.get(j);
                individuals.set(j, individual.mutate(variances));
            }
            descendents.sort(fitnessCalculator);
            currentGeneration = combine(currentGeneration, descendents);
            try {
                localOptimum = currentGeneration.getBetter(fitnessCalculator);
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
