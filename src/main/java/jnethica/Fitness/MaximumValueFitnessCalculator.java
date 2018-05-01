package jnethica.Fitness;

import jnethica.Fitness.Contracts.FitnessCalculator;
import jnethica.Fitness.Penalizer.Contracts.Penalizer;
import jnethica.Fitness.Restriction.Contracts.Restriction;
import jnethica.Individual.Contracts.Individual;

import java.util.Set;

public class MaximumValueFitnessCalculator implements FitnessCalculator {
    private Penalizer penalizer;
    private Set<Restriction> restrictions;

    public MaximumValueFitnessCalculator () {
        this.penalizer = null;
        this.restrictions = null;
    }

    public MaximumValueFitnessCalculator (Penalizer penalizer, Set<Restriction> restrictions) {
        this.penalizer = penalizer;
        this.restrictions = restrictions;
    }

    private Double applyPenalizers (Individual individual) {
        if (restrictions != null && penalizer != null) {
            return penalizer.penalize(individual, restrictions);
        }
        return .0;
    }

    @Override
    public Double getFitness (Individual individual) {
        Double fitness = individual.getValue();
        fitness += applyPenalizers(individual);
        return fitness;
    }

    @Override
    public int compare (Individual o1, Individual o2) {
        return (int) Math.floor(getFitness(o1) - getFitness(o2));
    }
}
