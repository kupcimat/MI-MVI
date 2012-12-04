package org.fit.cvut.mvi.model;

import java.util.List;

import org.fit.cvut.mvi.model.functions.Function;

public interface Node {

    public Function getFunction();

    public List<Integer> getConnections();

    public Node copy();

}
