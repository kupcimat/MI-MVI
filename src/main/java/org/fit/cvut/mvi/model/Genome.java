package org.fit.cvut.mvi.model;

import java.util.ArrayList;
import java.util.List;

public class Genome {

    private List<Node> nodes;
    private List<Integer> outputs;

    public Genome(List<Node> nodes, List<Integer> outputs) {
        this.nodes = nodes;
        this.outputs = outputs;
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

}
