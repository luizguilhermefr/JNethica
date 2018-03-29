import Contracts.FitnessCalculator;
import Contracts.Individual;
import Contracts.Population;
import Contracts.Strategy;
import Exceptions.EmptyPopulationException;
import Fitness.MaximumValueFitnessCalculator;
import Populations.QuadriGaussianPopulation;
import Strategies.OnePlusOne;

class NaturalSelectionMethodsComparator {

    private Integer populationSize;

    private Integer generations;

    private Population initialPopulation;

    private FitnessCalculator fitnessCalculator;

    private Individual bestOfOnePlusOne;

    NaturalSelectionMethodsComparator (Integer populationSize, Integer generations) {
        this.populationSize = populationSize;
        this.generations = generations;
        this.initialPopulation = new QuadriGaussianPopulation();
        this.initialPopulation.generateInitialPopulation(populationSize);
        this.fitnessCalculator = new MaximumValueFitnessCalculator();
    }

    private void printInitializer () {
        System.out.println("<< Comparing methods using " + populationSize + " pop. size and " + generations + " generations");
    }

    private void printResults () {
        System.out.println("1+1:\t" + bestOfOnePlusOne);
        System.out.println("<< -- END -- >>");
    }

    void compare () throws EmptyPopulationException {
        printInitializer();
        Strategy onePlusOne = new OnePlusOne(initialPopulation, fitnessCalculator);
        bestOfOnePlusOne = onePlusOne.run(generations);
        printResults();
    }
}
