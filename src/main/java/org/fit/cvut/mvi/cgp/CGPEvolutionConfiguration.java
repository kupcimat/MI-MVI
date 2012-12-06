package org.fit.cvut.mvi.cgp;

public class CGPEvolutionConfiguration {

    private final int populationSize;
    private final int mutations;
    private final int generations;

    public CGPEvolutionConfiguration(int populationSize, int mutations, int generations) {
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

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("CGPEvolutionConfiguration [populationSize=");
        builder.append(populationSize);
        builder.append(", mutations=");
        builder.append(mutations);
        builder.append(", generations=");
        builder.append(generations);
        builder.append("]");

        return builder.toString();
    }

    public static class Builder {

        private int populationSize;
        private int mutations;
        private int generations;

        public Builder populationSize(int populationSize) {
            this.populationSize = populationSize;
            return this;
        }

        public Builder mutations(int mutations) {
            this.mutations = mutations;
            return this;
        }

        public Builder generations(int generations) {
            this.generations = generations;
            return this;
        }

        public CGPEvolutionConfiguration build() {
            return new CGPEvolutionConfiguration(populationSize, mutations, generations);
        }

    }

}