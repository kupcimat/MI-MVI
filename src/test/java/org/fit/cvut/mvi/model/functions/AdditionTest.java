package org.fit.cvut.mvi.model.functions;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class AdditionTest {

    @Test
    public void testSafeExecute() {
        Addition add = new Addition();
        List<Double> args = new ArrayList<>();

        args.add(1.2);
        args.add(1.3);
        assertEquals(2.5, add.execute(args), 0.01);
    }

    @Test
    public void testArity() {
        Addition add = new Addition();
        assertEquals(2, add.arity());
    }

    @Test
    public void testName() {
        Addition add = new Addition();
        assertEquals("Addition", add.name());
    }

}
