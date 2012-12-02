package org.fit.cvut.mvi;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.fit.cvut.mvi.cgp.CGPConfiguration;
import org.fit.cvut.mvi.cgp.GenomeFactory;
import org.fit.cvut.mvi.model.Genome;
import org.fit.cvut.mvi.model.InnerNode;
import org.fit.cvut.mvi.model.InputNode;
import org.fit.cvut.mvi.model.Node;
import org.fit.cvut.mvi.model.functions.Addition;
import org.fit.cvut.mvi.model.functions.Function;
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

        // generate random genome
        List<Function> functions = new ArrayList<>();
        functions.add(new Addition());
        functions.add(new Subtraction());

        List<Function> inputs = new ArrayList<>();
        inputs.add(Inputs.constant(0));
        inputs.add(Inputs.constant(1));
        inputs.add(Inputs.constant(2));

        CGPConfiguration config = new CGPConfiguration.Builder().functions(functions).inputs(inputs).outputs(2).rows(2).columns(3)
                .levelsBack(1).build();
        GenomeFactory gf = new GenomeFactory();

        System.out.println(config);
        System.out.println(gf.createRandomGenome(config).decode());
    }
}
