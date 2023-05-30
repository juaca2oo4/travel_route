package model.Other_Implementations.Kruskal;

import java.util.Objects;

public class Kruskal_Edge<T> {
    private Kruskal_Vertex<T> source;
    private Kruskal_Vertex<T> destination;
    private int cost;

    public Kruskal_Edge(Kruskal_Vertex<T> source, Kruskal_Vertex<T> destination, int cost) {
        this.source = source;
        this.destination = destination;
        this.cost = cost;
    }

    public Kruskal_Vertex<T> getSource() {
        return source;
    }

    public Kruskal_Vertex<T> getDestination() {
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
        Kruskal_Edge<?> edge = (Kruskal_Edge<?>) obj;
        return cost == edge.cost && Objects.equals(source, edge.source) && Objects.equals(destination, edge.destination);
    }

    @Override
    public int hashCode() {
        return Objects.hash(source, destination, cost);
    }
}
