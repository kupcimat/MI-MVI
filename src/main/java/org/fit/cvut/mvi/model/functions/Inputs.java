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
                    result = "(count " + turtles + "-on patches at-points [[-2 4] [-1 4] [0 4] [1 4] [2 4] [-3 3] [-2 3] [-1 3] [0 3] [1 3] [2 3] [3 3]])";
                    break;
                case FAR_NORTH:
                    result = "(count " + turtles + "-on patches at-points [[-3 8] [-2 8] [-1 8] [0 8] [1 8] [2 8] [3 8] [-3 7] [-2 7] [-1 7] [0 7] [1 7] [2 7] [3 7]])";
                    break;
                case EAST:
                    result = "(count " + turtles + "-on patches at-points [[4 2] [4 1] [4 0] [4 -1] [4 -2] [3 3] [3 2] [3 1] [3 0] [3 -1] [3 -2] [3 -3]])";
                    break;
                case FAR_EAST:
                    result = "(count " + turtles + "-on patches at-points [[8 2] [8 1] [8 0] [8 -1] [8 -2] [7 2] [7 1] [7 0] [7 -1] [7 -2]])";
                    break;
                case SOUTH:
                    result = "(count " + turtles + "-on patches at-points [[-2 -4] [-1 -4] [0 -4] [1 -4] [2 -4] [-3 -3] [-2 -3] [-1 -3] [0 -3] [1 -3] [2 -3] [3 -3]])";
                    break;
                case FAR_SOUTH:
                    result = "(count " + turtles + "-on patches at-points [[-2 -8] [-1 -8] [0 -8] [1 -8] [2 -8] [-2 -7] [-1 -7] [0 -7] [1 -7] [2 -7]])";
                    break;
                case WEST:
                    result = "(count " + turtles + "-on patches at-points [[-4 2] [-4 1] [-4 0] [-4 -1] [-4 -2] [-3 3] [-3 2] [-3 1] [-3 0] [-3 -1] [-3 -2] [-3 -3]])";
                    break;
                case FAR_WEST:
                    result = "(count " + turtles + "-on patches at-points [[-8 2] [-8 1] [-8 0] [-8 -1] [-8 -2] [-7 2] [-7 1] [-7 0] [-7 -1] [-7 -2]])";
                    break;
                default:
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
                case NORTH_EAST:
                    result = "(count patches at-points [[1 1] [2 2] [3 3] [4 4] [5 5]] with [pcolor = " + type + "])";
                    break;
                case EAST:
                    result = "(count patches at-points [[1 0] [2 0] [3 0] [4 0] [5 0]] with [pcolor = " + type + "])";
                    break;
                case SOUTH_EAST:
                    result = "(count patches at-points [[1 -1] [2 -2] [3 -3] [4 -4] [5 -5]] with [pcolor = " + type + "])";
                    break;
                case SOUTH:
                    result = "(count patches at-points [[0 -1] [0 -2] [0 -3] [0 -4] [0 -5]] with [pcolor = " + type + "])";
                    break;
                case SOUTH_WEST:
                    result = "(count patches at-points [[-1 -1] [-2 -2] [-3 -3] [-4 -4] [-5 -5]] with [pcolor = " + type + "])";
                    break;
                case WEST:
                    result = "(count patches at-points [[-1 0] [-2 0] [-3 0] [-4 0] [-5 0]] with [pcolor = " + type + "])";
                    break;
                case NORTH_WEST:
                    result = "(count patches at-points [[-1 1] [-2 2] [-3 3] [-4 4] [-5 5]] with [pcolor = " + type + "])";
                    break;
                default:
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
        NORTH, EAST, SOUTH, WEST,
        NORTH_EAST, SOUTH_EAST, SOUTH_WEST, NORTH_WEST,
        FAR_NORTH, FAR_EAST, FAR_SOUTH, FAR_WEST
    }

}
