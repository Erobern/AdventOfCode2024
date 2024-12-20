package adventofcode2024.solvers;

import adventofcode2024.fileloaders.FileLoaders;
import adventofcode2024.utils.records.Coordinate;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicReference;

public class Day10 {
    public static String Puzzle1(String input) {
        List<String> lines = FileLoaders.loadInputIntoStringList(input);

        String[][] grid = new String[lines.size()][lines.getFirst().length()];
        List<Coordinate> zeros = new ArrayList<>();

        for (int row = 0; row < lines.size(); row++) {
            for (int col = 0; col < lines.get(row).length(); col++) {
                grid[row][col] = String.valueOf(lines.get(row).charAt(col));
                if (String.valueOf(lines.get(row).charAt(col)).equals("0")) {
                    zeros.add(new Coordinate(row, col));
                }
            }
        }

        AtomicReference<Integer> sum = new AtomicReference<>(0);

        // for each zero, calculate score
        for (Coordinate zero : zeros) {
            List<Coordinate> currentCoords = new ArrayList<>(Collections.singleton(zero));
            List<Coordinate> nextCoords = new ArrayList<>();

            for (int i = 1; i < 10; i++) {

                int finalI = i;
                for (Coordinate cur : currentCoords) {
                    // try up
                    try {
                        if (Objects.equals(grid[cur.row() - 1][cur.col()], String.valueOf(finalI))) {
                            addNextCoord(nextCoords, new Coordinate(cur.row() - 1, cur.col()));
                        }
                    } catch (Exception ignored) {

                    }
                    // try down
                    try {
                        if (Objects.equals(grid[cur.row() + 1][cur.col()], String.valueOf(finalI))) {
                            addNextCoord(nextCoords, new Coordinate(cur.row() + 1, cur.col()));
                        }
                    } catch (Exception ignored) {

                    }
                    // try left
                    try {
                        if (Objects.equals(grid[cur.row()][cur.col() - 1], String.valueOf(finalI))) {
                            addNextCoord(nextCoords, new Coordinate(cur.row(), cur.col() - 1));
                        }
                    } catch (Exception ignored) {

                    }
                    // try right
                    try {
                        if (Objects.equals(grid[cur.row()][cur.col() + 1], String.valueOf(finalI))) {
                            addNextCoord(nextCoords, new Coordinate(cur.row(), cur.col() + 1));
                        }
                    } catch (Exception ignored) {

                    }
                }

                currentCoords = new ArrayList<>(nextCoords);
                nextCoords = new ArrayList<>();

            }

            List<Coordinate> finalCurrentCoords = currentCoords;
            sum.updateAndGet(v -> v + finalCurrentCoords.size());
        }

        return sum.toString();
    }

    private static void addNextCoord(List<Coordinate> nextCoords, Coordinate newCoord) {
        if (nextCoords.stream().noneMatch(coord -> coord.row() == newCoord.row() && coord.col() == newCoord.col())) {
            nextCoords.add(newCoord);
        }
    }

    public static String Puzzle2(String input) {
        List<String> lines = FileLoaders.loadInputIntoStringList(input);

        String[][] grid = new String[lines.size()][lines.getFirst().length()];
        List<Coordinate> zeros = new ArrayList<>();

        for (int row = 0; row < lines.size(); row++) {
            for (int col = 0; col < lines.get(row).length(); col++) {
                grid[row][col] = String.valueOf(lines.get(row).charAt(col));
                if (String.valueOf(lines.get(row).charAt(col)).equals("0")) {
                    zeros.add(new Coordinate(row, col));
                }
            }
        }

        AtomicReference<Integer> sum = new AtomicReference<>(0);

        // for each zero, calculate score
        for (Coordinate zero : zeros) {
            List<Coordinate> currentCoords = new ArrayList<>(Collections.singleton(zero));
            List<Coordinate> nextCoords = new ArrayList<>();

            for (int i = 1; i < 10; i++) {

                int finalI = i;
                for (Coordinate cur : currentCoords) {
                    // try up
                    try {
                        if (Objects.equals(grid[cur.row() - 1][cur.col()], String.valueOf(finalI))) {
                            nextCoords.add(new Coordinate(cur.row() - 1, cur.col()));
                        }
                    } catch (Exception ignored) {

                    }
                    // try down
                    try {
                        if (Objects.equals(grid[cur.row() + 1][cur.col()], String.valueOf(finalI))) {
                            nextCoords.add(new Coordinate(cur.row() + 1, cur.col()));
                        }
                    } catch (Exception ignored) {

                    }
                    // try left
                    try {
                        if (Objects.equals(grid[cur.row()][cur.col() - 1], String.valueOf(finalI))) {
                            nextCoords.add(new Coordinate(cur.row(), cur.col() - 1));
                        }
                    } catch (Exception ignored) {

                    }
                    // try right
                    try {
                        if (Objects.equals(grid[cur.row()][cur.col() + 1], String.valueOf(finalI))) {
                            nextCoords.add(new Coordinate(cur.row(), cur.col() + 1));
                        }
                    } catch (Exception ignored) {

                    }
                }

                currentCoords = new ArrayList<>(nextCoords);
                nextCoords = new ArrayList<>();

            }

            List<Coordinate> finalCurrentCoords = currentCoords;
            sum.updateAndGet(v -> v + finalCurrentCoords.size());
        }

        return sum.toString();
    }
}
