package model;

import java.awt.*;
import java.util.ArrayList;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class  Controller {

   private GraphList<String> graph;
   private GraphMatriz<String> graph1 ;


   public Controller(){
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
                Vertex<String>  vertex= new Vertex<>(atributs[0]);
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

    public  String bestWayList(String city1, String city2, int type){
       String msj = "El mejor camino para llegar de " + city1 + " a " + city2+ " son los vuelos de : \n";
       String factor;
       if(type ==1){
           factor ="cost";
       } else {
           factor = "distance";
       }

       Vertex<String>  vertex1 =graph.searchVertex(city1);
       Vertex<String>  vertex2 =graph.searchVertex(city2);
       List<Vertex<String>> resultList;
       resultList =  graph.dijkstra(vertex1,vertex2,factor);
        msj += printList(resultList);

        int value = graph.bfs(resultList,factor);

        msj += "\n con factor de costo o distancia de :" + value;
    return  msj;
    }

    public  String bestWayMatriz(String city1, String city2, int type){
        String msj = "El mejor camino para llegar de " + city1 + " a " + city2+ " son los vuelos de : \n";
        String factor;
        if(type ==1){
            factor ="cost";
        } else {
            factor = "distance";
        }

        Vertex<String>  vertex1 =graph1.searchVertex(city1);
        Vertex<String>  vertex2 =graph1.searchVertex(city2);
        List<Vertex<String>> resultList;
        resultList =  graph1.dijkstra(vertex1,vertex2,factor);
        msj += printList(resultList);

        int value = graph1.bfs(resultList,factor);

        msj += "\ncon factor de costo o distancia de : " + value;
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
    public String printCities(){
       String msj = "Ciudades disponibles: \n";
        for (Vertex<String> element : graph.getVertices()) {
            msj += element.getData() + ", ";
        }
       return  msj;
    }

}
