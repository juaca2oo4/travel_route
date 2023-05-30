package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import model.Other_Implementations.Kruskal.*;
import org.junit.Test;

public class KruskalAlgorithmTest {

    /*-----------------Tests Kruskal lista adyacencia--------------*/
    @Test
    public void testListMinimumSpanningTree() {

        //Arrange
        Kruskal_GraphList<Integer> graph = new Kruskal_GraphList<>();
    
        Kruskal_Vertex<Integer> vertex0 = new Kruskal_Vertex<>(0);
        Kruskal_Vertex<Integer> vertex1 = new Kruskal_Vertex<>(1);
        Kruskal_Vertex<Integer> vertex2 = new Kruskal_Vertex<>(2);
    
        graph.addVertex(vertex0);
        graph.addVertex(vertex1);
        graph.addVertex(vertex2);

    
        graph.addEdge(vertex0, vertex1, 8);
        graph.addEdge(vertex1, vertex2, 10);
        graph.addEdge(vertex2, vertex0, 12);

        //Act
        KruskalAlgorithm_List<Integer> kruskalAlgorithm = new KruskalAlgorithm_List<>();
        List<Kruskal_Edge<Integer>> minimumSpanningTree = kruskalAlgorithm.findMinimumSpanningTree(graph);

        Set<Kruskal_Edge<Integer>> minimumSpanningTreeSet = new HashSet<>(minimumSpanningTree);
    
        List<Kruskal_Edge<Integer>> expectedEdges = new ArrayList<>();
        expectedEdges.add(new Kruskal_Edge<>(vertex1, vertex2, 10));
        expectedEdges.add(new Kruskal_Edge<>(vertex1, vertex0, 8));

        //Assert
        assertEquals(expectedEdges.size(), minimumSpanningTree.size());
       for (Kruskal_Edge<Integer> expectedEdge : expectedEdges) {
            assertTrue(minimumSpanningTreeSet.contains(expectedEdge));
        }
    }
    
    @Test
    public void testEmptyGraph() {
        // Assert
        Kruskal_GraphList<String> graph = new Kruskal_GraphList<>();

        // Act
        KruskalAlgorithm_List<String> kruskalAlgorithm = new KruskalAlgorithm_List<>();
        List<Kruskal_Edge<String>> minimumSpanningTree = kruskalAlgorithm.findMinimumSpanningTree(graph);

        // Assert
        assertEquals(0, minimumSpanningTree.size());
    }
    @Test
    public void testSingleVertex() {
        //Arrange
        Kruskal_GraphList<Integer> graph = new Kruskal_GraphList<>();

        Kruskal_Vertex<Integer> vertex = new Kruskal_Vertex<>(0);
        graph.addVertex(vertex);
        //Act
        KruskalAlgorithm_List<Integer> kruskalAlgorithm = new KruskalAlgorithm_List<>();
        List<Kruskal_Edge<Integer>> minimumSpanningTree = kruskalAlgorithm.findMinimumSpanningTree(graph);

        // Assert
        assertEquals(0, minimumSpanningTree.size());
    }

    /*Aplicamos nuevamente los casos de prueba pero esta vez sobre matriz*/
    /*-----------------Tests Kruskal Matriz adyacencia--------------*/
    @Test
    public void testMatrixFindMinimumSpanningTree() {
        //Arrange
        Kruskal_GraphMatrix<Integer> graph1 = new Kruskal_GraphMatrix<>();
        Kruskal_Vertex<Integer> vertexA1 = new Kruskal_Vertex<>(0);
        Kruskal_Vertex<Integer> vertexB1 = new Kruskal_Vertex<>(1);
        Kruskal_Vertex<Integer> vertexC1 = new Kruskal_Vertex<>(2);
        Kruskal_Vertex<Integer> vertexD1 = new Kruskal_Vertex<>(3);

        graph1.addVertex(vertexA1);
        graph1.addVertex(vertexB1);
        graph1.addVertex(vertexC1);
        graph1.addVertex(vertexD1);

        graph1.addEdge(vertexA1, vertexB1, 1);
        graph1.addEdge(vertexA1, vertexC1, 2);
        graph1.addEdge(vertexB1, vertexC1, 3);
        graph1.addEdge(vertexB1, vertexD1, 4);
        graph1.addEdge(vertexC1, vertexD1, 5);

        //Act and assert
        KruskalAlgorithm_Matrix<Integer> kruskal1 = new KruskalAlgorithm_Matrix<>();
        List<Kruskal_Edge<Integer>> minimumSpanningTree1 = kruskal1.findMinimumSpanningTree(graph1);

        assertEquals(3, minimumSpanningTree1.size());
    }
    @Test
    public void Matrix_SingleVertex() {
        //Arrange
        Kruskal_GraphMatrix<Integer> graph = new Kruskal_GraphMatrix<>();
        Kruskal_Vertex<Integer> vertexA = new Kruskal_Vertex<>( 0);

        graph.addVertex(vertexA);
        //Act
        KruskalAlgorithm_Matrix<Integer> kruskal = new KruskalAlgorithm_Matrix<>();
        List<Kruskal_Edge<Integer>> minimumSpanningTree = kruskal.findMinimumSpanningTree(graph);
        //Assert
        assertEquals(0, minimumSpanningTree.size());
    }
    @Test
    public void testFindMinimumSpanningTree_EmptyGraph() {
        //Act
        Kruskal_GraphMatrix<Integer> graph = new Kruskal_GraphMatrix<>();

        //Act and Assert
        KruskalAlgorithm_Matrix<Integer> kruskal = new KruskalAlgorithm_Matrix<>();
        List<Kruskal_Edge<Integer>> minimumSpanningTree = kruskal.findMinimumSpanningTree(graph);

        assertEquals(0, minimumSpanningTree.size());
    }
}

