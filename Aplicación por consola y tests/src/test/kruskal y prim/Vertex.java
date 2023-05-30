import java.util.Objects;

public class Vertex<T> {
    private T value;

    public Vertex(T value) {
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
        Vertex<?> vertex = (Vertex<?>) obj;
        return Objects.equals(value, vertex.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
