package test.java.Individuals;

import main.jnethica.Individual.Bits;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class BitsTest {

    private Bits bitsOne;

    private Bits bitsTwo;

    private void generateBitsOne () {
        ArrayList<Boolean> set;
        set = new ArrayList<>();
        set.add(true);
        set.add(false);
        set.add(true);
        set.add(true);
        set.add(false);
        bitsOne = new Bits(set);
    }

    private void generateBitsTwo () {
        ArrayList<Boolean> set;
        set = new ArrayList<>();
        set.add(false);
        set.add(true);
        set.add(false);
        set.add(true);
        set.add(true);
        bitsTwo = new Bits(set);
    }

    @BeforeEach
    void setUp () {
        generateBitsOne();
        generateBitsTwo();
    }

    @Test
    void testSize () {
        assertEquals(5, (int) bitsOne.size());
    }

    @Test
    void testGet () {
        assertTrue(bitsOne.get(0));
        assertFalse(bitsOne.get(4));
        assertFalse(bitsTwo.get(0));
        assertTrue(bitsTwo.get(4));
    }

    @Test
    void testSplice () {
        Bits spliced;

        spliced = bitsOne.splice(0, 2);
        assertEquals(3, (int) spliced.size());
        assertTrue(spliced.get(0));
        assertFalse(spliced.get(1));

        spliced = bitsTwo.splice(1, 4);
        assertEquals(4, (int) spliced.size());
        assertTrue(spliced.get(0));
        assertTrue(spliced.get(2));
    }
}