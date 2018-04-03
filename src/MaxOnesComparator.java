import Contracts.FitnessCalculator;
import Contracts.Individual;
import Contracts.Strategy;
import Exceptions.EmptyPopulationException;
import Fitness.MaximumValueFitnessCalculator;
import Populations.BitMapPopulation;
import Strategies.Crossover;

class MaxOnesComparator {

    private Integer generations;

    private BitMapPopulation initialPopulation;

    private FitnessCalculator fitnessCalculator;

    MaxOnesComparator (Integer populationSize, Integer generations) {
        this.generations = generations;
        this.initialPopulation = new BitMapPopulation();
        this.initialPopulation.generateInitialPopulation(populationSize);
        this.fitnessCalculator = new MaximumValueFitnessCalculator();
    }

    void compare () throws EmptyPopulationException {
        Strategy crossover = new Crossover(initialPopulation, fitnessCalculator);
        Individual best = crossover.run(generations);
        System.out.println(best);
    }
}
