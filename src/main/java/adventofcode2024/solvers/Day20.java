package adventofcode2024.solvers;

import adventofcode2024.fileloaders.FileLoaders;
import adventofcode2024.utils.records.Coordinate;

import java.util.*;

public class Day20 {
    public static String Puzzle1(String input, Integer cheatThreshold) {
        List<String> lines = FileLoaders.loadInputIntoStringList(input);

        String[][] grid = new String[lines.size()][lines.getFirst().length()];

        int startRow = 0;
        int startCol = 0;

        int endRow = 0;
        int endCol = 0;

        for (int row = 0; row < lines.size(); row++) {
            for (int col = 0; col < lines.get(row).length(); col++) {
                grid[row][col] = String.valueOf(lines.get(row).charAt(col));
                if (String.valueOf(lines.get(row).charAt(col)).equals("S")) {
                    startRow = row;
                    startCol = col;
                }
                if (String.valueOf(lines.get(row).charAt(col)).equals("E")) {
                    endRow = row;
                    endCol = col;
                }
            }
        }

        Integer row = startRow;
        Integer col = startCol;

        boolean endReached = false;
        Integer picoseconds = 0;

        List<Coordinate> travelledPath = new ArrayList<>();

        while (!endReached) {

            // traverse the maze
            travelledPath.add(new Coordinate(row, col));

            // up
            if (Objects.equals(grid[row - 1][col], ".")) {
                grid[row][col] = picoseconds.toString();
                row--;
                picoseconds++;
            }
            // down
            else if (Objects.equals(grid[row + 1][col], ".")) {
                grid[row][col] = picoseconds.toString();
                row++;
                picoseconds++;
            }
            // left
            else if (Objects.equals(grid[row][col - 1], ".")) {
                grid[row][col] = picoseconds.toString();
                col--;
                picoseconds++;
            }
            // right
            else if (Objects.equals(grid[row][col + 1], ".")) {
                grid[row][col] = picoseconds.toString();
                col++;
                picoseconds++;
            }

            // up
            if (Objects.equals(grid[row - 1][col], "E")) {
                grid[row][col] = picoseconds.toString();
                picoseconds++;
                grid[row - 1][col] = picoseconds.toString();
                endReached = true;
            }
            // down
            else if (Objects.equals(grid[row + 1][col], "E")) {
                grid[row][col] = picoseconds.toString();
                picoseconds++;
                grid[row + 1][col] = picoseconds.toString();
                endReached = true;
            }
            // left
            else if (Objects.equals(grid[row][col - 1], "E")) {
                grid[row][col] = picoseconds.toString();
                picoseconds++;
                grid[row][col - 1] = picoseconds.toString();
                endReached = true;
            }
            // right
            else if (Objects.equals(grid[row][col + 1], "E")) {
                grid[row][col] = picoseconds.toString();
                picoseconds++;
                grid[row][col + 1] = picoseconds.toString();
                endReached = true;
            }

        }

        List<Cheat> cheats = new ArrayList<>();

        for (Coordinate coordinate : travelledPath) {
            // look for cheats
            // up
            try {
                if (!Objects.equals(grid[coordinate.row() - 2][coordinate.col()], "#")) {
                    if (Integer.parseInt(grid[coordinate.row() - 2][coordinate.col()]) - Integer.parseInt(grid[coordinate.row()][coordinate.col()]) > 2) {
                        cheats.add(new Cheat(
                                coordinate,
                                new Coordinate(coordinate.row() - 2, coordinate.col()),
                                Integer.parseInt(grid[coordinate.row() - 2][coordinate.col()]) - Integer.parseInt(grid[coordinate.row()][coordinate.col()]) - 2
                        ));
                    }
                }
            } catch (Exception ignored) {

            }
            // down
            try {
                if (!Objects.equals(grid[coordinate.row() + 2][coordinate.col()], "#")) {
                    if (Integer.parseInt(grid[coordinate.row() + 2][coordinate.col()]) - Integer.parseInt(grid[coordinate.row()][coordinate.col()]) > 2) {
                        cheats.add(new Cheat(
                                coordinate,
                                new Coordinate(coordinate.row() + 2, coordinate.col()),
                                Integer.parseInt(grid[coordinate.row() + 2][coordinate.col()]) - Integer.parseInt(grid[coordinate.row()][coordinate.col()]) - 2
                        ));
                    }
                }
            } catch (Exception ignored) {

            }
            // left
            try {
                if (!Objects.equals(grid[coordinate.row()][coordinate.col() - 2], "#")) {
                    if (Integer.parseInt(grid[coordinate.row()][coordinate.col() - 2]) - Integer.parseInt(grid[coordinate.row()][coordinate.col()]) > 2) {
                        cheats.add(new Cheat(
                                coordinate,
                                new Coordinate(coordinate.row(), coordinate.col() - 2),
                                Integer.parseInt(grid[coordinate.row()][coordinate.col() - 2]) - Integer.parseInt(grid[coordinate.row()][coordinate.col()]) - 2
                        ));
                    }
                }
            } catch (Exception ignored) {

            }
            // right
            try {
                if (!Objects.equals(grid[coordinate.row()][coordinate.col() + 2], "#")) {
                    if (Integer.parseInt(grid[coordinate.row()][coordinate.col() + 2]) - Integer.parseInt(grid[coordinate.row()][coordinate.col()]) > 2) {
                        cheats.add(new Cheat(
                                coordinate,
                                new Coordinate(coordinate.row(), coordinate.col() + 2),
                                Integer.parseInt(grid[coordinate.row()][coordinate.col() + 2]) - Integer.parseInt(grid[coordinate.row()][coordinate.col()]) - 2
                        ));
                    }
                }
            } catch (Exception ignored) {

            }
        }

        cheats.sort(Comparator.comparingInt(Cheat::picosecondsSaved));

        return String.valueOf(cheats.stream().filter(cheat -> cheat.picosecondsSaved >= cheatThreshold).count());
    }

