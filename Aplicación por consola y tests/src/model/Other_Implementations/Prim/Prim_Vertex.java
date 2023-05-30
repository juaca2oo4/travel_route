package model.Other_Implementations.Prim;

import java.util.Objects;

public class Prim_Vertex<T> {
    private T value;

    public Prim_Vertex(T value) {
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
        Prim_Vertex<?> vertex = (Prim_Vertex<?>) obj;
        return Objects.equals(value, vertex.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
