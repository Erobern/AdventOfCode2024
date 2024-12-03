package adventofcode2024.solvers;

import adventofcode2024.fileloaders.FileLoaders;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;
import java.util.regex.MatchResult;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day3 {
    public static String Puzzle1(String input) {
        List<String> lines = FileLoaders.loadInputIntoStringList(input);

        AtomicReference<Long> sum = new AtomicReference<>(0L);
        Pattern pattern = Pattern.compile("mul\\([0-9]{1,3},[0-9]{1,3}\\)");

        lines.forEach(line -> {
            Matcher matcher = pattern.matcher(line);
            matcher.results().forEach(match -> {
                List<String> multiplicands = getMultiplicands(match);
                sum.updateAndGet(v -> v + multiplicands.stream().map(Long::parseLong).reduce(1L, (a, b) -> a * b));
            });
        });
        return sum.toString();
    }

    public static String Puzzle2(String input) {
        List<String> lines = FileLoaders.loadInputIntoStringList(input);

        AtomicReference<Long> sum = new AtomicReference<>(0L);
        AtomicBoolean enabled = new AtomicBoolean(true);
        Pattern pattern = Pattern.compile("(mul\\([0-9]{1,3},[0-9]{1,3}\\))|(do\\(\\))|(don't\\(\\))");

        lines.forEach(line -> {
            Matcher matcher = pattern.matcher(line);
            matcher.results().forEach(match -> {
                if (match.group().contains("do()")) {
                    enabled.set(true);
                } else if (match.group().contains("don't()")) {
                    enabled.set(false);
                } else if (match.group().contains("mul") && enabled.get()) {
                    List<String> multiplicands = getMultiplicands(match);
                    sum.updateAndGet(v -> v + multiplicands.stream().map(Long::parseLong).reduce(1L, (a, b) -> a * b));
                }
            });
        });
        return sum.toString();
    }

    private static List<String> getMultiplicands(MatchResult match) {
        return Arrays.stream(match.group()
                .replaceAll("mul\\(", "")
                .replaceAll("\\)", "")
                .split(",")).toList();
    }
}
