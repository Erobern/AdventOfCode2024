package adventofcode2024.solvers;

import adventofcode2024.fileloaders.FileLoaders;
import adventofcode2024.utils.records.Coordinate;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Day15 {
    public static String Puzzle1(String input_grid, String input_instructions) {
        List<String> gridLines = FileLoaders.loadInputIntoStringList(input_grid);

        String[][] grid = new String[gridLines.size()][gridLines.get(0).length()];
        int startRow = 0;
        int startCol = 0;

        for (int row = 0; row < gridLines.size(); row++) {
            for (int col = 0; col < gridLines.get(0).length(); col++) {
                grid[row][col] = String.valueOf(gridLines.get(row).charAt(col));
                if (Objects.equals(grid[row][col], "@")) {
                    startRow = row;
                    startCol = col;
                }
            }
        }

        List<String> instructionLines = FileLoaders.loadInputIntoStringList(input_instructions);

        int posRow = startRow;
        int posCol = startCol;

        for (String instructionLine : instructionLines) {
            for (String instruction : instructionLine.split("")) {
                // read the tape and process moves

                switch (instruction) {
                    case "v":
                        if (Objects.equals(grid[posRow + 1][posCol], ".")) {
                            grid[posRow][posCol] = ".";
                            grid[posRow + 1][posCol] = "@";
                            posRow++;
                        } else if (Objects.equals(grid[posRow + 1][posCol], "O")) {
                            // keep checking to see if we find a dot or an obstacle
                            boolean obstacleFound = false;
                            boolean dotFound = false;

                            int offset = 0;
                            do {
                                offset++;
                                if (Objects.equals(grid[posRow + offset][posCol], ".")) {
                                    dotFound = true;
                                } else if (Objects.equals(grid[posRow + offset][posCol], "#")) {
                                    obstacleFound = true;
                                }
                            } while (!obstacleFound && !dotFound);

                            if (dotFound) {
                                grid[posRow][posCol] = ".";
                                grid[posRow + 1][posCol] = "@";
                                grid[posRow + offset][posCol] = "O";
                                posRow++;
                            }
                        }

                        break;
                    case "^":
                        if (Objects.equals(grid[posRow - 1][posCol], ".")) {
                            grid[posRow][posCol] = ".";
                            grid[posRow - 1][posCol] = "@";
                            posRow--;
                        } else if (Objects.equals(grid[posRow - 1][posCol], "O")) {
                            // keep checking to see if we find a dot or an obstacle
                            boolean obstacleFound = false;
                            boolean dotFound = false;

                            int offset = 0;
                            do {
                                offset++;
                                if (Objects.equals(grid[posRow - offset][posCol], ".")) {
                                    dotFound = true;
                                } else if (Objects.equals(grid[posRow - offset][posCol], "#")) {
                                    obstacleFound = true;
                                }
                            } while (!obstacleFound && !dotFound);

                            if (dotFound) {
                                grid[posRow][posCol] = ".";
                                grid[posRow - 1][posCol] = "@";
                                grid[posRow - offset][posCol] = "O";
                                posRow--;
                            }
                        }

                        break;
                    case ">":
                        if (Objects.equals(grid[posRow][posCol + 1], ".")) {
                            grid[posRow][posCol] = ".";
                            grid[posRow][posCol + 1] = "@";
                            posCol++;
                        } else if (Objects.equals(grid[posRow][posCol + 1], "O")) {
                            // keep checking to see if we find a dot or an obstacle
                            boolean obstacleFound = false;
                            boolean dotFound = false;

                            int offset = 0;
                            do {
                                offset++;
                                if (Objects.equals(grid[posRow][posCol + offset], ".")) {
                                    dotFound = true;
                                } else if (Objects.equals(grid[posRow][posCol + offset], "#")) {
                                    obstacleFound = true;
                                }
                            } while (!obstacleFound && !dotFound);

                            if (dotFound) {
                                grid[posRow][posCol] = ".";
                                grid[posRow][posCol + 1] = "@";
                                grid[posRow][posCol + offset] = "O";
                                posCol++;
                            }
                        }

                        break;
                    case "<":
                        if (Objects.equals(grid[posRow][posCol - 1], ".")) {
                            grid[posRow][posCol] = ".";
                            grid[posRow][posCol - 1] = "@";
                            posCol--;
                        } else if (Objects.equals(grid[posRow][posCol - 1], "O")) {
                            // keep checking to see if we find a dot or an obstacle
                            boolean obstacleFound = false;
                            boolean dotFound = false;

                            int offset = 0;
                            do {
                                offset++;
                                if (Objects.equals(grid[posRow][posCol - offset], ".")) {
                                    dotFound = true;
                                } else if (Objects.equals(grid[posRow][posCol - offset], "#")) {
                                    obstacleFound = true;
                                }
                            } while (!obstacleFound && !dotFound);

                            if (dotFound) {
                                grid[posRow][posCol] = ".";
                                grid[posRow][posCol - 1] = "@";
                                grid[posRow][posCol - offset] = "O";
                                posCol--;
                            }
                        }

                        break;

                }
            }
        }

        int gpsSum = 0;

        for (int row = 0; row < gridLines.size(); row++) {
            for (int col = 0; col < gridLines.get(0).length(); col++) {
                if (Objects.equals(grid[row][col], "O")) {
                    gpsSum += (100 * row) + col;
                }
            }
        }


        return String.valueOf(gpsSum);
    }

    public static String Puzzle2(String input_grid, String input_instructions) {
        List<String> gridLines = FileLoaders.loadInputIntoStringList(input_grid);

        String[][] grid = new String[gridLines.size()][gridLines.get(0).length() * 2];
        int startRow = 0;
        int startCol = 0;

        for (int row = 0; row < gridLines.size(); row++) {
            for (int col = 0; col < gridLines.get(0).length() * 2; col++) {
                if (Objects.equals(String.valueOf(gridLines.get(row).charAt(col / 2)), "@")) {
                    startRow = row;
                    startCol = col;

                    grid[row][col] = "@";
                    col++;
                    grid[row][col] = ".";
                } else if (Objects.equals(String.valueOf(gridLines.get(row).charAt(col / 2)), "#")) {
                    grid[row][col] = "#";
                    col++;
                    grid[row][col] = "#";
                } else if (Objects.equals(String.valueOf(gridLines.get(row).charAt(col / 2)), ".")) {
                    grid[row][col] = ".";
                    col++;
                    grid[row][col] = ".";
                } else if (Objects.equals(String.valueOf(gridLines.get(row).charAt(col / 2)), "O")) {
                    grid[row][col] = "[";
                    col++;
                    grid[row][col] = "]";
                }
            }
        }

        List<String> instructionLines = FileLoaders.loadInputIntoStringList(input_instructions);

        int posRow = startRow;
        int posCol = startCol;

        for (String instructionLine : instructionLines) {
            for (String instruction : instructionLine.split("")) {
                // read the tape and process moves

                switch (instruction) {
                    case "v":
                        if (Objects.equals(grid[posRow + 1][posCol], ".")) {
                            grid[posRow][posCol] = ".";
                            grid[posRow + 1][posCol] = "@";
                            posRow++;
                        } else if (Objects.equals(grid[posRow + 1][posCol], "[") || Objects.equals(grid[posRow + 1][posCol], "]")) {
                            // keep checking to see if we find a dot or an obstacle
                            boolean obstacleFound = false;
                            boolean noNewBoxesFound = false;

                            List<List<Coordinate>> rowsAndBoxes = new ArrayList<>();

                            if (Objects.equals(grid[posRow + 1][posCol], "[")) {
                                rowsAndBoxes.add(List.of(new Coordinate(posRow + 1, posCol)));
                            } else {
                                rowsAndBoxes.add(List.of(new Coordinate(posRow + 1, posCol - 1)));
                            }

                            do {
                                // track any new boxes found
                                List<Coordinate> newBoxes = new ArrayList<>();

                                for (Coordinate coordinate : rowsAndBoxes.getLast()) {
                                    if (Objects.equals(grid[coordinate.row() + 1][coordinate.col()], "#") ||
                                            Objects.equals(grid[coordinate.row() + 1][coordinate.col() + 1], "#")) {
                                        obstacleFound = true;
                                        break;
                                    } else {
                                        if (Objects.equals(grid[coordinate.row() + 1][coordinate.col()], "]")) {
                                            if (newBoxes.stream().noneMatch(newBox -> newBox.row() == coordinate.row() + 1 && newBox.col() == coordinate.col() - 1)) {
                                                newBoxes.add(new Coordinate(coordinate.row() + 1, coordinate.col() - 1));
                                            }
                                        }
                                        if (Objects.equals(grid[coordinate.row() + 1][coordinate.col()], "[")) {
                                            if (newBoxes.stream().noneMatch(newBox -> newBox.row() == coordinate.row() + 1 && newBox.col() == coordinate.col())) {
                                                newBoxes.add(new Coordinate(coordinate.row() + 1, coordinate.col()));
                                            }
                                        }
                                        if (Objects.equals(grid[coordinate.row() + 1][coordinate.col() + 1], "[")) {
                                            if (newBoxes.stream().noneMatch(newBox -> newBox.row() == coordinate.row() + 1 && newBox.col() == coordinate.col() + 1)) {
                                                newBoxes.add(new Coordinate(coordinate.row() + 1, coordinate.col() + 1));
                                            }
                                        }
                                    }
                                }

                                if (newBoxes.isEmpty()) {
                                    noNewBoxesFound = true;
                                } else {
                                    rowsAndBoxes.add(newBoxes);
                                }

                            } while (!obstacleFound && !noNewBoxesFound);

                            if (!obstacleFound) {

                                for (List<Coordinate> row : rowsAndBoxes.reversed()) {
                                    for (Coordinate coordinate : row) {
                                        // progress the box
                                        grid[coordinate.row() + 1][coordinate.col()] = "[";
                                        grid[coordinate.row() + 1][coordinate.col() + 1] = "]";

                                        grid[coordinate.row()][coordinate.col()] = ".";
                                        grid[coordinate.row()][coordinate.col() + 1] = ".";
                                    }
                                }

                                grid[posRow][posCol] = ".";
                                grid[posRow + 1][posCol] = "@";
                                posRow++;
                            }

                        }

                        break;
                    case "^":
                        if (Objects.equals(grid[posRow - 1][posCol], ".")) {
                            grid[posRow][posCol] = ".";
                            grid[posRow - 1][posCol] = "@";
                            posRow--;
                        } else if (Objects.equals(grid[posRow - 1][posCol], "[") || Objects.equals(grid[posRow - 1][posCol], "]")) {
                            // keep checking to see if we find a dot or an obstacle
                            boolean obstacleFound = false;
                            boolean noNewBoxesFound = false;

                            List<List<Coordinate>> rowsAndBoxes = new ArrayList<>();

                            if (Objects.equals(grid[posRow - 1][posCol], "[")) {
                                rowsAndBoxes.add(List.of(new Coordinate(posRow - 1, posCol)));
                            } else {
                                rowsAndBoxes.add(List.of(new Coordinate(posRow - 1, posCol - 1)));
                            }

                            do {
                                // track any new boxes found
                                List<Coordinate> newBoxes = new ArrayList<>();

                                for (Coordinate coordinate : rowsAndBoxes.getLast()) {
                                    if (Objects.equals(grid[coordinate.row() - 1][coordinate.col()], "#") ||
                                            Objects.equals(grid[coordinate.row() - 1][coordinate.col() + 1], "#")) {
                                        obstacleFound = true;
                                        break;
                                    } else {
                                        if (Objects.equals(grid[coordinate.row() - 1][coordinate.col()], "]")) {
                                            if (newBoxes.stream().noneMatch(newBox -> newBox.row() == coordinate.row() - 1 && newBox.col() == coordinate.col() - 1)) {
                                                newBoxes.add(new Coordinate(coordinate.row() - 1, coordinate.col() - 1));
                                            }
                                        }
                                        if (Objects.equals(grid[coordinate.row() - 1][coordinate.col()], "[")) {
                                            if (newBoxes.stream().noneMatch(newBox -> newBox.row() == coordinate.row() - 1 && newBox.col() == coordinate.col())) {
                                                newBoxes.add(new Coordinate(coordinate.row() - 1, coordinate.col()));
                                            }
                                        }
                                        if (Objects.equals(grid[coordinate.row() - 1][coordinate.col() + 1], "[")) {
                                            if (newBoxes.stream().noneMatch(newBox -> newBox.row() == coordinate.row() - 1 && newBox.col() == coordinate.col() + 1)) {
                                                newBoxes.add(new Coordinate(coordinate.row() - 1, coordinate.col() + 1));
                                            }
                                        }
                                    }
                                }

                                if (newBoxes.isEmpty()) {
                                    noNewBoxesFound = true;
                                } else {
                                    rowsAndBoxes.add(newBoxes);
                                }

                            } while (!obstacleFound && !noNewBoxesFound);

                            if (!obstacleFound) {

                                for (List<Coordinate> row : rowsAndBoxes.reversed()) {
                                    for (Coordinate coordinate : row) {
                                        // progress the box
                                        grid[coordinate.row() - 1][coordinate.col()] = "[";
                                        grid[coordinate.row() - 1][coordinate.col() + 1] = "]";

                                        grid[coordinate.row()][coordinate.col()] = ".";
                                        grid[coordinate.row()][coordinate.col() + 1] = ".";
                                    }
                                }

                                grid[posRow][posCol] = ".";
                                grid[posRow - 1][posCol] = "@";
                                posRow--;
                            }
                        }

                        break;
                    case ">":
                        if (Objects.equals(grid[posRow][posCol + 1], ".")) {
                            grid[posRow][posCol] = ".";
                            grid[posRow][posCol + 1] = "@";
                            posCol++;
                        } else if (Objects.equals(grid[posRow][posCol + 1], "[")) {
                            // keep checking to see if we find a dot or an obstacle
                            boolean obstacleFound = false;
                            boolean dotFound = false;

                            int offset = 0;
                            do {
                                offset++;
                                if (Objects.equals(grid[posRow][posCol + offset], ".")) {
                                    dotFound = true;
                                } else if (Objects.equals(grid[posRow][posCol + offset], "#")) {
                                    obstacleFound = true;
                                }
                            } while (!obstacleFound && !dotFound);

                            if (dotFound) {
                                grid[posRow][posCol] = ".";
                                grid[posRow][posCol + 1] = "@";
                                boolean left = true;

                                for (int i = posCol + 2; i < posCol + offset + 1; i++) {
                                    if (left) {
                                        grid[posRow][i] = "[";
                                        left = false;
                                    } else {
                                        grid[posRow][i] = "]";
                                        left = true;
                                    }
                                }
                                posCol++;
                            }
                        }

                        break;
                    case "<":
                        if (Objects.equals(grid[posRow][posCol - 1], ".")) {
                            grid[posRow][posCol] = ".";
                            grid[posRow][posCol - 1] = "@";
                            posCol--;
                        } else if (Objects.equals(grid[posRow][posCol - 1], "]")) {
                            // keep checking to see if we find a dot or an obstacle
                            boolean obstacleFound = false;
                            boolean dotFound = false;

                            int offset = 0;
                            do {
                                offset++;
                                if (Objects.equals(grid[posRow][posCol - offset], ".")) {
                                    dotFound = true;
                                } else if (Objects.equals(grid[posRow][posCol - offset], "#")) {
                                    obstacleFound = true;
                                }
                            } while (!obstacleFound && !dotFound);

                            if (dotFound) {
                                grid[posRow][posCol] = ".";
                                grid[posRow][posCol - 1] = "@";
                                boolean right = true;

                                for (int i = posCol - 2; i > posCol - offset - 1; i--) {
                                    if (right) {
                                        grid[posRow][i] = "]";
                                        right = false;
                                    } else {
                                        grid[posRow][i] = "[";
                                        right = true;
                                    }
                                }
                                posCol--;
                            }
                        }

                        break;

                }
            }
        }

        int gpsSum = 0;

        for (int row = 0; row < gridLines.size(); row++) {
            for (int col = 0; col < gridLines.get(0).length() * 2; col++) {
                if (Objects.equals(grid[row][col], "[")) {
                    gpsSum += (100 * row) + col;
                }
            }
        }

        return String.valueOf(gpsSum);
    }

}
