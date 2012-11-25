package org.fit.cvut.mvi.model;

import java.util.List;

public abstract class AbstractFunction implements Function {

    @Override
    public String code(List<String> arguments) {
        if (arguments.size() < arity()) {
            throw new IllegalArgumentException(
                    String.format("Function %s expected %d arguments, got %d", name(), arity(), arguments.size()));
        }

        return safeCode(arguments);
    }

    protected abstract String safeCode(List<String> arguments);

}
