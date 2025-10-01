package cli;

import algorithms.InsertionSort;
import algorithms.SelectionSort;
import metrics.PerformanceTracker;
import java.util.Random;

public class BenchmarkRunner {

    public static void main(String[] args) {
        System.out.println("🚀 Algorithm Comparison Benchmark");
        System.out.println("=================================");

        int[] sizes = {100, 1000, 5000, 10000};

        for (int size : sizes) {
            System.out.printf("\n📊 Testing with array size: %,d%n", size);
            System.out.println("-------------------------------");
            benchmarkAlgorithms(size);
        }
    }

    private static void benchmarkAlgorithms(int size) {
        int[] randomData = generateRandomArray(size);
        int[] sortedData = generateSortedArray(size);
        int[] reverseData = generateReverseSortedArray(size);

        System.out.println("INSERTION SORT:");
        runBenchmark("Insertion", "Random", randomData.clone());
        runBenchmark("Insertion", "Sorted", sortedData.clone());
        runBenchmark("Insertion", "Reverse", reverseData.clone());

        System.out.println();

        System.out.println("SELECTION SORT:");
        runBenchmark("Selection", "Random", randomData.clone());
        runBenchmark("Selection", "Sorted", sortedData.clone());
        runBenchmark("Selection", "Reverse", reverseData.clone());
    }

    private static void runBenchmark(String algorithm, String type, int[] array) {
        PerformanceTracker tracker;

        if (algorithm.equals("Insertion")) {
            InsertionSort sorter = new InsertionSort();
            tracker = sorter.sortWithMetrics(array);
        } else {
            SelectionSort sorter = new SelectionSort();
            tracker = sorter.sortWithMetrics(array);
        }

        System.out.printf("  %s: %.3f ms, Comparisons: %,d, Operations: %,d%n",
                type,
                tracker.getExecutionTimeMillis(),
                tracker.getMetric("comparisons"),
                algorithm.equals("Insertion") ? tracker.getMetric("assignments") : tracker.getMetric("swaps")
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