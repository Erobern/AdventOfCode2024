package adventofcode2024.solvers;

import adventofcode2024.fileloaders.FileLoaders;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;
import java.util.regex.MatchResult;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day4 {
    public static String Puzzle1(String input) {
        List<String> lines = FileLoaders.loadInputIntoStringList(input);

        String[][] grid = new String[lines.size()][lines.get(0).length()];

        for (int i = 0; i < lines.size(); i++) {
            for (int j = 0; j < lines.get(i).length(); j++) {
                grid[i][j] = String.valueOf(lines.get(i).charAt(j));
            }
        }

        Integer xmasCount = 0;

        for (int i = 0; i < lines.size(); i++) {
            for (int j = 0; j < lines.get(i).length(); j++) {
                if (grid[i][j].equals("X")) {
                    xmasCount += getXmasCount(grid, i, j);
                }
            }
        }
        return xmasCount.toString();
    }

    private static Integer getXmasCount(String[][] grid, int i, int j) {
        // check all 8 directions
        Integer xmasCount = 0;

        // up
        try {
            if (grid[i-1][j].equals("M") && grid[i-2][j].equals("A") && grid[i-3][j].equals("S")) {
                xmasCount++;
            }
        } catch (Exception e) {
            // who cares
        }

        // down
        try {
            if (grid[i+1][j].equals("M") && grid[i+2][j].equals("A") && grid[i+3][j].equals("S")) {
                xmasCount++;
            }
        } catch (Exception e) {
            // who cares
        }

        // left
        try {
            if (grid[i][j-1].equals("M") && grid[i][j-2].equals("A") && grid[i][j-3].equals("S")) {
                xmasCount++;
            }
        } catch (Exception e) {
            // who cares
        }

        // right
        try {
            if (grid[i][j+1].equals("M") && grid[i][j+2].equals("A") && grid[i][j+3].equals("S")) {
                xmasCount++;
            }
        } catch (Exception e) {
            // who cares
        }

        // up-left
        try {
            if (grid[i-1][j-1].equals("M") && grid[i-2][j-2].equals("A") && grid[i-3][j-3].equals("S")) {
                xmasCount++;
            }
        } catch (Exception e) {
            // who cares
        }

        // up-right
        try {
            if (grid[i-1][j+1].equals("M") && grid[i-2][j+2].equals("A") && grid[i-3][j+3].equals("S")) {
                xmasCount++;
            }
        } catch (Exception e) {
            // who cares
        }

        // down-left
        try {
            if (grid[i+1][j-1].equals("M") && grid[i+2][j-2].equals("A") && grid[i+3][j-3].equals("S")) {
                xmasCount++;
            }
        } catch (Exception e) {
            // who cares
        }

        // down-right
        try {
            if (grid[i+1][j+1].equals("M") && grid[i+2][j+2].equals("A") && grid[i+3][j+3].equals("S")) {
                xmasCount++;
            }
        } catch (Exception e) {
            // who cares
        }

        return xmasCount;
    }

    public static String Puzzle2(String input) {
        List<String> lines = FileLoaders.loadInputIntoStringList(input);

        String[][] grid = new String[lines.size()][lines.get(0).length()];

        for (int i = 0; i < lines.size(); i++) {
            for (int j = 0; j < lines.get(i).length(); j++) {
                grid[i][j] = String.valueOf(lines.get(i).charAt(j));
            }
        }

        Integer masCount = 0;

        for (int i = 0; i < lines.size(); i++) {
            for (int j = 0; j < lines.get(i).length(); j++) {
                if (grid[i][j].equals("A")) {
                    masCount += getMasCount(grid, i, j);
                }
            }
        }
        return masCount.toString();
    }

    private static Integer getMasCount(String[][] grid, int i, int j) {
        Integer masCount = 0;

        // diagonal
        try {
            if ((grid[i-1][j-1].equals("M") && grid[i+1][j+1].equals("S") ||
                    grid[i-1][j-1].equals("S") && grid[i+1][j+1].equals("M")) &&
                    (grid[i+1][j-1].equals("M") && grid[i-1][j+1].equals("S") ||
                    grid[i+1][j-1].equals("S") && grid[i-1][j+1].equals("M")))  {
                masCount++;
            }
        } catch (Exception e) {
            // who cares
        }

        return masCount;
    }
}
