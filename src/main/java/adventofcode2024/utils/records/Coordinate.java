package adventofcode2024.utils.records;

public record Coordinate(Integer row, Integer col) {
    public String toString() {
        return row + "," + col;
    }

    public boolean equals(Coordinate aCoordinate) {
        return row == aCoordinate.row() && col == aCoordinate.col();
    }
}
