package org.fit.cvut.mvi.cgp;

import static org.apache.commons.lang.Validate.notNull;

import org.fit.cvut.mvi.evaluator.FitnessEvaluator;
import org.fit.cvut.mvi.model.Genome;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CGPEvolution {

    private static Logger logger = LoggerFactory.getLogger(CGPEvolution.class);

    /*
     * Represents tolerance which is used when comparing 2 fitness values.
     */
    public static final double FITNESS_TOLERANCE = 0.001;

    private CGPConfiguration config;
    private FitnessEvaluator evaluator;

    public CGPEvolution(CGPConfiguration config, FitnessEvaluator evaluator) {
        notNull(config, "CGPConfiguration shouldn't be null");
        notNull(evaluator, "FitnessEvaluator shouldn't be null");
        this.config = config;
        this.evaluator = evaluator;
    }

    public Genome evolve(Configuration evolutionConfig) {
        notNull(evolutionConfig, "Evolution configuration shouldn't be null");

        Genome parentGenome = init(evolutionConfig.populationSize());
        double parentFitness = getSheepFitness(parentGenome);

        int generation = 0;
        while (generation < evolutionConfig.generations()) {
            generation++;
            logger.debug(String.format("Creating generation %s...", generation));

            for (int i = 0; i < evolutionConfig.populationSize(); i++) {
                Genome mutatedGenome = parentGenome.mutate(config, evolutionConfig.mutations());
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

    protected Genome init(int populationSize) {
        GenomeFactory factory = new GenomeFactory();
        Genome bestGenome = factory.createRandomGenome(config);
        double bestFitness = getSheepFitness(bestGenome);

        for (int i = 0; i < populationSize; i++) {
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

    public static class Configuration {

        private final int populationSize;
        private final int mutations;
        private final int generations;

        public Configuration(int populationSize, int mutations, int generations) {
            this.populationSize = populationSize;
            this.mutations = mutations;
            this.generations = generations;
        }

        public int populationSize() {
            return populationSize;
        }

        public int mutations() {
            return mutations;
        }

        public int generations() {
            return generations;
        }

    }

}
