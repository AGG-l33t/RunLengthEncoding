package ssc.exercise;

public class RunLengthEncodingInefficient {

    public static String encodeBad(String str) {
        if (str == null || str.isEmpty()) return "";

        String encoded = ""; // BAD: immutable string concatenation
        int count = 1;

        for (int i = 1; i < str.length(); i++) {
            if (str.charAt(i) == str.charAt(i - 1)) {
                count++;
            } else {
                encoded += str.charAt(i - 1) + String.valueOf(count); // costly
                count = 1;
            }
        }
        encoded += str.charAt(str.length() - 1) + String.valueOf(count);
        return encoded;
    }

    public static String encodeGood(String str) {
        if (str == null || str.isEmpty()) return "";

        StringBuilder encoded = new StringBuilder();
        int count = 1;

        for (int i = 1; i < str.length(); i++) {
            if (str.charAt(i) == str.charAt(i - 1)) {
                count++;
            } else {
                encoded.append(str.charAt(i - 1)).append(count);
                count = 1;
            }
        }
        encoded.append(str.charAt(str.length() - 1)).append(count);
        return encoded.toString();
    }

    public static void benchmark(String input) {
        Runtime runtime = Runtime.getRuntime();

        // Bad solution benchmark
        long start = System.nanoTime();
        runtime.gc(); // clean up before measuring
        long beforeMem = runtime.totalMemory() - runtime.freeMemory();
        encodeBad(input);
        long afterMem = runtime.totalMemory() - runtime.freeMemory();
        long end = System.nanoTime();
        System.out.println("Bad Solution: Time = " + (end - start) + " ns, Memory = " + (afterMem - beforeMem) + " bytes");

        // Good solution benchmark
        start = System.nanoTime();
        runtime.gc();
        beforeMem = runtime.totalMemory() - runtime.freeMemory();
        encodeGood(input);
        afterMem = runtime.totalMemory() - runtime.freeMemory();
        end = System.nanoTime();
        System.out.println("Good Solution: Time = " + (end - start) + " ns, Memory = " + (afterMem - beforeMem) + " bytes");
    }

    public static void main(String[] args) {
        String test = "a".repeat(100000); // long input string
        benchmark(test);
    }

}
