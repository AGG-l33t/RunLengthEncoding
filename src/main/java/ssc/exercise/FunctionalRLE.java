package ssc.exercise;

import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class FunctionalRLE {

    public static String encode(String input) {
        if (input == null || input.isEmpty()) return "";

        // Stream over indices, group consecutive runs
        StringBuilder sb = new StringBuilder();
        int[] counts = new int[input.length()];
        int count = 1;

        for (int i = 1; i < input.length(); i++) {
            if (input.charAt(i) == input.charAt(i - 1)) {
                count++;
            } else {
                counts[i - 1] = count;
                count = 1;
            }
        }
        counts[input.length() - 1] = count;

        return IntStream.range(0, input.length())
                .filter(i -> counts[i] > 0)
                .mapToObj(i -> input.charAt(i) + String.valueOf(counts[i]))
                .collect(Collectors.joining());
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
        String input = "aaabbcccc";
        String encoded = encode(input);
        String decoded = decode(encoded);

        System.out.println("Input: " + input);
        System.out.println("Encoded: " + encoded);
        System.out.println("Decoded: " + decoded);
    }
}
