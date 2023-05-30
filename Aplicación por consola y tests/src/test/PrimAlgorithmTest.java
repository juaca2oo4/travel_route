package test;

import model.Other_Implementations.Prim.*;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

public class PrimAlgorithmTest {
    /*-----------------Tests Prim lista adyacencia--------------*/
    @Test
    public void testListMinimumSpanningTree() {
        // Arrange
        Prim_GraphList<Integer> graph = new Prim_GraphList<>();

        Prim_Vertex<Integer> vertex0 = new Prim_Vertex<>(0);
        Prim_Vertex<Integer> vertex1 = new Prim_Vertex<>(1);
        Prim_Vertex<Integer> vertex2 = new Prim_Vertex<>(2);
    
        graph.addVertex(vertex0);
        graph.addVertex(vertex1);
        graph.addVertex(vertex2);

    
        graph.addEdge(vertex0, vertex1, 8);
        graph.addEdge(vertex1, vertex2, 10);
        graph.addEdge(vertex2, vertex0, 12);

        // Act
        PrimAlgorithm_List<Integer> primAlgorithm = new PrimAlgorithm_List<>();
        List<Prim_Edge<Integer>> minimumSpanningTree = primAlgorithm.findMinimumSpanningTree(graph);


        List<Prim_Edge<Integer>> expectedEdges = new ArrayList<>();
        expectedEdges.add(new Prim_Edge<>(vertex1, vertex2, 10));
        expectedEdges.add(new Prim_Edge<>(vertex1, vertex0, 8));

        //Assert
        assertEquals(expectedEdges.size(), minimumSpanningTree.size());
        for (Prim_Edge<Integer> expectedEdge : expectedEdges) {
            assertTrue(minimumSpanningTree.contains(expectedEdge));
        }
    }

    @Test
    public void testListEmptyGraph() {
        // Arrange
        Prim_GraphList<String> graph = new Prim_GraphList<>();

        // Act
        PrimAlgorithm_List<String> primAlgorithm = new PrimAlgorithm_List<>();
        List<Prim_Edge<String>> minimumSpanningTree = primAlgorithm.findMinimumSpanningTree(graph);

        // Assert
        assertEquals(0, minimumSpanningTree.size());
    }

    @Test
    public void testListSingleVertex() {
        // Arrange
        Prim_GraphList<Integer> graph = new Prim_GraphList<>();

        Prim_Vertex<Integer> vertex = new Prim_Vertex<>(0);
        graph.addVertex(vertex);

        // Act
        PrimAlgorithm_List<Integer> primAlgorithm = new PrimAlgorithm_List<>();
        List<Prim_Edge<Integer>> minimumSpanningTree = primAlgorithm.findMinimumSpanningTree(graph);

        // Assert
        assertEquals(0, minimumSpanningTree.size());
    }
    /*Aplicamos nuevamente los casos de prueba pero esta vez sobre matriz*/
    /*-----------------Tests Prim matriz adyacencia--------------*/
    @Test
    public void testMatrixSingleVertexGraph() {
        //Arrange
        Prim_GraphMatrix<String> graph = new Prim_GraphMatrix<>();

        Prim_Vertex<String> vertexA = new Prim_Vertex<>("A");

        graph.addVertex(vertexA);

        //Act
        PrimAlgorithm_Matrix<String> prim = new PrimAlgorithm_Matrix<>();
        List<Prim_Edge<String>> minimumSpanningTree = prim.findMinimumSpanningTree(graph);
        //Assert
        assertEquals(0, minimumSpanningTree.size());
    }
    @Test
    public void testMatrixEmptyGraph() {
        //Arrange
        Prim_GraphMatrix<Integer> graph = new Prim_GraphMatrix<>();
        //Act
        PrimAlgorithm_Matrix<Integer> prim = new PrimAlgorithm_Matrix<>();
        List<Prim_Edge<Integer>> minimumSpanningTree = prim.findMinimumSpanningTree(graph);
        //Assert
        assertEquals(minimumSpanningTree.size(),0);
    }
    @Test
    public void testMatrixMinimumSpanningTree() {
        //Arrange
        Prim_GraphMatrix<String> graph = new Prim_GraphMatrix<>();

        Prim_Vertex<String> vertexA = new Prim_Vertex<>("A");
        Prim_Vertex<String> vertexB = new Prim_Vertex<>("B");
        Prim_Vertex<String> vertexC = new Prim_Vertex<>("C");
        Prim_Vertex<String> vertexD = new Prim_Vertex<>("D");

        graph.addVertex(vertexA);
        graph.addVertex(vertexB);
        graph.addVertex(vertexC);
        graph.addVertex(vertexD);

        graph.addEdge(vertexA, vertexB, 5);
        graph.addEdge(vertexA, vertexC, 3);
        graph.addEdge(vertexB, vertexC, 1);
        graph.addEdge(vertexB, vertexD, 4);
        graph.addEdge(vertexC, vertexD, 2);

        //Act
        PrimAlgorithm_Matrix<String> prim = new PrimAlgorithm_Matrix<>();
        List<Prim_Edge<String>> minimumSpanningTree = prim.findMinimumSpanningTree(graph);
        //MinimumSpanningTree me retorna objeto de tipo arista, por lo que hago un for para obtener su data y a eso le hago assert
        ArrayList<Integer> costosAristas= new ArrayList<>();
        //Assert
        assertEquals(3, minimumSpanningTree.size());
        for (int i = 0; i < minimumSpanningTree.size(); i++) {
            costosAristas.add(minimumSpanningTree.get(i).getCost());
        }
        assertTrue(costosAristas.contains(1));
        assertTrue(costosAristas.contains(2));
        assertTrue(costosAristas.contains(3));
    }
}
