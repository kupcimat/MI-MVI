package org.fit.cvut.mvi.model;

import java.util.List;

public interface Node {

    public Function getFunction();

    public List<Integer> getConnections();

}