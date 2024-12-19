package adventofcode2024.solvers;

import adventofcode2024.fileloaders.FileLoaders;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;

public class Day19 {

    public static String Puzzle1(String input) {
        List<String> lines = FileLoaders.loadInputIntoStringList(input);

        List<String> availableTowels = new ArrayList<>();

        List<String> availableTowelsWhite = new ArrayList<>();
        List<String> availableTowelsBlue = new ArrayList<>();
        List<String> availableTowelsBlack = new ArrayList<>();
        List<String> availableTowelsRed = new ArrayList<>();
        List<String> availableTowelsGreen = new ArrayList<>();

        List<String> patterns = new ArrayList<>();

        for (String line : lines) {
            if (!line.isEmpty()) {
                if (line.contains(",")) {
                    availableTowels = Arrays.stream(line.split(",")).map(String::trim).toList();
                } else {
                    patterns.add(line);
                }
            }
        }

        availableTowelsWhite = availableTowels.stream().filter(towel -> towel.startsWith("w")).toList();
        availableTowelsBlue = availableTowels.stream().filter(towel -> towel.startsWith("u")).toList();
        availableTowelsBlack = availableTowels.stream().filter(towel -> towel.startsWith("b")).toList();
        availableTowelsRed = availableTowels.stream().filter(towel -> towel.startsWith("r")).toList();
        availableTowelsGreen = availableTowels.stream().filter(towel -> towel.startsWith("g")).toList();

        Map<String, List<String>> towelMap = new HashMap<>();

        towelMap.put("w", availableTowelsWhite);
        towelMap.put("u", availableTowelsBlue);
        towelMap.put("b", availableTowelsBlack);
        towelMap.put("r", availableTowelsRed);
        towelMap.put("g", availableTowelsGreen);

        AtomicReference<Integer> possiblePatterns = new AtomicReference<>(0);

        patterns.forEach(pattern -> {
            List<String> currentTests = new ArrayList<>(Collections.singleton(pattern));
            List<String> nextTests = new ArrayList<>();

            boolean patternIsValid = false;

            while (!currentTests.isEmpty() && !patternIsValid) {
                for (String currentTest : currentTests) {
                    if (patternIsValid) {
                        break;
                    }
                    for (String availableTowel : towelMap.get(currentTest.substring(0, 1))) {
                        if (currentTest.startsWith(availableTowel)) {
                            if (currentTest.equals(availableTowel)) {
                                patternIsValid = true;
                            } else {
                                nextTests.add(currentTest.substring(availableTowel.length()));
                            }
                        }
                    }
                }

                currentTests = new ArrayList<>(nextTests.stream().distinct().toList());
                nextTests = new ArrayList<>();

            }

            if (patternIsValid) {
                possiblePatterns.getAndSet(possiblePatterns.get() + 1);
                System.out.println(pattern + ": Valid");
            } else {
                System.out.println(pattern + ": Invalid");
            }
        });

        return possiblePatterns.toString();
    }

    static Map<String, Long> solutionMap = new HashMap<>();

    public static String Puzzle2(String input) {
        List<String> lines = FileLoaders.loadInputIntoStringList(input);

        List<String> availableTowels = new ArrayList<>();

        List<String> availableTowelsWhite = new ArrayList<>();
        List<String> availableTowelsBlue = new ArrayList<>();
        List<String> availableTowelsBlack = new ArrayList<>();
        List<String> availableTowelsRed = new ArrayList<>();
        List<String> availableTowelsGreen = new ArrayList<>();

        List<String> patterns = new ArrayList<>();

        solutionMap = new HashMap<>();

        for (String line : lines) {
            if (!line.isEmpty()) {
                if (line.contains(",")) {
                    availableTowels = Arrays.stream(line.split(",")).map(String::trim).toList();
                } else {
                    patterns.add(line);
                }
            }
        }

        availableTowelsWhite = availableTowels.stream().filter(towel -> towel.startsWith("w")).toList();
        availableTowelsBlue = availableTowels.stream().filter(towel -> towel.startsWith("u")).toList();
        availableTowelsBlack = availableTowels.stream().filter(towel -> towel.startsWith("b")).toList();
        availableTowelsRed = availableTowels.stream().filter(towel -> towel.startsWith("r")).toList();
        availableTowelsGreen = availableTowels.stream().filter(towel -> towel.startsWith("g")).toList();

        Map<String, List<String>> towelMap = new HashMap<>();

        towelMap.put("w", availableTowelsWhite);
        towelMap.put("u", availableTowelsBlue);
        towelMap.put("b", availableTowelsBlack);
        towelMap.put("r", availableTowelsRed);
        towelMap.put("g", availableTowelsGreen);

        AtomicReference<Long> possibleDifferentPatterns = new AtomicReference<>(0L);

        patterns.forEach(pattern -> possibleDifferentPatterns.updateAndGet(v -> v + recursiveSearch(pattern, towelMap)));

        return possibleDifferentPatterns.toString();
    }


    private static long recursiveSearch(String pattern, Map<String, List<String>> towelMap) {
        List<String> nextTests = new ArrayList<>();

        Long validMatches = 0L;

        for (String availableTowel : towelMap.get(pattern.substring(0, 1))) {
            if (pattern.startsWith(availableTowel)) {
                if (pattern.equals(availableTowel)) {
                    validMatches++;
                } else {
                    nextTests.add(pattern.substring(availableTowel.length()));
                }
            }
        }

        return nextTests.stream().map(next -> {
            if (solutionMap.containsKey(next)) {
                return solutionMap.get(next);
            } else {
                Long solution = recursiveSearch(next, towelMap);
                solutionMap.put(next, solution);
                return solution;
            }
        }).reduce(validMatches, Long::sum);
    }
}
