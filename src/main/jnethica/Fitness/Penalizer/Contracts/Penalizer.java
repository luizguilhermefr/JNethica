package main.jnethica.Fitness.Penalizer.Contracts;

import main.jnethica.Fitness.Restriction.Contracts.Restriction;
import main.jnethica.Individual.Contracts.Individual;

import java.util.List;

@FunctionalInterface
public interface Penalizer {
    Double penalize (Individual target, List<Restriction> restrictions);
}
