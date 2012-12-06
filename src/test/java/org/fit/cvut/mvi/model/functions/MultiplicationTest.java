package org.fit.cvut.mvi.model.functions;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class MultiplicationTest {

    @Test
    public void testSafeCode() {
        Multiplication multiply = new Multiplication();
        List<String> args = new ArrayList<>();

        args.add("4.7");
        args.add("113");
        assertEquals("(4.7 * 113)", multiply.code(args));
    }

    @Test
    public void testArity() {
        Multiplication multiply = new Multiplication();
        assertEquals(2, multiply.arity());
    }

    @Test
    public void testName() {
        Multiplication multiply = new Multiplication();
        assertEquals("Multiplication", multiply.name());
    }

}
