package org.fit.cvut.mvi.cgp;

import static org.apache.commons.lang.Validate.notEmpty;
import static org.apache.commons.lang.Validate.notNull;

import java.util.ArrayList;
import java.util.List;

import org.fit.cvut.mvi.Main;
import org.fit.cvut.mvi.evaluator.FitnessEvaluator;
import org.fit.cvut.mvi.model.Genome;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CGPEvolution {

    private static Logger logger = LoggerFactory.getLogger(CGPEvolution.class);

    /*
     * Represents tolerance which is used when comparing 2 fitness values.
     */
    public static final double FITNESS_TOLERANCE = 0.01;

    private CGPConfiguration config;
    private FitnessEvaluator evaluator;
    private String turtles;

    public CGPEvolution(CGPConfiguration config, FitnessEvaluator evaluator, String turtles) {
        notNull(config, "CGPConfiguration shouldn't be null");
        notNull(evaluator, "FitnessEvaluator shouldn't be null");
        notEmpty(turtles, "Turtles shouldn't be null");
        this.config = config;
        this.evaluator = evaluator;
        this.turtles = turtles;
    }

    public Genome evolve(CGPEvolutionConfiguration evolutionConfig) {
        notNull(evolutionConfig, "Evolution configuration shouldn't be null");

        Genome parentGenome = init(evolutionConfig.populationSize());
        double parentFitness = getFitness(parentGenome);

        int generation = 0;
        while (generation < evolutionConfig.generations()) {
            generation++;
            logger.debug(String.format("Creating generation %s...", generation));

            // Generate population
            List<Genome> children = new ArrayList<>();
            for (int i = 0; i < evolutionConfig.populationSize(); i++) {
                children.add(parentGenome.mutate(config, evolutionConfig.mutations()));
            }

            // Select the best genome
            for (Genome genome : children) {
                double fitness;
                // If mutated and parent genomes have the same phenotype, don't evaluate fitness
                if (genome.equalsPhenotype(parentGenome)) {
                    fitness = parentFitness;
                    logger.debug("Skipping fitness evaluation...");
                } else {
                    fitness = getFitness(genome);
                }

                if (compareFitness(fitness, parentFitness) >= 0) {
                    parentGenome = genome;
                    parentFitness = fitness;
                }
            }

            logger.debug(String.format("Best individual in generation %s, fitness = %s, genome = %s", generation, parentFitness,
                    parentGenome.decode()));
        }

        return parentGenome;
    }

    protected Genome init(int populationSize) {
        GenomeFactory factory = new GenomeFactory();
        Genome bestGenome = factory.createRandomGenome(config);
        double bestFitness = getFitness(bestGenome);

        for (int i = 0; i < populationSize; i++) {
            Genome actGenome = factory.createRandomGenome(config);
            double actFitness = getFitness(actGenome);

            if (actFitness > bestFitness) {
                bestGenome = actGenome;
                bestFitness = actFitness;
            }
        }

        return bestGenome;
    }

    protected double getFitness(Genome genome) {
        if (Main.SHEEP.equals(turtles)) {
            return getSheepFitness(genome);
        }
        if (Main.WOLVES.equals(turtles)) {
            return getWolfFitness(genome);
        }

        return 0;
    }

    protected double getSheepFitness(Genome genome) {
        String code = genome.decode();
        double fitness = evaluator.evaluateSheepFitness(code, FitnessEvaluator.RANDOM_MOVE);

        logger.debug(String.format("Evaluated sheep with fitness = %s, genome = %s", fitness, code));
        return fitness;
    }

    protected double getWolfFitness(Genome genome) {
        String code = genome.decode();
        double fitness = evaluator.evaluateWolvesFitness(FitnessEvaluator.RANDOM_MOVE, code);

        logger.debug(String.format("Evaluated wolf with fitness = %s, genome = %s", fitness, code));
        return fitness;
    }

    protected int compareFitness(double first, double second) {
        if (Math.abs(first - second) < FITNESS_TOLERANCE) {
            return 0;
        }
        return ((first < second) ? -1 : 1);
    }

}
