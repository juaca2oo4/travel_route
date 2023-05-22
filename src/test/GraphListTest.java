package test;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.jupiter.api.Test;

import model.*;

public class GraphListTest {

    GraphList<String> graph;

    public void scenery() {

        graph = new GraphList<>();

        Vertex<String> v1 = new Vertex<>("A");
        Vertex<String> v2 = new Vertex<>("B");
        Vertex<String> v3 = new Vertex<>("C");
        Vertex<String> v4 = new Vertex<>("D");
        Vertex<String> v5 = new Vertex<>("E");
        graph.addVertex(v1);
        graph.addVertex(v2);
        graph.addVertex(v3);
        graph.addVertex(v4);
        graph.addVertex(v5);
    }

    public void scenery2() {

        graph = new GraphList<>();

        Vertex<String> v1 = new Vertex<>("A");
        Vertex<String> v2 = new Vertex<>("B");
        Vertex<String> v3 = new Vertex<>("C");
        Vertex<String> v4 = new Vertex<>("D");
        Vertex<String> v5 = new Vertex<>("E");
        graph.addVertex(v1);
        graph.addVertex(v2);
        graph.addVertex(v3);
        graph.addVertex(v4);
        graph.addVertex(v5);

        graph.addEdge(v1, v2, 10, 20);
        graph.addEdge(v2, v3, 15, 25);
        graph.addEdge(v2, v4, 5, 10);
        graph.addEdge(v4, v5, 8, 15);
    }

    @Test
    void testAddVertex() {
        scenery();
        Vertex<String> v1 = new Vertex<>("H");
        assertTrue(graph.addVertex(v1));
    }

    @Test
    void testAddVertex2() {
        scenery();
        Vertex<String> v1 = new Vertex<>("B");
        assertFalse(graph.addVertex(v1));
    }

    @Test
    void testAddVertex3() {
        scenery();
        Vertex<String> v1 = new Vertex<>("B");
        Vertex<String> v2 = new Vertex<>("A");
        assertFalse(graph.addVertex(v1));
        assertFalse(graph.addVertex(v2));
    }

    @Test
    void testAddEdge() {
        scenery();
        Vertex<String> v1 = new Vertex<>("H");
        Vertex<String> v2 = new Vertex<>("I");
        graph.addVertex(v1);
        graph.addVertex(v2);
        graph.addEdge(v1, v2, 20, 22);

        assertTrue(graph.getAdjacencyList().get(v1).get(0).getDestination().equals(v2));
    }

    @Test
    void testAddEdge2() {
        scenery();
        Vertex<String> v1 = new Vertex<>("H");
        Vertex<String> v2 = new Vertex<>("I");
        graph.addVertex(v1);
        try {
            graph.addEdge(v1, v2, 10, 20);
            assert false : "Se agregó una arista con un vértice de destino no válido";
        } catch (IllegalArgumentException e) {
            // Se espera que se lance una excepción IllegalArgumentException
        }

    }

    @Test
    void testAddEdge3() {
        scenery();
        Vertex<String> v1 = new Vertex<>("H");
        Vertex<String> v2 = new Vertex<>("I");
        try {
            graph.addEdge(v1, v2, 10, 20);
            assert false : "Se agregó una arista con un vértice de destino no válido";
        } catch (IllegalArgumentException e) {
            // Se espera que se lance una excepción IllegalArgumentException
        }
    }

    @Test
    void testBfs() {

    }

    @Test
    void testBfs2() {

    }

    @Test
    void testBfs3() {

    }

    @Test
    void testDijkstra() {

    }

    @Test
    void testDijkstra2() {

    }

    @Test
    void testDijkstra3() {

    }
}
