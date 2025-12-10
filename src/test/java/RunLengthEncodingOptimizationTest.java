import org.junit.jupiter.api.Test;
import ssc.exercise.RunLengthEncoding;

import static org.junit.jupiter.api.Assertions.*;

public class RunLengthEncodingOptimizationTest {

    // Test streaming encode/decode (simulate chunk processing)
    @Test
    void testStreamingEncodeDecode() {
        String chunk1 = "aaa";
        String chunk2 = "aaabbb";
        String encoded1 = RunLengthEncoding.encode(chunk1);
        String encoded2 = RunLengthEncoding.encode(chunk2);

        // Ensure chunks can be processed independently
        assertEquals("a3", encoded1);
        assertEquals("a3b3", encoded2);
    }

    // Test extremely large input (simulate GB-scale with smaller proxy)
    @Test
    void testExtremelyLargeInput() {
        String input = "a".repeat(5_000_000); // 5 million chars
        String encoded = RunLengthEncoding.encode(input);
        assertTrue(encoded.startsWith("a5000000"));
        String decoded = RunLengthEncoding.decode(encoded);
        assertEquals(input, decoded);
    }

    // Test StringBuilder vs char[] trade-off (functional correctness)
    @Test
    void testStringBuilderVsCharArray() {
        String input = "aaabb";
        String encodedBuilder = RunLengthEncoding.encode(input); // StringBuilder version
        String encodedCharArray = RunLengthEncoding.RunLengthEncodingCharArray.encode(input); // hypothetical char[] version
        assertEquals(encodedBuilder, encodedCharArray);
    }
}