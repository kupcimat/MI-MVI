package org.fit.cvut.mvi.model.functions;

import java.util.List;

public class Sine extends AbstractFunction {

    @Override
    public int arity() {
        return 1;
    }

    @Override
    public String name() {
        return "Sine";
    }

    @Override
    protected String safeCode(List<String> arguments) {
        return "(sin " + arguments.get(0) + ")";
    }

}
