import org.junit.jupiter.api.Test;
import ssc.exercise.RunLengthEncoding;

import static org.junit.jupiter.api.Assertions.*;

public class RunLengthEncodingTest {

    // Edge Case 1: Empty string
    @Test
    void testEmptyString() {
        assertEquals("", RunLengthEncoding.encode(""));
        assertEquals("", RunLengthEncoding.decode(""));
    }

    // Edge Case 2: Single character
    @Test
    void testSingleCharacter() {
        assertEquals("a1", RunLengthEncoding.encode("a"));
        assertEquals("a", RunLengthEncoding.decode("a1"));
    }

    // Edge Case 3: Very large counts
    @Test
    void testLargeCount() {
        String input = "a".repeat(1_000_000);
        String encoded = RunLengthEncoding.encode(input);
        assertTrue(encoded.startsWith("a1000000"));
        String decoded = RunLengthEncoding.decode(encoded);
        assertEquals(input, decoded);
    }

}