package org.fit.cvut.mvi.model.functions;

import static org.junit.Assert.*;
import static org.fit.cvut.mvi.model.functions.Inputs.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

public class InputsTest {

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void testConstant() {
        Function f = constant(113);

        assertEquals(0, f.arity());
        assertEquals("constant", f.name());
        assertEquals("113", f.code(new ArrayList<String>()));
    }

    @Test
    public void testTurtlesInCone() {
        Function f = turtlesInCone("turtles", 2, 90);

        assertEquals(0, f.arity());
        assertEquals("turtlesInCone", f.name());
        assertEquals("(count turtles in-cone 2 90)", f.code(new ArrayList<String>()));
    }

    @Test
    public void testTurtlesAt() {
        Function f = turtlesAt("turtles", "-1 0");

        assertEquals(0, f.arity());
        assertEquals("turtlesAt", f.name());
        assertEquals("(count turtles-at -1 0)", f.code(new ArrayList<String>()));
    }

    @Test
    public void testPatchAhead() {
        Function f = patchAhead("gray", 2);

        assertEquals(0, f.arity());
        assertEquals("patchAhead", f.name());
        assertEquals("(count (patch-set patch-ahead 2) with [pcolor = gray])", f.code(new ArrayList<String>()));
    }

    @Test
    public void testPatchAt() {
        Function f = patchAt("green", "0 1");

        assertEquals(0, f.arity());
        assertEquals("patchAt", f.name());
        assertEquals("(count (patch-set patch-at 0 1) with [pcolor = green])", f.code(new ArrayList<String>()));
    }

}
