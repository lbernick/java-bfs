public class Location {
    int row, col;

    public Location(int row, int col) {
        this.row = row;
        this.col = col;
    }

    @Override
    public boolean equals(Object other){
        return (other instanceof Location) && this.sameValue((Location)other);
    }

    private boolean sameValue(Location other) {
        return (this.row == other.row) && (this.col == other.col);
    }

    @Override
    public int hashCode() {
        return row + col;
    }

    @Override
    public String toString() {
        return "( " + row + " , " + col + " )";
    }
}
