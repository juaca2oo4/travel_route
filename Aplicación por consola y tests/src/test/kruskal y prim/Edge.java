import java.util.Objects;

public class Edge<T> {
    private Vertex<T> source;
    private Vertex<T> destination;
    private int cost;

    public Edge(Vertex<T> source, Vertex<T> destination, int cost) {
        this.source = source;
        this.destination = destination;
        this.cost = cost;
    }

    public Vertex<T> getSource() {
        return source;
    }

    public Vertex<T> getDestination() {
        return destination;
    }

    public int getCost() {
        return cost;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Edge<?> edge = (Edge<?>) obj;
        return cost == edge.cost && Objects.equals(source, edge.source) && Objects.equals(destination, edge.destination);
    }

    @Override
    public int hashCode() {
        return Objects.hash(source, destination, cost);
    }
}