    public static String Puzzle2(String input, Integer cheatThreshold) {
        List<String> lines = FileLoaders.loadInputIntoStringList(input);

        String[][] grid = new String[lines.size()][lines.getFirst().length()];

        int startRow = 0;
        int startCol = 0;

        int endRow = 0;
        int endCol = 0;

        for (int row = 0; row < lines.size(); row++) {
            for (int col = 0; col < lines.get(row).length(); col++) {
                grid[row][col] = String.valueOf(lines.get(row).charAt(col));
                if (String.valueOf(lines.get(row).charAt(col)).equals("S")) {
                    startRow = row;
                    startCol = col;
                }
                if (String.valueOf(lines.get(row).charAt(col)).equals("E")) {
                    endRow = row;
                    endCol = col;
                }
            }
        }

        Integer row = startRow;
        Integer col = startCol;

        boolean endReached = false;
        Integer picoseconds = 0;

        List<Coordinate> travelledPath = new ArrayList<>();
        List<Path> travelledPathScores = new ArrayList<>();

        while (!endReached) {

            // traverse the maze
            travelledPath.add(new Coordinate(row, col));

            // up
            if (Objects.equals(grid[row - 1][col], ".")) {
                grid[row][col] = picoseconds.toString();
                travelledPathScores.add(new Path(new Coordinate(row, col), picoseconds));
                row--;
                picoseconds++;
            }
            // down
            else if (Objects.equals(grid[row + 1][col], ".")) {
                grid[row][col] = picoseconds.toString();
                travelledPathScores.add(new Path(new Coordinate(row, col), picoseconds));
                row++;
                picoseconds++;
            }
            // left
            else if (Objects.equals(grid[row][col - 1], ".")) {
                grid[row][col] = picoseconds.toString();
                travelledPathScores.add(new Path(new Coordinate(row, col), picoseconds));
                col--;
                picoseconds++;
            }
            // right
            else if (Objects.equals(grid[row][col + 1], ".")) {
                grid[row][col] = picoseconds.toString();
                travelledPathScores.add(new Path(new Coordinate(row, col), picoseconds));
                col++;
                picoseconds++;
            }

            // up
            if (Objects.equals(grid[row - 1][col], "E")) {
                grid[row][col] = picoseconds.toString();
                travelledPathScores.add(new Path(new Coordinate(row, col), picoseconds));
                picoseconds++;
                grid[row - 1][col] = picoseconds.toString();
                travelledPathScores.add(new Path(new Coordinate(row - 1, col), picoseconds));
                endReached = true;
            }
            // down
            else if (Objects.equals(grid[row + 1][col], "E")) {
                grid[row][col] = picoseconds.toString();
                travelledPathScores.add(new Path(new Coordinate(row, col), picoseconds));
                picoseconds++;
                grid[row + 1][col] = picoseconds.toString();
                travelledPathScores.add(new Path(new Coordinate(row + 1, col), picoseconds));
                endReached = true;
            }
            // left
            else if (Objects.equals(grid[row][col - 1], "E")) {
                grid[row][col] = picoseconds.toString();
                travelledPathScores.add(new Path(new Coordinate(row, col), picoseconds));
                picoseconds++;
                grid[row][col - 1] = picoseconds.toString();
                travelledPathScores.add(new Path(new Coordinate(row, col - 1), picoseconds));
                endReached = true;
            }
            // right
            else if (Objects.equals(grid[row][col + 1], "E")) {
                grid[row][col] = picoseconds.toString();
                travelledPathScores.add(new Path(new Coordinate(row, col), picoseconds));
                picoseconds++;
                grid[row][col + 1] = picoseconds.toString();
                travelledPathScores.add(new Path(new Coordinate(row, col + 1), picoseconds));
                endReached = true;
            }

        }

        Map<String, Integer> cheats = new HashMap<>();

        Integer i = 0;

        for (Path travelledPathScoreCurrent : travelledPathScores) {

            i++;
            if (i % 250 == 0) {
                System.out.println("Path progress: " + i + " / " + travelledPathScores.size());
            }

            Coordinate coordinate = travelledPathScoreCurrent.coordinate;

            travelledPathScores.stream()
                    .filter(travelledPathScore ->
                            Math.abs(coordinate.row() - travelledPathScore.coordinate.row()) <= 20 &&
                                    Math.abs(coordinate.col() - travelledPathScore.coordinate.col()) <= 20)
                    .filter(travelledPathScore ->
                            Math.abs(coordinate.row() - travelledPathScore.coordinate.row()) +
                                    Math.abs(coordinate.col() - travelledPathScore.coordinate.col()) <= 20)
                    .filter(travelledPathScore ->
                            travelledPathScore.picoseconds - travelledPathScoreCurrent.picoseconds > 2)
                    .forEach(travelledPathScore -> {
                        Integer offset = Math.abs(coordinate.row() - travelledPathScore.coordinate.row()) +
                                Math.abs(coordinate.col() - travelledPathScore.coordinate.col());
                        if (travelledPathScore.picoseconds - Integer.parseInt(grid[coordinate.row()][coordinate.col()]) > offset) {
                            cheats.put(
                                    coordinate + ":" +
                                            travelledPathScore.coordinate,
                                    travelledPathScore.picoseconds - Integer.parseInt(grid[coordinate.row()][coordinate.col()]) - offset
                            );
                        }
                    });
        }

        return String.valueOf(cheats.values().stream().filter(cheat -> cheat >= cheatThreshold).count());
    }

    record Cheat(Coordinate startCoordinate, Coordinate endCoordinate, Integer picosecondsSaved) {
    }

    record Path(Coordinate coordinate, Integer picoseconds) {
    }
}
