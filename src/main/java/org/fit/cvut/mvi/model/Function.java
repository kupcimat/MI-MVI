package org.fit.cvut.mvi.model;

import java.util.List;

public interface Function {

    public String code(List<String> arguments);

    public int arity();

    public String name();

}
