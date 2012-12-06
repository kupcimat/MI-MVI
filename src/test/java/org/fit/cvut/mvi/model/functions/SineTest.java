package org.fit.cvut.mvi.model.functions;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class SineTest {

    @Test
    public void testSafeCode() {
        Sine sin = new Sine();
        List<String> args = new ArrayList<>();

        args.add("360");
        assertEquals("(sin 360)", sin.code(args));
    }

    @Test
    public void testArity() {
        Sine sin = new Sine();
        assertEquals(1, sin.arity());
    }

    @Test
    public void testName() {
        Sine sin = new Sine();
        assertEquals("Sine", sin.name());
    }

}
