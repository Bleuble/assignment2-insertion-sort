package algorithms;

public class InsertionSortTest {

    public static void main(String[] args) {
        testSort_EmptyArray();
        testSort_SingleElement();
        testSort_SortedArray();
        testSort_ReverseSortedArray();
        testSort_RandomArray();
        testSort_WithDuplicates();

        System.out.println("✅ All tests passed!");
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

    private static void assertSorted(int[] array) {
        for (int i = 1; i < array.length; i++) {
            if (array[i] < array[i - 1]) {
                throw new AssertionError("Array is not sorted at index " + i);
            }
        }
    }
}