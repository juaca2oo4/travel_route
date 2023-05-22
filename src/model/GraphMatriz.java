package model;

import java.util.*;;

public class GraphMatriz<T> {
    private List<Edge<T>>[][] adjacencyMatrix;
    private List<Vertex<T>> vertices;

    public GraphMatriz(int numVertices) {
        adjacencyMatrix = new ArrayList[numVertices][numVertices];
        vertices = new ArrayList<>();
    }

    public Vertex<T> searchVertex(String vertex) {
        for (int i = 0; i < vertices.size(); i++) {
            if(vertices.get(i).getData().equals(vertex)){
                return vertices.get(i);
            }
        }

        return null;
    }

    public boolean addVertex(Vertex<T> vertex) {
        for (int i = 0; i < vertices.size(); i++) {
            if (vertices.get(i).getData().equals(vertex.getData())) {
                return false;
            }
        }
        vertices.add(vertex);
        return true;
    }

    public void addEdge(Vertex<T> source, Vertex<T> destination, int cost, int distance) {
        if (!vertices.contains(source) || !vertices.contains(destination)) {
            throw new IllegalArgumentException("Los vértices de origen y destino deben existir en el grafo.");
        }
        int sourceIndex = vertices.indexOf(source);
        int destinationIndex = vertices.indexOf(destination);

        if (adjacencyMatrix[sourceIndex][destinationIndex] == null) {
            adjacencyMatrix[sourceIndex][destinationIndex] = new ArrayList<>();
        }
        adjacencyMatrix[sourceIndex][destinationIndex].add(new Edge(source ,destination,cost, distance));

        if (adjacencyMatrix[destinationIndex][sourceIndex] == null) {
            adjacencyMatrix[destinationIndex][sourceIndex] = new ArrayList<>();
        }
        adjacencyMatrix[destinationIndex][sourceIndex].add(new Edge(source ,destination,cost, distance));
    }

    public List<Vertex<T>> getVertices() {
        return vertices;
    }

    public List<Vertex<T>> dijkstra(Vertex<T> startVertex, Vertex<T> endVertex, String factor) {
        int start = vertices.indexOf(startVertex);
        int end = vertices.indexOf(endVertex);

        if (start == -1 || end == -1) {
            return null;
        }
        int numVertices = vertices.size();
        int[] distances = new int[numVertices];
        int[] parents = new int[numVertices];
        boolean[] visited = new boolean[numVertices];

        Arrays.fill(distances, Integer.MAX_VALUE);
        Arrays.fill(parents, -1);
        distances[start] = 0;

        for (int i = 0; i < numVertices - 1; i++) {
            int minVertex = findMinDistance(distances, visited);
            visited[minVertex] = true;

            for (int j = 0; j < numVertices; j++) {
                if (!visited[j] && adjacencyMatrix[minVertex][j] != null) {
                    List<Edge<T>> edges = adjacencyMatrix[minVertex][j];
                    for (Edge<T> edge : edges) {
                        int currentDistance = distances[minVertex] + (factor.equals("cost") ? edge.getCost() : edge.getDistance());
                        if (currentDistance < distances[j]) {
                            distances[j] = currentDistance;
                            parents[j] = minVertex;
                        }
                    }
                }
            }
        }

        List<Vertex<T>> shortestPath = new ArrayList<>();
        int currentVertex = end;
        while (currentVertex != -1) {
            shortestPath.add(0, vertices.get(currentVertex));
            currentVertex = parents[currentVertex];
        }

        return shortestPath;
    }

    public int bfs(List<Vertex<T>> path, String factor) {
        int totalValue = 0;
        Set<Vertex<T>> visited = new HashSet<>();
        Queue<Vertex<T>> queue = new LinkedList<>();

        if (path == null) {
            return -1;
        }
        queue.offer(path.get(0));

        while (!queue.isEmpty()) {
            Vertex<T> currentVertex = queue.poll();
            visited.add(currentVertex);

            int currentVertexIndex = vertices.indexOf(currentVertex);
            for (int i = 0; i < vertices.size(); i++) {
                Vertex<T> nextVertex = vertices.get(i);
                if (path.contains(nextVertex) && !visited.contains(nextVertex) && adjacencyMatrix[currentVertexIndex][i] != null) {
                    List<Edge<T>> edges = adjacencyMatrix[currentVertexIndex][i];
                    for (Edge<T> edge : edges) {
                        queue.offer(nextVertex);

                        int value = factor.equals("cost") ? edge.getCost() : edge.getDistance();
                        totalValue += value;
                    }
                }
            }
        }

        return totalValue;
    }

    private int findMinDistance(int[] distances, boolean[] visited) {
        int minDistance = Integer.MAX_VALUE;
        int minVertex = -1;

        for (int i = 0; i < distances.length; i++) {
            if (!visited[i] && distances[i] < minDistance) {
                minDistance = distances[i];
                minVertex = i;
            }
        }

        return minVertex;
    }

    public List<Edge<T>>[][] getAdjacencyMatrix() {
        return adjacencyMatrix;
    }

    public void setAdjacencyMatrix(List<Edge<T>>[][] adjacencyMatrix) {
        this.adjacencyMatrix = adjacencyMatrix;
    }

    public void setVertices(List<Vertex<T>> vertices) {
        this.vertices = vertices;
    }


    
}