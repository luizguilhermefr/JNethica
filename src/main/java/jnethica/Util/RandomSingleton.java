package jnethica.Util;

import java.security.SecureRandom;

public class RandomSingleton {
    private static RandomSingleton ourInstance = new RandomSingleton();

    private SecureRandom randomGenerator;

    private RandomSingleton() {
        randomGenerator = new SecureRandom();
    }

    public static RandomSingleton getInstance() {
        return ourInstance;
    }

    public Double doubleBetween(Double min, Double max) {
        return min + randomGenerator.nextDouble() * (max - min);
    }

    public Integer integerBetween(Integer min, Integer max) {
        return min + randomGenerator.nextInt(max + 1 - min);
    }

    public Boolean randomBoolean() {
        return randomGenerator.nextBoolean();
    }
}
