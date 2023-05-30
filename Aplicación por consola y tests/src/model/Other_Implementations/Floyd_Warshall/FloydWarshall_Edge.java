package model.Other_Implementations.Floyd_Warshall;

import model.Vertex;

public class FloydWarshall_Edge<T> {
    private FloydWarshall_Vertex<T> source;
    private FloydWarshall_Vertex<T> destination;
    private int weight;

    public FloydWarshall_Edge(FloydWarshall_Vertex<T> source, FloydWarshall_Vertex<T> destination, int weight) {
        this.source = source;
        this.destination = destination;
        this.weight = weight;
    }

    public FloydWarshall_Vertex<T> getSource() {
        return source;
    }

    public FloydWarshall_Vertex<T> getDestination() {
        return destination;
    }

    public int getWeight() {
        return weight;
    }
}
