package model.Other_Implementations;
import java.util.ArrayList;
import java.util.List;

public class DFS<T> {
    private ArrayList<DFS_vertex<T>> vertices;

    int distance;
    public DFS() {
        vertices = new ArrayList<>();
    }
    public void addVertex(T element) {
        vertices.add(new DFS_vertex<>(element, new ArrayList<>()));
    }
    public void addEdge(T elementA, T elementB) {
        for (DFS_vertex<T> g: vertices) {
            if(g.getValue().equals(elementA)) {
                for (DFS_vertex<T> f: vertices) {
                    if(f.getValue().equals(elementB)){
                        f.setDistance(distance);
                        g.getAdjacentList().add(f);
                        f.getAdjacentList().add(g);
                    }
                }
            }
        }
    }
    public List<DFS_vertex<T>> DFSRoute() { //Recorrido por profundidad (El nodo origen es el primer que se haya añadido)
        List<DFS_vertex<T>> visitedVertices = new ArrayList<>(); //Misma logica que el recorrido DFS que me retorna los arboles del grafo.
        for (DFS_vertex<T> vertex : vertices) {//Inicio el recorrido por el primer vertice añadido
            vertex.setPrevious(null);
            vertex.setColor(DFS_Vertex_Color.WHITE);
            vertex.setDistance(0);
        }
        for (DFS_vertex<T> vertex : vertices) {
            if (vertex.getColor() == DFS_Vertex_Color.WHITE) {
                DFSVisit1(vertex, visitedVertices);//LLamo al método de visita, pasandole el vertice que voy a recorrer y el arraylist de recorridos.
            }
        }
        return visitedVertices;//La diferencia radica en que aquí retorno una lista que me representa el recorrido.
    }
    public void DFSVisit1(DFS_vertex<T> initialVertex, List<DFS_vertex<T>> visitedVertices) {
        visitedVertices.add(initialVertex);
        initialVertex.setColor(DFS_Vertex_Color.GRAY);
        for (DFS_vertex<T> adjacencyNode : initialVertex.getAdjacentList()) {
            if (adjacencyNode.getColor() == DFS_Vertex_Color.WHITE) {
                adjacencyNode.setPrevious(initialVertex);
                adjacencyNode.setDistance(initialVertex.getDistance()+1);
                DFSVisit1(adjacencyNode, visitedVertices);
            }
        }
        initialVertex.setColor(DFS_Vertex_Color.BLACK);
    }
    public int DFSTrees(){//Recorrido por profundidad, pero me retorna los arboles que se pueden formar en el grafo
        int trees = 0;
        for (DFS_vertex<T> graphNode: vertices) {
            graphNode.setPrevious(null); //Seteo todos los predecesores de los vertices como null
            graphNode.setColor(DFS_Vertex_Color.WHITE); //Cambio su color
            graphNode.setDistance(0);//La distancia me representa el timestamp, inicia en 0.
        }
        for (DFS_vertex<T> vertex: vertices) { //Recorro todos los vertices
            if(vertex.getColor() == DFS_Vertex_Color.WHITE) { //Los seteo como blancos
                trees++; //trees, representa a los arboles que componen el grafo. Si exite más de uno, significa que hay vertices no conexos.
                DFSVisit(vertex); //Llamo el metodo visit (Es el que va recorriendo el grafo)
            }
        }
        return trees;
    }
    public void DFSVisit(DFS_vertex<T> vertex){
        distance++;//Voy aumentando la distacia del vertice "origen"
        vertex.setDistance(distance);//Le seteo la distancia al vertice que voy a recorrer
        vertex.setColor(DFS_Vertex_Color.GRAY); //seteo su color
        for (DFS_vertex<T> adjacencyNode: vertex.getAdjacentList()) { //Obtengo sus adyacentes
            if(adjacencyNode.getColor() == DFS_Vertex_Color.WHITE) {
                adjacencyNode.setPrevious(vertex); //Al adyacente, le seteo al vertice como padre.
                DFSVisit(adjacencyNode); //Al ser por profundidad, obtengo los adyacentes del adyacente.
            }
        }
        vertex.setColor(DFS_Vertex_Color.BLACK);//Cuando se termina el llamado recursivo, seteo el vertice recorrido a negro.
        distance++;
        vertex.setDistance(distance);
    }
}
