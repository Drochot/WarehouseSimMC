package com.nhlstenden.amazonsimulatie.models;

import java.util.*;

//Deze class returned een lijst van coordinaten die de snelste weg aangeeft tussen de 2 gegeven nodes.
//Hierin word het Dijkstra algoritme ook uitgevoerd.

public class GraphWeighted {
    private Set<NodeWeighted> nodes;
    

    public GraphWeighted() {
        nodes = new HashSet<>();
    }


 public void addNode(NodeWeighted... n) {
	 nodes.addAll(Arrays.asList(n));
 }
 
 public void addEdge(NodeWeighted source, NodeWeighted destination, double weight) {
	 	nodes.add(source);
	    nodes.add(destination);

	    addEdgeHelper(source, destination, weight);

	    if (source != destination) {
	        addEdgeHelper(destination, source, weight);
	    }
	}

	private void addEdgeHelper(NodeWeighted a, NodeWeighted b, double weight) {
	    for (EdgeWeighted edge : a.edges) {
	        if (edge.source == a && edge.destination == b) {
	            
	            edge.weight = weight;
	            return;
	        }
	    }
	    	    
	    a.edges.add(new EdgeWeighted(a, b, weight));
	}
	
	
	
	public boolean hasEdge(NodeWeighted source, NodeWeighted destination) {
	    LinkedList<EdgeWeighted> edges = source.edges;
	    for (EdgeWeighted edge : edges) {
	        if (edge.destination == destination) {
	            return true;
	        }
	    }
	    return false;
	}
	
	
	//returned een ArrayList van de lijst coordinaten die de snelste route aangeven tussen punt a en b.
	public ArrayList<IntPair> DijkstraShortestPath(NodeWeighted start, NodeWeighted end) {
		//Hier houden we bij welk pad voor iedere node het snelst is.
	    HashMap<NodeWeighted, NodeWeighted> changedAt = new HashMap<>();
	    changedAt.put(start, null);

	    HashMap<NodeWeighted, Double> shortestPathMap = new HashMap<>();

	    for (NodeWeighted node : nodes) {
	        if (node == start)
	            shortestPathMap.put(start, 0.0);
	        else shortestPathMap.put(node, Double.POSITIVE_INFINITY);
	    }

	    for (EdgeWeighted edge : start.edges) {
	        shortestPathMap.put(edge.destination, edge.weight);
	        changedAt.put(edge.destination, start);
	    }

	    start.visit();

	
		//Deze loop gaat door totdat er geen unvisited nodes meer zijn.
	    while (true) {
	        NodeWeighted currentNode = closestReachableUnvisited(shortestPathMap);

	        if (currentNode == end) {
	        	
	        	NodeWeighted child = end;
				// Als de dichtsbijzijnde niet bezochte node onze eindbestemming is willen we de lijst van coordinaten van het pad teruggeven.
				ArrayList<IntPair> coordinaten = new ArrayList<IntPair>();
				coordinaten.add(end.coords);
	            while (true) {
	                NodeWeighted parent = changedAt.get(child);
	                if (parent == null) {
	                    break;
	                }
	               child = parent;
	               coordinaten.add(0, parent.coords);
	            }
	            
	            return coordinaten;
			}			
	        currentNode.visit();

	        //hierbij word gekeken of er nog een pad bestaat met een lagere afstand.
	        for (EdgeWeighted edge : currentNode.edges) {
	            if (edge.destination.isVisited())
	                continue;

	            if (shortestPathMap.get(currentNode)
	               + edge.weight
	               < shortestPathMap.get(edge.destination)) {
	                shortestPathMap.put(edge.destination,
	            	shortestPathMap.get(currentNode) + edge.weight);
	                changedAt.put(edge.destination, currentNode);
	            }
	        }
	    }
	}

	//Methode die aangeeft welke niet bezochte node het minste afstand heeft.
	private NodeWeighted closestReachableUnvisited(HashMap<NodeWeighted, Double> shortestPathMap) {

	    double shortestDistance = Double.POSITIVE_INFINITY;
	    NodeWeighted closestReachableNode = null;
	    for (NodeWeighted node : nodes) {
	        if (node.isVisited())
	            continue;

	        double currentDistance = shortestPathMap.get(node);
	        if (currentDistance == Double.POSITIVE_INFINITY)
	            continue;

	        if (currentDistance < shortestDistance) {
	            shortestDistance = currentDistance;
	            closestReachableNode = node;
	        }
	    }
	    return closestReachableNode;
	}
}