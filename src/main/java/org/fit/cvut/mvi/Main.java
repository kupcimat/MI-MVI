package org.fit.cvut.mvi;

import java.util.ArrayList;
import java.util.List;

import org.fit.cvut.mvi.cgp.CGPConfiguration;
import org.fit.cvut.mvi.cgp.CGPEvolution;
import org.fit.cvut.mvi.cgp.CGPEvolutionConfiguration;
import org.fit.cvut.mvi.evaluator.FitnessEvaluator;
import org.fit.cvut.mvi.model.Genome;
import org.fit.cvut.mvi.model.functions.Addition;
import org.fit.cvut.mvi.model.functions.Function;
import org.fit.cvut.mvi.model.functions.Inputs;
import org.fit.cvut.mvi.model.functions.Subtraction;

public class Main {

    public static final String NETLOGO_PATH = "/home/matej/Downloads/netlogo-5.0.2/NetLogo.jar";
    public static final String TEMPLATE_PATH = "sablona.nlogo";
    public static final String SETUP_PATH = "sablona.xml";

    /**
     * @param args
     */
    public static void main(String[] args) {
        // Inner functions
        List<Function> functions = getFunctions();
        // Inputs
        List<Function> inputs = getInputs();

        // Create CGP configuration
        CGPConfiguration config = new CGPConfiguration.Builder().functions(functions).inputs(inputs).outputs(2).rows(2).columns(3)
                .levelsBack(1).build();
        CGPEvolutionConfiguration evolutionConfig = new CGPEvolutionConfiguration.Builder().populationSize(4).mutations(1).generations(1)
                .build();

        // Create fitness evaluator
        FitnessEvaluator evaluator = new FitnessEvaluator(NETLOGO_PATH, TEMPLATE_PATH, SETUP_PATH);

        // Evolution
        CGPEvolution evolution = new CGPEvolution(config, evaluator);
        Genome result = evolution.evolve(evolutionConfig);

        // Print results
        System.out.println(config);
        System.out.println(result.decode());
    }

    public static List<Function> getFunctions() {
        List<Function> functions = new ArrayList<>();
        functions.add(new Addition());
        functions.add(new Subtraction());

        return functions;

    }

    public static List<Function> getInputs() {
        List<Function> inputs = new ArrayList<>();
        inputs.add(Inputs.constant(0));
        inputs.add(Inputs.constant(1));
        inputs.add(Inputs.constant(2));

        return inputs;
    }

}
