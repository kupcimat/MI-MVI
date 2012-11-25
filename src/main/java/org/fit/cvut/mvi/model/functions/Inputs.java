package org.fit.cvut.mvi.model.functions;

import java.util.List;

public class Inputs {

    public static Function constant(final int number) {
        return new Function() {
            @Override
            public String name() {
                return "constant";
            }

            @Override
            public String code(List<String> arguments) {
                return String.valueOf(number);
            }

            @Override
            public int arity() {
                return 0;
            }
        };
    }

    public static Function wolvesInCone(final int distance, final int angle) {
        return new Function() {
            @Override
            public String name() {
                return "wolvesInCone";
            }

            @Override
            public String code(List<String> arguments) {
                return "(count wolves in-cone " + distance + " " + angle + ")";
            }

            @Override
            public int arity() {
                return 0;
            }
        };
    }
}
