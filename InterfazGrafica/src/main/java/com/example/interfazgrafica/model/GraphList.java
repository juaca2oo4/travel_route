package com.example.interfazgrafica.model;

import java.util.*;

public class GraphList<T> {
    private Map<Vertex<T>, List<Edge<T>>> adjacencyList;

    public GraphList() {
        adjacencyList = new HashMap<>();
    }

    public boolean addVertex(Vertex<T> vertex) {
        for (Vertex<T> existingVertex : adjacencyList.keySet()) {
            if (existingVertex.getData().equals(vertex.getData())) {
                return false;
            }
        }
        adjacencyList.putIfAbsent(vertex, new ArrayList<>());
        return true;
    }
    public Vertex<T> searchVertex(String vertex) {
        for (Vertex<T> existingVertex : adjacencyList.keySet()) {
            if (existingVertex.getData().equals(vertex)) {
                return existingVertex;
            }
        }

        return null;
    }

    public void addEdge(Vertex<T> source, Vertex<T> destination, int cost, int distance) {
        if (!adjacencyList.containsKey(source) || !adjacencyList.containsKey(destination)) {
            throw new IllegalArgumentException("Los vértices de origen y destino deben existir en el grafo.");
        }
        Edge<T> edge = new Edge<>(source, destination, cost, distance);
        Edge<T> edge2 = new Edge<>(destination, source, cost, distance);
        adjacencyList.get(source).add(edge);
        adjacencyList.get(destination).add(edge2);
    }

    public List<Edge<T>> getEdges(Vertex<T> vertex) {
        return adjacencyList.getOrDefault(vertex, new ArrayList<>());
    }

    public List<Vertex<T>> getVertices() {
        return new ArrayList<>(adjacencyList.keySet());
    }

    public List<Vertex<T>> dijkstra (Vertex<T> startVertex, Vertex<T> endVertex, String factor) {
        Map<Vertex<T>, Integer> distances = new HashMap<>();
        Map<Vertex<T>, Vertex<T>> previousVertices = new HashMap<>();
        Set<Vertex<T>> visited = new HashSet<>();

        PriorityQueue<Vertex<T>> minHeap = new PriorityQueue<>(Comparator.comparingInt(v -> distances.getOrDefault(v, Integer.MAX_VALUE)));
        distances.put(startVertex, 0);
        minHeap.offer(startVertex);

        while (!minHeap.isEmpty()) {
            Vertex<T> currentVertex = minHeap.poll();
            if (currentVertex == endVertex) {
                break;
            }

            visited.add(currentVertex);

            List<Edge<T>> edges = getEdges(currentVertex);
            for (Edge<T> edge : edges) {
                Vertex<T> nextVertex = edge.getDestination();
                if (visited.contains(nextVertex)) {
                    continue;
                }

                int weight = factor.equals("cost") ? edge.getCost() : edge.getDistance();
                int newDistance = distances.get(currentVertex) + weight;

                if (newDistance < distances.getOrDefault(nextVertex, Integer.MAX_VALUE)) {
                    distances.put(nextVertex, newDistance);
                    previousVertices.put(nextVertex, currentVertex);
                    minHeap.offer(nextVertex);
                }
            }
        }

        List<Vertex<T>> shortestPath = new ArrayList<>();
        Vertex<T> currentVertex = endVertex;

        while (currentVertex != null) {
            shortestPath.add(0, currentVertex);
            currentVertex = previousVertices.get(currentVertex);
        }

        return shortestPath;
    }

    public int bfs(List<Vertex<T>> path, String factor) {
        int totalValue = 0;
        Set<Vertex<T>> visited = new HashSet<>();
        Queue<Vertex<T>> queue = new LinkedList<>();

        queue.offer(path.get(0));
        while (!queue.isEmpty()) {
            Vertex<T> currentVertex = queue.poll();
            visited.add(currentVertex);

            List<Edge<T>> edges = getEdges(currentVertex);
            for (Edge<T> edge : edges) {
                Vertex<T> nextVertex = edge.getDestination();
                if (path.contains(nextVertex) && !visited.contains(nextVertex)) {
                    queue.offer(nextVertex);
                    int value = factor.equals("cost") ? edge.getCost() : edge.getDistance();
                    totalValue += value;
                    break;
                }
            }
        }
        return totalValue;
    }

    public Map<Vertex<T>, List<Edge<T>>> getAdjacencyList() {
        return adjacencyList;
    }

    public void setAdjacencyList(Map<Vertex<T>, List<Edge<T>>> adjacencyList) {
        this.adjacencyList = adjacencyList;
    }


}