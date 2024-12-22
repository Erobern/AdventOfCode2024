package adventofcode2024.solvers;

import adventofcode2024.fileloaders.FileLoaders;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;

public class Day22 {
    public static String Puzzle1(String input) {
        List<String> lines = FileLoaders.loadInputIntoStringList(input);

        AtomicReference<Long> answer = new AtomicReference<>(0L);
        Long prune = 16777216L;

        lines.parallelStream().forEach(line -> {
            Long secret = Long.parseLong(line);

            for (int i = 0; i < 2000; i++) {
                Long part1 = secret * 64;
                secret = secret ^ part1;
                secret = secret % prune;

                Long part2 = Long.divideUnsigned(secret, 32L);
                secret = secret ^ part2;
                secret = secret % prune;

                Long part3 = secret * 2048;
                secret = secret ^ part3;
                secret = secret % prune;

            }

            Long finalSecret = secret;
            answer.updateAndGet(v -> v + finalSecret);
        });
        return answer.toString();
    }

    public static String Puzzle2(String input) {
        List<String> lines = FileLoaders.loadInputIntoStringList(input);

        Long prune = 16777216L;

        Map<String, Long> globalBananaMap = new HashMap<>();

        lines.stream().forEach(line -> {
            Long secret = Long.parseLong(line);

            Map<String, Long> bananaMap = new HashMap<>();

            Long fourBack = 0L;
            Long threeBack = 0L;
            Long twoBack = 0L;
            Long oneBack = 0L;

            Long previousPrice = 0L;
            Long currentPrice = secret % 10;

            for (int i = 0; i < 2000; i++) {
                Long part1 = secret * 64;
                secret = secret ^ part1;
                secret = secret % prune;

                Long part2 = Long.divideUnsigned(secret, 32L);
                secret = secret ^ part2;
                secret = secret % prune;

                Long part3 = secret * 2048;
                secret = secret ^ part3;
                secret = secret % prune;

                previousPrice = currentPrice;
                currentPrice = secret % 10;

                fourBack = threeBack;
                threeBack = twoBack;
                twoBack = oneBack;
                oneBack = currentPrice - previousPrice;

                if (i >= 3) {
                    if (!bananaMap.containsKey(getKey(fourBack, threeBack, twoBack, oneBack))) {
                        bananaMap.put(getKey(fourBack, threeBack, twoBack, oneBack), currentPrice);
                    }
                }
            }

            for (String key : bananaMap.keySet()) {
                if (globalBananaMap.containsKey(key)) {
                    globalBananaMap.put(key, globalBananaMap.get(key) + bananaMap.get(key));
                } else {
                    globalBananaMap.put(key, bananaMap.get(key));
                }
            }

        });

        Long answer = 0L;

        for (Long value : globalBananaMap.values()) {
            if (answer < value) {
                answer = value;
            }
        }


        return answer.toString();
    }

    static String getKey(Long fourBack, Long threeBack, Long twoBack, Long oneBack) {
        return fourBack + "," + threeBack + "," + twoBack + "," + oneBack;
    }
}
