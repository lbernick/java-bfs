import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Queue;

public class Vertex {
    private int row, col;
    private Set<Vertex> neighbors;

    // Vertex doesn't store a "parent" field,
    // because that depends on the BFS.
    // Unlike "neighbors", it's not a property of the graph.

    public Vertex(int row, int col) {
        this.row = row;
        this.col = col;
        this.neighbors = new HashSet<Vertex>();
    }

    @Override
    public boolean equals(Object other){
        return (other instanceof Vertex) && this.sameValue((Vertex)other);
    }

    private boolean sameValue(Vertex other) {
        // Assume that the location uniquely identifies the vertex for this solution,
        // and comparing neighbors is not needed
        return (this.row == other.row) && (this.col == other.col);
    }

    @Override
    public int hashCode() {
        return row + col;
    }

    public void addNeighbor(Vertex other){
        neighbors.add(other);
    }

    public static List<Vertex> shortestPath(Vertex start, Vertex end) {
        Set<Vertex> visited = new HashSet<Vertex>();
        Queue<Vertex> queue = new LinkedList<Vertex>();
        Map<Vertex, Vertex> parents = new HashMap<Vertex, Vertex>();

        queue.add(start);
        Vertex current = start;

        while (queue.peek() != null) {
            current = queue.remove();
            visited.add(current);
            if (current.equals(end)) {
                break;
            }
            
            for (Vertex n : current.neighbors) {
                if (visited.contains(n)) {
                    continue;
                }
                parents.put(n, current);
                queue.add(n);
            }
        }

        List<Vertex> path = new ArrayList<Vertex>();
        while (current != null) {
            path.add(0, current);
            current = parents.get(current);
        }

        return path;
    }

    @Override
    public String toString(){
        return "location: ( " + row + " , " + col + " ); num neighbors: " + neighbors.size();
    }
}
