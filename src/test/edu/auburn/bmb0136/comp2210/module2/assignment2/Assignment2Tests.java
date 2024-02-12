package edu.auburn.bmb0136.comp2210.module2.assignment2;

import edu.auburn.bmb0136.comp2210.TestUtils;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

public final class Assignment2Tests {

    @Test
    public void min_throws_ifAnyNullOrEmpty() {
        TestUtils.<Collection<Integer>>assertThrows_ifNull(arg -> Assignment2.min(arg, Integer::compare));
        TestUtils.<Comparator<Integer>>assertThrows_ifNull(arg -> Assignment2.min(new ArrayList<>(), arg));
        TestUtils.<Integer>assertThrows_ifEmpty(arg -> Assignment2.min(arg, Integer::compare));
    }

    @Test
    public void max_throws_ifAnyNullOrEmpty() {
        TestUtils.<Collection<Integer>>assertThrows_ifNull(arg -> Assignment2.max(arg, Integer::compare));
        TestUtils.<Comparator<Integer>>assertThrows_ifNull(arg -> Assignment2.max(new ArrayList<>(), arg));
        TestUtils.<Integer>assertThrows_ifEmpty(arg -> Assignment2.max(arg, Integer::compare));
    }

    @Test
    public void kmin_throws_ifAnyNullOrEmpty() {
        TestUtils.<Collection<Integer>>assertThrows_ifNull(arg -> Assignment2.kmin(arg, 1, Integer::compare));
        TestUtils.<Comparator<Integer>>assertThrows_ifNull(arg -> Assignment2.kmin(new ArrayList<>(), 1, arg));
        TestUtils.<Integer>assertThrows_ifEmpty(arg -> Assignment2.kmin(arg, 1, Integer::compare));
    }

    @Test
    public void kmin_throws_ifNoKthMin() {
        assertThrows(NoSuchElementException.class, () -> Assignment2.kmin(Arrays.asList(1, 2, 3), 0, Integer::compare));
        assertThrows(NoSuchElementException.class, () -> Assignment2.kmin(Arrays.asList(1, 2, 3), 4, Integer::compare));
        assertThrows(NoSuchElementException.class, () -> Assignment2.kmin(Arrays.asList(1, 1, 1), 2, Integer::compare));
    }

    @Test
    public void kmax_throws_ifAnyNullOrEmpty() {
        TestUtils.<Collection<Integer>>assertThrows_ifNull(arg -> Assignment2.kmax(arg, 1, Integer::compare));
        TestUtils.<Comparator<Integer>>assertThrows_ifNull(arg -> Assignment2.kmax(new ArrayList<>(), 1, arg));
        TestUtils.<Integer>assertThrows_ifEmpty(arg -> Assignment2.kmax(arg, 1, Integer::compare));
    }

    @Test
    public void kmax_throws_ifNoKthMax() {
        assertThrows(NoSuchElementException.class, () -> Assignment2.kmax(Arrays.asList(1, 2, 3), 0, Integer::compare));
        assertThrows(NoSuchElementException.class, () -> Assignment2.kmax(Arrays.asList(1, 2, 3), 4, Integer::compare));
        assertThrows(NoSuchElementException.class, () -> Assignment2.kmax(Arrays.asList(1, 1, 1), 2, Integer::compare));
    }

    @Test
    public void range_throws_ifAnyNullOrEmpty() {
        TestUtils.<Collection<Integer>>assertThrows_ifNull(arg -> Assignment2.range(arg, 1, 2, Integer::compare));
        TestUtils.<Comparator<Integer>>assertThrows_ifNull(arg -> Assignment2.range(new ArrayList<>(), 1, 2, arg));
        TestUtils.<Integer>assertThrows_ifEmpty(arg -> Assignment2.range(arg, 1, 2, Integer::compare));
    }

    @Test
    public void range_throws_ifNoneFound() {
        assertThrows(NoSuchElementException.class, () -> Assignment2.range(Arrays.asList(1, 2, 3), 0, 0, Integer::compare));
    }

