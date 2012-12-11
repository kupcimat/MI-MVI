package org.fit.cvut.mvi;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.fit.cvut.mvi.cgp.CGPConfiguration;
import org.fit.cvut.mvi.cgp.CGPEvolution;
import org.fit.cvut.mvi.cgp.CGPEvolutionConfiguration;
import org.fit.cvut.mvi.evaluator.FitnessEvaluator;
import org.fit.cvut.mvi.model.Genome;
import org.fit.cvut.mvi.model.functions.Addition;
import org.fit.cvut.mvi.model.functions.Function;
import org.fit.cvut.mvi.model.functions.Inputs;
import org.fit.cvut.mvi.model.functions.Inputs.Direction;
import org.fit.cvut.mvi.model.functions.Multiplication;
import org.fit.cvut.mvi.model.functions.Sine;
import org.fit.cvut.mvi.model.functions.SquareRoot;
import org.fit.cvut.mvi.model.functions.Subtraction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Main {

    private static Logger logger = LoggerFactory.getLogger(Main.class);

    /*
     * Fitness evaluator and configuration resources
     */
    public static final String NETLOGO_PATH = "/home/matej/Downloads/netlogo-5.0.2/NetLogo.jar";
    public static final String TEMPLATE_PATH = "sablona.nlogo";
    public static final String SETUP_PATH = "sablona.xml";
    public static final String CONFIG_FILE = "src/main/resources/cgp.properties";

    public static final String SHEEP = "sheep";
    public static final String WOLVES = "wolves";

    /**
     * @param args
     */
    public static void main(String[] args) {

        // Read command line parameters
        if (args.length < 1) {
            System.out.println("Usage: java -jar <jar-file> <sheep|wolves>");
            return;
        }
        if (!args[0].equals(SHEEP) && !args[0].equals(WOLVES)) {
            System.out.println("Usage: java -jar <jar-file> <sheep|wolves>");
            return;
        }
        String turtles = args[0];

        try {
            Configuration appConfig = new PropertiesConfiguration(CONFIG_FILE);

            // Create CGP configuration
            List<Function> functions = getFunctions();
            List<Function> inputs = getInputs();

            CGPConfiguration config = new CGPConfiguration.Builder().functions(functions).inputs(inputs)
                    .outputs(appConfig.getInt("genome.outputs")).rows(appConfig.getInt("genome.rows"))
                    .columns(appConfig.getInt("genome.columns")).levelsBack(appConfig.getInt("genome.levelsBack")).build();
            CGPEvolutionConfiguration evolutionConfig = new CGPEvolutionConfiguration.Builder()
                    .populationSize(appConfig.getInt("evolution.populationSize")).mutations(appConfig.getInt("evolution.mutations"))
                    .generations(appConfig.getInt("evolution.generations")).build();

            logger.debug(config.toString());
            logger.debug(evolutionConfig.toString());

            // Create fitness evaluator
            FitnessEvaluator evaluator = new FitnessEvaluator(NETLOGO_PATH, TEMPLATE_PATH, SETUP_PATH);

            // Evolution
            CGPEvolution evolution = new CGPEvolution(config, evaluator, turtles);
            Genome result = evolution.evolve(evolutionConfig);

            // Print results
            System.out.println(result.decode());

        } catch (ConfigurationException e) {
            e.printStackTrace();
        }
    }

    public static List<Function> getFunctions() {
        List<Function> functions = new ArrayList<>();

        functions.add(new Addition());
        functions.add(new Subtraction());
        functions.add(new Multiplication());
        // functions.add(new Modulo());
        functions.add(new SquareRoot());
        functions.add(new Sine());

        return functions;

    }

    public static List<Function> getInputs() {
        List<Function> inputs = new ArrayList<>();

        // Grass patches
        inputs.add(Inputs.patchAt(Inputs.GRASS, Direction.NORTH));
        inputs.add(Inputs.patchAt(Inputs.GRASS, Direction.SOUTH));
        inputs.add(Inputs.patchAt(Inputs.GRASS, Direction.EAST));
        inputs.add(Inputs.patchAt(Inputs.GRASS, Direction.WEST));
        // Dirt patches
        inputs.add(Inputs.patchAt(Inputs.DIRT, Direction.NORTH));
        inputs.add(Inputs.patchAt(Inputs.DIRT, Direction.SOUTH));
        inputs.add(Inputs.patchAt(Inputs.DIRT, Direction.EAST));
        inputs.add(Inputs.patchAt(Inputs.DIRT, Direction.WEST));
        // Trap patches
        inputs.add(Inputs.patchAt(Inputs.TRAP, Direction.NORTH));
        inputs.add(Inputs.patchAt(Inputs.TRAP, Direction.SOUTH));
        inputs.add(Inputs.patchAt(Inputs.TRAP, Direction.EAST));
        inputs.add(Inputs.patchAt(Inputs.TRAP, Direction.WEST));
        // Wolves
        inputs.add(Inputs.turtlesAt(Inputs.WOLVES, Direction.NORTH));
        inputs.add(Inputs.turtlesAt(Inputs.WOLVES, Direction.SOUTH));
        inputs.add(Inputs.turtlesAt(Inputs.WOLVES, Direction.EAST));
        inputs.add(Inputs.turtlesAt(Inputs.WOLVES, Direction.WEST));
        inputs.add(Inputs.turtlesInCone(Inputs.WOLVES, 1, 120));
        inputs.add(Inputs.turtlesInCone(Inputs.WOLVES, 3, 120));
        inputs.add(Inputs.turtlesInCone(Inputs.WOLVES, 5, 120));
        // Sheep
        inputs.add(Inputs.turtlesAt(Inputs.SHEEP, Direction.NORTH));
        inputs.add(Inputs.turtlesAt(Inputs.SHEEP, Direction.SOUTH));
        inputs.add(Inputs.turtlesAt(Inputs.SHEEP, Direction.EAST));
        inputs.add(Inputs.turtlesAt(Inputs.SHEEP, Direction.WEST));
        inputs.add(Inputs.turtlesInCone(Inputs.SHEEP, 1, 120));
        inputs.add(Inputs.turtlesInCone(Inputs.SHEEP, 3, 120));
        inputs.add(Inputs.turtlesInCone(Inputs.SHEEP, 5, 120));

        return inputs;
    }

}
