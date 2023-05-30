package test;

import model.Other_Implementations.DFS.DFS_List_Adjacency;
import model.Other_Implementations.DFS.DFS_Matrix_Adjacency;
import model.Other_Implementations.DFS.DFS_vertex;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class DFSTest {

    /*-----------------Tests DFS Lista adyacencia--------------*/

    /*--------------------Tests DFS (Recorrido)-------------*/
    @Test
    public void testRouteDFS() {
        DFS_List_Adjacency<Integer> dfs = new DFS_List_Adjacency<>();
        // Arrange (Agrego vertices y aristas)
        dfs.addVertex(1);
        dfs.addVertex(2);
        dfs.addVertex(3);
        dfs.addVertex(4);
        dfs.addVertex(5);
        dfs.addVertex(6);

        dfs.addEdge(1, 2);
        dfs.addEdge(1, 3);
        dfs.addEdge(2, 4);
        dfs.addEdge(2, 5);
        dfs.addEdge(3, 6);

        //Act
        //Creo un arraylist con el orden de recorrido que se espera
        ArrayList<Integer> expectedOrder = new ArrayList<>();
        expectedOrder.add(1);
        expectedOrder.add(2);
        expectedOrder.add(4);
        expectedOrder.add(5);
        expectedOrder.add(3);
        expectedOrder.add(6);

        //Assert
        List<DFS_vertex<Integer>> recorrido = dfs.DFSRoute(); //DSF me retorna un arraylist con objetos de tipo vertices.
        List<Integer> valuesOfVertex = new ArrayList<>();

        for (int i = 0; i < recorrido.size(); i++) { //Por eso, los recorro y les hago el getValue a cada uno.
            valuesOfVertex.add(recorrido.get(i).getValue());
        }
        assertEquals(expectedOrder.toString(), valuesOfVertex.toString());
    }
    @Test
    public void testDFSRouteWithoutEdges() {
        DFS_List_Adjacency<Character> dfs = new DFS_List_Adjacency<>();
        //Arrange
        dfs.addVertex('K');
        dfs.addVertex('L');
        dfs.addVertex('M');
        dfs.addVertex('N');
        dfs.addVertex('O');
        dfs.addVertex('P');
        //Act
        ArrayList<Character> expectedOrder = new ArrayList<>();
        expectedOrder.add('K');
        expectedOrder.add('L');
        expectedOrder.add('M');
        expectedOrder.add('N');
        expectedOrder.add('O');
        expectedOrder.add('P');
        //Assert
        List<DFS_vertex<Character>> recorrido = dfs.DFSRoute(); //DSF me retorna un arraylist con objetos de tipo vertices.
        List<Character> valuesOfVertex = new ArrayList<>();

        for (int i = 0; i < recorrido.size(); i++) { //Por eso, los recorro y les hago el getValue a cada uno.
            valuesOfVertex.add(recorrido.get(i).getValue());
        }
        assertEquals(expectedOrder.toString(), valuesOfVertex.toString());
    }
    /*--------------------Tests DFS GetTrees-------------*/
    @Test
    public void testDFSGetTrees() {
        DFS_List_Adjacency<Integer> dfs = new DFS_List_Adjacency<>();
        //Arrange (Agrego vertices y aristas)
        dfs.addVertex(1);
        dfs.addVertex(2);
        dfs.addVertex(3);
        dfs.addVertex(4);
        dfs.addVertex(5);
        dfs.addVertex(6);
        dfs.addVertex(7);

        dfs.addEdge(1, 2);
        dfs.addEdge(1, 3);
        dfs.addEdge(2, 4);
        dfs.addEdge(2, 5);
        dfs.addEdge(3, 6);
        dfs.addEdge(4, 7);

        //Act and Assert
        int trees = dfs.DFSTrees();
        assertEquals(1, trees);
    }

    @Test
    public void testDFSGetMoreThanTree() {
        DFS_List_Adjacency<Integer> dfs = new DFS_List_Adjacency<>();
        // Arrange (agrego aristas y vertices)
        dfs.addVertex(2);
        dfs.addVertex(3);
        dfs.addVertex(4);
        dfs.addVertex(5);
        dfs.addVertex(6);
        dfs.addVertex(7);

        dfs.addEdge(2, 4);
        dfs.addEdge(2, 5);
        dfs.addEdge(3, 6);
        dfs.addEdge(4, 7);

        int trees = dfs.DFSTrees();
        assertEquals(2, trees);
    }
    @Test
    public void graphDFSTreesWithoutEdges() {
        DFS_List_Adjacency<Character> dfs = new DFS_List_Adjacency<>();
        dfs.addVertex('W');
        dfs.addVertex('X');
        dfs.addVertex('Y');
        dfs.addVertex('Z');
        int trees = dfs.DFSTrees();
        assertEquals(4, trees);
    }
    /*-----------------Tests DFS Matriz adyacencia--------------*/
    @Test
    public void testDFSRouteWithNoEdges() {
        DFS_Matrix_Adjacency<Integer> dfs = new DFS_Matrix_Adjacency<>(4);

        //Arrange
        dfs.addVertex(1);
        dfs.addVertex(2);
        dfs.addVertex(3);
        dfs.addVertex(4);

        List<DFS_vertex<Integer>> route = dfs.DFSRoute();

        //Assert
        assertEquals(4, route.size());
        assertEquals(Integer.valueOf(1), route.get(0).getValue());
        assertEquals(Integer.valueOf(2), route.get(1).getValue());
        assertEquals(Integer.valueOf(3), route.get(2).getValue());
        assertEquals(Integer.valueOf(4), route.get(3).getValue());
    }
    @Test
    public void testDFSRouteInConnectedGraph() {
        DFS_Matrix_Adjacency<Integer> dfs = new DFS_Matrix_Adjacency<>(5);

        // Arrange
        dfs.addVertex(1);
        dfs.addVertex(2);
        dfs.addVertex(3);
        dfs.addVertex(4);
        dfs.addVertex(5);

        dfs.addEdge(1, 2);
        dfs.addEdge(1, 3);
        dfs.addEdge(2, 4);
        dfs.addEdge(3, 4);
        dfs.addEdge(4, 5);

        List<DFS_vertex<Integer>> route = dfs.DFSRoute();

        // Act and Assert (Revisamos que la matriz funcione como la lista de adyacencia)
        assertEquals(5, route.size());
        assertEquals(Integer.valueOf(1), route.get(0).getValue());
        assertEquals(Integer.valueOf(2), route.get(1).getValue());
        assertEquals(Integer.valueOf(4), route.get(2).getValue());
        assertEquals(Integer.valueOf(3), route.get(3).getValue());
        assertEquals(Integer.valueOf(5), route.get(4).getValue());
    }
    @Test
    public void testDFSRouteInDisconnectedGraph() {
        DFS_Matrix_Adjacency<Integer> dfs = new DFS_Matrix_Adjacency<>(6);
        //Arrange
        dfs.addVertex(1);
        dfs.addVertex(2);
        dfs.addVertex(3);
        dfs.addVertex(4);
        dfs.addVertex(5);
        dfs.addVertex(6);

        dfs.addEdge(1, 2);
        dfs.addEdge(3, 4);
        dfs.addEdge(5, 6);

        List<DFS_vertex<Integer>> route = dfs.DFSRoute();

        // Assert
        assertEquals(6, route.size());
        assertEquals(Integer.valueOf(1), route.get(0).getValue());
        assertEquals(Integer.valueOf(2), route.get(1).getValue());
        assertEquals(Integer.valueOf(3), route.get(2).getValue());
        assertEquals(Integer.valueOf(4), route.get(3).getValue());
        assertEquals(Integer.valueOf(5), route.get(4).getValue());
        assertEquals(Integer.valueOf(6), route.get(5).getValue());
    }
    @Test
    public void testDFSTrees() {
        DFS_Matrix_Adjacency<Integer> dfs = new DFS_Matrix_Adjacency<>(6);

        // Arrange
        dfs.addVertex(1);
        dfs.addVertex(2);
        dfs.addVertex(3);
        dfs.addVertex(4);
        dfs.addVertex(5);
        dfs.addVertex(6);

        dfs.addEdge(1, 2);
        dfs.addEdge(3, 4);
        dfs.addEdge(5, 6);

        //Act and Assert (Verificamos el DFS forest)
        int trees = dfs.DFSTrees();
        assertEquals(3, trees);
    }
}
