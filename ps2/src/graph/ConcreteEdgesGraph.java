/*
 * Copyright (c) 2015-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package graph;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

/**
 * Tests for ConcreteEdgesGraph.
 *
 * This class runs the GraphInstanceTest tests against ConcreteEdgesGraph, as
 * well as tests for that particular implementation.
 *
 * Tests against the Graph spec should be in GraphInstanceTest.
 */
public class ConcreteEdgesGraphTest extends GraphInstanceTest {

    private static final String VERTEX_1 = "v1";
    private static final String VERTEX_2 = "v2";
    private static final String VERTEX_3 = "v3";

    private static final int WEIGHT_1 = 1;
    private static final int WEIGHT_2 = 2;

    /*
     * Provide a ConcreteEdgesGraph for tests in GraphInstanceTest.
     */
    @Override
    public Graph<String> emptyInstance() {
        return new ConcreteEdgesGraph();
    }

    /*
     * Testing ConcreteEdgesGraph...
     */

    // Testing strategy for ConcreteEdgesGraph.toString()
    // num vertices = 0, 1, n
    // num edges = 0, 1, n

    // TODO tests for ConcreteEdgesGraph.toString()

    // covers num vertices = 0
    // num edges = 0
    @Test
    public void testToStringZeroVerticesZeroEdges() {
        Graph<String> graph = emptyInstance();
        String result = "";

        assertEquals("expected empty string", result, graph.toString());
    }

    // covers num vertices = 1
    @Test
    public void testToStringOneVertex() {
        Graph<String> graph = emptyInstance();
        graph.add(VERTEX_1);
        String result = "";

        assertEquals("expected string", result, graph.toString());
    }

    // covers num vertices = n
    // num edges = 1
    @Test
    public void testToStringNVerticesOneEdge() {
        Graph<String> graph = emptyInstance();
        graph.add(VERTEX_1);
        graph.add(VERTEX_2);
        graph.set(VERTEX_1, VERTEX_2, WEIGHT_1);

        String result = "v1->v2(weight = 1)\n";

        assertEquals("expected string", result, graph.toString());
    }

    // covers num edges = n
    @Test
    public void testToStringNEdges() {
        Graph<String> graph = emptyInstance();
        graph.add(VERTEX_1);
        graph.add(VERTEX_2);
        graph.add(VERTEX_3);
        graph.set(VERTEX_1, VERTEX_2, WEIGHT_1);
        graph.set(VERTEX_1, VERTEX_3, WEIGHT_2);

        String expected = "v1->v2(weight = 1)\nv1->v3(weight = 2)\n";

        assertEquals("expected string", expected, graph.toString());
    }

    /*
     * Testing Edge...
     */

    // Testing strategy for Edge
    // test getters
    // test toString

    // TODO tests for operations of Edge
    // test getters
    @Test
    public void testGet() {
        Edge edge = new Edge(VERTEX_1, VERTEX_2, WEIGHT_1);

        assertEquals("expected string 'v2'", edge.getTarget(), "v2");
        assertEquals("expected string 'v1'", edge.getSource(), "v1");
    }

    // test toString
    @Test
    public void testToString() {
        Edge edge = new Edge(VERTEX_1, VERTEX_2, WEIGHT_1);
        String expected = "v1->v2(weight = 1)";

        assertEquals("expected string", expected, edge.toString());
    }
}
