

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Test;

public class KruskalAlgorithmTest {

    @Test
    public void testMinimumSpanningTree() {
        // Crear el grafo de ejemplo
        GraphList<Integer> graph = new GraphList<>();
    
        Vertex<Integer> vertex0 = new Vertex<>(0);
        Vertex<Integer> vertex1 = new Vertex<>(1);
        Vertex<Integer> vertex2 = new Vertex<>(2);
    
        graph.addVertex(vertex0);
        graph.addVertex(vertex1);
        graph.addVertex(vertex2);

    
        graph.addEdge(vertex0, vertex1, 8);
        graph.addEdge(vertex1, vertex2, 10);
        graph.addEdge(vertex2, vertex0, 12);

    
        // Ejecutar el algoritmo de Kruskal
        KruskalAlgorithm<Integer> kruskalAlgorithm = new KruskalAlgorithm<>();
        List<Edge<Integer>> minimumSpanningTree = kruskalAlgorithm.findMinimumSpanningTree(graph);
    
        // Verificar los bordes del árbol generador mínimo
        Set<Edge<Integer>> minimumSpanningTreeSet = new HashSet<>(minimumSpanningTree);
    
        List<Edge<Integer>> expectedEdges = new ArrayList<>();
        expectedEdges.add(new Edge<>(vertex1, vertex2, 10));
        expectedEdges.add(new Edge<>(vertex1, vertex0, 8));
    
        assertEquals(expectedEdges.size(), minimumSpanningTree.size());
       for (Edge<Integer> expectedEdge : expectedEdges) {
            assertTrue(minimumSpanningTreeSet.contains(expectedEdge));
        }
    }
    
    @Test
    public void testEmptyGraph() {
        // Crear un grafo vacío
        GraphList<String> graph = new GraphList<>();

        // Ejecutar el algoritmo de Kruskal
        KruskalAlgorithm<String> kruskalAlgorithm = new KruskalAlgorithm<>();
        List<Edge<String>> minimumSpanningTree = kruskalAlgorithm.findMinimumSpanningTree(graph);

        // Verificar que el árbol generador mínimo esté vacío
        assertEquals(0, minimumSpanningTree.size());
    }

    @Test
    public void testSingleVertex() {
        // Crear un grafo con un solo vértice
        GraphList<Integer> graph = new GraphList<>();

        Vertex<Integer> vertex = new Vertex<>(0);
        graph.addVertex(vertex);

        // Ejecutar el algoritmo de Kruskal
        KruskalAlgorithm<Integer> kruskalAlgorithm = new KruskalAlgorithm<>();
        List<Edge<Integer>> minimumSpanningTree = kruskalAlgorithm.findMinimumSpanningTree(graph);

        // Verificar que el árbol generador mínimo esté vacío
        assertEquals(0, minimumSpanningTree.size());
    }
}

