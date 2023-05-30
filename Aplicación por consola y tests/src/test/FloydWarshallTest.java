package test;

import model.Other_Implementations.Floyd_Warshall.*;
import org.junit.Test;

import static org.junit.Assert.*;
public class FloydWarshallTest {

    /*-----------------Tests Floyd-Warshall lista adyacencia--------------*/
    @Test
    public void testFloydWarshallList() {
        //Arrange
        FloydAlgorithm_GraphList<Integer> graph = new FloydAlgorithm_GraphList<>();

        FloydWarshall_Vertex<Integer> vertex1 = new FloydWarshall_Vertex<>(1);
        FloydWarshall_Vertex<Integer> vertex2 = new FloydWarshall_Vertex<>(2);
        FloydWarshall_Vertex<Integer> vertex3 = new FloydWarshall_Vertex<>(3);
        FloydWarshall_Vertex<Integer> vertex4 = new FloydWarshall_Vertex<>(4);

        graph.addVertex(vertex1);
        graph.addVertex(vertex2);
        graph.addVertex(vertex3);
        graph.addVertex(vertex4);

        graph.addEdge(vertex1, vertex2, 1);
        graph.addEdge(vertex1, vertex3, 3);
        graph.addEdge(vertex2, vertex3, 1);
        graph.addEdge(vertex3, vertex4, 2);

        //Act
        FloydWarshallAlgorithm_List<Integer> floydWarshall = new FloydWarshallAlgorithm_List<>();
        int[][] distances = floydWarshall.floydWarshall(graph);

        // Assert
        assertEquals(0, distances[graph.getVertexIndex(vertex1)][graph.getVertexIndex(vertex1)]);
        assertEquals(1, distances[graph.getVertexIndex(vertex1)][graph.getVertexIndex(vertex2)]);
        assertEquals(2, distances[graph.getVertexIndex(vertex1)][graph.getVertexIndex(vertex3)]);
        assertEquals(4, distances[graph.getVertexIndex(vertex1)][graph.getVertexIndex(vertex4)]);
    }
    @Test
    public void testListFloydWarshallDisconnectedGraph() {
        //Arrange
        FloydAlgorithm_GraphList<Integer> graph = new FloydAlgorithm_GraphList<>();

        FloydWarshall_Vertex<Integer> vertex1 = new FloydWarshall_Vertex<>(1);
        FloydWarshall_Vertex<Integer> vertex2 = new FloydWarshall_Vertex<>(2);
        FloydWarshall_Vertex<Integer> vertex3 = new FloydWarshall_Vertex<>(3);
        FloydWarshall_Vertex<Integer> vertex4 = new FloydWarshall_Vertex<>(4);

        graph.addVertex(vertex1);
        graph.addVertex(vertex2);
        graph.addVertex(vertex3);
        graph.addVertex(vertex4);

        graph.addEdge(vertex1, vertex2, 1);
        graph.addEdge(vertex3, vertex4, 2);

        FloydWarshallAlgorithm_List<Integer> floydWarshall = new FloydWarshallAlgorithm_List<>();
        int[][] distances = floydWarshall.floydWarshall(graph);

        // Verificar las distancias entre todos los pares de vértices
        assertEquals(0, distances[graph.getVertexIndex(vertex1)][graph.getVertexIndex(vertex1)]);
        assertEquals(1, distances[graph.getVertexIndex(vertex1)][graph.getVertexIndex(vertex2)]);
        assertEquals(Integer.MAX_VALUE, distances[graph.getVertexIndex(vertex1)][graph.getVertexIndex(vertex3)]);
        assertEquals(Integer.MAX_VALUE, distances[graph.getVertexIndex(vertex1)][graph.getVertexIndex(vertex4)]);
    }
    @Test
    public void testFloydWarshall_SingleVertexGraph() {
        //Arrange
        FloydAlgorithm_GraphList<Integer> graph = new FloydAlgorithm_GraphList<>();

        FloydWarshall_Vertex<Integer> vertex = new FloydWarshall_Vertex<>(1);

        graph.addVertex(vertex);

        //Act
        FloydWarshallAlgorithm_List<Integer> floydWarshall = new FloydWarshallAlgorithm_List<>();
        int[][] distances = floydWarshall.floydWarshall(graph);

        // Assert
        assertEquals(0, distances[graph.getVertexIndex(vertex)][graph.getVertexIndex(vertex)]);
    }

