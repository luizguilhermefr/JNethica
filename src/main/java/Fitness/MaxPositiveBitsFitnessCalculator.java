package main.java.Fitness;

import main.java.Contracts.FitnessCalculator;
import main.java.Contracts.Individual;
import main.java.Individuals.Bits;

public class MaxPositiveBitsFitnessCalculator implements FitnessCalculator {

    private Integer countPositives (Bits bits) {
        Integer count = 0;
        for (Integer i = 0; i < bits.size(); i++) {
            if (bits.get(i)) {
                count++;
            }
        }
        return count;
    }

    @Override
    public Double getFitness (Individual individual) {
        return (double) countPositives((Bits) individual);
    }

    public int compare (Individual o1, Individual o2) {
        return (int) Math.floor(countPositives((Bits) o1) - countPositives((Bits) o2));
    }
}
