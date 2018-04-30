package test.jnethica.Individuals;

import main.jnethica.Individual.GenericFunction;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotSame;

class GenericFunctionTest {

    private GenericFunction linearProgrammingExample;

    @BeforeEach
    void prepare () {
        Map<String, Object> values = new HashMap<>();
        values.put("x1", 1.5811394321);
        values.put("x2", 2.2360671257);
        values.put("x3", 1.5811394321);
        linearProgrammingExample = new GenericFunction("x1 * x2 + x2 * x3", values);
    }

    @Test
    void testValue () {
        assertEquals(7.0710678106, linearProgrammingExample.getValue(), 0.001);
    }

    @Test
    void testGetArgument () {
        assertEquals(1.5811394321, (double) linearProgrammingExample.getArgument("x1"));
        assertEquals(2.2360671257, (double) linearProgrammingExample.getArgument("x2"));
        assertEquals(1.5811394321, (double) linearProgrammingExample.getArgument("x3"));
    }

    @Test
    void testClone () {
        GenericFunction next = linearProgrammingExample.clone();
        assertNotSame(linearProgrammingExample, next);
        assertEquals(linearProgrammingExample.getArgument("x1"), next.getArgument("x1"));
        assertEquals(linearProgrammingExample.getArgument("x2"), next.getArgument("x2"));
        assertEquals(linearProgrammingExample.getArgument("x3"), next.getArgument("x3"));
        assertEquals(linearProgrammingExample.getValue(), next.getValue());
    }

    @Test
    void testToString () {
        assertEquals("x1=1.5811394321\tx2=2.2360671257\tx3=1.5811394321\tf(args)=7.071067810533555", linearProgrammingExample.toString());
    }

    @Test
    void testCountArguments () {
        assertEquals(3, (int) linearProgrammingExample.countArguments());
    }
}