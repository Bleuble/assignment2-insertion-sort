package algorithms;

import metrics.PerformanceTracker;

public class SelectionSort {
    private long comparisons;
    private long swaps;
    private long assignments;

    public void sort(int[] array) {
        resetMetrics();

        if (array == null || array.length <= 1) {
            return;
        }

        // Early check for already sorted array
        if (isAlreadySorted(array)) {
            return;
        }

        selectionSortWithOptimizations(array);
    }

    public PerformanceTracker sortWithMetrics(int[] array) {
        PerformanceTracker tracker = new PerformanceTracker();
        tracker.startTimer();

        resetMetrics();
        sort(array);

        tracker.stopTimer();
        tracker.setMetric("comparisons", comparisons);
        tracker.setMetric("swaps", swaps);
        tracker.setMetric("assignments", assignments);

        return tracker;
    }

    private void selectionSortWithOptimizations(int[] array) {
        int n = array.length;

        for (int i = 0; i < n - 1; i++) {
            int minIndex = i;

            for (int j = i + 1; j < n; j++) {
                comparisons++;
                if (array[j] < array[minIndex]) {
                    minIndex = j;
                }
            }

            if (minIndex != i) {
                swap(array, i, minIndex);
            }
        }
    }

    private void swap(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
        swaps++;
        assignments += 3;
    }

    private boolean isAlreadySorted(int[] array) {
        for (int i = 1; i < array.length; i++) {
            comparisons++;
            if (array[i] < array[i - 1]) {
                return false;
            }
        }
        return true;
    }

    public void resetMetrics() {
        comparisons = 0;
        swaps = 0;
        assignments = 0;
    }

    public long getComparisons() { return comparisons; }
    public long getSwaps() { return swaps; }
    public long getAssignments() { return assignments; }
}