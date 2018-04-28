package test.java.Fitness.Restriction;

import main.jnethica.Fitness.Restriction.BoundaryRestriction;
import main.jnethica.Individual.GenericFunction;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BoundaryRestrictionTest {

    private GenericFunction individual (Double x1, Double x2, Double x3) {
        Map<String, Object> values = new HashMap<>();
        values.put("x1", x1);
        values.put("x2", x2);
        values.put("x3", x3);
        return new GenericFunction("x1 * x2 + x2 * x3", values);
    }

    private Set<String> variables () {
        Set<String> variables = new HashSet<>();
        variables.add("x1");
        variables.add("x2");
        variables.add("x3");
        return variables;
    }

    private String pattern () {
        // -83 with x1 = 6, x2 = 12, x3 = 5
        // 868 with x1 = 30, x2 = 6, x3 = 2
        // 2 with x1 = 1, x2 = 0, x2 = -1
        return "x1^2 - x2^2 + x3^2";
    }

    @Test
    void testLessThan () {
        BoundaryRestriction restriction = new BoundaryRestriction(pattern(), variables(), 2.0, BoundaryRestriction.Modes.LESS_THAN);
        Double distance;
        distance = restriction.apply(individual(6.0, 12.0, 5.0));
        assertEquals(.0, (double) distance);
        distance = restriction.apply(individual(30.0, 6.0, 2.0));
        assertEquals(866.0, (double) distance);
        distance = restriction.apply(individual(1.0, 0.0, 1.0));
        assertEquals(.01, (double) distance);
    }

    @Test
    void testHigherThan () {
        BoundaryRestriction restriction = new BoundaryRestriction(pattern(), variables(), 2.0, BoundaryRestriction.Modes.HIGHER_THAN);
        Double distance;
        distance = restriction.apply(individual(6.0, 12.0, 5.0));
        assertEquals(85.0, (double) distance);
        distance = restriction.apply(individual(30.0, 6.0, 2.0));
        assertEquals(.0, (double) distance);
        distance = restriction.apply(individual(1.0, 0.0, 1.0));
        assertEquals(.01, (double) distance);
    }

    @Test
    void testHigherEqualThan () {
        BoundaryRestriction restriction = new BoundaryRestriction(pattern(), variables(), 2.0, BoundaryRestriction.Modes.HIGHER_OR_EQUAL_THAN);
        Double distance;
        distance = restriction.apply(individual(6.0, 12.0, 5.0));
        assertEquals(85.0, (double) distance);
        distance = restriction.apply(individual(30.0, 6.0, 2.0));
        assertEquals(.0, (double) distance);
        distance = restriction.apply(individual(1.0, 0.0, 1.0));
        assertEquals(.0, (double) distance);
    }

    @Test
    void testLessEqualThan () {
        BoundaryRestriction restriction = new BoundaryRestriction(pattern(), variables(), 2.0, BoundaryRestriction.Modes.LESS_OR_EQUAL_THAN);
        Double distance;
        distance = restriction.apply(individual(6.0, 12.0, 5.0));
        assertEquals(.0, (double) distance);
        distance = restriction.apply(individual(30.0, 6.0, 2.0));
        assertEquals(866.0, (double) distance);
        distance = restriction.apply(individual(1.0, 0.0, 1.0));
        assertEquals(.0, (double) distance);
    }
}