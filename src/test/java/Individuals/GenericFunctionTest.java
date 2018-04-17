package test.java.Individuals;

import main.java.Individuals.GenericFunction;
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
        values.put("x1", -8.0);
        values.put("x2", -8.0);
        values.put("x3", -8.0);
        linearProgrammingExample = new GenericFunction("x1 * x2 + x2 * x3", values);
    }

    @Test
    void testValue () {
        assertEquals(128.0, (double) linearProgrammingExample.getValue());
    }

    @Test
    void testGetArgument () {
        assertEquals(-8.0, (double) linearProgrammingExample.getArgument("x1"));
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
        assertEquals("x1=-8.0\tx2=-8.0\tx3=-8.0\tf(args)=128.0", linearProgrammingExample.toString());
    }

    @Test
    void testCountArguments () {
        assertEquals(3, (int) linearProgrammingExample.countArguments());
    }
}