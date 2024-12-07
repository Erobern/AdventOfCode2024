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

        parsedEvalRecords.stream().forEach(parsedEvalRecord -> {
            List<EvalRecord> currentList = new ArrayList<>();
            List<EvalRecord> nextList = new ArrayList<>();

            currentList = new ArrayList<>(Collections.singleton(parsedEvalRecord));

            AtomicBoolean continueEvals = new AtomicBoolean(true);
            while (continueEvals.get()) {

                List<EvalRecord> finalNextList = nextList;
                currentList.forEach(currentRecord -> {

                    if (currentRecord.tokens.size() >= 2 && continueEvals.get()) {
                        // add
                        Long testAddSum = currentRecord.tokens.get(0) + currentRecord.tokens.get(1);
                        List<Long> updatedAddTokens = new ArrayList<>(currentRecord.tokens.subList(1, currentRecord.tokens.size()));
                        updatedAddTokens.removeFirst();
                        updatedAddTokens.addFirst(testAddSum);
                        EvalRecord add = new EvalRecord(testAddSum, updatedAddTokens);
                        if (add.testSum <= parsedEvalRecord.testSum && add.tokens.size() >= 2) {
                            finalNextList.add(add);
                        } else if (add.tokens.size() == 1 && add.testSum.equals(parsedEvalRecord.testSum)) {
                            sum.updateAndGet(v -> v + parsedEvalRecord.testSum);
                            continueEvals.set(false);
                            return;
                        }
                        // multiply
                        Long testMultiplySum = currentRecord.tokens.get(0) * currentRecord.tokens.get(1);
                        List<Long> updatedMultTokens = new ArrayList<>(currentRecord.tokens.subList(1, currentRecord.tokens.size()));
                        updatedMultTokens.removeFirst();
                        updatedMultTokens.addFirst(testMultiplySum);
                        EvalRecord multiply = new EvalRecord(testMultiplySum, updatedMultTokens);
                        if (multiply.testSum <= parsedEvalRecord.testSum && add.tokens.size() >= 2) {
                            finalNextList.add(multiply);
                        } else if (multiply.tokens.size() == 1 && multiply.testSum.equals(parsedEvalRecord.testSum)) {
                            sum.updateAndGet(v -> v + parsedEvalRecord.testSum);
                            continueEvals.set(false);
                        }
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

        parsedEvalRecords.stream().forEach(parsedEvalRecord -> {
            List<EvalRecord> currentList = new ArrayList<>();
            List<EvalRecord> nextList = new ArrayList<>();

            currentList = new ArrayList<>(Collections.singleton(parsedEvalRecord));

            AtomicBoolean continueEvals = new AtomicBoolean(true);
            while (continueEvals.get()) {

                List<EvalRecord> finalNextList = nextList;
                currentList.forEach(currentRecord -> {

                    if (currentRecord.tokens.size() >= 2 && continueEvals.get()) {
                        // add
                        Long testAddSum = currentRecord.tokens.get(0) + currentRecord.tokens.get(1);
                        List<Long> updatedAddTokens = new ArrayList<>(currentRecord.tokens.subList(1, currentRecord.tokens.size()));
                        updatedAddTokens.removeFirst();
                        updatedAddTokens.addFirst(testAddSum);
                        EvalRecord add = new EvalRecord(testAddSum, updatedAddTokens);
                        if (add.testSum <= parsedEvalRecord.testSum && add.tokens.size() >= 2) {
                            finalNextList.add(add);
                        } else if (add.tokens.size() == 1 && add.testSum.equals(parsedEvalRecord.testSum)) {
                            sum.updateAndGet(v -> v + parsedEvalRecord.testSum);
                            continueEvals.set(false);
                            return;
                        }
                        // multiply
                        Long testMultiplySum = currentRecord.tokens.get(0) * currentRecord.tokens.get(1);
                        List<Long> updatedMultTokens = new ArrayList<>(currentRecord.tokens.subList(1, currentRecord.tokens.size()));
                        updatedMultTokens.removeFirst();
                        updatedMultTokens.addFirst(testMultiplySum);
                        EvalRecord multiply = new EvalRecord(testMultiplySum, updatedMultTokens);
                        if (multiply.testSum <= parsedEvalRecord.testSum && multiply.tokens.size() >= 2) {
                            finalNextList.add(multiply);
                        } else if (multiply.tokens.size() == 1 && multiply.testSum.equals(parsedEvalRecord.testSum)) {
                            sum.updateAndGet(v -> v + parsedEvalRecord.testSum);
                            continueEvals.set(false);
                        }
                        // concatenate
                        Long testConcatSum = Long.parseLong(currentRecord.tokens.get(0).toString() + currentRecord.tokens.get(1).toString());
                        List<Long> updatedConcatTokens = new ArrayList<>(currentRecord.tokens.subList(1, currentRecord.tokens.size()));
                        updatedConcatTokens.removeFirst();
                        updatedConcatTokens.addFirst(testConcatSum);
                        EvalRecord concat = new EvalRecord(testConcatSum, updatedConcatTokens);
                        if (concat.testSum <= parsedEvalRecord.testSum && concat.tokens.size() >= 2) {
                            finalNextList.add(concat);
                        } else if (concat.tokens.size() == 1 && concat.testSum.equals(parsedEvalRecord.testSum)) {
                            sum.updateAndGet(v -> v + parsedEvalRecord.testSum);
                            continueEvals.set(false);
                        }
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

    record EvalRecord(Long testSum, List<Long> tokens) {
    }

}
