package adventofcode2024.solvers;

import adventofcode2024.fileloaders.FileLoaders;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

public class Day2 {
    public static String Puzzle1(String input) {
        List<String> reports = FileLoaders.loadInputIntoStringList(input);

        AtomicReference<Integer> safeReports = new AtomicReference<>(0);

        reports.forEach(report -> {
            List<String> levels = Arrays.stream(report.split(" ")).toList();

            if (!isUnsafe(levels)) {
                safeReports.getAndSet(safeReports.get() + 1);
            }
        });

        return safeReports.toString();
    }

    public static String Puzzle2(String input) {
        List<String> reports = FileLoaders.loadInputIntoStringList(input);

        AtomicReference<Integer> safeReports = new AtomicReference<>(0);

        reports.forEach(report -> {
            List<String> levels = Arrays.stream(report.split(" ")).toList();

            boolean safeFound = false;

            for (int i = 0; i < levels.size(); i++) {
                List<String> testLevel = new ArrayList<String>(levels);
                testLevel.remove(i);

                if (!isUnsafe(testLevel)) {
                    safeFound = true;
                }
            }

            if (safeFound) {
                safeReports.getAndSet(safeReports.get() + 1);
            }
        });

        return safeReports.toString();
    }

    private static boolean isUnsafe(List<String> levels) {
        boolean first = true;
        boolean unsafe = false;
        SortOrder sortOrder = null;
        Integer previous = 0;

        for (String s : levels) {
            if (first) {
                previous = Integer.parseInt(s);
                first = false;
            } else {
                Integer current = Integer.parseInt(s);

                if (current.equals(previous)) {
                    unsafe = true;
                    break;
                }

                if (sortOrder == null) {
                    sortOrder = previous > current ? SortOrder.DESCENDING : SortOrder.ASCENDING;
                } else {
                    if (sortOrder == SortOrder.ASCENDING) {
                        if (previous > current) {
                            unsafe = true;
                            break;
                        }
                    } else {
                        if (previous < current) {
                            unsafe = true;
                            break;
                        }
                    }
                }

                if (Math.abs(previous - current) > 3) {
                    unsafe = true;
                    break;
                }

                previous = current;

            }
        }
        return unsafe;
    }

    public enum SortOrder {
        ASCENDING, DESCENDING
    }
}
