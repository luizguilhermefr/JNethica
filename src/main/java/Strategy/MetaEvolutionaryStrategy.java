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

public class MetaEvolutionaryStrategy extends MiPlusMiStrategy {
    public MetaEvolutionaryStrategy(Population initialPopulation, FitnessCalculator fitnessCalculator, StopCondition stopCondition, Mutator mutator) {
        super(initialPopulation, fitnessCalculator, stopCondition, mutator);
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
            setInitialBest();
        } catch (EmptyPopulationException e) {
            e.printStackTrace();
            return;
        }

        Individual localOptimum;
        Population currentGeneration = initialPopulation.clone();

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
            setLocalOptimum(localOptimum);
            reportStopConditionAndIncrementGeneration();
        } while (!stopCondition.mustStop());
    }
}
