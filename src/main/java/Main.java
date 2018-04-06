package main.java;

import main.java.Exceptions.EmptyPopulationException;

public class Main {

    private static final int ARGS_COUNT = 2;
    private static final int DEFAULT_POP_SIZE = 10;
    private static final int DEFAULT_GENERATIONS_NUMBER = 1000;

    private static Boolean validNumberArgument (String argument) {
        try {
            if (Integer.valueOf(argument) <= 0) {
                return false;
            }
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }

    private static Boolean validArgs (String[] args) {
        return args.length == ARGS_COUNT && validNumberArgument(args[0]) && validNumberArgument(args[1]);
    }

    public static void main (String[] args) throws EmptyPopulationException {
        // Args: population size, generations
        Integer populationSize;
        Integer generations;
        if (!validArgs(args)) {
            populationSize = DEFAULT_POP_SIZE;
            generations = DEFAULT_GENERATIONS_NUMBER;
        } else {
            populationSize = Integer.valueOf(args[0]);
            generations = Integer.valueOf(args[1]);
        }
        NaturalSelectionMethodsComparator comparator = new NaturalSelectionMethodsComparator(populationSize, generations);
        comparator.compare();
        System.exit(0);
    }
}