    @Test
    public void floor_throws_ifAnyNullOrEmpty() {
        TestUtils.<Collection<Integer>>assertThrows_ifNull(arg -> Assignment2.floor(arg, 1, Integer::compare));
        TestUtils.<Comparator<Integer>>assertThrows_ifNull(arg -> Assignment2.floor(new ArrayList<>(), 1, arg));
        TestUtils.<Integer>assertThrows_ifEmpty(arg -> Assignment2.floor(arg, 1, Integer::compare));
    }

    @Test
    public void floor_throws_ifNoneFound() {
        assertThrows(NoSuchElementException.class, () -> Assignment2.floor(Arrays.asList(1, 2, 3), 0, Integer::compare));
    }

    @Test
    public void ceiling_throws_ifAnyNullOrEmpty() {
        TestUtils.<Collection<Integer>>assertThrows_ifNull(arg -> Assignment2.ceiling(arg, 1, Integer::compare));
        TestUtils.<Comparator<Integer>>assertThrows_ifNull(arg -> Assignment2.ceiling(new ArrayList<>(), 1, arg));
        TestUtils.<Integer>assertThrows_ifEmpty(arg -> Assignment2.ceiling(arg, 1, Integer::compare));
    }

    @Test
    public void ceiling_throws_ifNoneFound() {
        assertThrows(NoSuchElementException.class, () -> Assignment2.ceiling(Arrays.asList(1, 2, 3), 9999, Integer::compare));
    }

    @Test
    public void min_returnsFirst_ifOneElement() {
        final int[] arr = {7};

        int result = Wrapper.min(arr);

        assertEquals(arr[0], result);
    }

    @Test
    public void min_returnsMinimum_positives() {
        final int[] arr = {0, 1};
        final int EXPECTED = 0;

        int result = Wrapper.min(arr);

        assertEquals(EXPECTED, result);
    }

    @Test
    public void min_returnsMinimum_negatives() {
        final int[] arr = {0, -1};
        final int EXPECTED = -1;

        int result = Wrapper.min(arr);

        assertEquals(EXPECTED, result);
    }

    @Test
    public void min_returnsMinimum_duplicates() {
        final int[] arr = {0, 0, -123, 0, 0};
        final int EXPECTED = -123;

        int result = Wrapper.min(arr);

        assertEquals(EXPECTED, result);
    }

    @Test
    public void max_returnsFirst_ifOneElement() {
        final int[] arr = {7};

        int result = Wrapper.max(arr);

        assertEquals(arr[0], result);
    }

    @Test
    public void max_returnsMaximum_positives() {
        final int[] arr = {0, 1};
        final int EXPECTED = 1;

        int result = Wrapper.max(arr);

        assertEquals(EXPECTED, result);
    }

    @Test
    public void min_returnsMaximum_negatives() {
        final int[] arr = {0, -1};
        final int EXPECTED = 0;

        int result = Wrapper.max(arr);

        assertEquals(EXPECTED, result);
    }

    @Test
    public void min_returnsMaximum_duplicates() {
        final int[] arr = {0, 0, 123, 0, 0};
        final int EXPECTED = 123;

        int result = Wrapper.max(arr);

        assertEquals(EXPECTED, result);
    }

    @Test
    public void range_leavesInputUnchanged() {
        TestUtils.assert_doesNotChange(new int[] {-3, -2, -1, 0, 1, 2, 3}, arr -> Wrapper.range(arr, Integer.MIN_VALUE, Integer.MAX_VALUE));
    }

    @Test
    public void range_removesValuesLessThanLow() {
        final int[] arr = {-3, -2, -1, 0, 1, 2, 3};
        final int[] EXPECTED = {0, 1, 2, 3};
        final int LOW = 0;

        int[] result = Wrapper.range(arr, LOW, Integer.MAX_VALUE);

        // Order does not matter
        Arrays.sort(result);
        Arrays.sort(EXPECTED);

        assertArrayEquals(EXPECTED, result);
    }

    @Test
    public void range_removesValuesGreaterThanHigh() {
        final int[] arr = {-3, -2, -1, 0, 1, 2, 3};
        final int[] EXPECTED = {-3, -2, -1, 0};
        final int HIGH = 0;

        int[] result = Wrapper.range(arr, Integer.MIN_VALUE, HIGH);

        // Order does not matter
        Arrays.sort(result);
        Arrays.sort(EXPECTED);

        assertArrayEquals(EXPECTED, result);
    }

