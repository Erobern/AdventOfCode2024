package adventofcode2024.solvers;

import adventofcode2024.fileloaders.FileLoaders;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;

public class Day7 {
    public static String Puzzle1(String input) {
        List<String> lines = FileLoaders.loadInputIntoStringList(input);

        List<EvalRecord> parsedEvalRecords = lines.stream().map(line -> {
            List<String> splitString = Arrays.stream(line.split(": ")).toList();

            return new EvalRecord(Long.parseLong(splitString.getFirst()),
                    Arrays.stream(splitString.getLast().split(" ")).map(Long::parseLong).toList());
        }).toList();

        AtomicReference<Long> sum = new AtomicReference<>(0L);

        parsedEvalRecords.forEach(parsedEvalRecord -> {
            List<EvalRecord> currentList;
            List<EvalRecord> nextList = new ArrayList<>();
            currentList = new ArrayList<>(Collections.singleton(parsedEvalRecord));

            AtomicBoolean continueEvals = new AtomicBoolean(true);

            while (continueEvals.get()) {
                List<EvalRecord> finalNextList = nextList;
                currentList.forEach(currentRecord -> {

                    if (currentRecord.tokens.size() >= 2 && continueEvals.get()) {
                        // add
                        testOperation(Operation.ADD, parsedEvalRecord, currentRecord, finalNextList, sum, continueEvals);
                        // multiply
                        testOperation(Operation.MULTIPLY, parsedEvalRecord, currentRecord, finalNextList, sum, continueEvals);
                    }
                });

                if (!nextList.isEmpty()) {
                    currentList = new ArrayList<>(finalNextList);
                    nextList = new ArrayList<>();
                } else {
                    return;
                }
            }
        });

        return sum.toString();
    }

    public static String Puzzle2(String input) {
        List<String> lines = FileLoaders.loadInputIntoStringList(input);

        List<EvalRecord> parsedEvalRecords = lines.stream().map(line -> {
            List<String> splitString = Arrays.stream(line.split(": ")).toList();

            return new EvalRecord(Long.parseLong(splitString.getFirst()),
                    Arrays.stream(splitString.getLast().split(" ")).map(Long::parseLong).toList());
        }).toList();

        AtomicReference<Long> sum = new AtomicReference<>(0L);

        parsedEvalRecords.forEach(parsedEvalRecord -> {
            List<EvalRecord> currentList;
            List<EvalRecord> nextList = new ArrayList<>();
            currentList = new ArrayList<>(Collections.singleton(parsedEvalRecord));

            AtomicBoolean continueEvals = new AtomicBoolean(true);

            while (continueEvals.get()) {
                List<EvalRecord> finalNextList = nextList;
                currentList.forEach(currentRecord -> {

                    if (currentRecord.tokens.size() >= 2 && continueEvals.get()) {
                        // add
                        testOperation(Operation.ADD, parsedEvalRecord, currentRecord, finalNextList, sum, continueEvals);
                        // multiply
                        testOperation(Operation.MULTIPLY, parsedEvalRecord, currentRecord, finalNextList, sum, continueEvals);
                        // concatenate
                        testOperation(Operation.CONCAT, parsedEvalRecord, currentRecord, finalNextList, sum, continueEvals);
                    }

                });

                if (!nextList.isEmpty()) {
                    currentList = new ArrayList<>(finalNextList);
                    nextList = new ArrayList<>();
                } else {
                    return;
                }
            }
        });

        return sum.toString();
    }

    private static void testOperation(Operation operation, EvalRecord parsedEvalRecord, EvalRecord currentRecord, List<EvalRecord> finalNextList, AtomicReference<Long> sum, AtomicBoolean continueEvals) {
        Long testSum = 0L;
        if (operation == Operation.CONCAT) {
            testSum = Long.parseLong(currentRecord.tokens.get(0).toString() + currentRecord.tokens.get(1).toString());
        } else if (operation == Operation.MULTIPLY) {
            testSum = currentRecord.tokens.get(0) * currentRecord.tokens.get(1);
        } else if (operation == Operation.ADD) {
            testSum = currentRecord.tokens.get(0) + currentRecord.tokens.get(1);
        }
        List<Long> updatedTokens = new ArrayList<>(currentRecord.tokens.subList(1, currentRecord.tokens.size()));
        updatedTokens.removeFirst();
        updatedTokens.addFirst(testSum);
        EvalRecord newRecord = new EvalRecord(testSum, updatedTokens);
        if (newRecord.testSum <= parsedEvalRecord.testSum && newRecord.tokens.size() >= 2) {
            finalNextList.add(newRecord);
        } else if (newRecord.tokens.size() == 1 && newRecord.testSum.equals(parsedEvalRecord.testSum)) {
            sum.updateAndGet(v -> v + parsedEvalRecord.testSum);
            continueEvals.set(false);
        }
    }

    enum Operation {
        ADD, MULTIPLY, CONCAT
    }

    record EvalRecord(Long testSum, List<Long> tokens) {
    }

}
