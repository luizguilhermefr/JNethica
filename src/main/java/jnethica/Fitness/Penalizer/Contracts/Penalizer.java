package jnethica.Fitness.Penalizer.Contracts;

import jnethica.Fitness.Restriction.Contracts.Restriction;
import jnethica.Individual.Contracts.Individual;

import java.util.Set;

@FunctionalInterface
public interface Penalizer {
    Double penalize (Individual target, Set<Restriction> restrictions);
}
