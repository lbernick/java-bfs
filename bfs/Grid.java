import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Queue;

class Grid {
    private boolean[][] values;  // True = passable; false = blocked

    public Grid(boolean[][] values) {
        this.values = values;
    }

    public List<Location> shortestPath(Location start, Location end){
        Set<Location> visited = new HashSet<Location>();
        Queue<Location> queue = new LinkedList<Location>();
        Map<Location, Location> parents = new HashMap<Location, Location>();

        queue.add(start);

        Location current = start;
        while (queue.peek() != null) {
            current = queue.remove();
            visited.add(current);
            if (current.equals(end)) {
                break;
            }

            for (Location n : neighbors(current)) {
                if (visited.contains(n)) {
                    continue;
                }
                parents.put(n, current);
                queue.add(n);
            }
        }

        List<Location> path = new ArrayList<Location>();
        while (current != null) {
            path.add(0, current);
            current = parents.get(current);
        }

        return path;
    }

    private Set<Location> neighbors(Location l) {
        Set<Location> out = new HashSet<Location>();

        // left
        if (l.row > 0 && values[l.row - 1][l.col]) {
            out.add(new Location(l.row - 1, l.col));
        }
        // right
        if (l.row < (values.length - 1) && values[l.row + 1][l.col]) {
            out.add(new Location(l.row + 1, l.col));
        }
        // top
        if (l.col > 0 && values[l.row][l.col - 1]) {
            out.add(new Location(l.row, l.col - 1));
        }
        // bottom
        if (l.col < (values[0].length - 1) && values[l.row][l.col + 1]) {
            out.add(new Location(l.row, l.col + 1));
        }

        return out;
    }

    public static void main(String []args) {
        boolean[][] values = {
            {true, false, true, true, true},
            {true, false, true, false, true},
            {true, true, true, false, true},
            {true, true, false, true, true},
        };
        Grid grid = new Grid(values);
        Location start = new Location(0, 0);
        Location end = new Location(3, 3);
        List<Location> path = grid.shortestPath(start, end);

        for (Location p : path) {
            System.out.println(p);
        }
    }
}
