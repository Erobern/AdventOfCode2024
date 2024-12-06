import adventofcode2024.solvers.Day5;
import adventofcode2024.solvers.Day6;
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
}
