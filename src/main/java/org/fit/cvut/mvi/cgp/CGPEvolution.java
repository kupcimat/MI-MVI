package org.fit.cvut.mvi.cgp;

import static org.apache.commons.lang.Validate.notNull;

import org.fit.cvut.mvi.evaluator.FitnessEvaluator;
import org.fit.cvut.mvi.model.Genome;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CGPEvolution {

    /*
     * Default population size according to recommended CGP evolution strategy (1 + 4).
     */
    public static final int POPULATION_SIZE = 4;
    public static final double FITNESS_TOLERANCE = 0.001;
    private static Logger logger = LoggerFactory.getLogger(CGPEvolution.class);

    private CGPConfiguration config;
    private FitnessEvaluator evaluator;

    public CGPEvolution(CGPConfiguration config, FitnessEvaluator evaluator) {
        notNull(config, "CGPConfiguration shouldn't be null");
        notNull(evaluator, "FitnessEvaluator shouldn't be null");
        this.config = config;
        this.evaluator = evaluator;
    }

    public Genome evolve(int maxGenerations) {
        Genome parentGenome = init();
        double parentFitness = getSheepFitness(parentGenome);

        int generation = 0;
        while (generation < maxGenerations) {
            generation++;
            logger.debug(String.format("Creating generation %s...", generation));

            for (int i = 0; i < POPULATION_SIZE; i++) {
                Genome mutatedGenome = mutate(parentGenome);
                double mutatedFitness = getSheepFitness(mutatedGenome);

                if (compareFitness(mutatedFitness, parentFitness) >= 0) {
                    parentGenome = mutatedGenome;
                    parentFitness = mutatedFitness;
                }
            }

            logger.debug(String.format("Best fitness in generation = %s...", parentFitness));
        }

        logger.debug(String.format("Evolution ended with genome = %s (fitness %s), after %s generations", parentGenome.decode(),
                parentFitness, generation));
        return parentGenome;
    }

    protected Genome mutate(Genome genome) {
        // TODO
        return new GenomeFactory().createRandomGenome(config);
    }

    protected Genome init() {
        GenomeFactory factory = new GenomeFactory();
        Genome bestGenome = factory.createRandomGenome(config);
        double bestFitness = getSheepFitness(bestGenome);

        for (int i = 0; i < POPULATION_SIZE; i++) {
            Genome actGenome = factory.createRandomGenome(config);
            double actFitness = getSheepFitness(actGenome);

            if (actFitness > bestFitness) {
                bestGenome = actGenome;
                bestFitness = actFitness;
            }
        }

        return bestGenome;
    }

    protected double getSheepFitness(Genome genome) {
        String code = genome.decode();
        double fitness = evaluator.evaluateSheepFitness(code, FitnessEvaluator.RANDOM_MOVE);

        logger.debug(String.format("Evaluated sheep with fitness = %s, genome = %s", fitness, code));
        return fitness;
    }

    protected int compareFitness(double first, double second) {
        if (Math.abs(first - second) < FITNESS_TOLERANCE) {
            return 0;
        }
        return ((first < second) ? -1 : 1);
    }

}
