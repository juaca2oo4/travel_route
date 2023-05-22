package ui;

import model.*;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        GraphList<String> graph = new GraphList<>();

        Vertex<String> v1 = new Vertex<>("A");
        Vertex<String> v2 = new Vertex<>("B");
        Vertex<String> v3 = new Vertex<>("C");
        Vertex<String> v4 = new Vertex<>("D");
        Vertex<String> v5 = new Vertex<>("E");

        graph.addVertex(v1);
        graph.addVertex(v2);
        graph.addVertex(v3);
        graph.addVertex(v4);
        graph.addVertex(v5);

        graph.addEdge(v1, v2, 10, 20);
        graph.addEdge(v2, v3, 15, 25);
        graph.addEdge(v2, v4, 5, 10);
        graph.addEdge(v4, v5, 8, 15);

        List<Vertex<String>> shortestPath = graph.dijkstra(v1, v5, "cost");
        List<Vertex<String>> shortestPath1 = graph.dijkstra(v1, v5, "cost");

        int totalCost = graph.bfs(shortestPath, "cost");

        int totalDistance = graph.bfs(shortestPath1, "distance");

        System.out.println("Valor total del costo durante el BFS: " + totalCost);
        System.out.println("Valor total de la distancia durante el BFS: " + totalDistance);


        GraphMatriz<String> graph1 = new GraphMatriz<>(5);


        Vertex<String> v11 = new Vertex<>("A");
        Vertex<String> v22 = new Vertex<>("B");
        Vertex<String> v33 = new Vertex<>("C");
        Vertex<String> v44 = new Vertex<>("D");
        Vertex<String> v55 = new Vertex<>("E");

        graph1.addVertex(v11);
        graph1.addVertex(v22);
        graph1.addVertex(v33);
        graph1.addVertex(v44);
        graph1.addVertex(v55);

        graph1.addEdge(v11, v22, 10, 20);
        graph1.addEdge(v22, v33, 15, 25);
        graph1.addEdge(v22, v44, 5, 10);
        graph1.addEdge(v44, v55, 8, 15);

        List<Vertex<String>> shortestPath2 = graph1.dijkstra(v11, v55, "cost");

        int totalCost1 = graph1.bfs(shortestPath2, "cost");

        int totalDistance1 = graph1.bfs(shortestPath2, "distance");

        System.out.println("Camino más corto: " + shortestPath2);
        System.out.println("Valor total del costo durante el BFS: " + totalCost1);
        System.out.println("Valor total de la distancia durante el BFS: " + totalDistance1);
    }

    }
