package edu.auburn.bmb0136.comp2210.module1.assignment1;

import edu.auburn.bmb0136.comp2210.TestUtils;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

public final class Assignment1Tests {

    @Test
    public void min_throws_ifNullOrEmptyArray() {
        TestUtils.assertThrows_ifNullOrEmptyIntArray(Assignment1::min);
    }

    @Test
    public void min_returnsFirst_ifOneElement() {
        final int[] arr = {7};

        int result = Assignment1.min(arr);

        assertEquals(arr[0], result);
    }

    @Test
    public void min_returnsMinimum_positives() {
        final int[] arr = {0, 1};
        final int EXPECTED = 0;

        int result = Assignment1.min(arr);

        assertEquals(EXPECTED, result);
    }

    @Test
    public void min_returnsMinimum_negatives() {
        final int[] arr = {0, -1};
        final int EXPECTED = -1;

        int result = Assignment1.min(arr);

        assertEquals(EXPECTED, result);
    }

    @Test
    public void min_returnsMinimum_duplicates() {
        final int[] arr = {0, 0, -123, 0, 0};
        final int EXPECTED = -123;

        int result = Assignment1.min(arr);

        assertEquals(EXPECTED, result);
    }

    @Test
    public void max_throws_ifNullOrEmptyArray() {
        TestUtils.assertThrows_ifNullOrEmptyIntArray(Assignment1::min);
    }

    @Test
    public void max_returnsFirst_ifOneElement() {
        final int[] arr = {7};

        int result = Assignment1.max(arr);

        assertEquals(arr[0], result);
    }

    @Test
    public void max_returnsMaximum_positives() {
        final int[] arr = {0, 1};
        final int EXPECTED = 1;

        int result = Assignment1.max(arr);

        assertEquals(EXPECTED, result);
    }

    @Test
    public void min_returnsMaximum_negatives() {
        final int[] arr = {0, -1};
        final int EXPECTED = 0;

        int result = Assignment1.max(arr);

        assertEquals(EXPECTED, result);
    }

    @Test
    public void min_returnsMaximum_duplicates() {
        final int[] arr = {0, 0, 123, 0, 0};
        final int EXPECTED = 123;

        int result = Assignment1.max(arr);

        assertEquals(EXPECTED, result);
    }

    @Test
    public void range_throws_IfNullOrEmpty() {
        TestUtils.assertThrows_ifNullOrEmptyIntArray(arr -> Assignment1.range(arr, 0, 0));
    }

    @Test
    public void range_leavesInputUnchanged() {
        TestUtils.assert_doesNotChange(new int[] {-3, -2, -1, 0, 1, 2, 3}, arr -> Assignment1.range(arr, Integer.MIN_VALUE, Integer.MAX_VALUE));
    }

    @Test
    public void range_removesValuesLessThanLow() {
        final int[] arr = {-3, -2, -1, 0, 1, 2, 3};
        final int[] EXPECTED = {0, 1, 2, 3};
        final int LOW = 0;

        int[] result = Assignment1.range(arr, LOW, Integer.MAX_VALUE);

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

        int[] result = Assignment1.range(arr, Integer.MIN_VALUE, HIGH);

        // Order does not matter
        Arrays.sort(result);
        Arrays.sort(EXPECTED);

        assertArrayEquals(EXPECTED, result);
    }

    @Test
    public void range_returnsEmptyArray_ifNoneMatch() {
        final int[] arr = {-3, -2, -1, 0, 1, 2, 3};
        final int[] EXPECTED = {};
        final int LOW = 100;
        final int HIGH = 200;

        int[] result = Assignment1.range(arr, LOW, HIGH);

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

        int[] result = Assignment1.range(arr, LOW, HIGH);

        // Order does not matter
        Arrays.sort(result);
        Arrays.sort(EXPECTED);

        assertArrayEquals(EXPECTED, result);
    }

    @Test
    public void floor_throws_ifNullOrEmpty() {
        TestUtils.assertThrows_ifNullOrEmptyIntArray(arr -> Assignment1.floor(arr, 0));
    }

