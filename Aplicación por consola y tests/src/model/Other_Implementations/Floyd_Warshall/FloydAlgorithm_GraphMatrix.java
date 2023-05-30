package model.Other_Implementations.Floyd_Warshall;

import java.util.ArrayList;
import java.util.List;

public class FloydAlgorithm_GraphMatrix<T> {
    private List<FloydWarshall_Vertex<T>> vertices;
    private int[][] adjacencyMatrix;

    public FloydAlgorithm_GraphMatrix() {
        vertices = new ArrayList<>();
    }

    public boolean addVertex(FloydWarshall_Vertex<T> vertex) {
        if (vertices.contains(vertex)) {
            return false;
        }

        vertices.add(vertex);
        updateAdjacencyMatrix();

        return true;
    }

    public void addEdge(FloydWarshall_Vertex<T> source, FloydWarshall_Vertex<T> destination, int weight) {
        int sourceIndex = vertices.indexOf(source);
        int destinationIndex = vertices.indexOf(destination);

        if (sourceIndex == -1 || destinationIndex == -1) {
            throw new IllegalArgumentException("Los vértices de origen y destino deben existir en el grafo.");
        }

        adjacencyMatrix[sourceIndex][destinationIndex] = weight;
        adjacencyMatrix[destinationIndex][sourceIndex] =weight;
    }

    public int getNumVertices() {
        return vertices.size();
    }

    public List<FloydWarshall_Vertex<T>> getVertices() {
        return new ArrayList<>(vertices);
    }
    private void updateAdjacencyMatrix() {
        int numVertices = vertices.size();
        int[][] newMatrix = new int[numVertices][numVertices];

        if (adjacencyMatrix != null) {
            for (int i = 0; i < numVertices - 1; i++) {
                System.arraycopy(adjacencyMatrix[i], 0, newMatrix[i], 0, numVertices - 1);
            }
        }

        adjacencyMatrix = newMatrix;
    }
    public int getVertexIndex(FloydWarshall_Vertex<T> vertex) {
        return vertices.indexOf(vertex);
    }

    public List<FloydWarshall_Edge<T>> getAllEdges() {
        List<FloydWarshall_Edge<T>> edges = new ArrayList<>();

        for (int i = 0; i < adjacencyMatrix.length; i++) {
            for (int j = 0; j < adjacencyMatrix[i].length; j++) {
                if (adjacencyMatrix[i][j] != 0) {
                    FloydWarshall_Vertex<T> source = vertices.get(i);
                    FloydWarshall_Vertex<T> destination = vertices.get(j);
                    int weight = adjacencyMatrix[i][j];
                    FloydWarshall_Edge<T> edge = new FloydWarshall_Edge<>(source, destination, weight);
                    edges.add(edge);
                }
            }
        }

        return edges;
    }
}