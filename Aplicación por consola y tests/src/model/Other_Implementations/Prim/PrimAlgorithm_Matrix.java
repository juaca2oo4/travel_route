package model.Other_Implementations.Prim;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Set;

public class PrimAlgorithm_Matrix<T> {
    private List<Prim_Edge<T>> minimumSpanningTree;
    private Set<Prim_Vertex<T>> visitedVertices;
    private PriorityQueue<Prim_Edge<T>> priorityQueue;

    public List<Prim_Edge<T>> findMinimumSpanningTree(Prim_GraphMatrix<T> graph) {
        minimumSpanningTree = new ArrayList<>();
        visitedVertices = new HashSet<>();
        priorityQueue = new PriorityQueue<>(Comparator.comparingInt(Prim_Edge::getCost));

        if (graph.getVertices().isEmpty()) {
            return minimumSpanningTree;
        }

        Prim_Vertex<T> startVertex = graph.getVertices().get(0);
        visitVertex(graph, startVertex);

        while (!priorityQueue.isEmpty() && visitedVertices.size() < graph.getVertices().size()) {
            Prim_Edge<T> edge = priorityQueue.poll();
            Prim_Vertex<T> source = edge.getSource();
            Prim_Vertex<T> destination = edge.getDestination();

            if (visitedVertices.contains(source) && !visitedVertices.contains(destination)) {
                minimumSpanningTree.add(edge);
                visitVertex(graph, destination);
            } else if (visitedVertices.contains(destination) && !visitedVertices.contains(source)) {
                minimumSpanningTree.add(edge);
                visitVertex(graph, source);
            }
        }

        return minimumSpanningTree;
    }

    private void visitVertex(Prim_GraphMatrix<T> graph, Prim_Vertex<T> vertex) {
        visitedVertices.add(vertex);
        int vertexIndex = graph.getVertexIndex(vertex);

        for (int i = 0; i < graph.getVertices().size(); i++) {
            if (i != vertexIndex && !visitedVertices.contains(graph.getVertex(i))) {
                int cost = graph.getAdjacencyMatrix()[vertexIndex][i];
                if (cost != 0) {
                    Prim_Vertex<T> destination = graph.getVertex(i);
                    Prim_Edge<T> edge = new Prim_Edge<>(vertex, destination, cost);
                    priorityQueue.offer(edge);
                }
            }
        }
    }
}
