package Fitness.Penalizer;

import jnethica.Fitness.Restriction.Contracts.Restriction;
import jnethica.Individual.GenericFunction;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class PenalizerTest {

    protected Set<Restriction> restrictions () {
        Restriction restrictionOneMock = individual -> 8.0;
        Restriction restrictionTwoMock = individual -> 2.0;
        Set<Restriction> restrictions = new HashSet<>();
        restrictions.add(restrictionOneMock);
        restrictions.add(restrictionTwoMock);
        return restrictions;
    }

    protected GenericFunction individual () {
        Map<String, Object> values = new HashMap<>();
        values.put("x", 2.0);
        values.put("y", 3.0);
        return new GenericFunction("x+y", values);
    }
}
