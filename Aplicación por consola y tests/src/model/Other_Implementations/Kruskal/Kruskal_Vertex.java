package model.Other_Implementations.Kruskal;

import java.util.Objects;

public class Kruskal_Vertex<T> {
    private T value;

    public Kruskal_Vertex(T value) {
        this.value = value;
    }

    public T getData() {
        return value;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Kruskal_Vertex<?> vertex = (Kruskal_Vertex<?>) obj;
        return Objects.equals(value, vertex.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
