package org.fit.cvut.mvi.model;

import java.util.List;

public interface Function {

    public double execute(List<Double> arguments);

    public int arity();

    public String name();

}
