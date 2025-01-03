import adventofcode2024.solvers.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class PuzzleAnswerTests {

    @Test
    public void Puzzle5Solutions() {
        Assertions.assertEquals("143", Day5.Puzzle1("Day5_1_TEST.txt"), "Day 5, Puzzle 1");
        Assertions.assertEquals("123", Day5.Puzzle2("Day5_1_TEST.txt"), "Day 5, Puzzle 2");
    }

    @Test
    public void Puzzle6Solutions() {
        Assertions.assertEquals("41", Day6.Puzzle1("Day6_1_TEST.txt"), "Day 6, Puzzle 1");
        Assertions.assertEquals("6", Day6.Puzzle2("Day6_1_TEST.txt"), "Day 6, Puzzle 2");
    }

    @Test
    public void Puzzle7Solutions() {
        Assertions.assertEquals("3749", Day7.Puzzle1("Day7_1_TEST.txt"), "Day 7, Puzzle 1");
        Assertions.assertEquals("11387", Day7.Puzzle2("Day7_1_TEST.txt"), "Day 7, Puzzle 2");
    }

    @Test
    public void Puzzle8Solutions() {
        Assertions.assertEquals("14", Day8.Puzzle1("Day8_1_TEST.txt"), "Day 8, Puzzle 1");
        Assertions.assertEquals("34", Day8.Puzzle2("Day8_1_TEST.txt"), "Day 8, Puzzle 2");
    }

    @Test
    public void Puzzle9Solutions() {
        Assertions.assertEquals("1928", Day9.Puzzle1("Day9_1_TEST.txt"), "Day 9, Puzzle 1");
        Assertions.assertEquals("2858", Day9.Puzzle2("Day9_1_TEST.txt"), "Day 9, Puzzle 2");
    }

    @Test
    public void Puzzle9Solutions_ExtraTests() {
        Assertions.assertEquals("132", Day9.Puzzle2("Day9_2_TEST.txt"), "Day 9, Puzzle 2 - EXTRA");
    }

    @Test
    public void Puzzle10Solutions() {
        Assertions.assertEquals("36", Day10.Puzzle1("Day10_1_TEST.txt"), "Day 10, Puzzle 1");
    }

    @Test
    public void Puzzle11Solutions() {
        Assertions.assertEquals("55312", Day11.Puzzle1("Day11_1_TEST.txt"), "Day 11, Puzzle 1");
        Assertions.assertEquals("65601038650482", Day11.Puzzle2("Day11_1_TEST.txt"), "Day 11, Puzzle 2");
    }

    @Test
    public void Puzzle12SolutionsSmall() {
        Assertions.assertEquals("140", Day12.Puzzle1("Day12_2_TEST_SMALL.txt"), "Day 12, Puzzle 1");
        Assertions.assertEquals("80", Day12.Puzzle2("Day12_2_TEST_SMALL.txt"), "Day 12, Puzzle 2");
    }

    @Test
    public void Puzzle12SolutionsBoxes() {
        Assertions.assertEquals("1184", Day12.Puzzle1("Day12_2_TEST_BOXES.txt"), "Day 12, Puzzle 1");
        Assertions.assertEquals("368", Day12.Puzzle2("Day12_2_TEST_BOXES.txt"), "Day 12, Puzzle 2");
    }

    @Test
    public void Puzzle12Solutions() {
        Assertions.assertEquals("1930", Day12.Puzzle1("Day12_1_TEST.txt"), "Day 12, Puzzle 1");
        Assertions.assertEquals("1206", Day12.Puzzle2("Day12_1_TEST.txt"), "Day 12, Puzzle 2");
    }

    @Test
    public void Puzzle12SolutionsLs() {
        Assertions.assertEquals("6568", Day12.Puzzle2("Day12_2_TEST_L.txt"), "Day 12, Puzzle 2");
    }

    @Test
    public void Puzzle13Solutions() {
        Assertions.assertEquals("480", Day13.Puzzle1("Day13_1_TEST.txt"), "Day 13, Puzzle 1");
        Assertions.assertEquals("875318608908", Day13.Puzzle2("Day13_1_TEST.txt"), "Day 13, Puzzle 2");
    }

    @Test
    public void Puzzle14Solutions() {
        Assertions.assertEquals("12", Day14.Puzzle1("Day14_1_TEST.txt", 11, 7), "Day 14, Puzzle 1");
    }

    @Test
    public void Puzzle15Solutions() {
        Assertions.assertEquals("10092", Day15.Puzzle1("Day15_1_TEST_grid.txt", "Day15_1_TEST_instruction.txt"), "Day 15, Puzzle 1");
        Assertions.assertEquals("9021", Day15.Puzzle2("Day15_1_TEST_grid.txt", "Day15_1_TEST_instruction.txt"), "Day 15, Puzzle 1");
    }

    @Test
    public void Puzzle16Solutions1() {
        Assertions.assertEquals("7036", Day16.Puzzle1("Day16_1_TEST.txt"), "Day 16, Puzzle 1");
        Assertions.assertEquals("45", Day16.Puzzle2("Day16_1_TEST.txt"), "Day 16, Puzzle 2");
    }

    @Test
    public void Puzzle16Solutions2() {
        Assertions.assertEquals("11048", Day16.Puzzle1("Day16_2_TEST.txt"), "Day 16, Puzzle 1");
        Assertions.assertEquals("64", Day16.Puzzle2("Day16_2_TEST.txt"), "Day 16, Puzzle 2");
    }

    @Test
    public void Puzzle17Solutions() {
        Assertions.assertEquals("4,6,3,5,6,3,5,2,1,0", Day17.Puzzle1("Day17_1_TEST.txt"), "Day 17, Puzzle 1");
        Assertions.assertEquals("4,2,5,6,7,7,7,7,3,1,0", Day17.Puzzle1("Day17_2_TEST.txt"), "Day 17, Puzzle 1");
        Assertions.assertEquals("117440", Day17.Puzzle2("Day17_3_TEST.txt"), "Day 17, Puzzle 2");
    }

    @Test
    public void Puzzle17SolutionsIndividualTests() {
        Assertions.assertEquals("4", Day17.Puzzle1("Day17Tests/Day17_1_TEST_ADV1.txt"), "Day 17, Puzzle 1");
        Assertions.assertEquals("0", Day17.Puzzle1("Day17Tests/Day17_1_TEST_ADV2.txt"), "Day 17, Puzzle 1");
        Assertions.assertEquals("0", Day17.Puzzle1("Day17Tests/Day17_1_TEST_ADV3.txt"), "Day 17, Puzzle 1");

        Assertions.assertEquals("2", Day17.Puzzle1("Day17Tests/Day17_1_TEST_BXL1.txt"), "Day 17, Puzzle 1");

        Assertions.assertEquals("2", Day17.Puzzle1("Day17Tests/Day17_1_TEST_BST1.txt"), "Day 17, Puzzle 1");

        Assertions.assertEquals("1", Day17.Puzzle1("Day17Tests/Day17_1_TEST_JNZ1.txt"), "Day 17, Puzzle 1");

        Assertions.assertEquals("1", Day17.Puzzle1("Day17Tests/Day17_1_TEST_BXC1.txt"), "Day 17, Puzzle 1");
    }

    @Test
    public void Puzzle18Solutions() {
        Assertions.assertEquals("22", Day18.Puzzle1("Day18_1_TEST.txt", 7, 12), "Day 18, Puzzle 1");
        Assertions.assertEquals("6,1", Day18.Puzzle2("Day18_1_TEST.txt", 7, 12), "Day 18, Puzzle 2");
    }

    @Test
    public void Puzzle19Solutions() {
        Assertions.assertEquals("6", Day19.Puzzle1("Day19_1_TEST.txt"), "Day 19, Puzzle 1");
        Assertions.assertEquals("16", Day19.Puzzle2("Day19_1_TEST.txt"), "Day 19, Puzzle 2");
    }

    @Test
    public void Puzzle20Solutions() {
        Assertions.assertEquals("5", Day20.Puzzle1("Day20_1_TEST.txt", 20), "Day 20, Puzzle 1");
        Assertions.assertEquals("41", Day20.Puzzle2("Day20_1_TEST.txt", 70), "Day 20, Puzzle 2");
    }

    @Test
    public void Puzzle21Solutions() {
        Assertions.assertEquals("126384", Day21.Puzzle1("Day21_1_TEST.txt"), "Day 21, Puzzle 1");
        Assertions.assertEquals("154115708116294", Day21.Puzzle2("Day21_1_TEST.txt"), "Day 21, Puzzle 2");
    }

    @Test
    public void Puzzle22Solutions() {
        Assertions.assertEquals("37327623", Day22.Puzzle1("Day22_1_TEST.txt"), "Day 22, Puzzle 1");
        Assertions.assertEquals("23", Day22.Puzzle2("Day22_2_TEST.txt"), "Day 22, Puzzle 2");
    }

    @Test
    public void Puzzle22Solutions123() {
        Assertions.assertEquals("9", Day22.Puzzle2("Day22_2_TEST_123.txt"), "Day 22, Puzzle 2");
    }

    @Test
    public void Puzzle23Solutions() {
        Assertions.assertEquals("7", Day23.Puzzle1("Day23_1_TEST.txt"), "Day 23, Puzzle 1");
        Assertions.assertEquals("co,de,ka,ta", Day23.Puzzle2("Day23_1_TEST.txt"), "Day 23, Puzzle 2");
    }

    @Test
    public void Puzzle24Solutions() {
        Assertions.assertEquals("2024", Day24.Puzzle1("Day24_1_TEST.txt"), "Day 24, Puzzle 1");
    }

    @Test
    public void Puzzle25Solutions() {
        Assertions.assertEquals("3", Day25.Puzzle1("Day25_1_TEST.txt"), "Day 25, Puzzle 1");
        //Assertions.assertEquals("6", Day25.Puzzle2("Day25_1_TEST.txt"), "Day 25, Puzzle 2");
    }
}
