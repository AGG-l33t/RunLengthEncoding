package ssc.exercise;

import org.openjdk.jmh.annotations.*;
import java.util.concurrent.TimeUnit;

@BenchmarkMode(Mode.AverageTime) // measure average execution time
@OutputTimeUnit(TimeUnit.MILLISECONDS)
@State(Scope.Thread)
public class RLEBenchmark {

    private String input;

    @Setup(Level.Iteration)
    public void setup() {
        input = "a".repeat(100000) + "b".repeat(50000) + "c".repeat(25000);
    }

    // BAD solution: String concatenation
    @Benchmark
    public String encodeBad() {
        String encoded = "";
        int count = 1;
        for (int i = 1; i < input.length(); i++) {
            if (input.charAt(i) == input.charAt(i - 1)) {
                count++;
            } else {
                encoded += input.charAt(i - 1) + String.valueOf(count);
                count = 1;
            }
        }
        encoded += input.charAt(input.length() - 1) + String.valueOf(count);
        return encoded;
    }

    // GOOD solution: StringBuilder
    @Benchmark
    public String encodeGood() {
        StringBuilder encoded = new StringBuilder();
        int count = 1;
        for (int i = 1; i < input.length(); i++) {
            if (input.charAt(i) == input.charAt(i - 1)) {
                count++;
            } else {
                encoded.append(input.charAt(i - 1)).append(count);
                count = 1;
            }
        }
        encoded.append(input.charAt(input.length() - 1)).append(count);
        return encoded.toString();
    }
}