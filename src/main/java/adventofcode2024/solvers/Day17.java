package adventofcode2024.solvers;

import adventofcode2024.fileloaders.FileLoaders;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Day17 {
    public static String Puzzle1(String input) {
        List<String> lines = FileLoaders.loadInputIntoStringList(input);

        Long registerA = 0L;
        Long registerB = 0L;
        Long registerC = 0L;
        Integer comboOperand = 0;
        Integer literalOperand = 0;

        List<Integer> program = new ArrayList<>();

        List<Long> output = new ArrayList<>();

        Integer programPointer = 0;
        boolean halt = false;

        for (String line : lines) {
            if (line.contains("Register A:")) {
                registerA = Long.parseLong(Arrays.stream(line.split(":")).toList().getLast().trim());
            }
            if (line.contains("Register B:")) {
                registerB = Long.parseLong(Arrays.stream(line.split(":")).toList().getLast().trim());
            }
            if (line.contains("Register C:")) {
                registerC = Long.parseLong(Arrays.stream(line.split(":")).toList().getLast().trim());
            }

            if (line.contains("Program:")) {
                program = Arrays.stream(Arrays.stream(line.split(":")).toList().getLast().trim().split(",")).map(Integer::parseInt).toList();
            }
        }

        while (!halt) {

            comboOperand = 0;
            literalOperand = 0;

            if (programPointer >= program.size()) {
                halt = true;
            } else {
                Integer currentInstruction = program.get(programPointer);

                switch (currentInstruction) {
                    case 0: // adv
                        if (programPointer + 1 >= program.size()) {
                            halt = true;
                        } else {
                            comboOperand = program.get(programPointer + 1);
                            Long denominator = 0L;
                            if (comboOperand >= 0 && comboOperand <= 3) {
                                denominator = (long) Math.pow(2, comboOperand);
                            } else if (comboOperand >= 4 && comboOperand <= 6) {
                                denominator = getRegisterValueByComboOperand(comboOperand, registerA, registerB, registerC);
                                denominator = (long) Math.pow(2, denominator);
                            } else if (comboOperand == 7) {
                                throw new RuntimeException("Invalid comboOperand");
                            }

                            registerA = Math.floorDiv(registerA, denominator);

                            programPointer++;
                            programPointer++;
                        }
                        break;
                    case 1: // bxl
                        if (programPointer + 1 >= program.size()) {
                            halt = true;
                        } else {
                            literalOperand = program.get(programPointer + 1);
                            registerB = registerB ^ literalOperand;

                            programPointer++;
                            programPointer++;
                        }
                        break;
                    case 2: // bst
                        if (programPointer + 1 >= program.size()) {
                            halt = true;
                        } else {
                            comboOperand = program.get(programPointer + 1);
                            Long modulo = 0L;
                            if (comboOperand == 0) {
                                halt = true; // divide by zero error
                            } else if (comboOperand >= 1 && comboOperand <= 3) {
                                modulo = Long.valueOf(comboOperand);
                            } else if (comboOperand >= 4 && comboOperand <= 6) {
                                modulo = getRegisterValueByComboOperand(comboOperand, registerA, registerB, registerC);
                            } else if (comboOperand == 7) {
                                throw new RuntimeException("Invalid comboOperand");
                            }

                            registerB = modulo % 8;

                            programPointer++;
                            programPointer++;
                        }
                        break;
                    case 3: // jnz
                        if (programPointer + 1 >= program.size()) {
                            halt = true;
                        } else {
                            literalOperand = program.get(programPointer + 1);
                            if (registerA != 0) {
                                programPointer = literalOperand;
                            } else {
                                programPointer++;
                                programPointer++;
                            }
                        }
                        break;
                    case 4: // bxc
                        if (programPointer + 1 >= program.size()) {
                            halt = true;
                        } else {
                            literalOperand = program.get(programPointer + 1);
                            registerB = registerB ^ registerC;
                        }

                        programPointer++;
                        programPointer++;

                        break;
                    case 5: // out
                        if (programPointer + 1 >= program.size()) {
                            halt = true;
                        } else {
                            comboOperand = program.get(programPointer + 1);
                            if (comboOperand >= 0 && comboOperand <= 3) {
                                output.add((long) (comboOperand % 8));
                            } else if (comboOperand >= 4 && comboOperand <= 6) {
                                output.add(getRegisterValueByComboOperand(comboOperand, registerA, registerB, registerC) % 8);
                            } else if (comboOperand == 7) {
                                throw new RuntimeException("Invalid comboOperand");
                            }

                            programPointer++;
                            programPointer++;
                        }
                        break;
                    case 6: // bdv
                        if (programPointer + 1 >= program.size()) {
                            halt = true;
                        } else {
                            comboOperand = program.get(programPointer + 1);
                            Long denominator = 0L;
                            if (comboOperand >= 0 && comboOperand <= 3) {
                                denominator = (long) Math.pow(2, comboOperand);
                            } else if (comboOperand >= 4 && comboOperand <= 6) {
                                denominator = getRegisterValueByComboOperand(comboOperand, registerA, registerB, registerC);
                                denominator = (long) Math.pow(2, denominator);
                            } else if (comboOperand == 7) {
                                throw new RuntimeException("Invalid comboOperand");
                            }

                            registerB = Math.floorDiv(registerA, denominator);

                            programPointer++;
                            programPointer++;
                        }
                        break;
                    case 7: // cdv
                        if (programPointer + 1 >= program.size()) {
                            halt = true;
                        } else {
                            comboOperand = program.get(programPointer + 1);
                            Long denominator = 0L;
                            if (comboOperand >= 0 && comboOperand <= 3) {
                                denominator = (long) Math.pow(2, comboOperand);
                            } else if (comboOperand >= 4 && comboOperand <= 6) {
                                denominator = getRegisterValueByComboOperand(comboOperand, registerA, registerB, registerC);
                                denominator = (long) Math.pow(2, denominator);
                            } else if (comboOperand == 7) {
                                throw new RuntimeException("Invalid comboOperand");
                            }

                            registerC = Math.floorDiv(registerA, denominator);

                            programPointer++;
                            programPointer++;
                        }
                        break;
                }
            }
        }

        String outputString = "";

        for (int i = 0; i < output.size(); i++) {
            outputString += output.get(i).toString();
            if (i < output.size() - 1) {
                outputString += ",";
            }
        }


        return outputString;
    }

    private static Long getRegisterValueByComboOperand(Integer comboOperand, Long registerA, Long registerB, Long registerC) {
        if (comboOperand == 4) {
            return registerA;
        } else if (comboOperand == 5) {
            return registerB;
        } else if (comboOperand == 6) {
            return registerC;
        } else {
            throw new RuntimeException("Invalid comboOperand");
        }
    }

    private static Long getRegisterValueByComboOperandLongs(Integer comboOperand, Long registerA, Long registerB, Long registerC) {
        if (comboOperand == 4) {
            return registerA;
        } else if (comboOperand == 5) {
            return registerB;
        } else if (comboOperand == 6) {
            return registerC;
        } else {
            throw new RuntimeException("Invalid comboOperand");
        }
    }

    public static String Puzzle2(String input) {
        List<String> lines = FileLoaders.loadInputIntoStringList(input);

        return "";
    }

}
