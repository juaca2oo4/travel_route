package model.Other_Implementations.Floyd_Warshall;

import java.util.ArrayList;
import java.util.List;

public class FloydWarshallAlgorithm_Matrix<T> {
    public int[][] floydWarshall(FloydAlgorithm_GraphMatrix<T> graph) {
        int numVertices = graph.getNumVertices();
        int[][] distances = initializeDistances(graph);

        for (int k = 0; k < numVertices; k++) {
            for (int i = 0; i < numVertices; i++) {
                for (int j = 0; j < numVertices; j++) {
                    int directDistance = distances[i][j];
                    int indirectDistance = distances[i][k] + distances[k][j];

                    if (indirectDistance>0&&indirectDistance < directDistance) {
                        distances[i][j] = indirectDistance;
                    }
                }
            }
        }

        return distances;
    }

    private int[][] initializeDistances(FloydAlgorithm_GraphMatrix<T> graph) {
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

        List<FloydWarshall_Edge<T>> edges = graph.getAllEdges();

        for (FloydWarshall_Edge<T> edge : edges) {
            int sourceIndex = graph.getVertexIndex(edge.getSource());
            int destinationIndex = graph.getVertexIndex(edge.getDestination());
            int weight = edge.getWeight();
            distances[sourceIndex][destinationIndex] = weight;
        }

        return distances;
    }
}
