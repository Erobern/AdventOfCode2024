package adventofcode2024.solvers;

import adventofcode2024.fileloaders.FileLoaders;
import adventofcode2024.utils.enums.Direction;
import adventofcode2024.utils.records.Coordinate;

import java.util.*;

public class Day16 {
    public static String Puzzle1(String input) {
        List<String> lines = FileLoaders.loadInputIntoStringList(input);

        String[][] grid = new String[lines.size()][lines.getFirst().length()];

        int startRow = 0;
        int startCol = 0;

        for (int row = 0; row < lines.size(); row++) {
            for (int col = 0; col < lines.get(row).length(); col++) {
                grid[row][col] = String.valueOf(lines.get(row).charAt(col));
                if (String.valueOf(lines.get(row).charAt(col)).equals("S")) {
                    startRow = row;
                    startCol = col;
                }
            }
        }

        List<TestPath> currentTestPaths = new ArrayList<>(List.of(
                new TestPath(new Coordinate(startRow, startCol), Direction.RIGHT, 0)
        ));
        List<TestPath> nextTestPaths = new ArrayList<>();

        Map<String, Integer> visitedCoordinates = new HashMap<>();

        Integer lowestScore = Integer.MAX_VALUE;

        while (!currentTestPaths.isEmpty()) {
            for (TestPath currentTestPath : currentTestPaths) {
                visitedCoordinates.put(getKeyName(currentTestPath.coordinate.row(), currentTestPath.coordinate.col(), currentTestPath.direction), currentTestPath.score);

                // up
                if (Objects.equals(grid[currentTestPath.coordinate.row() - 1][currentTestPath.coordinate.col()], ".") &&
                        (!visitedCoordinates.containsKey(getKeyName(currentTestPath.coordinate.row() - 1, currentTestPath.coordinate.col(), currentTestPath.direction)) ||
                                visitedCoordinates.get(getKeyName(currentTestPath.coordinate.row() - 1, currentTestPath.coordinate.col(), currentTestPath.direction)) >= currentTestPath.score)) {
                    if (currentTestPath.direction == Direction.UP) {
                        nextTestPaths.add(new TestPath(new Coordinate(currentTestPath.coordinate.row() - 1,
                                currentTestPath.coordinate.col()),
                                Direction.UP,
                                (currentTestPath.score) + 1));
                        visitedCoordinates.put(getKeyName(currentTestPath.coordinate.row() - 1, currentTestPath.coordinate.col(), currentTestPath.direction), currentTestPath.score + 1);
                    } else if (currentTestPath.direction == Direction.RIGHT || currentTestPath.direction == Direction.LEFT) {
                        nextTestPaths.add(new TestPath(new Coordinate(currentTestPath.coordinate.row() - 1,
                                currentTestPath.coordinate.col()),
                                Direction.UP,
                                (currentTestPath.score) + 1000 + 1));
                        visitedCoordinates.put(getKeyName(currentTestPath.coordinate.row() - 1, currentTestPath.coordinate.col(), currentTestPath.direction), currentTestPath.score + 1000 + 1);
                    }
                } else if (Objects.equals(grid[currentTestPath.coordinate.row() - 1][currentTestPath.coordinate.col()], "E")) {
                    if (currentTestPath.score + 1 < lowestScore) {
                        lowestScore = currentTestPath.score + 1;
                    }
                }

                // down
                if (Objects.equals(grid[currentTestPath.coordinate.row() + 1][currentTestPath.coordinate.col()], ".") &&
                        (!visitedCoordinates.containsKey(getKeyName(currentTestPath.coordinate.row() + 1, currentTestPath.coordinate.col(), currentTestPath.direction)) ||
                                visitedCoordinates.get(getKeyName(currentTestPath.coordinate.row() + 1, currentTestPath.coordinate.col(), currentTestPath.direction)) >= currentTestPath.score)) {
                    if (currentTestPath.direction == Direction.DOWN) {
                        nextTestPaths.add(new TestPath(new Coordinate(currentTestPath.coordinate.row() + 1,
                                currentTestPath.coordinate.col()),
                                Direction.DOWN,
                                (currentTestPath.score) + 1));
                        visitedCoordinates.put(getKeyName(currentTestPath.coordinate.row() + 1, currentTestPath.coordinate.col(), currentTestPath.direction), currentTestPath.score + 1);
                    } else if (currentTestPath.direction == Direction.RIGHT || currentTestPath.direction == Direction.LEFT) {
                        nextTestPaths.add(new TestPath(new Coordinate(currentTestPath.coordinate.row() + 1,
                                currentTestPath.coordinate.col()),
                                Direction.DOWN,
                                (currentTestPath.score) + 1000 + 1));
                        visitedCoordinates.put(getKeyName(currentTestPath.coordinate.row() + 1, currentTestPath.coordinate.col(), currentTestPath.direction), currentTestPath.score + 1000 + 1);
                    }
                } else if (Objects.equals(grid[currentTestPath.coordinate.row() + 1][currentTestPath.coordinate.col()], "E")) {
                    if (currentTestPath.score + 1 < lowestScore) {
                        lowestScore = currentTestPath.score + 1;
                    }
                }

                // left
                if (Objects.equals(grid[currentTestPath.coordinate.row()][currentTestPath.coordinate.col() - 1], ".") &&
                        (!visitedCoordinates.containsKey(getKeyName(currentTestPath.coordinate.row(), currentTestPath.coordinate.col() - 1, currentTestPath.direction)) ||
                                visitedCoordinates.get(getKeyName(currentTestPath.coordinate.row(), currentTestPath.coordinate.col() - 1, currentTestPath.direction)) >= currentTestPath.score)) {
                    if (currentTestPath.direction == Direction.LEFT) {
                        nextTestPaths.add(new TestPath(new Coordinate(currentTestPath.coordinate.row(),
                                currentTestPath.coordinate.col() - 1),
                                Direction.LEFT,
                                (currentTestPath.score) + 1));
                        visitedCoordinates.put(getKeyName(currentTestPath.coordinate.row(), currentTestPath.coordinate.col() - 1, currentTestPath.direction), currentTestPath.score + 1);
                    } else if (currentTestPath.direction == Direction.UP || currentTestPath.direction == Direction.DOWN) {
                        nextTestPaths.add(new TestPath(new Coordinate(currentTestPath.coordinate.row(),
                                currentTestPath.coordinate.col() - 1),
                                Direction.LEFT,
                                (currentTestPath.score) + 1000 + 1));
                        visitedCoordinates.put(getKeyName(currentTestPath.coordinate.row(), currentTestPath.coordinate.col() - 1, currentTestPath.direction), currentTestPath.score + 1000 + 1);
                    }
                } else if (Objects.equals(grid[currentTestPath.coordinate.row()][currentTestPath.coordinate.col() - 1], "E")) {
                    if (currentTestPath.score + 1 < lowestScore) {
                        lowestScore = currentTestPath.score + 1;
                    }
                }

                // right
                if (Objects.equals(grid[currentTestPath.coordinate.row()][currentTestPath.coordinate.col() + 1], ".") &&
                        (!visitedCoordinates.containsKey(getKeyName(currentTestPath.coordinate.row(), currentTestPath.coordinate.col() + 1, currentTestPath.direction)) ||
                                visitedCoordinates.get(getKeyName(currentTestPath.coordinate.row(), currentTestPath.coordinate.col() + 1, currentTestPath.direction)) >= currentTestPath.score)) {
                    if (currentTestPath.direction == Direction.RIGHT) {
                        nextTestPaths.add(new TestPath(new Coordinate(currentTestPath.coordinate.row(),
                                currentTestPath.coordinate.col() + 1),
                                Direction.RIGHT,
                                (currentTestPath.score) + 1));
                        visitedCoordinates.put(getKeyName(currentTestPath.coordinate.row(), currentTestPath.coordinate.col() + 1, currentTestPath.direction), currentTestPath.score + 1);
                    } else if (currentTestPath.direction == Direction.UP || currentTestPath.direction == Direction.DOWN) {
                        nextTestPaths.add(new TestPath(new Coordinate(currentTestPath.coordinate.row(),
                                currentTestPath.coordinate.col() + 1),
                                Direction.RIGHT,
                                (currentTestPath.score) + 1000 + 1));
                        visitedCoordinates.put(getKeyName(currentTestPath.coordinate.row(), currentTestPath.coordinate.col() + 1, currentTestPath.direction), currentTestPath.score + 1000 + 1);
                    }
                } else if (Objects.equals(grid[currentTestPath.coordinate.row()][currentTestPath.coordinate.col() + 1], "E")) {
                    if (currentTestPath.score + 1 < lowestScore) {
                        lowestScore = currentTestPath.score + 1;
                    }
                }
            }

            currentTestPaths = new ArrayList<>(nextTestPaths);
            nextTestPaths = new ArrayList<>();
        }

        return lowestScore.toString();
    }

