package test.java.Strategies;

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
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.util.ArrayList;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

class ClassicGeneticStrategyTest {

    private HashMap<String, Double> minimumValues() {
        HashMap<String, Double> minimumValues = new HashMap<>();
        minimumValues.put("x", -5.0);
        minimumValues.put("y", -5.0);
        return minimumValues;
    }

    private HashMap<String, Double> maximumValues() {
        HashMap<String, Double> maximumValues = new HashMap<>();
        maximumValues.put("x", 5.0);
        maximumValues.put("y", 5.0);
        return maximumValues;
    }

    private ArrayList<String> variables() {
        ArrayList<String> variables = new ArrayList<>();
        variables.add("x");
        variables.add("y");
        return variables;
    }

    private String function() {
        return CommonFunctions.QUADRI_GAUSSIAN;
    }

    private IndividualFactory individualFactory() {
        return new GenericFunctionFactory(function(), variables(), minimumValues(), maximumValues());
    }

    private Population initialPopulation() {
        Population<GenericFunction> initialPopulation = new Population<>();
        initialPopulation.generateInitialPopulation(20, individualFactory());
        return initialPopulation;
    }

    private FitnessCalculator fitnessCalculator() {
        return new MaximumValueFitnessCalculator();
    }

    private StopCondition stopCondition() {
        return new MaximumGenerationsStopCondition(10);
    }

    private Mutator mutator() {
        return new CreepMutator(2.0);
    }

    private Crossover crossover() {
        return new ArithmeticCrossover();
    }

    private Double crossoverRate() {
        return 70.0;
    }

    @Test
    void testAcceptableResult() throws InterruptedException {
        ClassicGeneticStrategy<GenericFunction> classicGeneticStrategy;
        classicGeneticStrategy = new ClassicGeneticStrategy<>(initialPopulation(), fitnessCalculator(), stopCondition(), mutator(), crossover(), crossoverRate());
        classicGeneticStrategy.start();
        classicGeneticStrategy.join();
        GenericFunction best = classicGeneticStrategy.getGlobalOptimum();
        assertNotNull(best);
        Boolean acceptableResult = best.getValue() >= 0.9 && best.getValue() <= 1.1;
        assertTrue(acceptableResult);
    }

    @Test
    void testAcceptableThroughput() {
        ClassicGeneticStrategy<GenericFunction> classicGeneticStrategy;
        classicGeneticStrategy = new ClassicGeneticStrategy<>(initialPopulation(), fitnessCalculator(), stopCondition(), mutator(), crossover(), crossoverRate());
        assertTimeout(Duration.ofMillis(130), classicGeneticStrategy::run);
    }
}