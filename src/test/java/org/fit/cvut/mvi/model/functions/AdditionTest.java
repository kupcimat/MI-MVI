package org.fit.cvut.mvi.model.functions;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class AdditionTest {

    @Test
    public void testSafeExecute() {
        Addition add = new Addition();
        List<String> args = new ArrayList<>();

        args.add("1.2");
        args.add("1.3");
        assertEquals("(1.2 + 1.3)", add.safeCode(args));
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
