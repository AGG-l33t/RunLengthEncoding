import org.junit.jupiter.api.Test;
import ssc.exercise.LogCompressor;

import static org.junit.jupiter.api.Assertions.*;

public class LogCompressorTest {

    @Test
    void testCorrectness() {
        String log = "ERROR::::::ConnectionFailed::::::Retry::::::";
        String compressed = LogCompressor.compressLog(log);
        String decompressed = LogCompressor.decompressLog(compressed);

        // Always true: correctness
        assertEquals(log, decompressed);
    }

    @Test
    void testCompressionRatioOnRepetitiveData() {
        String log = "::::::::::::::::::::::::::::::::::::::::"; // 40 colons
        double ratio = LogCompressor.compressionRatio(log);

        System.out.println("Compression ratio: " + ratio);

        // Efficiency check: ratio should be significantly less than 1
        assertTrue(ratio < 0.2, "Compression ratio should be efficient (<20%)");
    }

    @Test
    void testCompressionRatioOnMixedData() {
        String log = "ERROR::::::ConnectionFailed::::::Retry::::::";
        double ratio = LogCompressor.compressionRatio(log);

        System.out.println("Compression ratio: " + ratio);

        // Mixed data may not compress well, but ratio should still be <= 1.5
        assertTrue(ratio <= 1.5, "Compression ratio should not inflate excessively");
    }
}