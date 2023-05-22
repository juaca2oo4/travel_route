package test;

import static org.junit.Assert.*;

import java.util.*;

import org.junit.jupiter.api.Test;

import model.GraphMatriz;
import model.Vertex;

public class GraphMatrizTest {
    GraphMatriz<String> graph;

    public void scenery() {

        graph = new  GraphMatriz<>(7);

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
        graph = new  GraphMatriz<>(5);
        Vertex<String> v1 = new Vertex<>("H");
        Vertex<String> v2 = new Vertex<>("I");
        graph.addVertex(v1);
        graph.addVertex(v2);
        graph.addEdge(v1, v2, 20, 22);

        assertEquals(graph.getAdjacencyMatrix()[0][1].getCost(),20);
    }

    @Test
    void testAddEdge2() {
        graph = new  GraphMatriz<>(5);
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
        graph = new  GraphMatriz<>(5);
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
    void testDijkstra() {
        graph = new  GraphMatriz<>(5);

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

        assertEquals(graph.dijkstra(v1, v5, "cost").get(0),v1);
        assertEquals(graph.dijkstra(v1, v5, "cost").get(1),v2);
        assertEquals(graph.dijkstra(v1, v5, "cost").get(2),v4);
        assertEquals(graph.dijkstra(v1, v5, "cost").get(3),v5);

    }

    @Test
    void testDijkstra2() {
        graph = new  GraphMatriz<>(5);

        Vertex<String> v1 = new Vertex<>("A");
        Vertex<String> v2 = new Vertex<>("B");
        Vertex<String> v3 = new Vertex<>("C");
        Vertex<String> v4 = new Vertex<>("D");
        Vertex<String> v5 = new Vertex<>("E");
        Vertex<String> v6 = new Vertex<>("H");
        graph.addVertex(v1);
        graph.addVertex(v2);
        graph.addVertex(v3);
        graph.addVertex(v4);
        graph.addVertex(v5);

        graph.addEdge(v1, v2, 10, 20);
        graph.addEdge(v2, v3, 15, 25);
        graph.addEdge(v2, v4, 5, 10);
        graph.addEdge(v4, v5, 8, 15);

        assertEquals(graph.dijkstra(v1,v6,"cost"),null);

    }

    @Test
    void testDijkstra3() {
        graph = new  GraphMatriz<>(5);
        Vertex<String> v1 = new Vertex<>("A");
        Vertex<String> v2 = new Vertex<>("B");
        Vertex<String> v3 = new Vertex<>("C");
        Vertex<String> v4 = new Vertex<>("D");
        Vertex<String> v5 = new Vertex<>("E");
        Vertex<String> v6 = new Vertex<>("H");
        graph.addVertex(v1);
        graph.addVertex(v2);
        graph.addVertex(v3);
        graph.addVertex(v4);
        graph.addVertex(v5);

        graph.addEdge(v1, v2, 10, 20);
        graph.addEdge(v2, v3, 15, 25);
        graph.addEdge(v2, v4, 5, 10);
        graph.addEdge(v4, v5, 8, 15);

        assertEquals(graph.dijkstra(v6,v1,"cost"),null);
    }

        @Test
    void testBfs() {
        graph = new  GraphMatriz<>(5);
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

        List<Vertex<String>> x= graph.dijkstra(v4,v1,"cost");

        assertEquals(graph.bfs(x, "cost"),15);
    }

    @Test
    void testBfs2() {
        graph = new  GraphMatriz<>(5);

        Vertex<String> v1 = new Vertex<>("A");
        Vertex<String> v2 = new Vertex<>("B");
        Vertex<String> v3 = new Vertex<>("C");
        Vertex<String> v4 = new Vertex<>("D");
        Vertex<String> v5 = new Vertex<>("E");
        Vertex<String> v6 = new Vertex<>("H");
        graph.addVertex(v1);
        graph.addVertex(v2);
        graph.addVertex(v3);
        graph.addVertex(v4);
        graph.addVertex(v5);

        graph.addEdge(v1, v2, 10, 20);
        graph.addEdge(v2, v3, 15, 25);
        graph.addEdge(v2, v4, 5, 10);
        graph.addEdge(v4, v5, 8, 15);
        List<Vertex<String>> x= graph.dijkstra(v1,v6,"cost");

        assertEquals(graph.bfs(x, "cost"),-1);

    }

    @Test
    void testBfs3() {

        graph = new  GraphMatriz<>(5);

        Vertex<String> v1 = new Vertex<>("A");
        Vertex<String> v2 = new Vertex<>("B");
        Vertex<String> v3 = new Vertex<>("C");
        Vertex<String> v4 = new Vertex<>("D");
        Vertex<String> v5 = new Vertex<>("E");
        Vertex<String> v6 = new Vertex<>("H");
        graph.addVertex(v1);
        graph.addVertex(v2);
        graph.addVertex(v3);
        graph.addVertex(v4);
    

        graph.addEdge(v1, v2, 10, 20);
        graph.addEdge(v2, v3, 15, 25);
        graph.addEdge(v2, v4, 5, 10);
        List<Vertex<String>> x= graph.dijkstra(v5,v6,"cost");
        assertEquals(graph.bfs(x, "cost"),-1);
    }
}
