package adventofcode2024.solvers;

import adventofcode2024.fileloaders.FileLoaders;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class Day12 {
    public static String Puzzle1(String input) {
        List<String> lines = FileLoaders.loadInputIntoStringList(input);

        String[][] farm = new String[lines.size()][lines.getFirst().length()];
        String[][] traversedFarm = new String[lines.size()][lines.getFirst().length()];

        for (int row = 0; row < lines.size(); row++) {
            for (int col = 0; col < lines.get(row).length(); col++) {
                farm[row][col] = String.valueOf(lines.get(row).charAt(col));
                traversedFarm[row][col] = "N";
            }
        }

        Integer sum = 0;

        // traverse the farm and mark fields/areas

        String currentField = "";

        for (int row = 0; row < lines.size(); row++) {
            for (int col = 0; col < lines.get(row).length(); col++) {
                if (Objects.equals(traversedFarm[row][col], "N")) {
                    Integer fieldArea = 1;
                    Integer fieldPerimeter = 0;

                    currentField = farm[row][col];
                    traversedFarm[row][col] = "Y";

                    List<Coordinate> currentCoords = new ArrayList<>(Collections.singleton(new Coordinate(row, col)));
                    List<Coordinate> nextCoords = new ArrayList<>();

                    while (!currentCoords.isEmpty()) {
                        for (Coordinate cur : currentCoords) {
                            // try up
                            try {
                                if (Objects.equals(farm[cur.row - 1][cur.col], String.valueOf(currentField)) &&
                                        Objects.equals(traversedFarm[cur.row - 1][cur.col], "N")) {
                                    addNextCoord(nextCoords, new Coordinate(cur.row - 1, cur.col));
                                    traversedFarm[cur.row - 1][cur.col] = "Y";
                                } else if (!Objects.equals(farm[cur.row - 1][cur.col], String.valueOf(currentField))) {
                                    fieldPerimeter++;
                                }
                            } catch (Exception e) {
                                fieldPerimeter++;
                            }
                            // try down
                            try {
                                if (Objects.equals(farm[cur.row + 1][cur.col], String.valueOf(currentField)) &&
                                        Objects.equals(traversedFarm[cur.row + 1][cur.col], "N")) {
                                    addNextCoord(nextCoords, new Coordinate(cur.row + 1, cur.col));
                                    traversedFarm[cur.row + 1][cur.col] = "Y";
                                } else if (!Objects.equals(farm[cur.row + 1][cur.col], String.valueOf(currentField))) {
                                    fieldPerimeter++;
                                }
                            } catch (Exception e) {
                                fieldPerimeter++;
                            }
                            // try left
                            try {
                                if (Objects.equals(farm[cur.row][cur.col - 1], String.valueOf(currentField)) &&
                                        Objects.equals(traversedFarm[cur.row][cur.col - 1], "N")) {
                                    addNextCoord(nextCoords, new Coordinate(cur.row, cur.col - 1));
                                    traversedFarm[cur.row][cur.col - 1] = "Y";
                                } else if (!Objects.equals(farm[cur.row][cur.col - 1], String.valueOf(currentField))) {
                                    fieldPerimeter++;
                                }
                            } catch (Exception e) {
                                fieldPerimeter++;
                            }
                            // try right
                            try {
                                if (Objects.equals(farm[cur.row][cur.col + 1], String.valueOf(currentField)) &&
                                        Objects.equals(traversedFarm[cur.row][cur.col + 1], "N")) {
                                    addNextCoord(nextCoords, new Coordinate(cur.row, cur.col + 1));
                                    traversedFarm[cur.row][cur.col + 1] = "Y";
                                } else if (!Objects.equals(farm[cur.row][cur.col + 1], String.valueOf(currentField))) {
                                    fieldPerimeter++;
                                }
                            } catch (Exception e) {
                                fieldPerimeter++;
                            }
                        }

                        fieldArea += nextCoords.size();

                        currentCoords = new ArrayList<>(nextCoords);
                        nextCoords = new ArrayList<>();
                    }

                    sum += (fieldArea * fieldPerimeter);
                }
            }
        }

        return sum.toString();
    }

    public static String Puzzle2(String input) {
        List<String> lines = FileLoaders.loadInputIntoStringList(input);

        String[][] farm = new String[lines.size()][lines.getFirst().length()];
        String[][] traversedFarm = new String[lines.size()][lines.getFirst().length()];

        for (int row = 0; row < lines.size(); row++) {
            for (int col = 0; col < lines.get(row).length(); col++) {
                farm[row][col] = String.valueOf(lines.get(row).charAt(col));
                traversedFarm[row][col] = "N";
            }
        }

        Integer sum = 0;

        // traverse the farm and mark fields/areas

        String currentField = "";

        for (int row = 0; row < lines.size(); row++) {
            for (int col = 0; col < lines.get(row).length(); col++) {
                if (Objects.equals(traversedFarm[row][col], "N")) {
                    Integer fieldArea = 1;
                    Integer sides = 0;

                    currentField = farm[row][col];
                    System.out.println("analyzing field: " + currentField);
                    traversedFarm[row][col] = "Y";

                    List<Coordinate> currentCoords = new ArrayList<>(Collections.singleton(new Coordinate(row, col)));
                    List<Coordinate> allCoords = new ArrayList<>(Collections.singleton(new Coordinate(row, col)));
                    List<Coordinate> nextCoords = new ArrayList<>();

                    while (!currentCoords.isEmpty()) {
                        for (Coordinate cur : currentCoords) {
                            // try up
                            try {
                                if (Objects.equals(farm[cur.row - 1][cur.col], String.valueOf(currentField)) &&
                                        Objects.equals(traversedFarm[cur.row - 1][cur.col], "N")) {
                                    addNextCoord(nextCoords, new Coordinate(cur.row - 1, cur.col));
                                    traversedFarm[cur.row - 1][cur.col] = "Y";
                                }
                            } catch (Exception e) {
                            }
                            // try down
                            try {
                                if (Objects.equals(farm[cur.row + 1][cur.col], String.valueOf(currentField)) &&
                                        Objects.equals(traversedFarm[cur.row + 1][cur.col], "N")) {
                                    addNextCoord(nextCoords, new Coordinate(cur.row + 1, cur.col));
                                    traversedFarm[cur.row + 1][cur.col] = "Y";
                                }
                            } catch (Exception e) {
                            }
                            // try left
                            try {
                                if (Objects.equals(farm[cur.row][cur.col - 1], String.valueOf(currentField)) &&
                                        Objects.equals(traversedFarm[cur.row][cur.col - 1], "N")) {
                                    addNextCoord(nextCoords, new Coordinate(cur.row, cur.col - 1));
                                    traversedFarm[cur.row][cur.col - 1] = "Y";
                                }
                            } catch (Exception e) {
                            }
                            // try right
                            try {
                                if (Objects.equals(farm[cur.row][cur.col + 1], String.valueOf(currentField)) &&
                                        Objects.equals(traversedFarm[cur.row][cur.col + 1], "N")) {
                                    addNextCoord(nextCoords, new Coordinate(cur.row, cur.col + 1));
                                    traversedFarm[cur.row][cur.col + 1] = "Y";
                                }
                            } catch (Exception e) {
                            }
                        }

                        fieldArea += nextCoords.size();

                        currentCoords = new ArrayList<>(nextCoords);
                        allCoords.addAll(nextCoords);
                        nextCoords = new ArrayList<>();
                    }

                    // count sides?

                    List<Coordinate> fenceFindingCoords = new ArrayList<>();

                    for (Coordinate allCoord : allCoords) {
                        fenceFindingCoords.add(new Coordinate((allCoord.row * 2) + 3, (allCoord.col * 2) + 3));
                    }

                    String[][] fenceGrid = new String[lines.size() * 2 + 10][lines.getFirst().length() * 2 + 10];

                    for (Coordinate fenceFindingCoord : fenceFindingCoords) {

                        fenceGrid[fenceFindingCoord.row][fenceFindingCoord.col] = currentField;

                        final int finalFenceFindingRow = fenceFindingCoord.row;
                        final int finalFenceFindingCol = fenceFindingCoord.col;

                        // test up
                        if (fenceFindingCoords.stream().noneMatch(coord -> coord.row == (finalFenceFindingRow - 2) &&
                                coord.col == finalFenceFindingCol)) {
                            fenceGrid[fenceFindingCoord.row - 1][fenceFindingCoord.col] = "#";
                            fenceGrid[fenceFindingCoord.row - 1][fenceFindingCoord.col - 1] = "#";
                            fenceGrid[fenceFindingCoord.row - 1][fenceFindingCoord.col + 1] = "#";
                        }
                        // test down
                        if (fenceFindingCoords.stream().noneMatch(coord -> coord.row == (finalFenceFindingRow + 2) &&
                                coord.col == finalFenceFindingCol)) {
                            fenceGrid[fenceFindingCoord.row + 1][fenceFindingCoord.col] = "#";
                            fenceGrid[fenceFindingCoord.row + 1][fenceFindingCoord.col - 1] = "#";
                            fenceGrid[fenceFindingCoord.row + 1][fenceFindingCoord.col + 1] = "#";
                        }
                        // test left
                        if (fenceFindingCoords.stream().noneMatch(coord -> coord.row == finalFenceFindingRow &&
                                coord.col == (finalFenceFindingCol - 2))) {
                            fenceGrid[fenceFindingCoord.row][fenceFindingCoord.col - 1] = "#";
                            fenceGrid[fenceFindingCoord.row - 1][fenceFindingCoord.col - 1] = "#";
                            fenceGrid[fenceFindingCoord.row + 1][fenceFindingCoord.col - 1] = "#";
                        }
                        // test right
                        if (fenceFindingCoords.stream().noneMatch(coord -> coord.row == finalFenceFindingRow &&
                                coord.col == (finalFenceFindingCol + 2))) {
                            fenceGrid[fenceFindingCoord.row][fenceFindingCoord.col + 1] = "#";
                            fenceGrid[fenceFindingCoord.row - 1][fenceFindingCoord.col + 1] = "#";
                            fenceGrid[fenceFindingCoord.row + 1][fenceFindingCoord.col + 1] = "#";
                        }
//                        // test up-left
//                        if (fenceFindingCoords.stream().noneMatch(coord -> coord.row == fenceFindingCoord.row - 2 &&
//                                coord.col == fenceFindingCoord.col) &&
//                                fenceFindingCoords.stream().noneMatch(coord -> coord.row == fenceFindingCoord.row &&
//                                        coord.col == fenceFindingCoord.col - 2)) {
//                            fenceGrid[fenceFindingCoord.row - 1][fenceFindingCoord.col - 1] = "#";
//                        }
//                        // test up-right
//                        if (fenceFindingCoords.stream().noneMatch(coord -> coord.row == fenceFindingCoord.row - 2 &&
//                                coord.col == fenceFindingCoord.col) &&
//                                fenceFindingCoords.stream().noneMatch(coord -> coord.row == fenceFindingCoord.row &&
//                                        coord.col == fenceFindingCoord.col + 2)) {
//                            fenceGrid[fenceFindingCoord.row - 1][fenceFindingCoord.col + 1] = "#";
//                        }
//                        // test down-left
//                        if (fenceFindingCoords.stream().noneMatch(coord -> coord.row == fenceFindingCoord.row + 2 &&
//                                coord.col == fenceFindingCoord.col) &&
//                                fenceFindingCoords.stream().noneMatch(coord -> coord.row == fenceFindingCoord.row &&
//                                        coord.col == fenceFindingCoord.col - 2)) {
//                            fenceGrid[fenceFindingCoord.row + 1][fenceFindingCoord.col - 1] = "#";
//                        }
//                        // test down-right
//                        if (fenceFindingCoords.stream().noneMatch(coord -> coord.row == fenceFindingCoord.row + 2 &&
//                                coord.col == fenceFindingCoord.col) &&
//                                fenceFindingCoords.stream().noneMatch(coord -> coord.row == fenceFindingCoord.row &&
//                                        coord.col == fenceFindingCoord.col + 2)) {
//                            fenceGrid[fenceFindingCoord.row + 1][fenceFindingCoord.col + 1] = "#";
//                        }
                    }

                    boolean scanCompleted = false;
                    do {
                        // scan fence grid for the first post.  by rule it will always be a corner
                        int startRow = 0;
                        int startCol = 0;
                        boolean fenceFound = false;
                        for (int fenceRow = 0; fenceRow < lines.size() * 2 + 10; fenceRow++) {
                            try {
                                for (int fenceCol = 0; fenceCol < lines.getFirst().length() * 2 + 10; fenceCol++) {
                                    if (Objects.equals(fenceGrid[fenceRow][fenceCol], "#")) {
                                        startRow = fenceRow;
                                        startCol = fenceCol;
                                        fenceFound = true;
                                        throw new RuntimeException();
                                    }
                                }
                            } catch (Exception e) {
                                break;
                            }
                        }

                        if (fenceFound) {

                            int curRow = startRow;
                            int curCol = startCol;

                            // go around the fence and count how many times we turn
                            Direction direction = Direction.RIGHT;
                            int turns = 0;

                            boolean skipRemove = false;

                            do {

                                if (direction == Direction.RIGHT) {
                                    if (!Objects.equals(fenceGrid[curRow][curCol + 1], "#")) {
                                        // decide up or down
                                        turns++;
                                        if (Objects.equals(fenceGrid[curRow - 1][curCol], "#")) {
                                            direction = Direction.UP;
                                        } else {
                                            direction = Direction.DOWN;
                                        }
                                    } else if (
                                            Objects.equals(fenceGrid[curRow - 1][curCol], "#") &&
                                                    Objects.equals(fenceGrid[curRow + 1][curCol], "#")
                                    ) {
                                        direction = Direction.UP;
                                        turns++;
                                        skipRemove = true;
                                    } else {
                                        if (skipRemove) {
                                            skipRemove = false;
                                        } else {
                                            fenceGrid[curRow][curCol] = null;
                                        }
                                        curCol++;
                                    }
                                }

                                if (direction == Direction.DOWN) {
                                    if (!Objects.equals(fenceGrid[curRow + 1][curCol], "#")) {
                                        // decide left or right
                                        turns++;
                                        if (Objects.equals(fenceGrid[curRow][curCol - 1], "#")) {
                                            direction = Direction.LEFT;
                                        } else {
                                            direction = Direction.RIGHT;
                                        }
                                    } else if (
                                            Objects.equals(fenceGrid[curRow][curCol - 1], "#") &&
                                                    Objects.equals(fenceGrid[curRow][curCol + 1], "#")
                                    ) {
                                        direction = Direction.RIGHT;
                                        turns++;
                                        skipRemove = true;
                                    } else {
                                        if (skipRemove) {
                                            skipRemove = false;
                                        } else {
                                            fenceGrid[curRow][curCol] = null;
                                        }
                                        curRow++;
                                    }
                                }

                                if (direction == Direction.LEFT) {
                                    if (!Objects.equals(fenceGrid[curRow][curCol - 1], "#")) {
                                        // decide up or down
                                        turns++;
                                        if (Objects.equals(fenceGrid[curRow - 1][curCol], "#")) {
                                            direction = Direction.UP;
                                        } else {
                                            direction = Direction.DOWN;
                                        }
                                    } else if (
                                            Objects.equals(fenceGrid[curRow - 1][curCol], "#") &&
                                                    Objects.equals(fenceGrid[curRow + 1][curCol], "#")
                                    ) {
                                        direction = Direction.DOWN;
                                        turns++;
                                        skipRemove = true;
                                    } else {
                                        if (skipRemove) {
                                            skipRemove = false;
                                        } else {
                                            fenceGrid[curRow][curCol] = null;
                                        }
                                        curCol--;
                                    }
                                }

                                if (direction == Direction.UP) {
                                    if (!Objects.equals(fenceGrid[curRow - 1][curCol], "#")) {
                                        // decide left or right
                                        turns++;
                                        if (Objects.equals(fenceGrid[curRow][curCol - 1], "#")) {
                                            direction = Direction.LEFT;
                                        } else if (Objects.equals(fenceGrid[curRow][curCol + 1], "#")) {
                                            direction = Direction.RIGHT;
                                        } else {
                                            fenceGrid[curRow][curCol] = null;
                                            curRow--;
                                        }
                                    } else if (
                                            Objects.equals(fenceGrid[curRow][curCol - 1], "#") &&
                                                    Objects.equals(fenceGrid[curRow][curCol + 1], "#")
                                    ) {
                                        direction = Direction.LEFT;
                                        turns++;
                                        skipRemove = true;
                                    } else {
                                        if (skipRemove) {
                                            skipRemove = false;
                                        } else {
                                            fenceGrid[curRow][curCol] = null;
                                        }
                                        curRow--;
                                    }
                                }

                            } while (!(curRow == startRow && curCol == startCol));

                            sides += turns;
                        } else {
                            scanCompleted = true;
                        }

                    } while (!scanCompleted);

                    System.out.println("field " + currentField + " price:" + (fieldArea * sides));
                    sum += (fieldArea * sides);
                }
            }
        }

        return sum.toString();
    }

    private static void addNextCoord(List<Coordinate> nextCoords, Coordinate newCoord) {
        if (nextCoords.stream().noneMatch(coord -> coord.row == newCoord.row && coord.col == newCoord.col)) {
            nextCoords.add(newCoord);
        }
    }

    enum Direction {
        UP, DOWN, LEFT, RIGHT
    }

    record Coordinate(Integer row, Integer col) {
    }

}
