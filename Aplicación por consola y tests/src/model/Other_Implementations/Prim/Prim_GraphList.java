package model.Other_Implementations.Prim;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Prim_GraphList<T> {
    private Map<Prim_Vertex<T>, List<Prim_Edge<T>>> adjacencyList;

    public Prim_GraphList() {
        adjacencyList = new HashMap<>();
    }

    public boolean addVertex(Prim_Vertex<T> vertex) {
        for (Prim_Vertex<T> existingVertex : adjacencyList.keySet()) {
            if (existingVertex.getData().equals(vertex.getData())) {
                return false;
            }
        }
        adjacencyList.putIfAbsent(vertex, new ArrayList<>());
        return true;
    }
    public Prim_Vertex<T> searchVertex(String vertex) {
        for (Prim_Vertex<T> existingVertex : adjacencyList.keySet()) {
            if (existingVertex.getData().equals(vertex)) {
                return existingVertex;
            }
        }

        return null;
    }

    public void addEdge(Prim_Vertex<T> source, Prim_Vertex<T> destination, int cost) {
        if (!adjacencyList.containsKey(source) || !adjacencyList.containsKey(destination)) {
            throw new IllegalArgumentException("Los vértices de origen y destino deben existir en el grafo.");
        }
        Prim_Edge<T> edge = new Prim_Edge<>(source, destination, cost);
        Prim_Edge<T> edge2 = new Prim_Edge<>(destination, source, cost);
        adjacencyList.get(source).add(edge);
        adjacencyList.get(destination).add(edge2);
    }

    public List<Prim_Edge<T>> getEdges(Prim_Vertex<T> vertex) {
        return adjacencyList.getOrDefault(vertex, new ArrayList<>());
    }

    public List<Prim_Vertex<T>> getVertices() {
        return new ArrayList<>(adjacencyList.keySet());
    }

}