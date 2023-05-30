package model.Other_Implementations.Prim;

import model.Other_Implementations.Kruskal.Kruskal_Vertex;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Prim_GraphMatrix<T> {
    private Map<Prim_Vertex<T>, Integer> vertexIndices;
    private List<Prim_Vertex<T>> vertices;
    private int[][] adjacencyMatrix;

    public Prim_GraphMatrix() {
        vertexIndices = new HashMap<>();
        vertices = new ArrayList<>();
    }

    public boolean addVertex(Prim_Vertex<T> vertex) {
        if (vertexIndices.containsKey(vertex)) {
            return false;
        }

        int index = vertices.size();
        vertexIndices.put(vertex, index);
        vertices.add(vertex);

        int[][] newMatrix = new int[index + 1][index + 1];
        if (adjacencyMatrix != null) {
            for (int i = 0; i < index; i++) {
                System.arraycopy(adjacencyMatrix[i], 0, newMatrix[i], 0, index);
            }
        }
        adjacencyMatrix = newMatrix;

        return true;
    }

    public Prim_Vertex<T> searchVertex(String vertex) {
        for (Prim_Vertex<T> existingVertex : vertexIndices.keySet()) {
            if (existingVertex.getData().equals(vertex)) {
                return existingVertex;
            }
        }

        return null;
    }

    public void addEdge(Prim_Vertex<T> source, Prim_Vertex<T> destination, int cost) {
        if (!vertexIndices.containsKey(source) || !vertexIndices.containsKey(destination)) {
            throw new IllegalArgumentException("Los vértices de origen y destino deben existir en el grafo.");
        }

        int sourceIndex = vertexIndices.get(source);
        int destinationIndex = vertexIndices.get(destination);

        adjacencyMatrix[sourceIndex][destinationIndex] = cost;
        adjacencyMatrix[destinationIndex][sourceIndex] = cost;
    }

    public List<Prim_Vertex<T>> getVertices() {
        return new ArrayList<>(vertices);
    }

    public int[][] getAdjacencyMatrix() {
        return adjacencyMatrix;
    }
    public Prim_Vertex<T> getVertex(int index) {
        if (index < 0 || index >= vertices.size()) {
            throw new IllegalArgumentException("Índice de vértice inválido.");
        }
        return vertices.get(index);
    }

    public int getVertexIndex(Prim_Vertex<T> vertex) {
        Integer index = vertexIndices.get(vertex);
        if (index == null) {
            throw new IllegalArgumentException("El vértice no existe en el grafo.");
        }
        return index;
    }
}