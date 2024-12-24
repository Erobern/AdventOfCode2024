package adventofcode2024.solvers;

import adventofcode2024.fileloaders.FileLoaders;

import java.util.*;
import java.util.stream.Collectors;

public class Day23 {
    public static String Puzzle1(String input) {
        List<String> lines = FileLoaders.loadInputIntoStringList(input);

        Map<UUID, List<String>> network = new HashMap<>();

        for (int i = 0; i < lines.size(); i++) {
            List<String> split = Arrays.stream(lines.get(i).split("-")).toList();

            String computer1 = split.get(0);
            String computer2 = split.get(1);

            for (int j = i + 1; j < lines.size(); j++) {
                // search the remaining computers until we find a match to either token
                String candidateComputer3 = "";
                List<String> searchSplit = Arrays.stream(lines.get(j).split("-")).toList();

                boolean computer1PairingFound = false;
                boolean computer2PairingFound = false;

                if (searchSplit.get(0).equals(computer1)) {
                    candidateComputer3 = searchSplit.get(1);
                    computer1PairingFound = true;
                } else if (searchSplit.get(1).equals(computer1)) {
                    candidateComputer3 = searchSplit.get(0);
                    computer1PairingFound = true;
                } else if (searchSplit.get(0).equals(computer2)) {
                    candidateComputer3 = searchSplit.get(1);
                    computer2PairingFound = true;
                } else if (searchSplit.get(1).equals(computer2)) {
                    candidateComputer3 = searchSplit.get(0);
                    computer2PairingFound = true;
                }

                if (computer1PairingFound || computer2PairingFound) {

                    // search the reset of the list to see if there's a circular connection;

                    for (int k = j + 1; k < lines.size(); k++) {
                        List<String> finalSearchSplit = Arrays.stream(lines.get(k).split("-")).toList();

                        if (computer1PairingFound) {
                            if (finalSearchSplit.contains(computer2) && finalSearchSplit.contains(candidateComputer3)) {
                                network.put(UUID.randomUUID(), Arrays.asList(computer1, computer2, candidateComputer3));
                            }
                        } else if (computer2PairingFound) {
                            if (finalSearchSplit.contains(computer1) && finalSearchSplit.contains(candidateComputer3)) {
                                network.put(UUID.randomUUID(), Arrays.asList(computer1, computer2, candidateComputer3));
                            }
                        }

                    }
                }


            }


        }

        Integer answer = 0;

        for (UUID uuid : network.keySet()) {
            if (network.get(uuid).stream().anyMatch(computer -> computer.startsWith("t"))) {
                answer++;
            }
        }
        return answer.toString();
    }

    public static String Puzzle2(String input) {
        List<String> lines = FileLoaders.loadInputIntoStringList(input);

        Map<String, List<String>> networkConnectionsByComputer = new HashMap<>();

        for (int i = 0; i < lines.size(); i++) {
            List<String> split = Arrays.stream(lines.get(i).split("-")).toList();

            String computer1 = split.get(0);
            String computer2 = split.get(1);

            if (networkConnectionsByComputer.containsKey(computer1)) {
                networkConnectionsByComputer.get(computer1).add(computer2);
            } else {

                networkConnectionsByComputer.put(computer1, new ArrayList<>(split));
            }

            if (networkConnectionsByComputer.containsKey(computer2)) {
                networkConnectionsByComputer.get(computer2).add(computer1);
            } else {
                networkConnectionsByComputer.put(computer2, new ArrayList<>(split));
            }
        }

        List<String> biggestNetwork = new ArrayList<>();

        for (String nodeName : networkConnectionsByComputer.keySet()) {

            for (String computerInNetworkUnderTest : networkConnectionsByComputer.get(nodeName).stream().filter(computerName ->
                    !computerName.equals(nodeName)).toList()) {

                List<String> remainingMatches = networkConnectionsByComputer.get(computerInNetworkUnderTest).stream().filter(
                        computerName -> networkConnectionsByComputer.get(nodeName).contains(computerName) ||
                                computerName.equals(nodeName)
                ).toList();

                List<String> testTheRest = new ArrayList<>(remainingMatches);

                for (String theRest : testTheRest) {

                    if (remainingMatches.contains(theRest)) {
                        List<String> finalRemainingMatches = remainingMatches;
                        List<String> newRemainingMatches = networkConnectionsByComputer.get(theRest).stream().filter(
                                computerName -> finalRemainingMatches.contains(computerName)
                        ).toList();
                        remainingMatches = newRemainingMatches;
                    }

                }

                if (biggestNetwork.size() < remainingMatches.size()) {
                    biggestNetwork = remainingMatches;
                }

            }

        }

        return String.join(",", biggestNetwork.stream().sorted().collect(Collectors.toList()));
    }
}