    @Test
    public void range_keepsDuplicates() {
        final int[] arr = {0, 1, 2, 2, 3, 4, 5};
        final int[] EXPECTED = {1, 2, 2, 3};
        final int LOW = 1;
        final int HIGH = 3;

        int[] result = Wrapper.range(arr, LOW, HIGH);

        // Order does not matter
        Arrays.sort(result);
        Arrays.sort(EXPECTED);

        assertArrayEquals(EXPECTED, result);
    }

    @Test
    public void floor_returnsRightMax() {
        final int[] arr = {1, 9999};
        final int KEY = 10;
        final int EXPECTED = 1;

        int result = Wrapper.floor(arr, KEY);

        assertEquals(EXPECTED, result);
    }

    @Test
    public void floor_accountsForSign() {
        final int[] arr = {-20, 10};
        final int KEY = 100;
        final int EXPECTED = 10;

        int result = Wrapper.floor(arr, KEY);

        assertEquals(EXPECTED, result);
    }

    @Test
    public void ceiling_returnsRightMin() {
        final int[] arr = {-1, -9999};
        final int KEY = -10;
        final int EXPECTED = -1;

        int result = Wrapper.ceiling(arr, KEY);

        assertEquals(EXPECTED, result);
    }

    @Test
    public void ceiling_accountsForSign() {
        final int[] arr = {-20, 10};
        final int KEY = 0;
        final int EXPECTED = 10;

        int result = Wrapper.ceiling(arr, KEY);

        assertEquals(EXPECTED, result);
    }

    @Test
    public void kmin_returnsGlobalMin_whenKIs1() {
        final int[] arr = {-2, 1, 0, 1, 2};
        final int K = 1;
        final int EXPECTED = -2;

        int result = Wrapper.kmin(arr, K);

        assertEquals(EXPECTED, result);
    }

    @Test
    public void kmin_returnsCorrectMin_ifDuplicates() {
        final int[] arr = {1, 2, 2, 2, 2, 2, 3};
        final int K = 3;
        final int EXPECTED = 3;

        int result = Wrapper.kmin(arr, K);

        assertEquals(EXPECTED, result);
    }

    @Test
    public void kmax_returnsGlobalMin_whenKIs1() {
        final int[] arr = {-2, 1, 0, 1, 2};
        final int K = 1;
        final int EXPECTED = 2;

        int result = Wrapper.kmax(arr, K);

        assertEquals(EXPECTED, result);
    }

    @Test
    public void kmax_returnsCorrectMin_ifDuplicates() {
        final int[] arr = {1, 2, 2, 2, 2, 2, 3};
        final int K = 3;
        final int EXPECTED = 1;

        int result = Wrapper.kmax(arr, K);

        assertEquals(EXPECTED, result);
    }

    private static class Wrapper {

        private static List<Integer> toList(int[] arr) {
            ArrayList<Integer> list = new ArrayList<>();
            for (int i : arr) {
                list.add(i);
            }
            return list;
        }

        public static int min(int[] arr) {
            return Assignment2.min(toList(arr), Integer::compare);
        }

        public static int max(int[] arr) {
            return Assignment2.max(toList(arr), Integer::compare);
        }

        public static int kmin(int[] arr, int k) {
            return Assignment2.kmin(toList(arr), k, Integer::compare);
        }

        public static int kmax(int[] arr, int k) {
            return Assignment2.kmax(toList(arr), k, Integer::compare);
        }

        public static int[] range(int[] arr, int low, int high) {
            ArrayList<Integer> list = new ArrayList<>(Assignment2.range(toList(arr), low, high, Integer::compare));
            int[] res = new int[list.size()];
            for (int i = 0; i < list.size(); i++) {
                res[i] = list.get(i);
            }
            return res;
        }

        public static int floor(int[] arr, int key) {
            return Assignment2.floor(toList(arr), key, Integer::compare);
        }

        public static int ceiling(int[] arr, int key) {
            return Assignment2.ceiling(toList(arr), key, Integer::compare);
        }
    }
}
