package adventofcode2024.solvers;

import adventofcode2024.fileloaders.FileLoaders;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

public class Day5 {
    public static String Puzzle1(String input) {
        List<String> lines = FileLoaders.loadInputIntoStringList(input);

        Integer blankLine = 0;
        for (int i = 0; i < lines.size(); i++) {
            if (lines.get(i).isBlank() || lines.get(i).isEmpty()) {
                blankLine = i;
                break;
            }
        }

        List<Rule> rules = new ArrayList<>();
        for (int i = 0; i < blankLine; i++) {
            List<Integer> pageNumbers = Arrays.stream(lines.get(i).split("\\|")).map(Integer::parseInt).toList();
            rules.add(new Rule(pageNumbers.getFirst(), pageNumbers.getLast()));
        }

        List<List<Integer>> printOrders = new ArrayList<>();
        for(int i = blankLine + 1; i < lines.size(); i++) {
            printOrders.add(Arrays.stream(lines.get(i).split(",")).map(Integer::parseInt).toList());
        }

        AtomicReference<Integer> validOrdersSum = new AtomicReference<>(0);

        printOrders.forEach(printOrder -> {
            boolean validPrintOrder = true;

            for (Rule rule : rules) {
                // does print order contain both pages in the rule?
                if (printOrder
                        .stream()
                        .filter(page -> page.equals(rule.beforePageNumber) || page.equals(rule.afterPageNumber))
                        .count() == 2) {
                    // is print order ordered correctly?
                    if (printOrder.indexOf(rule.beforePageNumber) > printOrder.indexOf(rule.afterPageNumber)) {
                        validPrintOrder = false;
                        break;
                    }
                }
            }

            if (validPrintOrder) {
                validOrdersSum.updateAndGet(v -> v + (printOrder.get((printOrder.size() - 1) / 2)));
            }
        });

        return validOrdersSum.toString();
    }

    public static String Puzzle2(String input) {
        List<String> lines = FileLoaders.loadInputIntoStringList(input);

        Integer blankLine = 0;
        for (int i = 0; i < lines.size(); i++) {
            if (lines.get(i).isBlank() || lines.get(i).isEmpty()) {
                blankLine = i;
                break;
            }
        }

        List<Rule> rules = new ArrayList<>();
        for (int i = 0; i < blankLine; i++) {
            List<Integer> pageNumbers = Arrays.stream(lines.get(i).split("\\|")).map(Integer::parseInt).toList();
            rules.add(new Rule(pageNumbers.getFirst(), pageNumbers.getLast()));
        }

        List<List<Integer>> printOrders = new ArrayList<>();
        for(int i = blankLine + 1; i < lines.size(); i++) {
            printOrders.add(Arrays.stream(lines.get(i).split(",")).map(Integer::parseInt).toList());
        }

        List<List<Integer>> invalidPrintOrders = new ArrayList<>();

        printOrders.forEach(printOrder -> {
            boolean validPrintOrder = true;

            for (Rule rule : rules) {
                // does print order contain both pages in the rule?
                if (printOrder
                        .stream()
                        .filter(page -> page.equals(rule.beforePageNumber) || page.equals(rule.afterPageNumber))
                        .count() == 2) {
                    // is print order ordered correctly?
                    if (printOrder.indexOf(rule.beforePageNumber) > printOrder.indexOf(rule.afterPageNumber)) {
                        validPrintOrder = false;
                        break;
                    }
                }
            }

            if (!validPrintOrder) {
                invalidPrintOrders.add(printOrder);
            }


        });

        return "";
    }

    record Rule (Integer beforePageNumber, Integer afterPageNumber) {};
}
