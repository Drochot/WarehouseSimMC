package com.nhlstenden.amazonsimulatie.models;

import java.util.ArrayList;

public class ShippingPath {

    private GraphWeighted graphWeighted = new GraphWeighted();

    private NodeWeighted entrance = new NodeWeighted(1, "Entrance", -3.5, -3.5);
    private NodeWeighted destination = new NodeWeighted(2, "Destination", -3.5, 15.5);

    public ShippingPath(){
        addEdges();
    }

    public void addEdges() {
        graphWeighted.addEdge(entrance, destination, 1);
    }

    public ArrayList<IntPair> getNodesED() {
        return graphWeighted.DijkstraShortestPath(entrance, destination);
    }

    public ArrayList<IntPair> getNodesDE() {
        return graphWeighted.DijkstraShortestPath(destination, entrance);
    }

    public NodeWeighted getDestination(){
        return destination;
    }

    public NodeWeighted getEntrance(){
        return entrance;
    }
}