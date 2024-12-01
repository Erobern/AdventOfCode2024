package adventofcode2024.solvers;

import adventofcode2024.fileloaders.FileLoaders;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Day1 {
    public static String Puzzle1(String input) {

        List<String> lines = FileLoaders.loadInputIntoStringList(input);

        List<Integer> leftSide = new ArrayList<>();
        List<Integer> rightSide = new ArrayList<>();

        for (String l : lines) {
            List<String> split = Arrays.stream(l.replaceAll("\\s+", " ").split(" ")).toList();
            leftSide.add(Integer.parseInt(split.get(0)));
            rightSide.add(Integer.parseInt(split.get(1)));
        }

        Collections.sort(leftSide);
        Collections.sort(rightSide);

        Integer total = 0;
        for (int i = 0; i < leftSide.size(); i++) {
            total += Math.abs(leftSide.get(i) - rightSide.get(i));
        }
        return total.toString();
    }

    public static String Puzzle2(String input) {

        List<String> lines = FileLoaders.loadInputIntoStringList(input);

        List<Integer> leftSide = new ArrayList<>();
        List<Integer> rightSide = new ArrayList<>();

        for (String l : lines) {
            List<String> split = Arrays.stream(l.replaceAll("\\s+", " ").split(" ")).toList();
            leftSide.add(Integer.parseInt(split.get(0)));
            rightSide.add(Integer.parseInt(split.get(1)));
        }

        Long total = 0L;

        for (Integer i : leftSide) {
            total += (rightSide.stream().filter(rightInt -> rightInt.equals(i)).count()) * i;
        }
        return total.toString();
    }
}
