package adventofcode2024.solvers;

import adventofcode2024.fileloaders.FileLoaders;
import adventofcode2024.utils.records.Coordinate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Day21 {
    public static String Puzzle1(String input) {
        List<String> lines = FileLoaders.loadInputIntoStringList(input);

        // init keypads
        String[][] numpad = new String[4][3];

        seedNumpad(numpad);

        String[][] keypad = new String[2][3];

        seedKeypad(keypad);

        // build hashmaps
        Map<String, List<String>> numpadMap = new HashMap<>();
        Map<String, List<String>> directionMap = new HashMap<>();

        // numpad map

        populateHashmaps(numpad, numpadMap, keypad, directionMap);

        Integer shortestSequencesAnswer = 0;

        for (String line : lines) {

            // first order transformations

            List<String> firstOrder = new ArrayList<>();

            String previousLocation = "A";

            for (String targetPosition : line.split("")) {
                if (firstOrder.isEmpty()) {
                    firstOrder = numpadMap.get(previousLocation + ":" + targetPosition);
                } else {
                    String finalPreviousLocation = previousLocation;
                    firstOrder = firstOrder.stream().flatMap(f ->
                                    numpadMap.get(finalPreviousLocation + ":" + targetPosition).stream().map(n -> f + n)
                            )
                            .toList();
                }

                previousLocation = targetPosition;
            }

            // second order

            List<String> secondOrder = new ArrayList<>();

            previousLocation = "A";

            for (String firstOrderIteration : firstOrder) {

                List<String> secondOrderTemp = new ArrayList<>();

                for (String targetPosition : firstOrderIteration.split("")) {
                    if (secondOrderTemp.isEmpty()) {
                        secondOrderTemp = directionMap.get(previousLocation + ":" + targetPosition);
                    } else {
                        String finalPreviousLocation = previousLocation;
                        secondOrderTemp = secondOrderTemp.stream().flatMap(f ->
                                        directionMap.get(finalPreviousLocation + ":" + targetPosition).stream().map(n -> f + n)
                                )
                                .toList();
                    }

                    previousLocation = targetPosition;
                }

                secondOrder.addAll(secondOrderTemp);

            }

            // third order

            List<String> thirdOrder = new ArrayList<>();

            previousLocation = "A";

            for (String secondOrderIteration : secondOrder) {

                List<String> thirdOrderTemp = new ArrayList<>();

                for (String targetPosition : secondOrderIteration.split("")) {
                    if (thirdOrderTemp.isEmpty()) {
                        thirdOrderTemp = directionMap.get(previousLocation + ":" + targetPosition);
                    } else {
                        String finalPreviousLocation = previousLocation;
                        thirdOrderTemp = thirdOrderTemp.stream().flatMap(f ->
                                        directionMap.get(finalPreviousLocation + ":" + targetPosition).stream().map(n -> f + n)
                                )
                                .toList();
                        thirdOrderTemp = thirdOrderTemp.stream().min((e2, e1) -> e1.length() > e2.length() ? -1 : 1).stream().toList();
                    }

                    previousLocation = targetPosition;
                }

                thirdOrder.addAll(thirdOrderTemp);
            }

            String shortest = thirdOrder.stream().min((e2, e1) -> e1.length() > e2.length() ? -1 : 1).stream().findFirst().get();
            String lineTemp = line.replace("A", "");

            shortestSequencesAnswer += shortest.length() * Integer.parseInt(lineTemp);
        }


        return shortestSequencesAnswer.toString();
    }

    private static List<String> getAllRoutes(Coordinate startCoordinate, Coordinate endCoordinate, String currentPath, String[][] numpad) {

        int rowOffset = startCoordinate.row() - endCoordinate.row();
        int colOffset = startCoordinate.col() - endCoordinate.col();

        List<String> paths = new ArrayList<>();


        if (rowOffset > 0) {
            //up
            Coordinate nextCoordinate = new Coordinate(startCoordinate.row() - 1, startCoordinate.col());
            if (numpad[nextCoordinate.row()][nextCoordinate.col()] != null) {
                if (nextCoordinate.equals(endCoordinate)) {
                    paths.add(currentPath + "^A");
                } else {
                    paths.addAll(getAllRoutes(nextCoordinate, endCoordinate, currentPath + "^", numpad));
                }
            }
        } else if (rowOffset < 0) {
            // down
            Coordinate nextCoordinate = new Coordinate(startCoordinate.row() + 1, startCoordinate.col());
            if (numpad[nextCoordinate.row()][nextCoordinate.col()] != null) {
                if (nextCoordinate.equals(endCoordinate)) {
                    paths.add(currentPath + "vA");
                } else {
                    paths.addAll(getAllRoutes(nextCoordinate, endCoordinate, currentPath + "v", numpad));
                }
            }
        }

        if (colOffset > 0) {
            //left
            Coordinate nextCoordinate = new Coordinate(startCoordinate.row(), startCoordinate.col() - 1);
            if (numpad[nextCoordinate.row()][nextCoordinate.col()] != null) {
                if (nextCoordinate.equals(endCoordinate)) {
                    paths.add(currentPath + "<A");
                } else {
                    paths.addAll(getAllRoutes(nextCoordinate, endCoordinate, currentPath + "<", numpad));
                }
            }
        } else if (colOffset < 0) {
            // right
            Coordinate nextCoordinate = new Coordinate(startCoordinate.row(), startCoordinate.col() + 1);
            if (numpad[nextCoordinate.row()][nextCoordinate.col()] != null) {
                if (nextCoordinate.equals(endCoordinate)) {
                    paths.add(currentPath + ">A");
                } else {
                    paths.addAll(getAllRoutes(nextCoordinate, endCoordinate, currentPath + ">", numpad));
                }
            }
        }

        return paths;
    }

    public static String Puzzle2(String input) {
        List<String> lines = FileLoaders.loadInputIntoStringList(input);

        // init keypads
        String[][] numpad = new String[4][3];

        seedNumpad(numpad);

        String[][] keypad = new String[2][3];

        seedKeypad(keypad);

        // build hashmaps
        Map<String, List<String>> numpadMap = new HashMap<>();
        Map<String, List<String>> directionMap = new HashMap<>();

        populateHashmaps(numpad, numpadMap, keypad, directionMap);

        Long shortestSequencesAnswer = 0L;
        Map<String, Long> cachedSolutions = new HashMap<>();

        populateCachedSolutions(cachedSolutions, directionMap);

        for (String line : lines) {

            // first order transformations

            List<String> firstOrder = new ArrayList<>();

            String previousLocation = "A";

            for (String targetPosition : line.split("")) {
                if (firstOrder.isEmpty()) {
                    firstOrder = numpadMap.get(previousLocation + ":" + targetPosition);
                } else {
                    String finalPreviousLocation = previousLocation;
                    firstOrder = firstOrder.stream().flatMap(f ->
                                    numpadMap.get(finalPreviousLocation + ":" + targetPosition).stream().map(n -> f + n)
                            )
                            .toList();
                }

                previousLocation = targetPosition;
            }

            Long bestSolution = Long.MAX_VALUE;

            int depth = 25;

            for (String value : firstOrder) {
                Long solution = 0L;
                String previous = "A";

                for (String current : value.split("")) {
                    solution += cachedSolutions.get(depth + ":" + previous + ":" + current);
                    previous = current;
                }

                if (bestSolution > solution) {
                    bestSolution = solution;
                }
            }

            String lineTemp = line.replace("A", "");

            shortestSequencesAnswer += bestSolution * Integer.parseInt(lineTemp);

        }

        return shortestSequencesAnswer.toString();
    }

    private static void populateCachedSolutions(Map<String, Long> cachedSolutions, Map<String, List<String>> directionMap) {

        for (int depth = 1; depth < 26; depth++) {

            if (depth == 1) {
                for (String key : directionMap.keySet()) {
                    cachedSolutions.put(depth + ":" + key, (long) directionMap.get(key)
                            .stream().min((e2, e1) -> e1.length() > e2.length() ? -1 : 1).stream().findFirst().get().length());
                }
            } else {
                for (String key : directionMap.keySet()) {

                    List<String> values = directionMap.get(key);

                    Long bestSolution = Long.MAX_VALUE;

                    for (String value : values) {
                        Long solution = 0L;
                        String previous = "A";

                        for (String current : value.split("")) {
                            solution += cachedSolutions.get(depth - 1 + ":" + previous + ":" + current);
                            previous = current;
                        }

                        if (bestSolution > solution) {
                            bestSolution = solution;
                        }
                    }

                    cachedSolutions.put(depth + ":" + key, bestSolution);
                }
            }

        }

    }

    private static void seedKeypad(String[][] keypad) {
        keypad[0][0] = null;
        keypad[0][1] = "^";
        keypad[0][2] = "A";

        keypad[1][0] = "<";
        keypad[1][1] = "v";
        keypad[1][2] = ">";
    }

    private static void seedNumpad(String[][] numpad) {
        numpad[0][0] = "7";
        numpad[0][1] = "8";
        numpad[0][2] = "9";

        numpad[1][0] = "4";
        numpad[1][1] = "5";
        numpad[1][2] = "6";

        numpad[2][0] = "1";
        numpad[2][1] = "2";
        numpad[2][2] = "3";

        numpad[3][0] = null;
        numpad[3][1] = "0";
        numpad[3][2] = "A";
    }

    private static void populateHashmaps(String[][] numpad, Map<String, List<String>> numpadMap, String[][] keypad, Map<String, List<String>> directionMap) {
        // numpad map

        for (int sourceRow = 0; sourceRow < 4; sourceRow++) {
            for (int sourceCol = 0; sourceCol < 3; sourceCol++) {

                if (numpad[sourceRow][sourceCol] != null) {

                    for (int targetRow = 0; targetRow < 4; targetRow++) {
                        for (int targetCol = 0; targetCol < 3; targetCol++) {

                            if (numpad[targetRow][targetCol] != null && numpad[targetRow][targetCol] != numpad[sourceRow][sourceCol]) {

                                Coordinate startCoordinate = new Coordinate(sourceRow, sourceCol);
                                Coordinate endCoordinate = new Coordinate(targetRow, targetCol);

                                List<String> routes = getAllRoutes(startCoordinate, endCoordinate, "", numpad);

                                numpadMap.put(numpad[sourceRow][sourceCol] + ":" + numpad[targetRow][targetCol], routes);

                            }
                        }
                    }

                }
            }
        }


        for (int sourceRow = 0; sourceRow < 2; sourceRow++) {
            for (int sourceCol = 0; sourceCol < 3; sourceCol++) {

                if (keypad[sourceRow][sourceCol] != null) {

                    for (int targetRow = 0; targetRow < 2; targetRow++) {
                        for (int targetCol = 0; targetCol < 3; targetCol++) {

                            if (keypad[targetRow][targetCol] != null && keypad[targetRow][targetCol] != keypad[sourceRow][sourceCol]) {

                                Coordinate startCoordinate = new Coordinate(sourceRow, sourceCol);
                                Coordinate endCoordinate = new Coordinate(targetRow, targetCol);

                                List<String> routes = getAllRoutes(startCoordinate, endCoordinate, "", keypad);

                                directionMap.put(keypad[sourceRow][sourceCol] + ":" + keypad[targetRow][targetCol], routes);

                            } else if (keypad[targetRow][targetCol] == keypad[sourceRow][sourceCol]) {
                                directionMap.put(keypad[sourceRow][sourceCol] + ":" + keypad[targetRow][targetCol], List.of("A"));
                            }
                        }
                    }

                }
            }
        }
    }
}
