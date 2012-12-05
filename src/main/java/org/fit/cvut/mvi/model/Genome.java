package org.fit.cvut.mvi.model;

import java.util.ArrayList;
import java.util.List;

import org.fit.cvut.mvi.cgp.CGPConfiguration;
import org.fit.cvut.mvi.cgp.GenomeFactory;
import org.fit.cvut.mvi.model.functions.Function;

/**
 * Represents CGP genome. Its internal structure consists of input, internal and output nodes. It can be imagined as arrays of nodes: nodes
 * - [input_nodes, internal_nodes], outputs - [output_nodes].
 */
public class Genome {

    private List<Node> nodes;
    private List<Integer> outputs;

    public Genome(List<Node> nodes, List<Integer> outputs) {
        this.nodes = nodes;
        this.outputs = outputs;
    }

    public Genome mutate(CGPConfiguration c, int mutations) {
        List<Node> newNodes = copyNodes();
        List<Integer> newOutputs = new ArrayList<>(outputs);

        for (int i = 0; i < mutations; i++) {
            int randIndex = c.getInputs().size() + (int) (Math.random() * (c.getColumns() * c.getRows() + c.getOutputs()));
            if (randIndex < newNodes.size()) {
                newNodes.set(randIndex, mutateNode(c, newNodes.get(randIndex), (randIndex - c.getInputs().size()) / c.getRows()));
            } else {
                mutateOutput(c, newOutputs);
            }
        }

        return new Genome(newNodes, newOutputs);
    }

    protected Node mutateNode(CGPConfiguration config, Node node, int column) {
        GenomeFactory gf = new GenomeFactory();
        Function function = node.getFunction();
        List<Integer> inputs = new ArrayList<>(node.getConnections());

        int randIndex = (int) (Math.random() * (node.getFunction().arity() + 1));
        if (randIndex < node.getFunction().arity()) {
            // mutate connection
            inputs.set(randIndex, gf.randomInput(config, column));
        } else {
            // mutate function
            function = gf.randomFunction(config);
            // check function arity
            if (function.arity() > node.getFunction().arity()) {
                int missingInputs = function.arity() - node.getFunction().arity();
                for (int i = 0; i < missingInputs; i++) {
                    inputs.add(gf.randomInput(config, column));
                }
            }
        }

        return new InnerNode(function, inputs);
    }

    protected void mutateOutput(CGPConfiguration config, List<Integer> outputs) {
        int rand = (int) (Math.random() * outputs.size());
        // mutate output
        outputs.set(rand, new GenomeFactory().randomOutput(config));
    }

    public String decode() {
        StringBuffer result = new StringBuffer();

        result.append("report list");
        for (int output : outputs) {
            result.append(" ");
            result.append(generateCode(output));
        }

        return result.toString();
    }

    private String generateCode(int node) {
        Node n = nodes.get(node);

        List<String> args = new ArrayList<>();
        for (int connection : n.getConnections()) {
            args.add(generateCode(connection));
        }

        return n.getFunction().code(args);
    }

    private List<Node> copyNodes() {
        List<Node> copies = new ArrayList<>();
        for (Node node : nodes) {
            copies.add(node.copy());
        }

        return copies;
    }

}
