package org.fit.cvut.mvi.model.functions;

import java.util.List;

public class Modulo extends AbstractFunction {

    @Override
    public int arity() {
        return 2;
    }

    @Override
    public String name() {
        return "Modulo";
    }

    @Override
    protected String safeCode(List<String> arguments) {
        return "(" + arguments.get(0) + " mod " + arguments.get(1) + ")";
    }

}
