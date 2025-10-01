package metrics;

import algorithms.InsertionSort;
import algorithms.SelectionSort;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class DataExporter {

    public static void main(String[] args) {
        try {
            System.out.println("📊 Exporting performance data to CSV files...");
            exportInsertionSortData();
            exportSelectionSortData();
            exportComparisonData();
            System.out.println("✅ All data exported successfully!");
        } catch (IOException e) {
            System.out.println("❌ Error exporting data: " + e.getMessage());
        }
    }

    private static void exportInsertionSortData() throws IOException {
        FileWriter writer = new FileWriter("insertion_sort_comprehensive.csv");
        writer.write("DataType,Size,Time(ms),Comparisons,Assignments,ArrayCopies\n");

        int[] sizes = {100, 500, 1000, 2000, 5000, 10000};
        Random random = new Random();

        for (int size : sizes) {
            // Test different data distributions
            String[] dataTypes = {"Random", "Sorted", "Reverse", "NearlySorted"};

            for (String dataType : dataTypes) {
                int[] array = generateArray(dataType, size, random);
                InsertionSort sorter = new InsertionSort();
                PerformanceTracker tracker = sorter.sortWithMetrics(array);

                writer.write(String.format("%s,%d,%.6f,%d,%d,%d\n",
                        dataType, size, tracker.getExecutionTimeMillis(),
                        tracker.getMetric("comparisons"), tracker.getMetric("assignments"),
                        tracker.getMetric("arrayCopies")
                ));
            }
        }
        writer.close();
    }

    private static void exportSelectionSortData() throws IOException {
        FileWriter writer = new FileWriter("selection_sort_comprehensive.csv");
        writer.write("DataType,Size,Time(ms),Comparisons,Swaps,Assignments\n");

        int[] sizes = {100, 500, 1000, 2000, 5000, 10000};
        Random random = new Random();

        for (int size : sizes) {
            String[] dataTypes = {"Random", "Sorted", "Reverse", "NearlySorted"};

            for (String dataType : dataTypes) {
                int[] array = generateArray(dataType, size, random);
                SelectionSort sorter = new SelectionSort();
                PerformanceTracker tracker = sorter.sortWithMetrics(array);

                writer.write(String.format("%s,%d,%.6f,%d,%d,%d\n",
                        dataType, size, tracker.getExecutionTimeMillis(),
                        tracker.getMetric("comparisons"), tracker.getMetric("swaps"),
                        tracker.getMetric("assignments")
                ));
            }
        }
        writer.close();
    }

    private static void exportComparisonData() throws IOException {
        FileWriter writer = new FileWriter("algorithm_comparison.csv");
        writer.write("Algorithm,Size,Time(ms),Comparisons,Operations\n");

        int[] sizes = {100, 500, 1000, 2000, 5000, 10000};
        Random random = new Random();

        for (int size : sizes) {
            int[] array = generateRandomArray(size, random);

            // Insertion Sort
            InsertionSort insertionSorter = new InsertionSort();
            PerformanceTracker insertionTracker = insertionSorter.sortWithMetrics(array.clone());
            writer.write(String.format("Insertion,%d,%.6f,%d,%d\n",
                    size, insertionTracker.getExecutionTimeMillis(),
                    insertionTracker.getMetric("comparisons"),
                    insertionTracker.getMetric("assignments")
            ));

            // Selection Sort
            SelectionSort selectionSorter = new SelectionSort();
            PerformanceTracker selectionTracker = selectionSorter.sortWithMetrics(array.clone());
            writer.write(String.format("Selection,%d,%.6f,%d,%d\n",
                    size, selectionTracker.getExecutionTimeMillis(),
                    selectionTracker.getMetric("comparisons"),
                    selectionTracker.getMetric("swaps")
            ));
        }
        writer.close();
    }

    private static int[] generateArray(String dataType, int size, Random random) {
        switch (dataType) {
            case "Random":
                return generateRandomArray(size, random);
            case "Sorted":
                return generateSortedArray(size);
            case "Reverse":
                return generateReverseSortedArray(size);
            case "NearlySorted":
                return generateNearlySortedArray(size, random);
            default:
                return generateRandomArray(size, random);
        }
    }

    private static int[] generateRandomArray(int size, Random random) {
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

    private static int[] generateNearlySortedArray(int size, Random random) {
        int[] array = generateSortedArray(size);
        int swaps = size / 20; // 5% of elements
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