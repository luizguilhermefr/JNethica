package main.jnethica.Fitness.Penalizer;

import main.jnethica.Fitness.Penalizer.Contracts.Penalizer;
import main.jnethica.Fitness.Restriction.Contracts.Restriction;
import main.jnethica.Individual.Contracts.Individual;

import java.util.List;

public class SquarePenalizer implements Penalizer {
    @Override
    public Double penalize (Individual target, List<Restriction> restrictions) {
        Double sum = 0.0;
        for (Restriction restriction : restrictions) {
            sum += restriction.apply(target);
        }
        return -Math.pow(sum, 2);
    }
}
