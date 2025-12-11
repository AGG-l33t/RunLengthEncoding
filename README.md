# 📦 Run Length Encoding (RLE) in Java

A Java implementation of **Run Length Encoding (RLE)** with:
- ✅ Encode & Decode functions
- ✅ Unit tests (JUnit 5) for correctness and edge cases
- ✅ Performance benchmarks (JMH) comparing bad vs good solutions
- ✅ GC profiling for memory allocation analysis

---

## 🚀 Features
- **Encode**: Compress consecutive characters into `<char><count>` format  
  Example: `wwwwaaadexxxxxx → w4a3d1e1x6`
- **Decode**: Expand encoded string back to original
- **Unit Tests**: Cover edge cases (empty string, single char, large counts, non‑alphabetic input)
- **Benchmarks**: Compare inefficient string concatenation vs efficient `StringBuilder`
- **GC Profiler**: Measure allocation rates and GC events

---

## 📂 Project Structure
rle-benchmark/ ├── src/ │   ├── main/java/com/example/ │   │   ├── RunLengthEncoding.java │   │   └── RLEBenchmark.java │   └── test/java/com/example/ │       └── RunLengthEncodingTest.java ├── pom.xml └── README.md

---

## ⚙️ Prerequisites
- Java 21+
- Maven 3.8+

---

## 🛠 Build & Run

# 📦 Run Length Encoding (RLE) Benchmark Results

This project implements **Run Length Encoding (RLE)** in Java and compares two approaches:

- **Bad Implementation** → Uses `String` concatenation (`+`) inside a loop.
- **Good Implementation** → Uses `StringBuilder` for efficient string construction.

Benchmarks were executed using **JMH 1.37** on **JDK 21.0.9 (Corretto)**.

---

## ⚙️ Benchmark Setup
- **Warmup:** 5 iterations, 10s each
- **Measurement:** 5 iterations, 10s each
- **Forks:** 5
- **Threads:** 1
- **Mode:** Average time per operation (`avgt`)
- **Unit:** Milliseconds per operation (`ms/op`)

---

## 📊 Results Summary

| Benchmark                  | Mode | Count | Avg Time (ms/op) | Error (99.9%) | Min | Max | StdDev |
|-----------------------------|------|-------|------------------|---------------|-----|-----|--------|
| `RLEBenchmark.encodeBad`    | avgt | 25    | **0.393**        | ±0.013        | 0.373 | 0.433 | 0.017 |
| `RLEBenchmark.encodeGood`   | avgt | 25    | **0.404**        | ±0.031        | 0.354 | 0.541 | 0.041 |

---

## 🔎 Observations
- Both implementations show **similar average times** (~0.39–0.40 ms/op) under this workload.
- The **bad implementation** (`String +`) has **lower variance** but risks **hidden quadratic costs** for larger inputs due to repeated allocations.
- The **good implementation** (`StringBuilder`) is more **scalable** and avoids excessive memory churn, even if average times appear close in this benchmark.
- For **GB-scale inputs**, `StringBuilder` will outperform `String +` significantly in both **time** and **space efficiency**.

---

## 🧪 Next Steps
- Run with **larger input sizes** to highlight differences more clearly.
- Add **GC profiling** (`-prof gc`) to measure allocation rates and GC pressure.
- mvn clean package
  PS ~\RunLengthEncoding\target> java -jar rle-benchmark-1.0-SNAPSHOT.jar -prof gc
- Compare with a **char[] buffer implementation** for even lower allocation overhead.
- Explore **streaming RLE** for chunked data processing in distributed systems.

---

## 👨‍💻 Author
Developed by **Alexandru**
---

## 📜 License
This project is licensed under the MIT License.

