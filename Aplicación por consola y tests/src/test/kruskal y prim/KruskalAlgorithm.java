import java.util.*;

public class KruskalAlgorithm<T> {
    private List<Edge<T>> minimumSpanningTree;
    private Map<Vertex<T>, Vertex<T>> parent;

    public List<Edge<T>> findMinimumSpanningTree(GraphList<T> graph) {
        minimumSpanningTree = new ArrayList<>();
        parent = new HashMap<>();

        List<Edge<T>> edges = getAllEdges(graph);
        edges.sort(Comparator.comparingInt(Edge::getCost));

        for (Vertex<T> vertex : graph.getVertices()) {
            makeSet(vertex);
        }

        for (Edge<T> edge : edges) {
            Vertex<T> sourceRoot = find(edge.getSource());
            Vertex<T> destinationRoot = find(edge.getDestination());

            if (!sourceRoot.equals(destinationRoot)) {
                minimumSpanningTree.add(edge);
                union(sourceRoot, destinationRoot);
            }
        }

        return minimumSpanningTree;
    }

    private List<Edge<T>> getAllEdges(GraphList<T> graph) {
        List<Edge<T>> edges = new ArrayList<>();
        for (Vertex<T> vertex : graph.getVertices()) {
            edges.addAll(graph.getEdges(vertex));
        }
        return edges;
    }

    private void makeSet(Vertex<T> vertex) {
        parent.put(vertex, vertex);
    }

    private Vertex<T> find(Vertex<T> vertex) {
        if (!vertex.equals(parent.get(vertex))) {
            parent.put(vertex, find(parent.get(vertex)));
        }
        return parent.get(vertex);
    }

    private void union(Vertex<T> vertex1, Vertex<T> vertex2) {
        parent.put(vertex1, vertex2);
    }
}
