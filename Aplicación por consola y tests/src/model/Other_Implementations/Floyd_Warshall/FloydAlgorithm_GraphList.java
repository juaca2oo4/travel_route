package model.Other_Implementations.Floyd_Warshall;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FloydAlgorithm_GraphList<T> {
    private List<FloydWarshall_Vertex<T>> vertices;
    private Map<FloydWarshall_Vertex<T>, List<FloydWarshall_Edge<T>>> adjacencyList;

    public FloydAlgorithm_GraphList() {
        vertices = new ArrayList<>();
        adjacencyList = new HashMap<>();
    }

    public boolean addVertex(FloydWarshall_Vertex<T> vertex) {
        if (vertices.contains(vertex)) {
            return false;
        }

        vertices.add(vertex);
        adjacencyList.put(vertex, new ArrayList<>());

        return true;
    }

    public void addEdge(FloydWarshall_Vertex<T> source, FloydWarshall_Vertex<T> destination, int weight) {
        List<FloydWarshall_Edge<T>> edges = adjacencyList.get(source);
        FloydWarshall_Edge<T> edge = new FloydWarshall_Edge<>(source, destination, weight);
        edges.add(edge);
    }

    public int getNumVertices() {
        return vertices.size();
    }

    public List<FloydWarshall_Vertex<T>> getVertices() {
        return new ArrayList<>(vertices);
    }

    public int getVertexIndex(FloydWarshall_Vertex<T> vertex) {
        return vertices.indexOf(vertex);
    }

    public List<FloydWarshall_Edge<T>> getEdges(FloydWarshall_Vertex<T> vertex) {
        return adjacencyList.get(vertex);
    }

    public List<FloydWarshall_Edge<T>> getAllEdges() {
        List<FloydWarshall_Edge<T>> edges = new ArrayList<>();

        for (List<FloydWarshall_Edge<T>> vertexEdges : adjacencyList.values()) {
            edges.addAll(vertexEdges);
        }

        return edges;
    }
}
