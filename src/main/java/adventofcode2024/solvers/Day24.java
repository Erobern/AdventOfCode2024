package adventofcode2024.solvers;

import adventofcode2024.fileloaders.FileLoaders;

import java.util.*;
import java.util.stream.Collectors;

public class Day24 {
    public static String Puzzle1(String input) {
        List<String> lines = FileLoaders.loadInputIntoStringList(input);

        Map<String, Long> inputs = new HashMap<>();

        List<Wiring> wirings = new ArrayList<>();

        for (String line : lines) {
            if (line.contains(":")) {
                // input
                List<String> initialValue = Arrays.stream(line.split(": ")).toList();
                inputs.put(initialValue.get(0), Long.parseLong(initialValue.get(1)));

            } else if (line.contains("->")) {
                // mapping
                List<String> wiringValue = Arrays.stream(line.split(" -> ")).toList();
                List<String> leftWiring = Arrays.stream(wiringValue.get(0).split(" ")).toList();

                wirings.add(new Wiring(
                        leftWiring.get(0),
                        leftWiring.get(2),
                        leftWiring.get(1),
                        wiringValue.get(1)
                ));

            }
        }

        boolean updateMade = true;

        do {

            updateMade = false;

            for (Wiring wiring : wirings) {
                if (!inputs.containsKey(wiring.output())) {
                    // calculate it
                    updateMade = true;

                    if (inputs.containsKey(wiring.input1) && inputs.containsKey(wiring.input2)) {
                        if (wiring.operation().equals("AND")) {
                            inputs.put(wiring.output, inputs.get(wiring.input1()) & inputs.get(wiring.input2()));
                        } else if (wiring.operation().equals("OR")) {
                            inputs.put(wiring.output, inputs.get(wiring.input1()) | inputs.get(wiring.input2()));
                        } else if (wiring.operation().equals("XOR")) {
                            inputs.put(wiring.output, inputs.get(wiring.input1()) ^ inputs.get(wiring.input2()));
                        }
                    }
                }
            }

        } while (updateMade);

        List<String> zOps = new ArrayList<>();

        for (String s : inputs.keySet()) {
            if (s.startsWith("z")) {
                zOps.add(s);
            }
        }

        zOps = zOps.stream().sorted().collect(Collectors.toList());

        Long answer = 0L;

        for (int i = zOps.size() - 1; i >= 0; i--) {
            answer = answer << 1;
            answer += inputs.get(zOps.get(i));
        }

        return answer.toString();
    }

    public static String Puzzle2(String input) {
        List<String> lines = FileLoaders.loadInputIntoStringList(input);

        Map<String, Long> originalInputs = new HashMap<>();

        List<Wiring> originalWirings = new ArrayList<>();

        for (String line : lines) {
            if (line.contains(":")) {
                // input
                List<String> initialValue = Arrays.stream(line.split(": ")).toList();
                originalInputs.put(initialValue.get(0), Long.parseLong(initialValue.get(1)));

            } else if (line.contains("->")) {
                // mapping
                List<String> wiringValue = Arrays.stream(line.split(" -> ")).toList();
                List<String> leftWiring = Arrays.stream(wiringValue.get(0).split(" ")).toList();

                originalWirings.add(new Wiring(
                        leftWiring.get(0),
                        leftWiring.get(2),
                        leftWiring.get(1),
                        wiringValue.get(1)
                ));

            }
        }

        boolean updateMade = true;

        Map<String, Long> originalCalculatedInputs = new HashMap<>(originalInputs);

        do {

            updateMade = false;

            for (Wiring wiring : originalWirings) {
                if (!originalCalculatedInputs.containsKey(wiring.output())) {
                    // calculate it
                    updateMade = true;

                    if (originalCalculatedInputs.containsKey(wiring.input1) && originalCalculatedInputs.containsKey(wiring.input2)) {
                        if (wiring.operation().equals("AND")) {
                            originalCalculatedInputs.put(wiring.output, originalCalculatedInputs.get(wiring.input1()) & originalCalculatedInputs.get(wiring.input2()));
                        } else if (wiring.operation().equals("OR")) {
                            originalCalculatedInputs.put(wiring.output, originalCalculatedInputs.get(wiring.input1()) | originalCalculatedInputs.get(wiring.input2()));
                        } else if (wiring.operation().equals("XOR")) {
                            originalCalculatedInputs.put(wiring.output, originalCalculatedInputs.get(wiring.input1()) ^ originalCalculatedInputs.get(wiring.input2()));
                        }
                    }
                }
            }

        } while (updateMade);

        List<String> xOps = new ArrayList<>();

        for (String s : originalCalculatedInputs.keySet()) {
            if (s.startsWith("x")) {
                xOps.add(s);
            }
        }

        xOps = xOps.stream().sorted().collect(Collectors.toList());

        Long xInputDecimal = 0L;
        String xInputBinary = "";

        for (int i = xOps.size() - 1; i >= 0; i--) {
            xInputDecimal = xInputDecimal << 1;
            xInputDecimal += originalCalculatedInputs.get(xOps.get(i));
            xInputBinary += originalCalculatedInputs.get(xOps.get(i)).toString();
        }

        List<String> yOps = new ArrayList<>();

        for (String s : originalCalculatedInputs.keySet()) {
            if (s.startsWith("y")) {
                yOps.add(s);
            }
        }

        yOps = yOps.stream().sorted().collect(Collectors.toList());

        Long yInputDecimal = 0L;
        String yInputBinary = "";

        for (int i = yOps.size() - 1; i >= 0; i--) {
            yInputDecimal = yInputDecimal << 1;
            yInputDecimal += originalCalculatedInputs.get(yOps.get(i));
            yInputBinary += originalCalculatedInputs.get(yOps.get(i)).toString();
        }

        List<String> zOps = new ArrayList<>();

        for (String s : originalCalculatedInputs.keySet()) {
            if (s.startsWith("z")) {
                zOps.add(s);
            }
        }

        zOps = zOps.stream().sorted().collect(Collectors.toList());

        Long zOutputDecimal = 0L;
        String zOutputBinary = "";

        String _OutputBinary = "1100001110001111100010001100100000011011100110";

        for (int i = zOps.size() - 1; i >= 0; i--) {
            zOutputDecimal = zOutputDecimal << 1;
            zOutputDecimal += originalCalculatedInputs.get(zOps.get(i));
            zOutputBinary += originalCalculatedInputs.get(zOps.get(i)).toString();
        }

        // z45 to z24 look to be locked in
        // z00 to maybe z04 also look to be locked
        // any swaps shouldn't harm these bits

        return zOutputDecimal.toString();
    }

    record Wiring(String input1, String input2, String operation, String output) {
    }
}