    public static String Puzzle2(String input) {
        List<String> lines = FileLoaders.loadInputIntoStringList(input);

        String[][] grid = new String[lines.size()][lines.getFirst().length()];

        int startRow = 0;
        int startCol = 0;

        for (int row = 0; row < lines.size(); row++) {
            for (int col = 0; col < lines.get(row).length(); col++) {
                grid[row][col] = String.valueOf(lines.get(row).charAt(col));
                if (String.valueOf(lines.get(row).charAt(col)).equals("S")) {
                    startRow = row;
                    startCol = col;
                }
            }
        }

        List<TestPathPart2> currentTestPaths = new ArrayList<>(List.of(
                new TestPathPart2(new Coordinate(startRow, startCol), Direction.RIGHT, 0, startRow + ":" + startCol)
        ));
        List<TestPathPart2> nextTestPaths = new ArrayList<>();

        List<TestPathPart2> candidateLowPaths = new ArrayList<>();

        Map<String, Integer> visitedCoordinates = new HashMap<>();

        Integer lowestScore = Integer.MAX_VALUE;

        while (!currentTestPaths.isEmpty()) {
            for (TestPathPart2 currentTestPath : currentTestPaths) {
                visitedCoordinates.put(getKeyName(currentTestPath.coordinate.row(), currentTestPath.coordinate.col(), currentTestPath.direction), currentTestPath.score);

                // up
                if (Objects.equals(grid[currentTestPath.coordinate.row() - 1][currentTestPath.coordinate.col()], ".") &&
                        (!visitedCoordinates.containsKey(getKeyName(currentTestPath.coordinate.row() - 1, currentTestPath.coordinate.col(), currentTestPath.direction)) ||
                                visitedCoordinates.get(getKeyName(currentTestPath.coordinate.row() - 1, currentTestPath.coordinate.col(), currentTestPath.direction)) >= currentTestPath.score)) {
                    if (currentTestPath.direction == Direction.UP) {
                        nextTestPaths.add(new TestPathPart2(new Coordinate(currentTestPath.coordinate.row() - 1,
                                currentTestPath.coordinate.col()),
                                Direction.UP,
                                (currentTestPath.score) + 1,
                                currentTestPath.pathHistory.concat("," + getPathHistoryCoordinate(currentTestPath.coordinate.row() - 1, currentTestPath.coordinate.col()))));
                        visitedCoordinates.put(getKeyName(currentTestPath.coordinate.row() - 1, currentTestPath.coordinate.col(), currentTestPath.direction), currentTestPath.score + 1);
                    } else if (currentTestPath.direction == Direction.RIGHT || currentTestPath.direction == Direction.LEFT) {
                        nextTestPaths.add(new TestPathPart2(new Coordinate(currentTestPath.coordinate.row() - 1,
                                currentTestPath.coordinate.col()),
                                Direction.UP,
                                (currentTestPath.score) + 1000 + 1,
                                currentTestPath.pathHistory.concat("," + getPathHistoryCoordinate(currentTestPath.coordinate.row() - 1, currentTestPath.coordinate.col()))));
                        visitedCoordinates.put(getKeyName(currentTestPath.coordinate.row() - 1, currentTestPath.coordinate.col(), currentTestPath.direction), currentTestPath.score + 1000 + 1);
                    }
                } else if (Objects.equals(grid[currentTestPath.coordinate.row() - 1][currentTestPath.coordinate.col()], "E")) {
                    if (currentTestPath.score + 1 < lowestScore) {
                        lowestScore = currentTestPath.score + 1;
                        candidateLowPaths = new ArrayList<>(List.of(currentTestPath));
                    } else if (currentTestPath.score + 1 == lowestScore) {
                        candidateLowPaths.add(currentTestPath);
                    }
                }

                // down
                if (Objects.equals(grid[currentTestPath.coordinate.row() + 1][currentTestPath.coordinate.col()], ".") &&
                        (!visitedCoordinates.containsKey(getKeyName(currentTestPath.coordinate.row() + 1, currentTestPath.coordinate.col(), currentTestPath.direction)) ||
                                visitedCoordinates.get(getKeyName(currentTestPath.coordinate.row() + 1, currentTestPath.coordinate.col(), currentTestPath.direction)) >= currentTestPath.score)) {
                    if (currentTestPath.direction == Direction.DOWN) {
                        nextTestPaths.add(new TestPathPart2(new Coordinate(currentTestPath.coordinate.row() + 1,
                                currentTestPath.coordinate.col()),
                                Direction.DOWN,
                                (currentTestPath.score) + 1,
                                currentTestPath.pathHistory.concat("," + getPathHistoryCoordinate(currentTestPath.coordinate.row() + 1, currentTestPath.coordinate.col()))));
                        visitedCoordinates.put(getKeyName(currentTestPath.coordinate.row() + 1, currentTestPath.coordinate.col(), currentTestPath.direction), currentTestPath.score + 1);
                    } else if (currentTestPath.direction == Direction.RIGHT || currentTestPath.direction == Direction.LEFT) {
                        nextTestPaths.add(new TestPathPart2(new Coordinate(currentTestPath.coordinate.row() + 1,
                                currentTestPath.coordinate.col()),
                                Direction.DOWN,
                                (currentTestPath.score) + 1000 + 1,
                                currentTestPath.pathHistory.concat("," + getPathHistoryCoordinate(currentTestPath.coordinate.row() + 1, currentTestPath.coordinate.col()))));
                        visitedCoordinates.put(getKeyName(currentTestPath.coordinate.row() + 1, currentTestPath.coordinate.col(), currentTestPath.direction), currentTestPath.score + 1000 + 1);
                    }
                } else if (Objects.equals(grid[currentTestPath.coordinate.row() + 1][currentTestPath.coordinate.col()], "E")) {
                    if (currentTestPath.score + 1 < lowestScore) {
                        lowestScore = currentTestPath.score + 1;
                        candidateLowPaths = new ArrayList<>(List.of(currentTestPath));
                    } else if (currentTestPath.score + 1 == lowestScore) {
                        candidateLowPaths.add(currentTestPath);
                    }
                }

                // left
                if (Objects.equals(grid[currentTestPath.coordinate.row()][currentTestPath.coordinate.col() - 1], ".") &&
                        (!visitedCoordinates.containsKey(getKeyName(currentTestPath.coordinate.row(), currentTestPath.coordinate.col() - 1, currentTestPath.direction)) ||
                                visitedCoordinates.get(getKeyName(currentTestPath.coordinate.row(), currentTestPath.coordinate.col() - 1, currentTestPath.direction)) >= currentTestPath.score)) {
                    if (currentTestPath.direction == Direction.LEFT) {
                        nextTestPaths.add(new TestPathPart2(new Coordinate(currentTestPath.coordinate.row(),
                                currentTestPath.coordinate.col() - 1),
                                Direction.LEFT,
                                (currentTestPath.score) + 1,
                                currentTestPath.pathHistory.concat("," + getPathHistoryCoordinate(currentTestPath.coordinate.row(), currentTestPath.coordinate.col() - 1))));
                        visitedCoordinates.put(getKeyName(currentTestPath.coordinate.row(), currentTestPath.coordinate.col() - 1, currentTestPath.direction), currentTestPath.score + 1);
                    } else if (currentTestPath.direction == Direction.UP || currentTestPath.direction == Direction.DOWN) {
                        nextTestPaths.add(new TestPathPart2(new Coordinate(currentTestPath.coordinate.row(),
                                currentTestPath.coordinate.col() - 1),
                                Direction.LEFT,
                                (currentTestPath.score) + 1000 + 1,
                                currentTestPath.pathHistory.concat("," + getPathHistoryCoordinate(currentTestPath.coordinate.row(), currentTestPath.coordinate.col() - 1))));
                        visitedCoordinates.put(getKeyName(currentTestPath.coordinate.row(), currentTestPath.coordinate.col() - 1, currentTestPath.direction), currentTestPath.score + 1000 + 1);
                    }
                } else if (Objects.equals(grid[currentTestPath.coordinate.row()][currentTestPath.coordinate.col() - 1], "E")) {
                    if (currentTestPath.score + 1 < lowestScore) {
                        lowestScore = currentTestPath.score + 1;
                        candidateLowPaths = new ArrayList<>(List.of(currentTestPath));
                    } else if (currentTestPath.score + 1 == lowestScore) {
                        candidateLowPaths.add(currentTestPath);
                    }
                }

                // right
                if (Objects.equals(grid[currentTestPath.coordinate.row()][currentTestPath.coordinate.col() + 1], ".") &&
                        (!visitedCoordinates.containsKey(getKeyName(currentTestPath.coordinate.row(), currentTestPath.coordinate.col() + 1, currentTestPath.direction)) ||
                                visitedCoordinates.get(getKeyName(currentTestPath.coordinate.row(), currentTestPath.coordinate.col() + 1, currentTestPath.direction)) >= currentTestPath.score)) {
                    if (currentTestPath.direction == Direction.RIGHT) {
                        nextTestPaths.add(new TestPathPart2(new Coordinate(currentTestPath.coordinate.row(),
                                currentTestPath.coordinate.col() + 1),
                                Direction.RIGHT,
                                (currentTestPath.score) + 1,
                                currentTestPath.pathHistory.concat("," + getPathHistoryCoordinate(currentTestPath.coordinate.row(), currentTestPath.coordinate.col() + 1))));
                        visitedCoordinates.put(getKeyName(currentTestPath.coordinate.row(), currentTestPath.coordinate.col() + 1, currentTestPath.direction), currentTestPath.score + 1);
                    } else if (currentTestPath.direction == Direction.UP || currentTestPath.direction == Direction.DOWN) {
                        nextTestPaths.add(new TestPathPart2(new Coordinate(currentTestPath.coordinate.row(),
                                currentTestPath.coordinate.col() + 1),
                                Direction.RIGHT,
                                (currentTestPath.score) + 1000 + 1,
                                currentTestPath.pathHistory.concat("," + getPathHistoryCoordinate(currentTestPath.coordinate.row(), currentTestPath.coordinate.col() + 1))));
                        visitedCoordinates.put(getKeyName(currentTestPath.coordinate.row(), currentTestPath.coordinate.col() + 1, currentTestPath.direction), currentTestPath.score + 1000 + 1);
                    }
                } else if (Objects.equals(grid[currentTestPath.coordinate.row()][currentTestPath.coordinate.col() + 1], "E")) {
                    if (currentTestPath.score + 1 < lowestScore) {
                        lowestScore = currentTestPath.score + 1;
                        candidateLowPaths = new ArrayList<>(List.of(currentTestPath));
                    } else if (currentTestPath.score + 1 == lowestScore) {
                        candidateLowPaths.add(currentTestPath);
                    }
                }
            }

            currentTestPaths = new ArrayList<>(nextTestPaths);
            nextTestPaths = new ArrayList<>();
        }

        for (TestPathPart2 candidateLowPath : candidateLowPaths) {
            Arrays.stream(candidateLowPath.pathHistory.split(",")).forEach(coordinateString -> {
                List<Integer> parsed = Arrays.stream(coordinateString.split(":")).map(Integer::parseInt).toList();
                grid[parsed.get(0)][parsed.get(1)] = "O";
            });
        }

        Integer sum = 0;

        for (int row = 0; row < lines.size(); row++) {
            for (int col = 0; col < lines.get(row).length(); col++) {
                if (String.valueOf(grid[row][col]).equals("S") ||
                        String.valueOf(grid[row][col]).equals("O") ||
                        String.valueOf(grid[row][col]).equals("E")) {
                    sum++;
                }
            }
        }


        return sum.toString();
    }

    private static String getKeyName(Integer row, Integer col, Direction direction) {
        return row + ":" + col + ":" + direction;
    }

    private static String getPathHistoryCoordinate(Integer row, Integer col) {
        return row + ":" + col;
    }

    record TestPath(Coordinate coordinate, Direction direction, Integer score) {
    }

    record TestPathPart2(Coordinate coordinate, Direction direction, Integer score, String pathHistory) {
    }

}
