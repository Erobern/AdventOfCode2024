package adventofcode2024.solvers;

import adventofcode2024.fileloaders.FileLoaders;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Day13 {
    public static String Puzzle1(String input) {
        List<String> lines = FileLoaders.loadInputIntoStringList(input);

        Location buttonA = null;
        Location buttonB = null;
        Location prizeLocation = null;

        List<Game> games = new ArrayList<>();

        for (String line : lines) {
            if (line.contains("Button A")) {
                String s = line.split(":")[1];
                List<String> locations = Arrays.stream(s.split(",")).toList();
                buttonA = new Location(
                        Integer.parseInt(locations.get(0).replace("X+", "").replace(" ", "")),
                        Integer.parseInt(locations.get(1).replace("Y+", "").replace(" ", ""))
                );
            } else if (line.contains("Button B")) {
                String s = line.split(":")[1];
                List<String> locations = Arrays.stream(s.split(",")).toList();
                buttonB = new Location(
                        Integer.parseInt(locations.get(0).replace("X+", "").replace(" ", "")),
                        Integer.parseInt(locations.get(1).replace("Y+", "").replace(" ", ""))
                );
            } else if (line.contains("Prize")) {
                String s = line.split(":")[1];
                List<String> locations = Arrays.stream(s.split(",")).toList();
                prizeLocation = new Location(
                        Integer.parseInt(locations.get(0).replace("X=", "").replace(" ", "")),
                        Integer.parseInt(locations.get(1).replace("Y=", "").replace(" ", ""))
                );

                games.add(new Game(buttonA, buttonB, prizeLocation));
                buttonA = null;
                buttonB = null;
            }
        }

        // test if games are solvable

        final long buttonACost = 3;
        final long buttonBCost = 1;

        Long sum = 0L;

        for (Game game : games) {

            long testBCount = 0;
            long testACount = 0;

            boolean failed = false;

            try {
                if (((game.prizeLocation.y * game.buttonA.x) - (game.prizeLocation.x * game.buttonA.y)) %
                        ((game.buttonB.y * game.buttonA.x) - (game.buttonA.y * game.buttonB.x)) == 0) {
                    testBCount = ((game.prizeLocation.y * game.buttonA.x) - (game.prizeLocation.x * game.buttonA.y)) /
                            ((game.buttonB.y * game.buttonA.x) - (game.buttonA.y * game.buttonB.x));
                } else {
                    throw new RuntimeException();
                }
            } catch (Exception e) {
                failed = true;
            }

            try {
                if ((game.prizeLocation.x - (game.buttonB.x * testBCount)) % game.buttonA.x == 0) {
                    testACount = (game.prizeLocation.x - (game.buttonB.x * testBCount)) / game.buttonA.x;
                } else {
                    throw new RuntimeException();
                }
            } catch (Exception e) {
                failed = true;
            }

            if (!failed) {
                sum += (testACount * buttonACost) + (testBCount * buttonBCost);
            }

//            long cost = 0L;
//
//            for (long a = 1; a < 101; a++) {
//                for (long b = 1; b < 101; b++) {
//                    if ((game.buttonA.x * a) + (game.buttonB.x * b) == game.prizeLocation.x &&
//                            (game.buttonA.y * a) + (game.buttonB.y * b) == game.prizeLocation.y) {
//                        if (cost == 0 || ((a) * buttonACost) + ((b) * buttonBCost) < cost) {
//                            cost = ((a) * buttonACost) + ((b) * buttonBCost);
//                        }
//                        break;
//                    }
//                }
//            }
//
//            sum += cost;
        }

        return sum.toString();
    }

    public static String Puzzle2(String input) {
        List<String> lines = FileLoaders.loadInputIntoStringList(input);

        Location buttonA = null;
        Location buttonB = null;
        Location prizeLocation = null;

        List<Game> games = new ArrayList<>();

        for (String line : lines) {
            if (line.contains("Button A")) {
                String s = line.split(":")[1];
                List<String> locations = Arrays.stream(s.split(",")).toList();
                buttonA = new Location(
                        Integer.parseInt(locations.get(0).replace("X+", "").replace(" ", "")),
                        Integer.parseInt(locations.get(1).replace("Y+", "").replace(" ", ""))
                );
            } else if (line.contains("Button B")) {
                String s = line.split(":")[1];
                List<String> locations = Arrays.stream(s.split(",")).toList();
                buttonB = new Location(
                        Integer.parseInt(locations.get(0).replace("X+", "").replace(" ", "")),
                        Integer.parseInt(locations.get(1).replace("Y+", "").replace(" ", ""))
                );
            } else if (line.contains("Prize")) {
                String s = line.split(":")[1];
                List<String> locations = Arrays.stream(s.split(",")).toList();
                prizeLocation = new Location(
                        10_000_000_000_000L + Integer.parseInt(locations.get(0).replace("X=", "").replace(" ", "")),
                        10_000_000_000_000L + Integer.parseInt(locations.get(1).replace("Y=", "").replace(" ", ""))
                );

                games.add(new Game(buttonA, buttonB, prizeLocation));
                buttonA = null;
                buttonB = null;
            }
        }

        // test if games are solvable

        final long buttonACost = 3;
        final long buttonBCost = 1;

        Long sum = 0L;

        for (Game game : games) {

            long testBCount = 0;
            long testACount = 0;
            boolean failed = false;

            try {
                if (((game.prizeLocation.y * game.buttonA.x) - (game.prizeLocation.x * game.buttonA.y)) %
                        ((game.buttonB.y * game.buttonA.x) - (game.buttonA.y * game.buttonB.x)) == 0) {
                    testBCount = ((game.prizeLocation.y * game.buttonA.x) - (game.prizeLocation.x * game.buttonA.y)) /
                            ((game.buttonB.y * game.buttonA.x) - (game.buttonA.y * game.buttonB.x));
                } else {
                    throw new RuntimeException();
                }
            } catch (Exception e) {
                failed = true;
            }

            try {
                if ((game.prizeLocation.x - (game.buttonB.x * testBCount)) % game.buttonA.x == 0) {
                    testACount = (game.prizeLocation.x - (game.buttonB.x * testBCount)) / game.buttonA.x;
                } else {
                    throw new RuntimeException();
                }
            } catch (Exception e) {
                failed = true;
            }

            if (!failed) {
                sum += (testACount * buttonACost) + (testBCount * buttonBCost);
            }
        }

        return sum.toString();
    }

    public record Game(Location buttonA, Location buttonB, Location prizeLocation) {
    }

    public record Location(long x, long y) {

    }
}
