package org.fit.cvut.mvi.model.functions;

import java.util.List;

public class Multiplication extends AbstractFunction {

    @Override
    public int arity() {
        return 2;
    }

    @Override
    public String name() {
        return "Multiplication";
    }

    @Override
    protected String safeCode(List<String> arguments) {
        return "(" + arguments.get(0) + " * " + arguments.get(1) + ")";
    }

}
