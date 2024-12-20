package adventofcode2024.solvers;

import adventofcode2024.fileloaders.FileLoaders;
import adventofcode2024.utils.enums.Direction;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Day6 {
    public static String Puzzle1(String input) {
        List<String> lines = FileLoaders.loadInputIntoStringList(input);

        String[][] grid = new String[lines.size()][lines.get(0).length()];

        int x = 0;
        int y = 0;

        for (int i = 0; i < lines.size(); i++) {
            for (int j = 0; j < lines.get(i).length(); j++) {
                grid[i][j] = String.valueOf(lines.get(i).charAt(j));
                if (Objects.equals(grid[i][j], "^")) {
                    x = j;
                    y = i;
                }
            }
        }

        boolean inGrid = true;
        Direction direction = Direction.UP;

        while (inGrid) {
            // move, rotate if you hit a block, proceed until you exit grid
            try {
                if (direction == Direction.UP) {
                    if (Objects.equals(grid[y - 1][x], "#")) {
                        direction = Direction.RIGHT;
                    } else {
                        grid[y][x] = "X";
                        y--;
                    }
                } else if (direction == Direction.RIGHT) {
                    if (Objects.equals(grid[y][x + 1], "#")) {
                        direction = Direction.DOWN;
                    } else {
                        grid[y][x] = "X";
                        x++;
                    }
                } else if (direction == Direction.DOWN) {
                    if (Objects.equals(grid[y + 1][x], "#")) {
                        direction = Direction.LEFT;
                    } else {
                        grid[y][x] = "X";
                        y++;
                    }
                } else if (direction == Direction.LEFT) {
                    if (Objects.equals(grid[y][x - 1], "#")) {
                        direction = Direction.UP;
                    } else {
                        grid[y][x] = "X";
                        x--;
                    }
                }
            } catch (ArrayIndexOutOfBoundsException e) {
                inGrid = false;
                grid[x][y] = "X";
            }
        }

        Integer sum = 0;

        for (int i = 0; i < lines.size(); i++) {
            for (int j = 0; j < lines.get(i).length(); j++) {
                if (Objects.equals(grid[i][j], "X")) {
                    sum++;
                }
            }
        }

        return sum.toString();
    }

    public static String Puzzle2(String input) {
        List<String> lines = FileLoaders.loadInputIntoStringList(input);

        String[][] grid = new String[lines.size()][lines.get(0).length()];
        String[][] newObstacleMap = new String[lines.size()][lines.get(0).length()];
        String[][] originalCopyMap = new String[lines.size()][lines.get(0).length()];
        String[][] testCopyMap = new String[lines.size()][lines.get(0).length()];


        int x = 0;
        int y = 0;

        int startX = 0;
        int startY = 0;

        for (int i = 0; i < lines.size(); i++) {
            for (int j = 0; j < lines.get(i).length(); j++) {
                grid[i][j] = String.valueOf(lines.get(i).charAt(j));
                originalCopyMap[i][j] = String.valueOf(lines.get(i).charAt(j));
                if (Objects.equals(grid[i][j], "^")) {
                    x = j;
                    y = i;
                    startX = x;
                    startY = y;
                }
            }
        }

        boolean inGrid = true;
        Direction direction = Direction.UP;
        Integer obstacleSum = 0;

        while (inGrid) {
            // move, rotate if you hit a block, proceed until you exit grid
            try {
                if (direction == Direction.UP) {
                    if (Objects.equals(grid[y - 1][x], "#")) {
                        direction = Direction.RIGHT;
                    } else {
                        // test new obstacle
                        if (!Objects.equals(newObstacleMap[y - 1][x], "O")) {
                            for (int i = 0; i < lines.size(); i++) {
                                System.arraycopy(originalCopyMap[i], 0, testCopyMap[i], 0, lines.get(i).length());
                            }
                            testCopyMap[y - 1][x] = "#";
                            if (doesNewObstacleCreateLoop(testCopyMap, startX, startY, x, y, direction)) {
                                newObstacleMap[y - 1][x] = "O";
                            }
                        }

                        grid[y][x] = "X";
                        y--;
                    }
                } else if (direction == Direction.RIGHT) {
                    if (Objects.equals(grid[y][x + 1], "#")) {
                        direction = Direction.DOWN;
                    } else {
                        // test new obstacle
                        if (!Objects.equals(newObstacleMap[y][x + 1], "O")) {
                            for (int i = 0; i < lines.size(); i++) {
                                System.arraycopy(originalCopyMap[i], 0, testCopyMap[i], 0, lines.get(i).length());
                            }
                            testCopyMap[y][x + 1] = "#";
                            if (doesNewObstacleCreateLoop(testCopyMap, startX, startY, x, y, direction)) {
                                newObstacleMap[y][x + 1] = "O";
                            }
                        }

                        grid[y][x] = "X";
                        x++;
                    }
                } else if (direction == Direction.DOWN) {
                    if (Objects.equals(grid[y + 1][x], "#")) {
                        direction = Direction.LEFT;
                    } else {
                        // test new obstacle
                        if (!Objects.equals(newObstacleMap[y + 1][x], "O")) {
                            for (int i = 0; i < lines.size(); i++) {
                                System.arraycopy(originalCopyMap[i], 0, testCopyMap[i], 0, lines.get(i).length());
                            }
                            testCopyMap[y + 1][x] = "#";
                            if (doesNewObstacleCreateLoop(testCopyMap, startX, startY, x, y, direction)) {
                                newObstacleMap[y + 1][x] = "O";
                            }
                        }

                        grid[y][x] = "X";
                        y++;
                    }
                } else if (direction == Direction.LEFT) {
                    if (Objects.equals(grid[y][x - 1], "#")) {
                        direction = Direction.UP;
                    } else {
                        // test new obstacle
                        if (!Objects.equals(newObstacleMap[y][x - 1], "O")) {
                            for (int i = 0; i < lines.size(); i++) {
                                System.arraycopy(originalCopyMap[i], 0, testCopyMap[i], 0, lines.get(i).length());
                            }
                            testCopyMap[y][x - 1] = "#";
                            if (doesNewObstacleCreateLoop(testCopyMap, startX, startY, x, y, direction)) {
                                newObstacleMap[y][x - 1] = "O";
                            }
                        }
                        grid[y][x] = "X";
                        x--;
                    }
                }
            } catch (ArrayIndexOutOfBoundsException e) {
                inGrid = false;
                grid[y][x] = "X";
            }
        }

        Integer sum = 0;

        for (int i = 0; i < lines.size(); i++) {
            for (int j = 0; j < lines.get(i).length(); j++) {
                if (Objects.equals(newObstacleMap[i][j], "O")) {
                    sum++;
                }
            }
        }

        return sum.toString();
    }

    private static boolean doesNewObstacleCreateLoop(String[][] testGrid, Integer startX, Integer startY, Integer loopX, Integer loopY, Direction loopDirection) {
        Direction direction = Direction.UP;

        Integer x = startX;
        Integer y = startY;

        List<String> checkMap = new ArrayList<>();

        while (true) {
            // move, rotate if you hit a block, proceed until you exit grid
            try {

                if (direction == Direction.UP) {
                    if (Objects.equals(testGrid[y - 1][x], "#")) {
                        direction = Direction.RIGHT;
                    } else {
                        testGrid[y][x] = "X";
                        if (checkMap.contains(x + "-" + y + "-" + direction)) {
                            return true;
                        } else {
                            checkMap.add(x + "-" + y + "-" + direction);
                        }
                        y--;
                    }
                } else if (direction == Direction.RIGHT) {
                    if (Objects.equals(testGrid[y][x + 1], "#")) {
                        direction = Direction.DOWN;
                    } else {
                        testGrid[y][x] = "X";
                        if (checkMap.contains(x + "-" + y + "-" + direction)) {
                            return true;
                        } else {
                            checkMap.add(x + "-" + y + "-" + direction);
                        }
                        x++;
                    }
                } else if (direction == Direction.DOWN) {
                    if (Objects.equals(testGrid[y + 1][x], "#")) {
                        direction = Direction.LEFT;
                    } else {
                        testGrid[y][x] = "X";
                        if (checkMap.contains(x + "-" + y + "-" + direction)) {
                            return true;
                        } else {
                            checkMap.add(x + "-" + y + "-" + direction);
                        }
                        y++;
                    }
                } else if (direction == Direction.LEFT) {
                    if (Objects.equals(testGrid[y][x - 1], "#")) {
                        direction = Direction.UP;
                    } else {
                        testGrid[y][x] = "X";
                        if (checkMap.contains(x + "-" + y + "-" + direction)) {
                            return true;
                        } else {
                            checkMap.add(x + "-" + y + "-" + direction);
                        }
                        x--;
                    }
                }

            } catch (ArrayIndexOutOfBoundsException e) {
                return false;
            }
        }
    }

}
