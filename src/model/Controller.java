package model;

import java.util.ArrayList;
import java.io.*;
import java.nio.charset.StandardCharsets;

public class Controller {

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

}
