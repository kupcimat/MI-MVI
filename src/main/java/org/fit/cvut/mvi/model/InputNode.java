package org.fit.cvut.mvi.model;

import java.util.ArrayList;
import java.util.List;

public class InputNode implements Node {

    private final Function terminal;

    public InputNode(Function terminal) {
        this.terminal = terminal;
    }

    @Override
    public Function getFunction() {
        return terminal;
    }

    @Override
    public List<Integer> getConnections() {
        return new ArrayList<>();
    }

}
