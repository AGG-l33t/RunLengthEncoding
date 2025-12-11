package ssc.exercise;

import java.util.LinkedHashMap;
import java.util.Map;

public class SafeRLE {

    // Bounded LRU cache (max 100 entries)
    private static final int MAX_CACHE_SIZE = 100;
    private static final Map<String, String> cache = new LinkedHashMap<>(MAX_CACHE_SIZE, 0.75f, true) {
        @Override
        protected boolean removeEldestEntry(Map.Entry<String, String> eldest) {
            return size() > MAX_CACHE_SIZE;
        }
    };

    public static String encode(String str) {
        if (str == null || str.isEmpty()) return "";

        // Check cache first
        if (cache.containsKey(str)) {
            return cache.get(str);
        }

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

        // Store only bounded number of results
        cache.put(str, result);

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
        for (int i = 0; i < 200; i++) {
            String input = "a".repeat(1000) + i;
            String encoded = encode(input);
            System.out.println("Encoded length: " + encoded.length() + ", cache size: " + cache.size());
        }
    }
}