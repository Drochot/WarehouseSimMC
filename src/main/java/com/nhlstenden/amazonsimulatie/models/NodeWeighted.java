package com.nhlstenden.amazonsimulatie.models;

import java.util.*;

public class NodeWeighted {

    double n;
    String name;
    
    static ArrayList<NodeWeighted> weightedNodes = new ArrayList<NodeWeighted>();
    IntPair coords;
    
    private boolean visited;
    LinkedList<EdgeWeighted> edges;

    public NodeWeighted(int n, String name, double x, double y) {
        this.n = n;
        this.name = name;
        this.coords = new IntPair(x,y);
        
        
        visited = false;
        edges = new LinkedList<>();
    }

    boolean isVisited() {
        return visited;
    }

    void visit() {
        visited = true;
    }

    void unvisit() {
        visited = false;
    }

    
}