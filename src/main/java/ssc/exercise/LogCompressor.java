package ssc.exercise;

public class LogCompressor {
    public static String compressLog(String log) {
        return RunLengthEncoding.encode(log);
    }

    public static String decompressLog(String compressed) {
        return RunLengthEncoding.decode(compressed);
    }

    /**
     * Calculate compression ratio = compressed length / original length.
     * A ratio < 1 means compression saved space.
     */
    public static double compressionRatio(String original) {
        if (original == null || original.isEmpty()) return 1.0;
        String compressed = compressLog(original);
        return (double) compressed.length() / original.length();
    }


    public static void main(String[] args) {
        String log = "ERROR::::::ConnectionFailed::::::Retry::::::";
        String compressed = compressLog(log);
        String decompressed = decompressLog(compressed);

        System.out.println("Original:    " + log);
        System.out.println("Compressed:  " + compressed);
        System.out.println("Decompressed:" + decompressed);
    }
}