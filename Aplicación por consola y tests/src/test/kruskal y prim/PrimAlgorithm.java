import java.util.*;

public class PrimAlgorithm<T> {
    private List<Edge<T>> minimumSpanningTree;
    private Set<Vertex<T>> visitedVertices;
    private PriorityQueue<Edge<T>> priorityQueue;

    public List<Edge<T>> findMinimumSpanningTree(GraphList<T> graph) {
        minimumSpanningTree = new ArrayList<>();
        visitedVertices = new HashSet<>();
        priorityQueue = new PriorityQueue<>(Comparator.comparingInt(Edge::getCost));
    
        if (graph.getVertices().isEmpty()) {
            return minimumSpanningTree;
        }
    
        Vertex<T> startVertex = graph.getVertices().get(0);
        visitVertex(graph, startVertex);
    
        while (!priorityQueue.isEmpty() && visitedVertices.size() < graph.getVertices().size()) {
            Edge<T> edge = priorityQueue.poll();
            Vertex<T> source = edge.getSource();
            Vertex<T> destination = edge.getDestination();
    
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
    

    private void visitVertex(GraphList<T> graph, Vertex<T> vertex) {
        visitedVertices.add(vertex);
        for (Edge<T> edge : graph.getEdges(vertex)) {
            if (!visitedVertices.contains(edge.getDestination())) {
                priorityQueue.offer(edge);
            }
        }
    }
}