    /*-----------------Tests Floyd-Warshall matriz adyacencia--------------*/
    @Test
    public void testMatrixShortestPath() {
        FloydAlgorithm_GraphMatrix<String> graph = new FloydAlgorithm_GraphMatrix<>();

        //Arrange
        FloydWarshall_Vertex<String> vertexA = new FloydWarshall_Vertex<>("A");
        FloydWarshall_Vertex<String> vertexB = new FloydWarshall_Vertex<>("B");
        FloydWarshall_Vertex<String> vertexC = new FloydWarshall_Vertex<>("C");
        FloydWarshall_Vertex<String> vertexD = new FloydWarshall_Vertex<>("D");

        graph.addVertex(vertexA);
        graph.addVertex(vertexB);
        graph.addVertex(vertexC);
        graph.addVertex(vertexD);

        graph.addEdge(vertexA, vertexB, 3);
        graph.addEdge(vertexA, vertexC, 2);
        graph.addEdge(vertexB, vertexC, 1);
        graph.addEdge(vertexB, vertexD, 6);
        graph.addEdge(vertexC, vertexD, 4);

        //Act
        FloydWarshallAlgorithm_Matrix<String> floydWarshall = new FloydWarshallAlgorithm_Matrix<>();
        int[][] shortestPaths = floydWarshall.floydWarshall(graph);

        //Assert
        assertEquals(6, shortestPaths[graph.getVertexIndex(vertexA)][graph.getVertexIndex(vertexD)]);
        assertEquals(2, shortestPaths[graph.getVertexIndex(vertexA)][graph.getVertexIndex(vertexC)]);
        assertEquals(3, shortestPaths[graph.getVertexIndex(vertexA)][graph.getVertexIndex(vertexB)]);
        assertEquals(5, shortestPaths[graph.getVertexIndex(vertexB)][graph.getVertexIndex(vertexD)]);
    }
    @Test
    public void testMatrixFloydWarshallDisconnectedGraph() {

        //Arrange
        FloydAlgorithm_GraphMatrix<String> graph = new FloydAlgorithm_GraphMatrix<>();

        FloydWarshall_Vertex<String> vertexA = new FloydWarshall_Vertex<>("A");
        FloydWarshall_Vertex<String> vertexB = new FloydWarshall_Vertex<>("B");
        FloydWarshall_Vertex<String> vertexC = new FloydWarshall_Vertex<>("C");
        FloydWarshall_Vertex<String> vertexD = new FloydWarshall_Vertex<>("D");

        graph.addVertex(vertexA);
        graph.addVertex(vertexB);
        graph.addVertex(vertexC);
        graph.addVertex(vertexD);

        //Act
        FloydWarshallAlgorithm_Matrix<String> floydWarshall = new FloydWarshallAlgorithm_Matrix<>();
        int[][] distances = floydWarshall.floydWarshall(graph);

        //Assert
        assertEquals(0, distances[graph.getVertexIndex(vertexA)][graph.getVertexIndex(vertexA)]);
        assertEquals(Integer.MAX_VALUE, distances[graph.getVertexIndex(vertexA)][graph.getVertexIndex(vertexB)]);
        assertEquals(Integer.MAX_VALUE, distances[graph.getVertexIndex(vertexA)][graph.getVertexIndex(vertexC)]);
        assertEquals(Integer.MAX_VALUE, distances[graph.getVertexIndex(vertexA)][graph.getVertexIndex(vertexD)]);
        assertEquals(Integer.MAX_VALUE, distances[graph.getVertexIndex(vertexB)][graph.getVertexIndex(vertexA)]);
    }

    @Test
    public void testMatrixFloydWarshallAlgorithmSingleVertex() {
        //Arrange
        FloydAlgorithm_GraphMatrix<Integer> graph = new FloydAlgorithm_GraphMatrix<>();

        FloydWarshall_Vertex<Integer> vertex1 = new FloydWarshall_Vertex<>(1);
        graph.addVertex(vertex1);

        // Act
        FloydWarshallAlgorithm_Matrix<Integer> floydWarshall = new FloydWarshallAlgorithm_Matrix<>();
        int[][] distances = floydWarshall.floydWarshall(graph);

        // Assert
        assertEquals(0, distances[graph.getVertexIndex(vertex1)][graph.getVertexIndex(vertex1)]);
    }
}