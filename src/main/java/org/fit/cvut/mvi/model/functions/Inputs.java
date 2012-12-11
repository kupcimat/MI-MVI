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

    public static Function turtlesAt(final String turtles, final Direction direction) {
        return new Function() {
            @Override
            public String name() {
                return "turtlesAt";
            }

            @Override
            public String code(List<String> arguments) {
                String result = "";
                switch (direction) {
                case NORTH:
                    result = "(count " + turtles + "-on patches at-points [[-2 4] [-1 4] [0 4] [1 4] [2 4] [-2 3] [-1 3] [0 3] [1 3] [2 3]])";
                    break;
                case EAST:
                    result = "(count " + turtles + "-on patches at-points [[4 2] [4 1] [4 0] [4 -1] [4 -2] [3 2] [3 1] [3 0] [3 -1] [3 -2]])";
                    break;
                case SOUTH:
                    result = "(count " + turtles + "-on patches at-points [[-2 -4] [-1 -4] [0 -4] [1 -4] [2 -4] [-2 -3] [-1 -3] [0 -3] [1 -3] [2 -3]])";
                    break;
                case WEST:
                    result = "(count " + turtles + "-on patches at-points [[-4 2] [-4 1] [-4 0] [-4 -1] [-4 -2] [-3 2] [-3 1] [-3 0] [-3 -1] [-3 -2]])";
                    break;
                }

                return result;
            }

            @Override
            public int arity() {
                return 0;
            }
        };
    }

    public static Function patchAt(final String type, final Direction direction) {
        return new Function() {
            @Override
            public String name() {
                return "patchAt";
            }

            @Override
            public String code(List<String> arguments) {
                String result = "";
                switch (direction) {
                case NORTH:
                    result = "(count patches at-points [[0 1] [0 2] [0 3] [0 4] [0 5]] with [pcolor = " + type + "])";
                    break;
                case EAST:
                    result = "(count patches at-points [[1 0] [2 0] [3 0] [4 0] [5 0]] with [pcolor = " + type + "])";
                    break;
                case SOUTH:
                    result = "(count patches at-points [[0 -1] [0 -2] [0 -3] [0 -4] [0 -5]] with [pcolor = " + type + "])";
                    break;
                case WEST:
                    result = "(count patches at-points [[-1 0] [-2 0] [-3 0] [-4 0] [-5 0]] with [pcolor = " + type + "])";
                    break;
                }

                return result;
            }

            @Override
            public int arity() {
                return 0;
            }
        };
    }

    public static enum Direction {
        NORTH, EAST, SOUTH, WEST
    }

}
