package dijkstra.model;

public class Edge {
    private final String id;
    private final Vertex from;
    private final Vertex to;
    private final int weight;
    
    public Edge(String id, Vertex from, Vertex to, int weight) {
        this.id = id;
        this.from = from;
        this.to = to;
        this.weight = weight;
    }

    public String getId() {
        return this.id;
    }
    
    public Vertex getFrom() {
        return from;
    }
    
    public Vertex getTo() {
        return to;
    }
    
    public int getWeight() {
        return weight;
    }
}
