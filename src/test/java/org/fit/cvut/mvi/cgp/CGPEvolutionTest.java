package org.fit.cvut.mvi.cgp;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.mock;

import org.fit.cvut.mvi.evaluator.FitnessEvaluator;
import org.junit.Before;
import org.junit.Test;

public class CGPEvolutionTest {

    private CGPEvolution evolution;

    @Before
    public void setUp() throws Exception {
        // Configure mocks
        CGPConfiguration mockConfig = mock(CGPConfiguration.class);
        FitnessEvaluator mockEvaluator = mock(FitnessEvaluator.class);

        evolution = new CGPEvolution(mockConfig, mockEvaluator, "sheep");
    }

    @Test
    public void testEvolve() {
        fail("Not yet implemented");
    }

    @Test
    public void testMutate() {
        fail("Not yet implemented");
    }

    @Test
    public void testGetSheepFitness() {
        fail("Not yet implemented");
    }

    @Test
    public void testCompareFitness() {
        assertEquals(-1, evolution.compareFitness(12.0, 13.0));
        assertEquals(1, evolution.compareFitness(13.0, 12.0));
        assertEquals(0, evolution.compareFitness(13.0, 13.0 + CGPEvolution.FITNESS_TOLERANCE / 2));
        assertEquals(-1, evolution.compareFitness(13.0, 13.0 + CGPEvolution.FITNESS_TOLERANCE * 2));
    }

    @Test
    public void testInit() {
        fail("Not yet implemented");
    }

}
