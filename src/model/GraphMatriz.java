package model;

import java.util.*;;

public class GraphMatriz<T> {
    private int[][] adjacencyMatrix;
    private List<Vertex<T>> vertices;

    public GraphMatriz(int numVertices) {
        adjacencyMatrix = new int[numVertices][numVertices];
        vertices = new ArrayList<>();
    }

    public void addVertex(Vertex<T> vertex) {
        vertices.add(vertex);
    }

    public void addEdge(Vertex<T> source, Vertex<T> destination, int cost, int distance) {
        int sourceIndex = vertices.indexOf(source);
        int destinationIndex = vertices.indexOf(destination);
        adjacencyMatrix[sourceIndex][destinationIndex] = cost;
        adjacencyMatrix[destinationIndex][sourceIndex] = distance;
    }

    public List<Vertex<T>> getVertices() {
        return vertices;
    }

    public List<Vertex<T>> dijkstra(Vertex<T> startVertex, Vertex<T> endVertex, String factor) {
        int start = vertices.indexOf(startVertex);
        int end = vertices.indexOf(endVertex);

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
                if (!visited[j] && adjacencyMatrix[minVertex][j] != 0) {
                    int currentDistance = distances[minVertex] + (factor.equals("cost") ? adjacencyMatrix[minVertex][j] : adjacencyMatrix[j][minVertex]);
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

        queue.offer(path.get(0));

        while (!queue.isEmpty()) {
            Vertex<T> currentVertex = queue.poll();
            visited.add(currentVertex);

            int currentVertexIndex = vertices.indexOf(currentVertex);
            for (int i = 0; i < vertices.size(); i++) {
                Vertex<T> nextVertex = vertices.get(i);
                if (path.contains(nextVertex) && !visited.contains(nextVertex) && adjacencyMatrix[currentVertexIndex][i] != 0) {
                    queue.offer(nextVertex);

                    int value = factor.equals("cost") ? adjacencyMatrix[currentVertexIndex][i] : adjacencyMatrix[i][currentVertexIndex];
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
}