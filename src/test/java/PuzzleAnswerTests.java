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
}
