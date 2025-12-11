package ssc.exercise;

import java.util.concurrent.TimeUnit;
import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;

@State(Scope.Thread)
@BenchmarkMode(Mode.Throughput)
@OutputTimeUnit(TimeUnit.SECONDS)
@Warmup(iterations = 3)
@Measurement(iterations = 5)
@Fork(1)
public class FunctionalRLEBenchmark {

    private String sample;
    private String encoded;

    @Setup(Level.Trial)
    public void setup() {
        sample = "aaabbcccc".repeat(1000); // larger input for measurable work
        encoded = FunctionalRLE.encode(sample);
    }

    @Benchmark
    public void benchmarkEncode(Blackhole bh) {
        bh.consume(FunctionalRLE.encode(sample));
    }

    @Benchmark
    public void benchmarkDecode(Blackhole bh) {
        bh.consume(FunctionalRLE.decode(encoded));
    }
}