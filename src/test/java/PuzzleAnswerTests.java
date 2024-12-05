import adventofcode2024.solvers.Day5;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class PuzzleAnswerTests {

    @Test
    public void AllPuzzleSolutions() {
        Assertions.assertEquals("143", Day5.Puzzle1("Day5_1_TEST.txt"), "Day 5, Puzzle 1");
        Assertions.assertEquals("123", Day5.Puzzle2("Day5_1_TEST.txt"), "Day 5, Puzzle 2");
    }
}
