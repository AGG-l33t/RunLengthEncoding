package ssc.exercise;

import java.util.ArrayList;
import java.util.List;

public class LeakyRLE {

    // Static list keeps references forever → memory leak
    private static final List<String> cache = new ArrayList<>();

    public static String encode(String str) {
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

        String result = encoded.toString();

        // BUG: store every encoded string in a static list
        // This grows forever and prevents GC from reclaiming memory
        cache.add(result);

        return result;
    }

    public static String decode(String encoded) {
        if (encoded == null || encoded.isEmpty()) return "";

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < encoded.length(); ) {
            char c = encoded.charAt(i++);
            StringBuilder num = new StringBuilder();
            while (i < encoded.length() && Character.isDigit(encoded.charAt(i))) {
                num.append(encoded.charAt(i++));
            }
            int count = Integer.parseInt(num.toString());
            sb.append(String.valueOf(c).repeat(count));
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        try {
            int counter = 0;
            while (true) {
                // Encode a large string repeatedly
                String input = "a".repeat(1_000_000);
                encode(input);
                counter++;
                if (counter % 100 == 0) {
                    System.out.println("Encoded " + counter + " times, cache size = " + cache.size());
                }
            }
        } catch (OutOfMemoryError e) {
            System.err.println("Memory leak triggered: " + e.getMessage());
        }
    }
}