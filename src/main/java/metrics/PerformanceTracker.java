package metrics;

import java.util.HashMap;
import java.util.Map;

public class PerformanceTracker {
    private final Map<String, Long> metrics;
    private long startTime;
    private long endTime;

    public PerformanceTracker() {
        this.metrics = new HashMap<>();
        reset();
    }

    public void startTimer() {
        startTime = System.nanoTime();
    }

    public void stopTimer() {
        endTime = System.nanoTime();
        metrics.put("executionTimeNanos", endTime - startTime);
    }

    public void setMetric(String name, long value) {
        metrics.put(name, value);
    }

    public long getMetric(String name) {
        return metrics.getOrDefault(name, 0L);
    }

    public double getExecutionTimeMillis() {
        return (endTime - startTime) / 1_000_000.0;
    }

    public void reset() {
        metrics.clear();
        startTime = 0;
        endTime = 0;
    }

    public void printMetrics() {
        System.out.println("=== Performance Metrics ===");
        System.out.printf("Execution time: %.3f ms%n", getExecutionTimeMillis());
        metrics.forEach((key, value) -> {
            if (!key.equals("executionTimeNanos")) {
                System.out.printf("%s: %d%n", key, value);
            }
        });
        System.out.println("===========================");
    }

    public String toCSV() {
        return String.format("%.3f,%s",
                getExecutionTimeMillis(),
                String.join(",", metrics.entrySet().stream()
                        .filter(entry -> !entry.getKey().equals("executionTimeNanos"))
                        .map(entry -> entry.getValue().toString())
                        .toArray(String[]::new))
        );
    }
}