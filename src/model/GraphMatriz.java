package model;

import java.util.*;;

public class GraphMatriz<T> {
    private Edge<T>[][] adjacencyMatrix;
    private List<Vertex<T>> vertices;

    public GraphMatriz(int numVertices) {
        adjacencyMatrix = new Edge[numVertices][numVertices];
        vertices = new ArrayList<>();
    }

    public boolean addVertex(Vertex<T> vertex) {
        for(int i=0; i<vertices.size();i++){
            if(vertices.get(i).getData().equals(vertex.getData())){
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
        Edge<T> edge = new Edge<>(source, destination, cost, distance);
        adjacencyMatrix[sourceIndex][destinationIndex] = edge;
        adjacencyMatrix[destinationIndex][sourceIndex] = edge;
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
                    int currentDistance = distances[minVertex] + (factor.equals("cost") ? adjacencyMatrix[minVertex][j].getCost() : adjacencyMatrix[minVertex][j].getDistance());
                    if (currentDistance < distances[j]) {
                        distances[j] = currentDistance;
                        parents[j] = minVertex;
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
                    queue.offer(nextVertex);

                    int value = factor.equals("cost") ? adjacencyMatrix[currentVertexIndex][i].getCost() : adjacencyMatrix[currentVertexIndex][i].getDistance();
                    totalValue += value;
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


    public void setVertices(List<Vertex<T>> vertices) {
        this.vertices = vertices;
    }

    public Edge<T>[][] getAdjacencyMatrix() {
        return adjacencyMatrix;
    }

    public void setAdjacencyMatrix(Edge<T>[][] adjacencyMatrix) {
        this.adjacencyMatrix = adjacencyMatrix;
    }

    

    
}