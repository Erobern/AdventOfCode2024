package adventofcode2024.utils;

public class StringUtils {

    public static String get2DArrayPrint(String[][] matrix) {
        String output = "";
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                output = output + (matrix[i][j]);
            }
            output = output + "\n";
        }
        return output;
    }
}
