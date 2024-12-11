package adventofcode2024.solvers;

import adventofcode2024.fileloaders.FileLoaders;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.groupingBy;

public class Day11 {
    public static String Puzzle1(String input) {
        List<String> lines = FileLoaders.loadInputIntoStringList(input);

        List<Long> currList = Arrays.stream(lines.getFirst().split(" ")).map(Long::parseLong).toList();
        List<Long> nextList = new ArrayList<>();

        for (int i = 0; i < 25; i++) {
            for (Long l : currList) {
                if (l == 0L) {
                    nextList.add(1L);
                } else if (isEvenNumberDigits(l)) {
                    nextList.add((getFirstHalf(l)));
                    nextList.add((getSecondHalf(l)));
                } else {
                    nextList.add(l * 2024);
                }
            }

            currList = new ArrayList<>(nextList);
            nextList = new ArrayList<>();
        }
        return String.valueOf(currList.size());
    }

    private static Long getSecondHalf(Long l) {
        return Long.parseLong(String.valueOf(l).substring((String.valueOf(l).length() / 2)));
    }

    private static Long getFirstHalf(Long l) {
        return Long.parseLong(String.valueOf(l).substring(0, String.valueOf(l).length() / 2));
    }

    private static boolean isEvenNumberDigits(Long l) {
        return String.valueOf(l).length() % 2 == 0;
    }

    public static String Puzzle2(String input) {
        List<String> lines = FileLoaders.loadInputIntoStringList(input);

        List<Stone> currList = Arrays.stream(lines.getFirst().split(" ")).map(Long::parseLong).map(l -> new Stone(l, 1L)).toList();
        List<Stone> nextList = new ArrayList<>();
        List<Stone> groupList = new ArrayList<>();

        for (int i = 0; i < 75; i++) {
            for (Stone s : currList) {
                if (s.value == 0L) {
                    nextList.add(new Stone(1L, s.copies));
                } else if (isEvenNumberDigits(s.value)) {
                    nextList.add(new Stone(getFirstHalf(s.value), s.copies));
                    nextList.add(new Stone(getSecondHalf(s.value), s.copies));
                } else {
                    nextList.add(new Stone(s.value * 2024, s.copies));
                }
            }

            // condense the list
            Map<Long, List<Stone>> grouped = nextList.stream().collect(groupingBy(Stone::value));
            groupList = grouped.keySet().stream().map(value ->
                    new Stone(value,
                            grouped.get(value).stream().map(Stone::copies).reduce(0L, Long::sum))
            ).toList();

            currList = new ArrayList<>(groupList);
            nextList = new ArrayList<>();
            groupList = new ArrayList<>();
        }

        return String.valueOf(currList.stream().map(Stone::copies).reduce(0L, Long::sum));
    }

    record Stone(Long value, Long copies) {
    }
}
