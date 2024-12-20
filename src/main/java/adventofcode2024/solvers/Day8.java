package adventofcode2024.solvers;

import adventofcode2024.fileloaders.FileLoaders;
import adventofcode2024.utils.records.Coordinate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

public class Day8 {
    public static String Puzzle1(String input) {
        List<String> lines = FileLoaders.loadInputIntoStringList(input);

        HashMap<String, List<Coordinate>> emitters = new HashMap<>();

        String[][] grid = new String[lines.size()][lines.getFirst().length()];
        String[][] antinodeGrid = new String[lines.size()][lines.getFirst().length()];

        for (int row = 0; row < lines.size(); row++) {
            for (int col = 0; col < lines.get(row).length(); col++) {
                String frequency = String.valueOf(lines.get(row).charAt(col));
                grid[row][col] = frequency;
                if (!frequency.equals(".")) {
                    if (!emitters.containsKey(frequency)) {
                        emitters.put(frequency, List.of(new Coordinate(row, col)));
                    } else {
                        List<Coordinate> coordinates = new ArrayList<>(emitters.get(frequency));
                        coordinates.add(new Coordinate(row, col));
                        emitters.put(frequency, coordinates);
                    }
                }
            }
        }

        for (String emitter : emitters.keySet()) {
            for (int i = 0; i < emitters.get(emitter).size(); i++) {
                // for every emitter, consult every other emitter and place an anitnode
                Coordinate coordinate = emitters.get(emitter).get(i);
                List<Coordinate> otherCoordinates = new ArrayList<>(emitters.get(emitter));
                otherCoordinates.remove(i);

                for (Coordinate otherCoordinate : otherCoordinates) {
                    Integer rowOffset = otherCoordinate.row() - coordinate.row();
                    Integer colOffset = otherCoordinate.col() - coordinate.col();

                    try {
                        antinodeGrid[coordinate.row() - rowOffset][coordinate.col() - colOffset] = "#";
                    } catch (Exception ignored) {
                        // who cares
                    }
                }
            }
        }

        Integer sum = 0;

        for (int row = 0; row < lines.size(); row++) {
            for (int col = 0; col < lines.get(row).length(); col++) {
                if (antinodeGrid[row][col] == "#") {
                    sum++;
                }
            }
        }

        return sum.toString();
    }

    public static String Puzzle2(String input) {
        List<String> lines = FileLoaders.loadInputIntoStringList(input);

        HashMap<String, List<Coordinate>> emitters = new HashMap<>();

        String[][] grid = new String[lines.size()][lines.getFirst().length()];
        String[][] antinodeGrid = new String[lines.size()][lines.getFirst().length()];

        for (int row = 0; row < lines.size(); row++) {
            for (int col = 0; col < lines.get(row).length(); col++) {
                String frequency = String.valueOf(lines.get(row).charAt(col));
                grid[row][col] = frequency;
                if (!frequency.equals(".")) {
                    if (!emitters.containsKey(frequency)) {
                        emitters.put(frequency, List.of(new Coordinate(row, col)));
                    } else {
                        List<Coordinate> coordinates = new ArrayList<>(emitters.get(frequency));
                        coordinates.add(new Coordinate(row, col));
                        emitters.put(frequency, coordinates);
                    }
                }
            }
        }

        for (String emitter : emitters.keySet()) {
            for (int i = 0; i < emitters.get(emitter).size(); i++) {
                // for every emitter, consult every other emitter and place an antinode
                Coordinate coordinate = emitters.get(emitter).get(i);
                List<Coordinate> otherCoordinates = new ArrayList<>(emitters.get(emitter));
                otherCoordinates.remove(i);

                for (Coordinate otherCoordinate : otherCoordinates) {
                    Integer rowOffset = otherCoordinate.row() - coordinate.row();
                    Integer colOffset = otherCoordinate.col() - coordinate.col();

                    boolean continueResonating = true;
                    Integer counter = 0;

                    while (continueResonating) {
                        try {
                            antinodeGrid[coordinate.row() - (rowOffset * counter)][coordinate.col() - (colOffset * counter)] = "#";
                            counter++;
                        } catch (Exception ignored) {
                            // this is our sign to quit
                            continueResonating = false;
                            counter = 0;
                        }
                    }
                }
            }
        }

        Integer sum = 0;

        for (int row = 0; row < lines.size(); row++) {
            for (int col = 0; col < lines.get(row).length(); col++) {
                if (Objects.equals(antinodeGrid[row][col], "#")) {
                    sum++;
                }
            }
        }

        return sum.toString();
    }

}
