package cli;

import algorithms.InsertionSort;
import metrics.PerformanceTracker;
import java.util.Random;

public class BenchmarkRunner {

    public static void main(String[] args) {
        System.out.println("🚀 Insertion Sort Benchmark");
        System.out.println("============================");

        // Тестируем на разных размерах массивов
        int[] sizes = {100, 1000, 5000, 10000};

        for (int size : sizes) {
            System.out.printf("\n📊 Testing with array size: %,d%n", size);
            benchmarkRandomArray(size);
            benchmarkSortedArray(size);
            benchmarkReverseSortedArray(size);
        }
    }

    private static void benchmarkRandomArray(int size) {
        int[] array = generateRandomArray(size);
        runBenchmark("Random", array);
    }

    private static void benchmarkSortedArray(int size) {
        int[] array = generateSortedArray(size);
        runBenchmark("Sorted", array);
    }

    private static void benchmarkReverseSortedArray(int size) {
        int[] array = generateReverseSortedArray(size);
        runBenchmark("Reverse Sorted", array);
    }

    private static void runBenchmark(String type, int[] array) {
        InsertionSort sorter = new InsertionSort();
        PerformanceTracker tracker = sorter.sortWithMetrics(array);

        System.out.printf("  %s: %.3f ms, Comparisons: %,d, Assignments: %,d%n",
                type,
                tracker.getExecutionTimeMillis(),
                tracker.getMetric("comparisons"),
                tracker.getMetric("assignments")
        );
    }

    private static int[] generateRandomArray(int size) {
        Random random = new Random();
        int[] array = new int[size];
        for (int i = 0; i < size; i++) {
            array[i] = random.nextInt(size * 10);
        }
        return array;
    }

    private static int[] generateSortedArray(int size) {
        int[] array = new int[size];
        for (int i = 0; i < size; i++) {
            array[i] = i;
        }
        return array;
    }

    private static int[] generateReverseSortedArray(int size) {
        int[] array = new int[size];
        for (int i = 0; i < size; i++) {
            array[i] = size - i;
        }
        return array;
    }
}
