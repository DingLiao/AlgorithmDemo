package dijkstra.model;

public class Vertex {
    private final String id;
    private final String name;
    
    public Vertex(String id, String name) {
        this.id = id;
        this.name = name;
    }
    
    public String getId() {
        return id;
    }
    
    public String getName() {
        return name;
    }
    
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        return prime * result + (id==null ? 0:id.hashCode());
    }
    
    public String toString() {
        return this.name;
    }
}
