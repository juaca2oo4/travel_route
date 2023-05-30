package model.Other_Implementations.Kruskal;

import java.util.*;

public class KruskalAlgorithm_List<T> {
    private List<Kruskal_Edge<T>> minimumSpanningTree;
    private Map<Kruskal_Vertex<T>, Kruskal_Vertex<T>> parent;

    public List<Kruskal_Edge<T>> findMinimumSpanningTree(Kruskal_GraphList<T> graph) {
        minimumSpanningTree = new ArrayList<>();
        parent = new HashMap<>();

        List<Kruskal_Edge<T>> edges = getAllEdges(graph);
        edges.sort(Comparator.comparingInt(Kruskal_Edge::getCost));

        for (Kruskal_Vertex<T> vertex : graph.getVertices()) {
            makeSet(vertex);
        }

        for (Kruskal_Edge<T> edge : edges) {
            Kruskal_Vertex<T> sourceRoot = find(edge.getSource());
            Kruskal_Vertex<T> destinationRoot = find(edge.getDestination());

            if (!sourceRoot.equals(destinationRoot)) {
                minimumSpanningTree.add(edge);
                union(sourceRoot, destinationRoot);
            }
        }

        return minimumSpanningTree;
    }

    private List<Kruskal_Edge<T>> getAllEdges(Kruskal_GraphList<T> graph) {
        List<Kruskal_Edge<T>> edges = new ArrayList<>();
        for (Kruskal_Vertex<T> vertex : graph.getVertices()) {
            edges.addAll(graph.getEdges(vertex));
        }
        return edges;
    }

    private void makeSet(Kruskal_Vertex<T> vertex) {
        parent.put(vertex, vertex);
    }

    private Kruskal_Vertex<T> find(Kruskal_Vertex<T> vertex) {
        if (!vertex.equals(parent.get(vertex))) {
            parent.put(vertex, find(parent.get(vertex)));
        }
        return parent.get(vertex);
    }

    private void union(Kruskal_Vertex<T> vertex1, Kruskal_Vertex<T> vertex2) {
        parent.put(vertex1, vertex2);
    }
}
