package jnethica;

import jnethica.Crossover.ArithmeticCrossover;
import jnethica.Crossover.Contracts.Crossover;
import jnethica.Factory.Contracts.IndividualFactory;
import jnethica.Factory.GenericFunctionFactory;
import jnethica.Fitness.Contracts.FitnessCalculator;
import jnethica.Fitness.MaximumValueFitnessCalculator;
import jnethica.Fitness.Penalizer.Contracts.Penalizer;
import jnethica.Fitness.Penalizer.ExponentialPenalizer;
import jnethica.Fitness.Restriction.BoundaryRestriction;
import jnethica.Fitness.Restriction.Contracts.Restriction;
import jnethica.Individual.GenericFunction;
import jnethica.Mutator.Contracts.Mutator;
import jnethica.Mutator.CreepMutator;
import jnethica.Population.Population;
import jnethica.StopCondition.Contracts.StopCondition;
import jnethica.StopCondition.MaximumGenerationsStopCondition;
import jnethica.Strategy.ClassicGeneticStrategy;
import jnethica.Strategy.Contracts.Strategy;
import jnethica.Util.Tuple;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class Main {
    // General
    private static final int GENERATIONS = 500;
    private static final double MUTATION_RATE = 2.0;
    private static final double CROSSOVER_RATE = 80.0;

    /* P1 */
    // Boundaries
    private static final double P1_MIN = -8.0;
    private static final double P1_MAX = +8.0;

    // Func
    private static final String P1_FUNC = "x1 * x2 + x2 * x3";

    // Restrictions
    private static final String P1_R1 = "x1^2 - x2^2 + x3^2";
    private static final BoundaryRestriction.Modes P1_R1_OP = BoundaryRestriction.Modes.LESS_OR_EQUAL_THAN;
    private static final double P1_R1_V = 2.0;
    private static final String P1_R2 = "x1^2 + x2^2 + x3^2";
    private static final BoundaryRestriction.Modes P1_R2_OP = BoundaryRestriction.Modes.LESS_OR_EQUAL_THAN;
    private static final double P1_R2_V = 10.0;

    /* P2 */
    // Boundaries
    private static final double P2_MIN = -3.0;
    private static final double P2_MAX = +6.0;

    // Func
    private static final String P2_FUNC = "(x1 - 3)^2 + (x2 - 4)^2";

    // Restrictions
    private static final String P2_R1 = "5 - x1 - x2";
    private static final BoundaryRestriction.Modes P2_R1_OP = BoundaryRestriction.Modes.HIGHER_OR_EQUAL_THAN;
    private static final double P2_R1_V = .0;
    private static final String P2_R2 = "-2.5 + x1 - x2";
    private static final BoundaryRestriction.Modes P2_R2_OP = BoundaryRestriction.Modes.LESS_OR_EQUAL_THAN;
    private static final double P2_R2_V = .0;

    private static Strategy generateFirstProblemThread (Integer population, Boolean elitism) {
        // Define variables
        ArrayList<String> variables = new ArrayList<>();
        variables.add("x1");
        variables.add("x2");
        variables.add("x3");

        // Define minimum and values for variables
        HashMap<String, Tuple<Double>> boundaries = new HashMap<>();
        boundaries.put("x1", new Tuple<>(P1_MIN, P1_MAX));
        boundaries.put("x2", new Tuple<>(P1_MIN, P1_MAX));
        boundaries.put("x3", new Tuple<>(P1_MIN, P1_MAX));

        // Define individual factory
        IndividualFactory individualFactory = new GenericFunctionFactory(P1_FUNC, variables, boundaries);

        // Generate initial population
        Population<GenericFunction> initialPopulation = new Population<>();
        initialPopulation.generateInitialPopulation(population, individualFactory);

        // Define problem restrictions
        Set<Restriction> restrictions = new HashSet<>();
        Set<String> restrictionVariables = new HashSet<>();
        restrictionVariables.add("x1");
        restrictionVariables.add("x2");
        restrictionVariables.add("x3");
        restrictions.add(new BoundaryRestriction(P1_R1, restrictionVariables, P1_R1_V, P1_R1_OP));
        restrictions.add(new BoundaryRestriction(P1_R2, restrictionVariables, P1_R2_V, P2_R2_OP));

        // Define a penalizer
        Penalizer penalizer = new ExponentialPenalizer();

        // Define a fitness calculator
        FitnessCalculator fitnessCalculator = new MaximumValueFitnessCalculator(penalizer, restrictions);

        // Define a stop condition
        StopCondition stopCondition = new MaximumGenerationsStopCondition(GENERATIONS);

        // Define a mutator
        Mutator mutator = new CreepMutator(MUTATION_RATE);

        // Define crossover method
        Crossover crossover = new ArithmeticCrossover();

        return new ClassicGeneticStrategy<GenericFunction>(initialPopulation, fitnessCalculator, stopCondition, mutator, crossover, CROSSOVER_RATE, elitism);
    }

    private static Strategy generateSecondProblemThread (Integer population, Boolean elitism) {
        // Define variables
        ArrayList<String> variables = new ArrayList<>();
        variables.add("x1");
        variables.add("x2");

        // Define minimum and values for variables
        HashMap<String, Tuple<Double>> boundaries = new HashMap<>();
        boundaries.put("x1", new Tuple<>(P2_MIN, P2_MAX));
        boundaries.put("x2", new Tuple<>(P2_MIN, P2_MAX));

        // Define individual factory
        IndividualFactory individualFactory = new GenericFunctionFactory(P2_FUNC, variables, boundaries);

        // Generate initial population
        Population<GenericFunction> initialPopulation = new Population<>();
        initialPopulation.generateInitialPopulation(population, individualFactory);

        // Define problem restrictions
        Set<Restriction> restrictions = new HashSet<>();
        Set<String> restrictionVariables = new HashSet<>();
        restrictionVariables.add("x1");
        restrictionVariables.add("x2");
        restrictions.add(new BoundaryRestriction(P2_R1, restrictionVariables, P2_R1_V, P2_R1_OP));
        restrictions.add(new BoundaryRestriction(P2_R2, restrictionVariables, P2_R2_V, P2_R2_OP));

        // Define a penalizer
        Penalizer penalizer = new ExponentialPenalizer();

        // Define a fitness calculator
        FitnessCalculator fitnessCalculator = new MaximumValueFitnessCalculator(penalizer, restrictions);

        // Define a stop condition
        StopCondition stopCondition = new MaximumGenerationsStopCondition(GENERATIONS);

        // Define a mutator
        Mutator mutator = new CreepMutator(MUTATION_RATE);

        // Define crossover method
        Crossover crossover = new ArithmeticCrossover();

        return new ClassicGeneticStrategy<GenericFunction>(initialPopulation, fitnessCalculator, stopCondition, mutator, crossover, CROSSOVER_RATE, elitism);
    }

    public static void main (String[] args) throws InterruptedException {
        // Create threads
        Strategy p1WithElitism = generateFirstProblemThread(30, Boolean.TRUE);
        Strategy p1WithoutElitism = generateFirstProblemThread(30, Boolean.FALSE);
        Strategy p1WithElitism20 = generateFirstProblemThread(20, Boolean.TRUE);
        Strategy p1WithElitism50 = generateFirstProblemThread(50, Boolean.TRUE);

        Strategy p2WithElitism = generateSecondProblemThread(30, Boolean.TRUE);
        Strategy p2WithoutElitism = generateSecondProblemThread(30, Boolean.FALSE);
        Strategy p2WithElitism20 = generateSecondProblemThread(20, Boolean.TRUE);
        Strategy p2WithElitism50 = generateSecondProblemThread(50, Boolean.TRUE);

        // Start threads
        p1WithElitism.start();
        p1WithoutElitism.start();
        p1WithElitism20.start();
        p1WithElitism50.start();

        p2WithElitism.start();
        p2WithoutElitism.start();
        p2WithElitism20.start();
        p2WithElitism50.start();

        // Join threads
        p1WithElitism.join();
        p1WithoutElitism.join();
        p1WithElitism20.join();
        p1WithElitism50.join();

        p2WithElitism.join();
        p2WithoutElitism.join();
        p1WithElitism20.join();
        p1WithElitism50.join();

        // Print results
        System.out.println("\nP1");
        System.out.println("ELITISM=Y,POP=30\t" + p1WithElitism.getGlobalOptimum() + "\tgen=" + p1WithElitism.getGlobalOptimumGeneration() + "\tfitness=" + p1WithElitism.getGlobalOptimumFitness());
        System.out.println("ELITISM=N,POP=30\t" + p1WithoutElitism.getGlobalOptimum() + "\tgen=" + p1WithoutElitism.getGlobalOptimumGeneration() + "\tfitness=" + p1WithoutElitism.getGlobalOptimumFitness());
        System.out.println("ELITISM=Y,POP=20\t" + p1WithElitism20.getGlobalOptimum() + "\tgen=" + p1WithElitism20.getGlobalOptimumGeneration() + "\tfitness=" + p1WithElitism20.getGlobalOptimumFitness());
        System.out.println("ELITISM=Y,POP=50\t" + p1WithElitism50.getGlobalOptimum() + "\tgen=" + p1WithElitism50.getGlobalOptimumGeneration() + "\tfitness=" + p1WithElitism50.getGlobalOptimumFitness());
        System.out.println("\nP2");
        System.out.println("ELITISM=Y,POP=30\t" + p2WithElitism.getGlobalOptimum() + "\tgen=" + p2WithElitism.getGlobalOptimumGeneration() + "\tfitness=" + p2WithElitism.getGlobalOptimumFitness());
        System.out.println("ELITISM=N,POP=30\t" + p2WithoutElitism.getGlobalOptimum() + "\tgen=" + p2WithoutElitism.getGlobalOptimumGeneration() + "\tfitness=" + p2WithoutElitism.getGlobalOptimumFitness());
        System.out.println("ELITISM=Y,POP=20\t" + p2WithElitism20.getGlobalOptimum() + "\tgen=" + p2WithElitism20.getGlobalOptimumGeneration() + "\tfitness=" + p2WithElitism20.getGlobalOptimumFitness());
        System.out.println("ELITISM=Y,POP=50\t" + p2WithElitism50.getGlobalOptimum() + "\tgen=" + p2WithElitism50.getGlobalOptimumGeneration() + "\tfitness=" + p2WithElitism50.getGlobalOptimumFitness());

        // Say goodbye
        System.exit(0);
    }
}
