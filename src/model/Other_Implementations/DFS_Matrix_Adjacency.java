package model.Other_Implementations;

import java.util.ArrayList;
import java.util.List;

public class DFS_Matrix_Adjacency<T> {
    private ArrayList<DFS_vertex<T>> vertices;
    private int[][] adjacencyMatrix;
    private int distance;

    public DFS_Matrix_Adjacency(int numVertices) {
        vertices = new ArrayList<>();
        adjacencyMatrix = new int[numVertices][numVertices];
    }

    public void addVertex(T element) {
        vertices.add(new DFS_vertex<>(element, new ArrayList<>()));
    }

    public void addEdge(T elementA, T elementB) {
        int indexA = searchVertex(elementA);
        int indexB = searchVertex(elementB);
        if (indexA != -1 && indexB != -1) {
            adjacencyMatrix[indexA][indexB] = 1;
            adjacencyMatrix[indexB][indexA] = 1;
        } else {
            throw new IllegalArgumentException("One or both vertices not found.");
        }
    }

    public int searchVertex(T element) {
        for (int i = 0; i < vertices.size(); i++) {
            if (vertices.get(i).getValue().equals(element)) {
                return i;
            }
        }
        return -1;
    }
    public List<DFS_vertex<T>> DFSRoute() {
        List<DFS_vertex<T>> visitedVertices = new ArrayList<>();
        for (DFS_vertex<T> vertex : vertices) {
            vertex.setPrevious(null);
            vertex.setColor(DFS_Vertex_Color.WHITE);
            vertex.setDistance(0);
        }
        for (DFS_vertex<T> vertex : vertices) {
            if (vertex.getColor() == DFS_Vertex_Color.WHITE) {
                DFSVisit(vertex, visitedVertices);
            }
        }
        return visitedVertices;
    }

    public void DFSVisit(DFS_vertex<T> initialVertex, List<DFS_vertex<T>> visitedVertices) {
        visitedVertices.add(initialVertex);
        initialVertex.setColor(DFS_Vertex_Color.GRAY);
        for (int i = 0; i < vertices.size(); i++) {
            if (adjacencyMatrix[vertices.indexOf(initialVertex)][i] == 1) {
                DFS_vertex<T> adjacencyNode = vertices.get(i);
                if (adjacencyNode.getColor() == DFS_Vertex_Color.WHITE) {
                    adjacencyNode.setPrevious(initialVertex);
                    adjacencyNode.setDistance(initialVertex.getDistance() + 1);
                    DFSVisit(adjacencyNode, visitedVertices);
                }
            }
        }
        initialVertex.setColor(DFS_Vertex_Color.BLACK);
    }

    public int DFSTrees() {
        int trees = 0;
        for (DFS_vertex<T> graphNode : vertices) {
            graphNode.setPrevious(null);
            graphNode.setColor(DFS_Vertex_Color.WHITE);
            graphNode.setDistance(0);
        }
        for (DFS_vertex<T> vertex : vertices) {
            if (vertex.getColor() == DFS_Vertex_Color.WHITE) {
                trees++;
                DFSVisit(vertex);
            }
        }
        return trees;
    }

    public void DFSVisit(DFS_vertex<T> vertex) {
        distance++;
        vertex.setDistance(distance);
        vertex.setColor(DFS_Vertex_Color.GRAY);
        for (int i = 0; i < vertices.size(); i++) {
            if (adjacencyMatrix[vertices.indexOf(vertex)][i] == 1) {
                DFS_vertex<T> adjacencyNode = vertices.get(i);
                if (adjacencyNode.getColor() == DFS_Vertex_Color.WHITE) {
                    adjacencyNode.setPrevious(vertex);
                    DFSVisit(adjacencyNode);
                }
            }
        }
        vertex.setColor(DFS_Vertex_Color.BLACK);
        distance++;
        vertex.setDistance(distance);
    }
}
