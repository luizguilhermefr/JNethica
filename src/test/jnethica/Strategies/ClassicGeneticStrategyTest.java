package test.jnethica.Strategies;

import main.jnethica.Crossover.ArithmeticCrossover;
import main.jnethica.Crossover.Contracts.Crossover;
import main.jnethica.Factory.Contracts.IndividualFactory;
import main.jnethica.Factory.GenericFunctionFactory;
import main.jnethica.Fitness.Contracts.FitnessCalculator;
import main.jnethica.Fitness.MaximumValueFitnessCalculator;
import main.jnethica.Individual.GenericFunction;
import main.jnethica.Mutator.Contracts.Mutator;
import main.jnethica.Mutator.CreepMutator;
import main.jnethica.Population.Population;
import main.jnethica.StopCondition.Contracts.StopCondition;
import main.jnethica.StopCondition.MaximumGenerationsStopCondition;
import main.jnethica.Strategy.ClassicGeneticStrategy;
import main.jnethica.Util.CommonFunctions;
import main.jnethica.Util.Tuple;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.util.ArrayList;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

class ClassicGeneticStrategyTest {

    private HashMap<String, Tuple<Double>> quadriGaussianBoundaries () {
        HashMap<String, Tuple<Double>> boundaries = new HashMap<>();
        boundaries.put("x", new Tuple<>(-5.0, 5.0));
        boundaries.put("y", new Tuple<>(-5.0, 5.0));
        return boundaries;
    }

    private ArrayList<String> quadriGaussianVariables () {
        ArrayList<String> variables = new ArrayList<>();
        variables.add("x");
        variables.add("y");
        return variables;
    }

    private String quadriGaussianFunction () {
        return CommonFunctions.QUADRI_GAUSSIAN;
    }

    private IndividualFactory quadriGaussianFactory () {
        return new GenericFunctionFactory(quadriGaussianFunction(), quadriGaussianVariables(), quadriGaussianBoundaries());
    }

    private Population quadriGaussianInitialPopulation () {
        Population<GenericFunction> initialPopulation = new Population<>();
        initialPopulation.generateInitialPopulation(20, quadriGaussianFactory());
        return initialPopulation;
    }

    private FitnessCalculator fitnessCalculator () {
        return new MaximumValueFitnessCalculator();
    }

    private StopCondition quadriGaussianStopCondition () {
        return new MaximumGenerationsStopCondition(10);
    }

    private Mutator mutator () {
        return new CreepMutator(2.0);
    }

    private Crossover crossover () {
        return new ArithmeticCrossover();
    }

    private Double crossoverRate () {
        return 70.0;
    }

    @Test
    void testAcceptableResult () throws InterruptedException {
        ClassicGeneticStrategy<GenericFunction> classicGeneticStrategy;
        classicGeneticStrategy = new ClassicGeneticStrategy<>(quadriGaussianInitialPopulation(), fitnessCalculator(), quadriGaussianStopCondition(), mutator(), crossover(), crossoverRate());
        classicGeneticStrategy.start();
        classicGeneticStrategy.join();
        GenericFunction best = classicGeneticStrategy.getGlobalOptimum();
        assertNotNull(best);
        Boolean acceptableResult = best.getValue() >= 0.9 && best.getValue() <= 1.1;
        assertTrue(acceptableResult);
    }

    @Test
    void testAcceptableThroughput () {
        ClassicGeneticStrategy<GenericFunction> classicGeneticStrategy;
        classicGeneticStrategy = new ClassicGeneticStrategy<>(quadriGaussianInitialPopulation(), fitnessCalculator(), quadriGaussianStopCondition(), mutator(), crossover(), crossoverRate());
        assertTimeout(Duration.ofMillis(130), classicGeneticStrategy::run);
    }
}