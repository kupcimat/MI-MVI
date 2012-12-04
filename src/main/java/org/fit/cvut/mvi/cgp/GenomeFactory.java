package org.fit.cvut.mvi.cgp;

import java.util.ArrayList;
import java.util.List;

import org.fit.cvut.mvi.model.Genome;
import org.fit.cvut.mvi.model.InnerNode;
import org.fit.cvut.mvi.model.InputNode;
import org.fit.cvut.mvi.model.Node;
import org.fit.cvut.mvi.model.functions.Function;

public class GenomeFactory {

    public Genome createRandomGenome(CGPConfiguration config) {
        List<Node> nodes = new ArrayList<>();

        // add input nodes to genome
        for (Function function : config.getInputs()) {
            nodes.add(new InputNode(function));
        }

        // add random inner nodes to genome
        for (int column = 0; column < config.getColumns(); column++) {
            for (int row = 0; row < config.getRows(); row++) {
                Function function = randomFunction(config);
                List<Integer> connections = randomConnections(config, function.arity(), column);
                nodes.add(new InnerNode(function, connections));
            }
        }

        return new Genome(nodes, randomOutputs(config));
    }

    public Function randomFunction(CGPConfiguration config) {
        return config.getFunctions().get((int) (Math.random() * config.getFunctions().size()));
    }

    public int randomInput(CGPConfiguration c, int column) {
        int min, max, shiftedMax, randIndex;

        max = c.getInputs().size() + column * c.getRows();
        if (column >= c.getLevelsBack()) {
            min = c.getInputs().size() + (column - c.getLevelsBack()) * c.getRows();
            // include input nodes
            shiftedMax = max + c.getInputs().size();
            // ensure that index won't be out of bounds
            randIndex = (min + (int) (Math.random() * (shiftedMax - min))) % max;
        } else {
            randIndex = (int) (Math.random() * max);
        }

        return randIndex;
    }

    public int randomOutput(CGPConfiguration config) {
        return (int) (Math.random() * (config.getInputs().size() + config.getRows() * config.getColumns()));
    }

    protected List<Integer> randomConnections(CGPConfiguration config, int arity, int column) {
        List<Integer> nodes = new ArrayList<>();
        for (int i = 0; i < arity; i++) {
            nodes.add(randomInput(config, column));
        }

        return nodes;
    }

    protected List<Integer> randomOutputs(CGPConfiguration config) {
        List<Integer> nodes = new ArrayList<>();
        for (int i = 0; i < config.getOutputs(); i++) {
            nodes.add(randomOutput(config));
        }

        return nodes;
    }

}