    @Test
    public void floor_throws_ifNoValue() {
        final int[] arr = {1, 2, 3};
        final int KEY = -999;
        assertThrows(IllegalArgumentException.class, () -> Assignment1.floor(arr, KEY));
    }

    @Test
    public void floor_returnsRightMax() {
        final int[] arr = {1, 9999};
        final int KEY = 10;
        final int EXPECTED = 1;

        int result = Assignment1.floor(arr, KEY);

        assertEquals(EXPECTED, result);
    }

    @Test
    public void floor_accountsForSign() {
        final int[] arr = {-20, 10};
        final int KEY = 100;
        final int EXPECTED = 10;

        int result = Assignment1.floor(arr, KEY);

        assertEquals(EXPECTED, result);
    }

    @Test
    public void ceiling_throws_ifNullOrEmpty() {
        TestUtils.assertThrows_ifNullOrEmptyIntArray(arr -> Assignment1.ceiling(arr, 0));
    }

    @Test
    public void ceiling_throws_ifNoValue() {
        final int[] arr = {1, 2, 3};
        final int KEY = 999;
        assertThrows(IllegalArgumentException.class, () -> Assignment1.ceiling(arr, KEY));
    }

    @Test
    public void ceiling_returnsRightMin() {
        final int[] arr = {-1, -9999};
        final int KEY = -10;
        final int EXPECTED = -1;

        int result = Assignment1.ceiling(arr, KEY);

        assertEquals(EXPECTED, result);
    }

    @Test
    public void ceiling_accountsForSign() {
        final int[] arr = {-20, 10};
        final int KEY = 0;
        final int EXPECTED = 10;

        int result = Assignment1.ceiling(arr, KEY);

        assertEquals(EXPECTED, result);
    }

    @Test
    public void kmin_throws_ifNullOrEmpty() {
        TestUtils.assertThrows_ifNullOrEmptyIntArray(arr -> Assignment1.kmin(arr, 1));
    }

    @Test
    public void kmin_throws_kLessThan1() {
        assertThrows(IllegalArgumentException.class, () -> Assignment1.kmin(new int[5], 0));
    }

    @Test
    public void kmin_throws_kLargerThanArray() {
        assertThrows(IllegalArgumentException.class, () -> Assignment1.kmin(new int[5], 6));
    }

    @Test
    public void kmin_returnsGlobalMin_whenKIs1() {
        final int[] arr = {-2, 1, 0, 1, 2};
        final int K = 1;
        final int EXPECTED = -2;

        int result = Assignment1.kmin(arr, K);

        assertEquals(EXPECTED, result);
    }

    @Test
    public void kmin_returnsCorrectMin_ifDuplicates() {
        final int[] arr = {1, 2, 2, 2, 2, 2, 3};
        final int K = 3;
        final int EXPECTED = 3;

        int result = Assignment1.kmin(arr, K);

        assertEquals(EXPECTED, result);
    }

    @Test
    public void kmax_throws_ifNullOrEmpty() {
        TestUtils.assertThrows_ifNullOrEmptyIntArray(arr -> Assignment1.kmax(arr, 1));
    }

    @Test
    public void kmax_throws_kLessThan1() {
        assertThrows(IllegalArgumentException.class, () -> Assignment1.kmax(new int[5], 0));
    }

    @Test
    public void kmax_throws_kLargerThanArray() {
        assertThrows(IllegalArgumentException.class, () -> Assignment1.kmax(new int[5], 6));
    }

    @Test
    public void kmax_returnsGlobalMin_whenKIs1() {
        final int[] arr = {-2, 1, 0, 1, 2};
        final int K = 1;
        final int EXPECTED = 2;

        int result = Assignment1.kmax(arr, K);

        assertEquals(EXPECTED, result);
    }

    @Test
    public void kmax_returnsCorrectMin_ifDuplicates() {
        final int[] arr = {1, 2, 2, 2, 2, 2, 3};
        final int K = 3;
        final int EXPECTED = 1;

        int result = Assignment1.kmax(arr, K);

        assertEquals(EXPECTED, result);
    }
}
