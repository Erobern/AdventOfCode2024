package adventofcode2024.solvers;

import adventofcode2024.fileloaders.FileLoaders;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Day14 {
    public static String Puzzle1(String input, int width, int height) {
        List<String> lines = FileLoaders.loadInputIntoStringList(input);

        List<Robot> robots = new ArrayList<>();

        for (String line : lines) {
            List<String> split = Arrays.stream(line.split(" ")).toList();
            List<Integer> positions = Arrays.stream(split.get(0).replace("p=", "").split(",")).map(Integer::parseInt).toList();
            List<Integer> velocities = Arrays.stream(split.get(1).replace("v=", "").split(",")).map(Integer::parseInt).toList();

            robots.add(new Robot(
                    positions.get(1),
                    positions.get(0),
                    velocities.get(1) >= 0 ? velocities.get(1) : height + velocities.get(1),
                    velocities.get(0) >= 0 ? velocities.get(0) : width + velocities.get(0)));
        }

        Integer[][] grid = new Integer[height][width];

        for (int row = 0; row < height; row++) {
            for (int col = 0; col < width; col++) {
                grid[row][col] = 0;
            }
        }

        for (Robot robot : robots) {
            grid[(robot.posRow + (robot.velRow * 100)) % height][(robot.posCol + (robot.velCol * 100)) % width]++;
        }

        int q1Count = 0;
        for (int row = 0; row < Math.floorDiv(height, 2); row++) {
            for (int col = 0; col < Math.floorDiv(width, 2); col++) {
                q1Count += grid[row][col];
            }
        }

        int q2Count = 0;
        for (int row = Math.floorDiv(height, 2) + 1; row < height; row++) {
            for (int col = 0; col < Math.floorDiv(width, 2); col++) {
                q2Count += grid[row][col];
            }
        }

        int q3Count = 0;
        for (int row = 0; row < Math.floorDiv(height, 2); row++) {
            for (int col = Math.floorDiv(width, 2) + 1; col < width; col++) {
                q3Count += grid[row][col];
            }
        }

        int q4Count = 0;
        for (int row = Math.floorDiv(height, 2) + 1; row < height; row++) {
            for (int col = Math.floorDiv(width, 2) + 1; col < width; col++) {
                q4Count += grid[row][col];
            }
        }


        return String.valueOf(q1Count * q2Count * q3Count * q4Count);
    }

    public static String Puzzle2(String input, int width, int height) {
        List<String> lines = FileLoaders.loadInputIntoStringList(input);

        List<Robot> robots = new ArrayList<>();

        for (String line : lines) {
            List<String> split = Arrays.stream(line.split(" ")).toList();
            List<Integer> positions = Arrays.stream(split.get(0).replace("p=", "").split(",")).map(Integer::parseInt).toList();
            List<Integer> velocities = Arrays.stream(split.get(1).replace("v=", "").split(",")).map(Integer::parseInt).toList();

            robots.add(new Robot(
                    positions.get(1),
                    positions.get(0),
                    velocities.get(1) >= 0 ? velocities.get(1) : height + velocities.get(1),
                    velocities.get(0) >= 0 ? velocities.get(0) : width + velocities.get(0)));
        }

        for (int i = 1; i < Integer.MAX_VALUE; i++) {
            Integer[][] grid = new Integer[height][width];

            for (int row = 0; row < height; row++) {
                for (int col = 0; col < width; col++) {
                    grid[row][col] = 0;
                }
            }

            for (Robot robot : robots) {
                grid[(robot.posRow + (robot.velRow * i)) % height][(robot.posCol + (robot.velCol * i)) % width]++;
            }
            String answer = "";

            boolean candidateTree = true;

            for (int row = 0; row < height; row++) {
                for (int col = 0; col < width; col++) {
                    answer += grid[row][col].toString();
                    if (grid[row][col] > 1) {
                        candidateTree = false;
                    }
                }
                answer += '\n';
            }

            if (candidateTree) {
                System.out.println(answer);
                System.out.println(i);
            }
        }

        return "";
    }

    record Robot(Integer posRow, Integer posCol, Integer velRow, Integer velCol) {
    }
}
