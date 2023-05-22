package model;
import java.util.*;

public class Edge<T> {
    private Vertex<T> source;
    private Vertex<T> destination;
    private int cost;
    private int distance;

    public Edge(Vertex<T> source, Vertex<T> destination, int cost, int distance) {
        this.source = source;
        this.destination = destination;
        this.cost = cost;
        this.distance = distance;
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

    public int getDistance() {
        return distance;
    }
}