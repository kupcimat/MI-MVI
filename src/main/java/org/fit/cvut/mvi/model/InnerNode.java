package org.fit.cvut.mvi.model;

import java.util.List;

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

}
