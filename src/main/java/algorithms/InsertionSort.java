package algorithms;

import metrics.PerformanceTracker;

public class InsertionSort {
    private long comparisons;
    private long assignments;
    private long arrayCopies;

    public void sort(int[] array) {
        resetMetrics();

        if (array == null || array.length <= 1) {
            return;
        }

        if (isNearlySorted(array)) {
            optimizedInsertionSort(array);
        } else {
            binaryInsertionSort(array);
        }
    }

    public PerformanceTracker sortWithMetrics(int[] array) {
        PerformanceTracker tracker = new PerformanceTracker();
        tracker.startTimer();

        resetMetrics();
        sort(array);

        tracker.stopTimer();
        tracker.setMetric("comparisons", comparisons);
        tracker.setMetric("assignments", assignments);
        tracker.setMetric("arrayCopies", arrayCopies);

        return tracker;
    }

    private void binaryInsertionSort(int[] array) {
        for (int i = 1; i < array.length; i++) {
            int key = array[i];
            assignments++;

            int pos = binarySearchPosition(array, 0, i - 1, key);

            if (i - pos > 0) {
                System.arraycopy(array, pos, array, pos + 1, i - pos);
                arrayCopies++;
                assignments += (i - pos);
            }

            array[pos] = key;
            assignments++;
        }
    }

    private void optimizedInsertionSort(int[] array) {
        for (int i = 1; i < array.length; i++) {
            int key = array[i];
            assignments++;
            int j = i - 1;

            while (j >= 0 && array[j] > key) {
                comparisons++;
                array[j + 1] = array[j];
                assignments++;
                j--;
            }
            if (j >= 0) comparisons++;

            array[j + 1] = key;
            assignments++;
        }
    }

    private int binarySearchPosition(int[] array, int low, int high, int key) {
        while (low <= high) {
            int mid = low + (high - low) / 2;
            comparisons++;

            if (array[mid] == key) {
                return mid + 1;
            } else if (array[mid] < key) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return low;
    }

    private boolean isNearlySorted(int[] array) {
        int outOfOrder = 0;
        for (int i = 1; i < array.length; i++) {
            comparisons++;
            if (array[i] < array[i - 1]) {
                outOfOrder++;
            }
            if (outOfOrder > array.length / 10) {
                return false;
            }
        }
        return true;
    }

    public long getComparisons() { return comparisons; }
    public long getAssignments() { return assignments; }
    public long getArrayCopies() { return arrayCopies; }
    public void resetMetrics() {
        comparisons = 0;
        assignments = 0;
        arrayCopies = 0;
    }
}