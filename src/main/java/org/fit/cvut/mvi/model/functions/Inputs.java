package org.fit.cvut.mvi.model.functions;

import java.util.List;

public class Inputs {

    /*
     * Map elements constants
     */
    public static final String GRASS = "green";
    public static final String DIRT = "brown";
    public static final String TRAP = "gray";

    /*
     * Breed constants
     */
    public static final String SHEEP = "sheep";
    public static final String WOLVES = "wolves";

    /*
     * Location constants
     */
    public static final String NORTH = "0 1";
    public static final String SOUTH = "0 -1";
    public static final String EAST = "1 0";
    public static final String WEST = "-1 0";

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

    public static Function turtlesInCone(final String turtles, final int distance, final int angle) {
        return new Function() {
            @Override
            public String name() {
                return "turtlesInCone";
            }

            @Override
            public String code(List<String> arguments) {
                return "(count " + turtles + " in-cone " + distance + " " + angle + ")";
            }

            @Override
            public int arity() {
                return 0;
            }
        };
    }

    public static Function turtlesAt(final String turtles, final String coordinates) {
        return new Function() {
            @Override
            public String name() {
                return "turtlesAt";
            }

            @Override
            public String code(List<String> arguments) {
                return "(count " + turtles + "-at " + coordinates + ")";
            }

            @Override
            public int arity() {
                return 0;
            }
        };
    }

    public static Function patchAhead(final String type, final int distance) {
        return new Function() {
            @Override
            public String name() {
                return "patchAhead";
            }

            @Override
            public String code(List<String> arguments) {
                return "(count (patch-set patch-ahead " + distance + ") with [pcolor = " + type + "])";
            }

            @Override
            public int arity() {
                return 0;
            }
        };
    }

    public static Function patchAt(final String type, final String coordinates) {
        return new Function() {
            @Override
            public String name() {
                return "patchAt";
            }

            @Override
            public String code(List<String> arguments) {
                return "(count (patch-set patch-at " + coordinates + ") with [pcolor = " + type + "])";
            }

            @Override
            public int arity() {
                return 0;
            }
        };
    }

}
