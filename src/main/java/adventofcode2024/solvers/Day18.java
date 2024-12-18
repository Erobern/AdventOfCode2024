package adventofcode2024.solvers;

import adventofcode2024.fileloaders.FileLoaders;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Day18 {
    public static String Puzzle1(String input, Integer gridSize, Integer bytesToTest) {
        List<String> lines = FileLoaders.loadInputIntoStringList(input);

        String[][] grid = new String[gridSize][gridSize];

        for (int i = 0; i < gridSize; i++) {
            for (int j =0; j < gridSize; j++) {
                grid[i][j] = ".";
            }
        }

        for (int i = 0; i < bytesToTest && i < lines.size(); i++ ){
            List<Integer> coords = Arrays.stream(lines.get(i).split(",")).map(Integer::parseInt).toList();
            grid[coords.get(0)][coords.get(1)] = "#";

        }

        List<Coordinate> currentCoordinates = new ArrayList<>(Collections.singleton(new Coordinate(0, 0)));
        List<Coordinate> nextCoordinates = new ArrayList<>();

        Integer stepCounter = 0;
        boolean exitFound = false;

        while (!exitFound) {

            for (Coordinate currentCoordinate : currentCoordinates) {
                // up
                try {
                    if (grid[currentCoordinate.row - 1][currentCoordinate.col].equals(".")) {
                        addNextCoordinate(new Coordinate(currentCoordinate.row - 1, currentCoordinate.col), nextCoordinates);
                    };
                } catch (Exception ignored) {

                }
                // down
                try {
                    if (grid[currentCoordinate.row + 1][currentCoordinate.col].equals(".")) {
                        addNextCoordinate(new Coordinate(currentCoordinate.row + 1, currentCoordinate.col), nextCoordinates);
                    };
                } catch (Exception ignored) {

                }
                // left
                try {
                    if (grid[currentCoordinate.row][currentCoordinate.col - 1].equals(".")) {
                        addNextCoordinate(new Coordinate(currentCoordinate.row, currentCoordinate.col - 1), nextCoordinates);
                    };
                } catch (Exception ignored) {

                }
                // right
                try {
                    if (grid[currentCoordinate.row][currentCoordinate.col + 1].equals(".")) {
                        addNextCoordinate(new Coordinate(currentCoordinate.row, currentCoordinate.col + 1), nextCoordinates);
                    };
                } catch (Exception ignored) {

                }
            }

            for (Coordinate currentCoordinate : currentCoordinates) {
                grid[currentCoordinate.row][currentCoordinate.col] = "O";
            }

            stepCounter++;

            if (nextCoordinates.stream().anyMatch(nextCoordinate -> nextCoordinate.row == gridSize - 1 &&
                    nextCoordinate.col == gridSize - 1)) {
                exitFound = true;
            }

            currentCoordinates = new ArrayList<>(nextCoordinates);
            nextCoordinates = new ArrayList<>();
        }

        return stepCounter.toString();
    }

    private static void addNextCoordinate(Coordinate testCoordinate, List<Coordinate> nextCoordinates) {
        if (nextCoordinates.stream().noneMatch(nextCoordinate -> nextCoordinate.row == testCoordinate.row &&
                nextCoordinate.col == testCoordinate.col)) {
            nextCoordinates.add(testCoordinate);
        }
    }

    public static String Puzzle2(String input, Integer gridSize, Integer bytesToTest) {
        List<String> lines = FileLoaders.loadInputIntoStringList(input);

        String[][] grid = new String[gridSize][gridSize];

        for (int byteTest = bytesToTest; byteTest < lines.size(); byteTest++) {
            for (int i = 0; i < gridSize; i++) {
                for (int j =0; j < gridSize; j++) {
                    grid[i][j] = ".";
                }
            }

            for (int i = 0; i < byteTest; i++ ){
                List<Integer> coords = Arrays.stream(lines.get(i).split(",")).map(Integer::parseInt).toList();
                grid[coords.get(0)][coords.get(1)] = "#";
            }

            List<Coordinate> currentCoordinates = new ArrayList<>(Collections.singleton(new Coordinate(0, 0)));
            List<Coordinate> nextCoordinates = new ArrayList<>();

            Integer stepCounter = 0;
            boolean exitFound = false;
            boolean noExitFound = false;

            while (!exitFound && !noExitFound) {

                for (Coordinate currentCoordinate : currentCoordinates) {
                    // up
                    try {
                        if (grid[currentCoordinate.row - 1][currentCoordinate.col].equals(".")) {
                            addNextCoordinate(new Coordinate(currentCoordinate.row - 1, currentCoordinate.col), nextCoordinates);
                        };
                    } catch (Exception ignored) {

                    }
                    // down
                    try {
                        if (grid[currentCoordinate.row + 1][currentCoordinate.col].equals(".")) {
                            addNextCoordinate(new Coordinate(currentCoordinate.row + 1, currentCoordinate.col), nextCoordinates);
                        };
                    } catch (Exception ignored) {

                    }
                    // left
                    try {
                        if (grid[currentCoordinate.row][currentCoordinate.col - 1].equals(".")) {
                            addNextCoordinate(new Coordinate(currentCoordinate.row, currentCoordinate.col - 1), nextCoordinates);
                        };
                    } catch (Exception ignored) {

                    }
                    // right
                    try {
                        if (grid[currentCoordinate.row][currentCoordinate.col + 1].equals(".")) {
                            addNextCoordinate(new Coordinate(currentCoordinate.row, currentCoordinate.col + 1), nextCoordinates);
                        };
                    } catch (Exception ignored) {

                    }
                }

                for (Coordinate currentCoordinate : currentCoordinates) {
                    grid[currentCoordinate.row][currentCoordinate.col] = "O";
                }

                stepCounter++;

                if (nextCoordinates.stream().anyMatch(nextCoordinate -> nextCoordinate.row == gridSize - 1 &&
                        nextCoordinate.col == gridSize - 1)) {
                    exitFound = true;
                }

                if (nextCoordinates.isEmpty()) {
                    noExitFound = true;
                }

                currentCoordinates = new ArrayList<>(nextCoordinates);
                nextCoordinates = new ArrayList<>();
            }

            if (noExitFound) {
                return lines.get(byteTest - 1);
            }
        }

        return "No blocking byte found";
    }

    enum Direction {
        UP, DOWN, RIGHT, LEFT
    }

    record Coordinate(Integer row, Integer col) {
    }
}
