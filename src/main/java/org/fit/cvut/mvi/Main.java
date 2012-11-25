package org.fit.cvut.mvi;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.fit.cvut.mvi.model.Genome;
import org.fit.cvut.mvi.model.InnerNode;
import org.fit.cvut.mvi.model.InputNode;
import org.fit.cvut.mvi.model.Node;
import org.fit.cvut.mvi.model.functions.Addition;
import org.fit.cvut.mvi.model.functions.Inputs;
import org.fit.cvut.mvi.model.functions.Subtraction;

public class Main {

    /**
     * @param args
     */
    public static void main(String[] args) {
        List<Node> nodes = new ArrayList<>();
        List<Integer> outputs = new ArrayList<>();

        nodes.add(new InputNode(Inputs.constant(0)));
        nodes.add(new InputNode(Inputs.constant(1)));
        nodes.add(new InnerNode(new Addition(), Arrays.asList(0, 1)));
        nodes.add(new InnerNode(new Subtraction(), Arrays.asList(0, 1)));
        nodes.add(new InnerNode(new Addition(), Arrays.asList(2, 3)));
        nodes.add(new InnerNode(new Subtraction(), Arrays.asList(2, 1)));

        outputs.add(2);
        outputs.add(4);
        outputs.add(5);

        Genome g = new Genome(nodes, outputs);
        System.out.println(g.decode());
    }

}
