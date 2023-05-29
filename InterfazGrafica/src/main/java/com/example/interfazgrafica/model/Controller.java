package com.example.interfazgrafica.model;

import javafx.collections.ObservableList;

import java.io.*;
import java.util.List;

public class  Controller {

    private GraphList<String> graph;
    private GraphMatriz<String> graph1 ;
    private static Controller instance = null;

    public static Controller getInstance() { //Mecanismo para crear la instancia de la clase.
        if (instance == null) {
            instance = new Controller(); //Solo puedo crear una instancia (Por ello el if) (Solo una lista de contactos)
        }
        return instance;
    }
    private Controller(){
        graph = new GraphList<>();
        graph1 = new GraphMatriz<>(50);
    }

    public GraphList<String> getGraph() {
        return graph;
    }

    public GraphMatriz<String> getGraph1() {
        return graph1;
    }


    public void addDataVertex(String archivo) {
        File file = new File(archivo);
        try {
            FileInputStream fis = new FileInputStream(file);
            BufferedReader reader = new BufferedReader(new InputStreamReader(fis));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] atributs = line.split(" ");
                Vertex<String> vertex= new Vertex<>(atributs[0]);
                graph.addVertex(vertex);
                graph1.addVertex(vertex);
            }
            fis.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void addDataEdgeList(String archivo) {
        File file = new File(archivo);
        try {
            FileInputStream fis = new FileInputStream(file);
            BufferedReader reader = new BufferedReader(new InputStreamReader(fis));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] atributs = line.split(",");
                Vertex<String>  vertex1= graph.searchVertex(atributs[0]);
                Vertex<String>  vertex2= graph.searchVertex(atributs[1]);
                int cost = Integer.parseInt(atributs[2]);
                int distance = Integer.parseInt(atributs[3]);

                graph.addEdge(vertex1,vertex2,cost,distance);

            }
            fis.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void addDataEdgeMatriz(String archivo) {
        File file = new File(archivo);
        try {
            FileInputStream fis = new FileInputStream(file);
            BufferedReader reader = new BufferedReader(new InputStreamReader(fis));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] atributs = line.split(",");
                Vertex<String>  vertex1= graph1.searchVertex(atributs[0]);
                Vertex<String>  vertex2= graph1.searchVertex(atributs[1]);
                int cost = Integer.parseInt(atributs[2]);
                int distance = Integer.parseInt(atributs[3]);

                graph1.addEdge(vertex1,vertex2,cost,distance);
            }
            fis.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public  String bestWayList(String city1, String city2, String factor){
        String msj = "The best way to arrive from " + city1 + " to " + city2+ " are the flights of : \n";
        Vertex<String>  vertex1 =graph.searchVertex(city1);
        Vertex<String>  vertex2 =graph.searchVertex(city2);
        List<Vertex<String>> resultList;
        resultList =  graph.dijkstra(vertex1,vertex2,factor);
        msj += printList(resultList);

        int value = graph.bfs(resultList,factor);

        msj += "\nWith factor of cost or distance:" + value;
        return  msj;
    }

    public  String bestWayMatriz(String city1, String city2, String factor){
        String msj = "The best way to arrive from " + city1 + " to " + city2+ " are the flights of : \n";
        Vertex<String>  vertex1 =graph1.searchVertex(city1);
        Vertex<String>  vertex2 =graph1.searchVertex(city2);
        List<Vertex<String>> resultList;
        resultList =  graph1.dijkstra(vertex1,vertex2,factor);
        msj += printList(resultList);

        int value = graph1.bfs(resultList,factor);

        msj += "\nWith factor of cost or distance: " + value;
        return  msj;

    }

    public String  printList(List<Vertex<String>> list) {
        String msj ="";
        for (Vertex<String> item : list) {
            msj += item.getData() + " - ";
        }
        msj=msj.substring(0,msj.length()-3);
        return  msj;
    }
    public  String printCities(){
        String msj = "";
        for (int i=0; i<graph.getVertices().size(); i++){
            if(i%5==0){
                msj+="\n";
            }
            msj+= graph.getVertices().get(i).getData() + ", ";
        }
        return msj;
    }

}
