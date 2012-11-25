package org.fit.cvut.mvi.model;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.fit.cvut.mvi.model.functions.Function;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class GenomeTest {

    @Mock
    private InputNode inputNode;
    @Mock
    private InnerNode innerNode;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        // prepare mock functions
        Function inputFunction = mock(Function.class);
        Function innerFunction = mock(Function.class);
        when(inputFunction.code(new ArrayList<String>())).thenReturn("input_value");
        when(innerFunction.code(Arrays.asList("input_value", "input_value"))).thenReturn("(input_value op input_value)");

        // prepare mock nodes
        when(inputNode.getFunction()).thenReturn(inputFunction);
        when(inputNode.getConnections()).thenReturn(new ArrayList<Integer>());
        when(innerNode.getFunction()).thenReturn(innerFunction);
        when(innerNode.getConnections()).thenReturn(Arrays.asList(0, 1));
    }

    @Test
    public void testDecode() {
        List<Node> nodes = new ArrayList<>();
        List<Integer> outputs = new ArrayList<>();

        nodes.add(inputNode);
        nodes.add(inputNode);
        nodes.add(innerNode);
        outputs.add(2);

        Genome g = new Genome(nodes, outputs);
        assertEquals("report list (input_value op input_value)", g.decode());
    }

}
