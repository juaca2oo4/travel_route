package model.Other_Implementations;

import java.util.ArrayList;

public class DFS_vertex<T> {
        private T value;
        private ArrayList<DFS_vertex<T>> adjacentList;
        private DFS_Vertex_Color color;
        private DFS_vertex<T> previous;
        private int distance;

        public DFS_vertex(T value, ArrayList<DFS_vertex<T>> adjacentList) {
            this.value = value;
            this.adjacentList = adjacentList;
        }
        public T getValue() {
            return value;
        }

        public void setValue(T value) {
            this.value = value;
        }

        public ArrayList<DFS_vertex<T>> getAdjacentList() {
            return adjacentList;
        }

        public void setAdjacentList(ArrayList<DFS_vertex<T>> adjacentList) {
            this.adjacentList = adjacentList;
        }

        public DFS_Vertex_Color getColor() {
            return color;
        }

        public void setColor(DFS_Vertex_Color color) {
            this.color = color;
        }

        public DFS_vertex<T> getPrevious() {
            return previous;
        }

        public void setPrevious(DFS_vertex<T> previous) {
            this.previous = previous;
        }

        public int getDistance() {
            return distance;
        }
        public void setDistance(int distance) {
            this.distance = distance;
        }
    }
