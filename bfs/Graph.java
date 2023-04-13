import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Graph {
    Set<Vertex> vertices;

    public Graph(boolean[][] grid){  // True = passable; false = blocked
        vertices = new HashSet<Vertex>();
        if (grid.length == 0) {
            return;
        }

        Vertex[][] v = new Vertex[grid.length][grid[0].length];

        // Create vertices and store them in a temporary grid
        // so we have an easy mapping from location -> Vertex
        for (int i = 0; i < grid.length; i++){
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j]) {
                    v[i][j] = new Vertex(i, j);
                }
            }
        }

        // Build up the neighbor relationships of each Vertex
        for (int i = 0; i < grid.length; i++){
            for (int j = 0; j < grid[0].length; j++) {
                Vertex current = v[i][j];
                if (current == null) {
                    continue;
                }
                // left
                if (i > 0 && v[i - 1][j] != null) {
                    current.addNeighbor(v[i - 1][j]);
                }
                // right
                if (i < grid.length - 1 && v[i + 1][j] != null) {
                    current.addNeighbor(v[i + 1][j]);
                }
                // top
                if (j > 0 && v[i][j - 1] != null) {
                    current.addNeighbor(v[i][j - 1]);
                }
                // bottom
                if (j < grid[0].length - 1 && v[i][j + 1] != null) {
                    current.addNeighbor(v[i][j + 1]);
                }

                vertices.add(current);
            }
        }        
    }

    @Override
    public String toString(){
        String out = "";
        for (Vertex v : vertices){
            out = out + v.toString() + "\n";
        }
        return out;
    }

    public static void main(String []args){
        boolean[][] values = {
            {true, false, true, true, true},
            {true, false, true, false, true},
            {true, true, true, false, true},
            {true, true, false, true, true},
        };

        Graph graph = new Graph(values);
        System.out.println(graph);

        Vertex[] v = {};
        Vertex[] vertexArray = graph.vertices.toArray(v);
        Vertex start = vertexArray[0];
        Vertex end = vertexArray[vertexArray.length -1];
        List<Vertex> path = Vertex.shortestPath(start, end);

        System.out.println("---------------------------------");
        System.out.println("Shortest path:");
        for (Vertex p : path) {
            System.out.println(p);
        }
    }
}