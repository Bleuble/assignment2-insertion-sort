package algorithms;

import java.util.Random;
import java.util.Arrays;

public class InsertionSortTest {

    public static void main(String[] args) {
        testSort_EmptyArray();
        testSort_SingleElement();
        testSort_SortedArray();
        testSort_ReverseSortedArray();
        testSort_RandomArray();
        testSort_WithDuplicates();
        testPropertyBased();
        testCrossValidation();

        System.out.println("✅ All Insertion Sort tests passed!");
    }

    public static void testSort_EmptyArray() {
        InsertionSort sorter = new InsertionSort();
        int[] array = {};
        sorter.sort(array);
        System.out.println("✓ Empty array test passed");
    }

    public static void testSort_SingleElement() {
        InsertionSort sorter = new InsertionSort();
        int[] array = {5};
        sorter.sort(array);
        assertSorted(array);
        System.out.println("✓ Single element test passed");
    }

    public static void testSort_SortedArray() {
        InsertionSort sorter = new InsertionSort();
        int[] array = {1, 2, 3, 4, 5};
        sorter.sort(array);
        assertSorted(array);
        System.out.println("✓ Sorted array test passed");
    }

    public static void testSort_ReverseSortedArray() {
        InsertionSort sorter = new InsertionSort();
        int[] array = {5, 4, 3, 2, 1};
        sorter.sort(array);
        assertSorted(array);
        System.out.println("✓ Reverse sorted array test passed");
    }

    public static void testSort_RandomArray() {
        InsertionSort sorter = new InsertionSort();
        int[] array = {3, 1, 4, 1, 5, 9, 2, 6};
        sorter.sort(array);
        assertSorted(array);
        System.out.println("✓ Random array test passed");
    }

    public static void testSort_WithDuplicates() {
        InsertionSort sorter = new InsertionSort();
        int[] array = {4, 2, 2, 8, 3, 3, 1};
        sorter.sort(array);
        assertSorted(array);
        System.out.println("✓ Duplicates array test passed");
    }

    public static void testPropertyBased() {
        Random random = new Random();
        for (int test = 0; test < 100; test++) {
            int size = random.nextInt(1000) + 1;
            int[] array = new int[size];
            for (int i = 0; i < size; i++) {
                array[i] = random.nextInt(10000);
            }

            InsertionSort sorter = new InsertionSort();
            int[] original = array.clone();
            sorter.sort(array);

            assertSorted(array);
            assertSameElements(original, array);
        }
        System.out.println("✓ Property-based testing passed (100 tests)");
    }

    public static void testCrossValidation() {
        Random random = new Random();
        for (int test = 0; test < 50; test++) {
            int size = random.nextInt(500) + 1;
            int[] array = new int[size];
            for (int i = 0; i < size; i++) {
                array[i] = random.nextInt(1000);
            }

            int[] array1 = array.clone();
            int[] array2 = array.clone();

            InsertionSort sorter = new InsertionSort();
            sorter.sort(array1);

            Arrays.sort(array2);

            if (!Arrays.equals(array1, array2)) {
                throw new AssertionError("Cross-validation failed: arrays not equal");
            }
        }
        System.out.println("✓ Cross-validation with Java built-in sort passed (50 tests)");
    }

    private static void assertSorted(int[] array) {
        for (int i = 1; i < array.length; i++) {
            if (array[i] < array[i - 1]) {
                throw new AssertionError("Array is not sorted at index " + i);
            }
        }
    }

    private static void assertSameElements(int[] original, int[] sorted) {
        int[] countOriginal = new int[10001];
        int[] countSorted = new int[10001];

        for (int value : original) {
            if (value >= 0 && value <= 10000) {
                countOriginal[value]++;
            }
        }
        for (int value : sorted) {
            if (value >= 0 && value <= 10000) {
                countSorted[value]++;
            }
        }

        if (!Arrays.equals(countOriginal, countSorted)) {
            throw new AssertionError("Arrays have different elements");
        }
    }
}