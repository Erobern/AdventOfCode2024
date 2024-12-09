package adventofcode2024.solvers;

import adventofcode2024.fileloaders.FileLoaders;

import java.util.ArrayList;
import java.util.List;

public class Day9 {
    public static String Puzzle1(String input) {
        List<String> lines = FileLoaders.loadInputIntoStringList(input);

        char[] compressedDisk = lines.getFirst().toCharArray();

        Integer[] inflatedDisk = new Integer[lines.getFirst().length() * 10];

        // inflate disk
        int diskIndex = 0;
        int fileId = 0;
        boolean isFile = true;

        for (int i = 0; i < lines.getFirst().length(); i++) {
            int size = Integer.parseInt(String.valueOf(compressedDisk[i]));

            for (int j = 0; j < size; j++) {
                if (isFile) {
                    inflatedDisk[diskIndex] = fileId;
                } else {
                    inflatedDisk[diskIndex] = null;
                }
                diskIndex++;
            }

            if (isFile) {
                fileId++;
            }

            isFile = !isFile;
        }

        // defrag disk

        // traverse array from the right to find first non-null element

        int rightIndex = 0;
        int leftIndex = 0;

        for (int i = (lines.getFirst().length() * 10) - 1; i >= 0; i--) {
            if (inflatedDisk[i] != null) {
                rightIndex = i;
                break;
            }
        }

        while (leftIndex < rightIndex) {
            if (inflatedDisk[leftIndex] == null) {
                inflatedDisk[leftIndex] = inflatedDisk[rightIndex];
                inflatedDisk[rightIndex] = null;
                rightIndex--;
            }

            leftIndex++;


            // fast fowrard indexes

            boolean leftReady = false;
            boolean rightReady = false;

            while (!leftReady) {
                if (inflatedDisk[leftIndex] != null) {
                    leftIndex++;
                } else {
                    leftReady = true;
                }
            }

            while (!rightReady) {
                if (inflatedDisk[rightIndex] == null) {
                    rightIndex--;
                } else {
                    rightReady = true;
                }
            }
        }


        // build checksum

        boolean continueChecksum = true;
        int leftChecksumIndex = 0;
        long sum = 0L;
        while (continueChecksum) {
            if (inflatedDisk[leftChecksumIndex] != null) {
                sum += (long) leftChecksumIndex * inflatedDisk[leftChecksumIndex];
                leftChecksumIndex++;
            } else {
                continueChecksum = false;
            }
        }

        return Long.toString(sum);
    }

    public static String Puzzle2(String input) {
        List<String> lines = FileLoaders.loadInputIntoStringList(input);

        char[] compressedDisk = lines.getFirst().toCharArray();

        Integer[] inflatedDisk = new Integer[lines.getFirst().length() * 10];

        // inflate disk
        int diskIndex = 0;
        int fileId = 0;
        boolean isFile = true;

        for (int i = 0; i < lines.getFirst().length(); i++) {
            int size = Integer.parseInt(String.valueOf(compressedDisk[i]));

            for (int j = 0; j < size; j++) {
                if (isFile) {
                    inflatedDisk[diskIndex] = fileId;
                } else {
                    inflatedDisk[diskIndex] = null;
                }
                diskIndex++;
            }

            if (isFile) {
                fileId++;
            }

            isFile = !isFile;
        }

        // defrag disk

        // traverse array from the right to find first non-null element

        int rightIndex = 0;
        int leftIndex = 0;

        for (int i = (lines.getFirst().length() * 10) - 1; i >= 0; i--) {
            if (inflatedDisk[i] != null) {
                rightIndex = i;
                break;
            }
        }

        List<Integer> fileIdsAlreadyProcessed = new ArrayList<>();

        while (leftIndex < rightIndex) {
            // get size of right index file
            int rightIndexFileTest = rightIndex;
            int rightIndexFileSize = 0;
            int rightIndexFileId = inflatedDisk[rightIndexFileTest];
            if (fileIdsAlreadyProcessed.contains(rightIndexFileId)) {
                // skip ahead
                rightIndex--;
            } else {
                while (inflatedDisk[rightIndexFileTest] != null &&
                        rightIndexFileId == inflatedDisk[rightIndexFileTest]) {
                    rightIndexFileSize++;
                    rightIndexFileTest--;
                }

                // find a null block big enough to fit;
                boolean bigEnoughBlockFound = false;
                int leftIndexNullBLock = leftIndex;
                while (!bigEnoughBlockFound && leftIndexNullBLock <= rightIndexFileTest - (rightIndexFileSize - 1)) {
                    if (inflatedDisk[leftIndexNullBLock] != null) {
                        leftIndexNullBLock++;
                        continue;
                    } else {
                        // test if the block is big enough
                        int leftIndexTestNullBlock = leftIndexNullBLock;
                        bigEnoughBlockFound = true; // maybe?
                        for (int i = leftIndexTestNullBlock; i < leftIndexTestNullBlock + rightIndexFileSize; i++) {
                            if (inflatedDisk[i] != null) {
                                bigEnoughBlockFound = false;
                                break;
                            }
                        }

                        if (bigEnoughBlockFound) {
                            for (int i = leftIndexTestNullBlock; i < leftIndexTestNullBlock + rightIndexFileSize; i++) {
                                inflatedDisk[i] = rightIndexFileId;
                            }
                            for (int i = rightIndex; i > rightIndex - rightIndexFileSize; i--) {
                                inflatedDisk[i] = null;
                            }

                            rightIndex -= rightIndexFileSize;
                            fileIdsAlreadyProcessed.add(rightIndexFileId);
                        } else {
                            leftIndexNullBLock++;
                        }
                    }
                }

                if (!bigEnoughBlockFound) {
                    for (int i = 0; i < rightIndexFileSize; i++) {
                        rightIndex--;
                    }
                    fileIdsAlreadyProcessed.add(rightIndexFileId);
                }

                boolean nextFileFound = false;
                while (!nextFileFound) {
                    if (inflatedDisk[rightIndex] == null) {
                        rightIndex--;
                    } else {
                        nextFileFound = true;
                    }
                }

                // always reset left index to the first null to save time
                leftIndex = 0;
                for (int i = 0; i < rightIndex; i++) {
                    if (inflatedDisk[leftIndex] != null) {
                        leftIndex++;
                    } else {
                        break;
                    }
                }
            }
        }
        // build checksum

        long sum = 0L;
        for (int i = 0; i < (lines.getFirst().length() * 10) - 1; i++) {
            if (inflatedDisk[i] != null) {
                sum += (long) i * inflatedDisk[i];
            } else {
                // noop
            }
        }

        return Long.toString(sum);
    }
}
