package Contracts;

public interface Individual {
    public Individual mutate(Double mutationRate);
    public Double getValue();
    public Boolean isBetterThan(Individual individual, FitnessCalculator fitnessFunction);
    public Individual clone();
    public String toString();
}
