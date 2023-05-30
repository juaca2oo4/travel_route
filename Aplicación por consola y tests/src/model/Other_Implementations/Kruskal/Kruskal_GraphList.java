package model.Other_Implementations.Kruskal;

import java.util.*;

public class Kruskal_GraphList<T> {
    private Map<Kruskal_Vertex<T>, List<Kruskal_Edge<T>>> adjacencyList;

    public Kruskal_GraphList() {
        adjacencyList = new HashMap<>();
    }

    public boolean addVertex(Kruskal_Vertex<T> vertex) {
        for (Kruskal_Vertex<T> existingVertex : adjacencyList.keySet()) {
            if (existingVertex.getData().equals(vertex.getData())) {
                return false;
            }
        }
        adjacencyList.putIfAbsent(vertex, new ArrayList<>());
        return true;
    }
    public Kruskal_Vertex<T> searchVertex(String vertex) {
        for (Kruskal_Vertex<T> existingVertex : adjacencyList.keySet()) {
            if (existingVertex.getData().equals(vertex)) {
                return existingVertex;
            }
        }

        return null;
    }

    public void addEdge(Kruskal_Vertex<T> source, Kruskal_Vertex<T> destination, int cost) {
        if (!adjacencyList.containsKey(source) || !adjacencyList.containsKey(destination)) {
            throw new IllegalArgumentException("Los vértices de origen y destino deben existir en el grafo.");
        }
        Kruskal_Edge<T> edge = new Kruskal_Edge<>(source, destination, cost);
        Kruskal_Edge<T> edge2 = new Kruskal_Edge<>(destination, source, cost);
        adjacencyList.get(source).add(edge);
        adjacencyList.get(destination).add(edge2);
    }

    public List<Kruskal_Edge<T>> getEdges(Kruskal_Vertex<T> vertex) {
        return adjacencyList.getOrDefault(vertex, new ArrayList<>());
    }

    public List<Kruskal_Vertex<T>> getVertices() {
        return new ArrayList<>(adjacencyList.keySet());
    }

}