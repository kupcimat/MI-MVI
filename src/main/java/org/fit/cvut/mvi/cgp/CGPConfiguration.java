package org.fit.cvut.mvi.cgp;

import java.util.List;

import org.fit.cvut.mvi.model.functions.Function;

public class CGPConfiguration {

    private final List<Function> inputs;
    private final int outputs;
    private final int rows;
    private final int columns;
    private final int levelsBack;

    public CGPConfiguration(List<Function> inputs, int outputs, int rows, int columns, int levelsBack) {
        this.inputs = inputs;
        this.outputs = outputs;
        this.rows = rows;
        this.columns = columns;
        this.levelsBack = levelsBack;
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

    public static class Builder {

        private List<Function> inputs;
        private int outputs;
        private int rows;
        private int columns;
        private int levelsBack;

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
            return new CGPConfiguration(inputs, outputs, rows, columns, levelsBack);
        }

    }

}
