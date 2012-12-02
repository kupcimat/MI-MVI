package org.fit.cvut.mvi.cgp;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;

import java.util.ArrayList;
import java.util.List;

import org.fit.cvut.mvi.model.functions.Function;
import org.junit.Before;
import org.junit.Test;

public class GenomeFactoryTest {

    private static final int TEST_COUNT = 100;

    private CGPConfiguration config;
    private GenomeFactory factory;

    @Before
    public void setUp() throws Exception {
        // create mock functions
        Function function1 = mock(Function.class);
        Function function2 = mock(Function.class);
        Function input1 = mock(Function.class);
        Function input2 = mock(Function.class);

        // create configuration and genome factory
        List<Function> functions = new ArrayList<>();
        functions.add(function1);
        functions.add(function2);

        List<Function> inputs = new ArrayList<>();
        inputs.add(input1);
        inputs.add(input2);

        config = new CGPConfiguration.Builder().functions(functions).inputs(inputs).outputs(2).rows(2).columns(3).levelsBack(1).build();
        factory = new GenomeFactory();
    }

    @Test
    public void testCreateRandomGenome() {
        factory.createRandomGenome(config).decode();
    }

    @Test
    public void testRandomFunction() {
        factory.randomFunction(config);
    }

    @Test
    public void testRandomConnections() {
        List<Integer> list;

        for (int i = 0; i < TEST_COUNT; i++) {
            // column 0
            list = factory.randomConnections(config, 1, 0);
            assertTrue((list.get(0) == 0) || (list.get(0) == 1));

            // column 1
            list = factory.randomConnections(config, 1, 1);
            assertTrue((list.get(0) == 0) || (list.get(0) == 1) || (list.get(0) == 2) || (list.get(0) == 3));

            // column 2
            list = factory.randomConnections(config, 1, 2);
            assertTrue((list.get(0) == 0) || (list.get(0) == 1) || (list.get(0) == 4) || (list.get(0) == 5));
        }
    }

    @Test
    public void testRandomOutputs() {
        List<Integer> list;

        for (int i = 0; i < TEST_COUNT; i++) {
            // check all outputs
            for (int j = 0; j < 2; j++) {
                list = factory.randomOutputs(config);
                assertTrue((list.get(j) >= 0) && (list.get(j) <= 7));
            }
        }
    }

}
