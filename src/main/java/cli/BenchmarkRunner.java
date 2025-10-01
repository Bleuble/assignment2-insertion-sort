package cli;

import algorithms.InsertionSort;
import algorithms.SelectionSort;
import metrics.PerformanceTracker;
import java.util.Random;
import java.util.Arrays;

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

        // Export data to CSV
        exportDataToCSV();
    }

    private static void benchmarkAlgorithms(int size) {
        int[] randomData = generateRandomArray(size);
        int[] sortedData = generateSortedArray(size);
        int[] reverseData = generateReverseSortedArray(size);
        int[] nearlySortedData = generateNearlySortedArray(size);

        System.out.println("INSERTION SORT:");
        runBenchmark("Insertion", "Random", randomData.clone());
        runBenchmark("Insertion", "Sorted", sortedData.clone());
        runBenchmark("Insertion", "Reverse", reverseData.clone());
        runBenchmark("Insertion", "NearlySorted", nearlySortedData.clone());

        System.out.println();

        System.out.println("SELECTION SORT:");
        runBenchmark("Selection", "Random", randomData.clone());
        runBenchmark("Selection", "Sorted", sortedData.clone());
        runBenchmark("Selection", "Reverse", reverseData.clone());
        runBenchmark("Selection", "NearlySorted", nearlySortedData.clone());

        System.out.println();

        System.out.println("JAVA BUILT-IN SORT:");
        benchmarkJavaBuiltIn("Random", randomData.clone());
        benchmarkJavaBuiltIn("Sorted", sortedData.clone());
        benchmarkJavaBuiltIn("Reverse", reverseData.clone());
        benchmarkJavaBuiltIn("NearlySorted", nearlySortedData.clone());
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

        tracker.setArraySize(array.length);

        System.out.printf("  %s: %.3f ms, Comparisons: %,d, Operations: %,d%n",
                type,
                tracker.getExecutionTimeMillis(),
                tracker.getMetric("comparisons"),
                algorithm.equals("Insertion") ? tracker.getMetric("assignments") : tracker.getMetric("swaps")
        );
    }

    private static void benchmarkJavaBuiltIn(String type, int[] array) {
        long startTime = System.nanoTime();
        Arrays.sort(array);
        long endTime = System.nanoTime();
        double timeMs = (endTime - startTime) / 1_000_000.0;
        System.out.printf("  %s: %.3f ms%n", type, timeMs);
    }

    private static void exportDataToCSV() {
        try {
            java.io.FileWriter insertionWriter = new java.io.FileWriter("insertion_sort_results.csv");
            java.io.FileWriter selectionWriter = new java.io.FileWriter("selection_sort_results.csv");

            insertionWriter.write(PerformanceTracker.getCSVHeader() + "\n");
            selectionWriter.write(PerformanceTracker.getCSVHeader() + "\n");

            int[] sizes = {100, 500, 1000, 2000, 5000, 10000};
            Random random = new Random();

            for (int size : sizes) {
                // Test Insertion Sort
                int[] array1 = generateRandomArray(size);
                InsertionSort insertionSorter = new InsertionSort();
                PerformanceTracker tracker1 = insertionSorter.sortWithMetrics(array1);
                tracker1.setArraySize(size);
                insertionWriter.write(tracker1.toCSV() + "\n");

                // Test Selection Sort
                int[] array2 = array1.clone(); // Same data for fair comparison
                SelectionSort selectionSorter = new SelectionSort();
                PerformanceTracker tracker2 = selectionSorter.sortWithMetrics(array2);
                tracker2.setArraySize(size);
                selectionWriter.write(tracker2.toCSV() + "\n");
            }

            insertionWriter.close();
            selectionWriter.close();
            System.out.println("\n✅ Data exported to CSV files successfully!");

        } catch (java.io.IOException e) {
            System.out.println("❌ Error exporting data to CSV: " + e.getMessage());
        }
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

    private static int[] generateNearlySortedArray(int size) {
        int[] array = generateSortedArray(size);
        Random random = new Random();
        // Swap 5% of elements to create nearly sorted array
        int swaps = size / 20;
        for (int i = 0; i < swaps; i++) {
            int idx1 = random.nextInt(size);
            int idx2 = random.nextInt(size);
            int temp = array[idx1];
            array[idx1] = array[idx2];
            array[idx2] = temp;
        }
        return array;
    }
}