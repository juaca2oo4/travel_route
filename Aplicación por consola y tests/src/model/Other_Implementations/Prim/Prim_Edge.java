package model.Other_Implementations.Prim;

import java.util.Objects;

public class Prim_Edge<T> {
    private Prim_Vertex<T> source;
    private Prim_Vertex<T> destination;
    private int cost;

    public Prim_Edge(Prim_Vertex<T> source, Prim_Vertex<T> destination, int cost) {
        this.source = source;
        this.destination = destination;
        this.cost = cost;
    }

    public Prim_Vertex<T> getSource() {
        return source;
    }

    public Prim_Vertex<T> getDestination() {
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
        Prim_Edge<?> edge = (Prim_Edge<?>) obj;
        return cost == edge.cost && Objects.equals(source, edge.source) && Objects.equals(destination, edge.destination);
    }

    @Override
    public int hashCode() {
        return Objects.hash(source, destination, cost);
    }
}
