
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

    public void addEdge(Vertex<T> source, Vertex<T> destination, int cost) {
        if (!adjacencyList.containsKey(source) || !adjacencyList.containsKey(destination)) {
            throw new IllegalArgumentException("Los vértices de origen y destino deben existir en el grafo.");
        }
        Edge<T> edge = new Edge<>(source, destination, cost);
        Edge<T> edge2 = new Edge<>(destination, source, cost);
        adjacencyList.get(source).add(edge);
        adjacencyList.get(destination).add(edge2);
    }

    public List<Edge<T>> getEdges(Vertex<T> vertex) {
        return adjacencyList.getOrDefault(vertex, new ArrayList<>());
    }

    public List<Vertex<T>> getVertices() {
        return new ArrayList<>(adjacencyList.keySet());
    }

}