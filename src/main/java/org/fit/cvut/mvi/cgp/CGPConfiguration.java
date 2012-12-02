package org.fit.cvut.mvi.cgp;

import java.util.List;

import org.fit.cvut.mvi.model.functions.Function;

public class CGPConfiguration {

    private final List<Function> functions;
    private final List<Function> inputs;
    private final int outputs;
    private final int rows;
    private final int columns;
    private final int levelsBack;

    public CGPConfiguration(List<Function> functions, List<Function> inputs, int outputs, int rows, int columns, int levelsBack) {
        this.functions = functions;
        this.inputs = inputs;
        this.outputs = outputs;
        this.rows = rows;
        this.columns = columns;
        this.levelsBack = levelsBack;
    }

    public List<Function> getFunctions() {
        return functions;
    }

    public List<Function> getInputs() {
        return inputs;
    }

    public int getOutputs() {
        return outputs;
    }

    public int getRows() {
        return rows;
    }

    public int getColumns() {
        return columns;
    }

    public int getLevelsBack() {
        return levelsBack;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("CGPConfiguration [functions=");
        builder.append(functions.size());
        builder.append(", inputs=");
        builder.append(inputs.size());
        builder.append(", outputs=");
        builder.append(outputs);
        builder.append(", rows=");
        builder.append(rows);
        builder.append(", columns=");
        builder.append(columns);
        builder.append(", levelsBack=");
        builder.append(levelsBack);
        builder.append("]");

        return builder.toString();
    }

    public static class Builder {

        private List<Function> functions;
        private List<Function> inputs;
        private int outputs;
        private int rows;
        private int columns;
        private int levelsBack;

        public Builder functions(List<Function> functions) {
            this.functions = functions;
            return this;
        }

        public Builder inputs(List<Function> inputs) {
            this.inputs = inputs;
            return this;
        }

        public Builder outputs(int outputs) {
            this.outputs = outputs;
            return this;
        }

        public Builder rows(int rows) {
            this.rows = rows;
            return this;
        }

        public Builder columns(int columns) {
            this.columns = columns;
            return this;
        }

        public Builder levelsBack(int levelsBack) {
            this.levelsBack = levelsBack;
            return this;
        }

        public CGPConfiguration build() {
            return new CGPConfiguration(functions, inputs, outputs, rows, columns, levelsBack);
        }

    }

}
