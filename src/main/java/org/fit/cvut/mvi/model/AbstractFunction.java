package org.fit.cvut.mvi.model;

import java.util.List;

public abstract class AbstractFunction implements Function {

    @Override
    public double execute(List<Double> arguments) {
        if (arguments.size() < arity()) {
            throw new IllegalArgumentException(
                    String.format("Function %s expected %d arguments, got %d", name(), arity(), arguments.size()));
        }

        return safeExecute(arguments);
    }

    protected abstract double safeExecute(List<Double> arguments);

}
