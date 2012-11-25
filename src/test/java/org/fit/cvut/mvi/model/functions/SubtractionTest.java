package org.fit.cvut.mvi.model.functions;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class SubtractionTest {

    @Test
    public void testSafeExecute() {
        Subtraction sub = new Subtraction();
        List<String> args = new ArrayList<>();

        args.add("2.5");
        args.add("1.3");
        assertEquals("(2.5 - 1.3)", sub.safeCode(args));
    }

    @Test
    public void testArity() {
        Subtraction sub = new Subtraction();
        assertEquals(2, sub.arity());
    }

    @Test
    public void testName() {
        Subtraction sub = new Subtraction();
        assertEquals("Subtraction", sub.name());
    }

}
