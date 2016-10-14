package dijkstra.algorithm;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.concurrent.LinkedBlockingQueue;

import dijkstra.model.Edge;
import dijkstra.model.Graph;
import dijkstra.model.Vertex;

public class DijkstraAlgorithm {
    private final List<Vertex> nodes;
    private final List<Edge> edges;
    private Queue<Vertex> fringes;
    private Map<Vertex, Vertex> predecessors;
    private Map<Vertex, Integer> distance;
    private final Vertex startNode;
    private final Vertex endNode;
    
    
    public DijkstraAlgorithm(Graph graph, Vertex startNode, Vertex endNode) {
        this.nodes = new ArrayList<Vertex> (graph.getVertexes());
        this.edges = new ArrayList<Edge> (graph.getEdges());
        this.startNode = startNode;
        this.endNode = endNode;
        fringes = new LinkedBlockingQueue<Vertex>();
        distance = new HashMap<Vertex, Integer>();
        predecessors = new HashMap<Vertex, Vertex>();
        
        distance.put(startNode, Integer.MAX_VALUE);
        fringes.add(startNode);
    }
    
    public void execute() {
        while(fringes.peek()!=null) {
            Vertex fringe = fringes.poll();
            Set<Vertex> neighbours = listNeighbours(fringe);
            for(Vertex neighbour : neighbours){
                int newDistance = findDistance(startNode, fringe) + findDistance(fringe, neighbour);
                if(newDistance < findDistance(startNode, neighbour)) {
                    distance.put(neighbour, newDistance);
                    predecessors.put(neighbour, fringe);
                    fringes.add(neighbour);
                }
            }
        }
    }
    
    public LinkedList<Vertex> getPath() {
        LinkedList<Vertex> path = new LinkedList<Vertex> ();
        Vertex currentNode = endNode;
        if(predecessors.get(currentNode) == null)
            return null;
        
        path.add(currentNode);
        while(predecessors.get(currentNode) != null) {
            currentNode = predecessors.get(currentNode);
            path.add(currentNode);
        }
        
        Collections.reverse(path);
        return path;
    }
    
    public int findDistance(Vertex from, Vertex to) {
        if(from.equals(startNode) && distance.containsKey(to))
            return distance.get(to);
        
        for(Edge edge : edges) {
            if(edge.getFrom().equals(from) && edge.getTo().equals(to))
                return edge.getWeight();
        }
        
        return Integer.MAX_VALUE;
    }
    
    public Set<Vertex> listNeighbours(Vertex node) {
        Set<Vertex> result = new HashSet<Vertex>();
        for(Edge edge : edges) {
            if(edge.getFrom().equals(node))
                result.add(edge.getTo());
        }
        return result;
    }
}
