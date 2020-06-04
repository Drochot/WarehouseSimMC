package com.nhlstenden.amazonsimulatie.models;

import java.util.ArrayList;
/* Deze class bevat de route voor de brengrobot/cart
*/
public class CargoPath {

    private GraphWeighted graphWeighted = new GraphWeighted();

    private NodeWeighted entrance = new NodeWeighted(1, "Entrance", 3.5, -3.5);
    private NodeWeighted corner = new NodeWeighted(3, "Corner", 3.5, 11.5);
    private NodeWeighted mine = new NodeWeighted(2, "Mine", 7.5, 11.5);

    public CargoPath(){
        //edges worden toegevoegd
        addEdges();
    }

    //voeg edges toe aan de graaf
    public void addEdges() {
        graphWeighted.addEdge(entrance, corner, 1);
        graphWeighted.addEdge(corner, mine, 1);
    }

    //get kortste route entrance naar mine
    public ArrayList<IntPair> getNodesEM() {
        return graphWeighted.DijkstraShortestPath(entrance, mine);
    }

    //get kortste route mine naar entrance
    public ArrayList<IntPair> getNodesME() {
        return graphWeighted.DijkstraShortestPath(mine, entrance);
    }

    //getter
    public NodeWeighted getMine(){
        return mine;
    }

    //getter
    public NodeWeighted getEntrance(){
        return entrance;
    }
}