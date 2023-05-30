package model.Other_Implementations.Floyd_Warshall;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FloydWarshallAlgorithm_List<T> {
    public int[][] floydWarshall(FloydAlgorithm_GraphList<T> graph) {
        int numVertices = graph.getNumVertices();
        int[][] distances = initializeDistances(graph);

        for (int k = 0; k < numVertices; k++) {
            for (int i = 0; i < numVertices; i++) {
                for (int j = 0; j < numVertices; j++) {
                    int directDistance = distances[i][j];
                    int indirectDistance = distances[i][k] + distances[k][j];

                    if (indirectDistance > 0 && indirectDistance < directDistance) {
                        distances[i][j] = indirectDistance;
                    }
                }
            }
        }

        return distances;
    }

    private int[][] initializeDistances(FloydAlgorithm_GraphList<T> graph) {
        int numVertices = graph.getNumVertices();
        int[][] distances = new int[numVertices][numVertices];

        for (int i = 0; i < numVertices; i++) {
            for (int j = 0; j < numVertices; j++) {
                if (i == j) {
                    distances[i][j] = 0;
                } else {
                    distances[i][j] = Integer.MAX_VALUE;
                }
            }
        }
        List<FloydWarshall_Vertex<T>> vertices = graph.getVertices();
        Map<FloydWarshall_Vertex<T>, Integer> vertexIndices = new HashMap<>();

        for (int i = 0; i < numVertices; i++) {
            vertexIndices.put(vertices.get(i), i);
        }

        List<FloydWarshall_Edge<T>> edges = graph.getAllEdges();

        for (FloydWarshall_Edge<T> edge : edges) {
            int sourceIndex = vertexIndices.get(edge.getSource());
            int destinationIndex = vertexIndices.get(edge.getDestination());
            int weight = edge.getWeight();
            distances[sourceIndex][destinationIndex] = weight;
        }
        return distances;
    }
}
