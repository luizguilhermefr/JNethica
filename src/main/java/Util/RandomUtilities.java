package main.java.Util;

import java.util.Random;

public class RandomUtilities {
    public static Double floatBetween (Double min, Double max) {
        Random r = new Random();
        return min + r.nextDouble() * (max - min);
    }

    public static Integer integerBetween (Integer min, Integer max) {
        Random r = new Random();
        return min + r.nextInt(max + 1 - min);
    }

    public static Boolean randomBoolean () {
        Random r = new Random();
        return r.nextBoolean();
    }
}
