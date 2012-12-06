package org.fit.cvut.mvi.model.functions;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class ModuloTest {

    @Test
    public void testSafeCode() {
        Modulo mod = new Modulo();
        List<String> args = new ArrayList<>();

        args.add("47");
        args.add("13");
        assertEquals("(47 mod 13)", mod.code(args));
    }

    @Test
    public void testArity() {
        Modulo mod = new Modulo();
        assertEquals(2, mod.arity());
    }

    @Test
    public void testName() {
        Modulo mod = new Modulo();
        assertEquals("Modulo", mod.name());
    }

}
