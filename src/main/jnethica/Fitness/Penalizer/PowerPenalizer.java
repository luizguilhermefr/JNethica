package main.jnethica.Fitness.Penalizer;

import main.jnethica.Fitness.Penalizer.Contracts.Penalizer;
import main.jnethica.Fitness.Restriction.Contracts.Restriction;
import main.jnethica.Individual.Contracts.Individual;

import java.util.Set;

public class PowerPenalizer implements Penalizer {
    private final Double power;

    public PowerPenalizer (Double power) {
        this.power = power;
    }

    @Override
    public Double penalize (Individual target, Set<Restriction> restrictions) {
        Double sum = 0.0;
        for (Restriction restriction : restrictions) {
            sum += restriction.apply(target);
        }
        return -Math.pow(sum, power);
    }
}
