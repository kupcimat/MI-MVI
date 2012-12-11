package org.fit.cvut.mvi.model.functions;

import static org.fit.cvut.mvi.model.functions.Inputs.constant;
import static org.fit.cvut.mvi.model.functions.Inputs.patchAt;
import static org.fit.cvut.mvi.model.functions.Inputs.turtlesAt;
import static org.fit.cvut.mvi.model.functions.Inputs.turtlesInCone;
import static org.junit.Assert.assertEquals;

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
        Function f = turtlesAt("turtles", Inputs.Direction.WEST);

        assertEquals(0, f.arity());
        assertEquals("turtlesAt", f.name());
        assertEquals("(count turtles-on patches at-points [[-4 2] [-4 1] [-4 0] [-4 -1] [-4 -2] [-3 2] [-3 1] [-3 0] [-3 -1] [-3 -2]])",
                f.code(new ArrayList<String>()));
    }

    @Test
    public void testPatchAt() {
        Function f = patchAt("green", Inputs.Direction.NORTH);

        assertEquals(0, f.arity());
        assertEquals("patchAt", f.name());
        assertEquals("(count patches at-points [[0 1] [0 2] [0 3] [0 4] [0 5]] with [pcolor = green])", f.code(new ArrayList<String>()));
    }

}
