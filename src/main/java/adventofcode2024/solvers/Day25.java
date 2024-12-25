package adventofcode2024.solvers;

import adventofcode2024.fileloaders.FileLoaders;

import java.util.ArrayList;
import java.util.List;

public class Day25 {
    public static String Puzzle1(String input) {
        List<String> lines = FileLoaders.loadInputIntoStringList(input);

        List<Schematic> schematics = new ArrayList<>();
        List<Key> keys = new ArrayList<>();

        for (int i = 0; i < lines.size(); i++) {

            Integer pin1 = 0;
            Integer pin2 = 0;
            Integer pin3 = 0;
            Integer pin4 = 0;
            Integer pin5 = 0;

            if (lines.get(i).equals("#####") && lines.get(i + 6).equals(".....")) {
                //schematic
                for (int j = 0; j < 5; j++) {
                    pin1 += lines.get(i + 1 + j).charAt(0) == '#' ? 1 : 0;
                    pin2 += lines.get(i + 1 + j).charAt(1) == '#' ? 1 : 0;
                    pin3 += lines.get(i + 1 + j).charAt(2) == '#' ? 1 : 0;
                    pin4 += lines.get(i + 1 + j).charAt(3) == '#' ? 1 : 0;
                    pin5 += lines.get(i + 1 + j).charAt(4) == '#' ? 1 : 0;
                }

                schematics.add(new Schematic(pin1, pin2, pin3, pin4, pin5));

            } else if (lines.get(i).equals(".....") && lines.get(i + 6).equals("#####")) {
                //key
                for (int j = 0; j < 5; j++) {
                    pin1 += lines.get(i + 1 + j).charAt(0) == '#' ? 1 : 0;
                    pin2 += lines.get(i + 1 + j).charAt(1) == '#' ? 1 : 0;
                    pin3 += lines.get(i + 1 + j).charAt(2) == '#' ? 1 : 0;
                    pin4 += lines.get(i + 1 + j).charAt(3) == '#' ? 1 : 0;
                    pin5 += lines.get(i + 1 + j).charAt(4) == '#' ? 1 : 0;
                }

                keys.add(new Key(pin1, pin2, pin3, pin4, pin5));
            }

            i = i + 7;
        }

        Integer uniqueCombinations = 0;

        for (Schematic schematic : schematics) {
            for (Key key : keys) {
                if (schematic.pin1 + key.pin1 <= 5 &&
                        schematic.pin2 + key.pin2 <= 5 &&
                        schematic.pin3 + key.pin3 <= 5 &&
                        schematic.pin4 + key.pin4 <= 5 &&
                        schematic.pin5 + key.pin5 <= 5) {
                    uniqueCombinations++;
                }
            }
        }
        return uniqueCombinations.toString();
    }

    public static String Puzzle2(String input) {
        List<String> lines = FileLoaders.loadInputIntoStringList(input);
        return "";
    }

    record Schematic(Integer pin1, Integer pin2, Integer pin3, Integer pin4, Integer pin5) {
    }

    record Key(Integer pin1, Integer pin2, Integer pin3, Integer pin4, Integer pin5) {
    }
}
