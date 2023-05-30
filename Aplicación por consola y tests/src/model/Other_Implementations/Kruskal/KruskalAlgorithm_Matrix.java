package model.Other_Implementations.Kruskal;

import java.util.*;

public class KruskalAlgorithm_Matrix<T> {
    private List<Kruskal_Edge<T>> minimumSpanningTree;
    private Map<Kruskal_Vertex<T>, Kruskal_Vertex<T>> parent;

    public List<Kruskal_Edge<T>> findMinimumSpanningTree(Kruskal_GraphMatrix<T> graph) {
        minimumSpanningTree = new ArrayList<>();
        parent = new HashMap<>();

        List<Kruskal_Edge<T>> edges = getAllEdges(graph);
        edges.sort(Comparator.comparingInt(Kruskal_Edge::getCost));

        for (Kruskal_Vertex<T> vertex : graph.getVertices()) {
            makeSet(vertex);
        }

        for (Kruskal_Edge<T> edge : edges) {
            int sourceIndex = graph.getVertexIndex(edge.getSource());
            int destinationIndex = graph.getVertexIndex(edge.getDestination());

            Kruskal_Vertex<T> sourceRoot = find(graph.getVertex(sourceIndex));
            Kruskal_Vertex<T> destinationRoot = find(graph.getVertex(destinationIndex));

            if (!sourceRoot.equals(destinationRoot)) {
                minimumSpanningTree.add(edge);
                union(sourceRoot, destinationRoot);
            }
        }

        return minimumSpanningTree;
    }

    private List<Kruskal_Edge<T>> getAllEdges(Kruskal_GraphMatrix<T> graph) {
        List<Kruskal_Edge<T>> edges = new ArrayList<>();
        int[][] adjacencyMatrix = graph.getAdjacencyMatrix();
        if(adjacencyMatrix!=null){
            int numVertices = adjacencyMatrix.length;
            for (int i = 0; i < numVertices; i++) {
                for (int j = i + 1; j < numVertices; j++) {
                    if (adjacencyMatrix[i][j] != 0) {
                        Kruskal_Vertex<T> source = graph.getVertex(i);
                        Kruskal_Vertex<T> destination = graph.getVertex(j);
                        int cost = adjacencyMatrix[i][j];
                        Kruskal_Edge<T> edge = new Kruskal_Edge<>(source, destination, cost);
                        edges.add(edge);
                    }
                }
            }
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