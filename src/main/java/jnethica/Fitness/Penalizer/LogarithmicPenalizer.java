package jnethica.Fitness.Penalizer;

import jnethica.Fitness.Penalizer.Contracts.Penalizer;
import jnethica.Fitness.Restriction.Contracts.Restriction;
import jnethica.Individual.Contracts.Individual;

import java.util.Set;

public class LogarithmicPenalizer implements Penalizer {
    @Override
    public Double penalize (Individual target, Set<Restriction> restrictions) {
        Double sum = 0.0;
        for (Restriction restriction : restrictions) {
            sum += restriction.apply(target);
        }
        if (sum.equals(.0)) {
            return .0;
        }
        return -Math.log(sum);
    }
}
