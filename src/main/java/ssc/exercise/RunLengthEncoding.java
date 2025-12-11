package ssc.exercise;

public class RunLengthEncoding {

    // Encode: compress string into char+count
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
        return encoded.toString();
    }

    // Decode: expand char+count back into original string
    public static String decode(String encoded) {
        if (encoded == null || encoded.isEmpty()) return "";
        StringBuilder decoded = new StringBuilder();

        for (int i = 0; i < encoded.length(); i++) {
            char c = encoded.charAt(i);
            int j = i + 1;
            StringBuilder num = new StringBuilder();
            while (j < encoded.length() && Character.isDigit(encoded.charAt(j))) {
                num.append(encoded.charAt(j));
                j++;
            }
            int count = Integer.parseInt(num.toString());
            decoded.append(String.valueOf(c).repeat(count));
            i = j - 1; // move pointer
        }
        return decoded.toString();
    }

    // Alternative implementation using char array for encoding
    public static class RunLengthEncodingCharArray {

        public static String encode(String str) {
            if (str == null || str.isEmpty()) return "";

            // Preallocate a char buffer roughly twice the input length
            char[] buffer = new char[str.length() * 2];
            int pos = 0;
            int count = 1;

            for (int i = 1; i < str.length(); i++) {
                if (str.charAt(i) == str.charAt(i - 1)) {
                    count++;
                } else {
                    buffer[pos++] = str.charAt(i - 1);
                    for (char c : String.valueOf(count).toCharArray()) {
                        buffer[pos++] = c;
                    }
                    count = 1;
                }
            }

            // Append last run
            buffer[pos++] = str.charAt(str.length() - 1);
            for (char c : String.valueOf(count).toCharArray()) {
                buffer[pos++] = c;
            }

            return new String(buffer, 0, pos);
        }
    }

    public static void main(String[] args) {
        String original = "wwwwaaadexxxxxx";
        String encoded = encode(original);
        String decoded = decode(encoded);

        System.out.println("Original: " + original);
        System.out.println("Encoded: " + encoded);
        System.out.println("Decoded: " + decoded);
    }
}