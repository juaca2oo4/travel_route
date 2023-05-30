package model.Other_Implementations.Prim;

import java.util.*;

public class PrimAlgorithm_List<T> {
    private List<Prim_Edge<T>> minimumSpanningTree;
    private Set<Prim_Vertex<T>> visitedVertices;
    private PriorityQueue<Prim_Edge<T>> priorityQueue;

    public List<Prim_Edge<T>> findMinimumSpanningTree(Prim_GraphList<T> graph) {
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
    

    private void visitVertex(Prim_GraphList<T> graph, Prim_Vertex<T> vertex) {
        visitedVertices.add(vertex);
        for (Prim_Edge<T> edge : graph.getEdges(vertex)) {
            if (!visitedVertices.contains(edge.getDestination())) {
                priorityQueue.offer(edge);
            }
        }
    }
}
