package org.fit.cvut.mvi.model;

import java.util.ArrayList;
import java.util.List;

import org.fit.cvut.mvi.model.functions.Function;

public class InnerNode implements Node {

    private final Function function;
    private final List<Integer> connections;

    public InnerNode(Function function, List<Integer> connections) {
        if (connections.size() < function.arity()) {
            throw new IllegalArgumentException("Number of connections must match function arity");
        }

        this.function = function;
        this.connections = connections;
    }

    @Override
    public Function getFunction() {
        return function;
    }

    @Override
    public List<Integer> getConnections() {
        return connections;
    }

    @Override
    public Node copy() {
        return new InnerNode(function, new ArrayList<Integer>(connections));
    }

}
