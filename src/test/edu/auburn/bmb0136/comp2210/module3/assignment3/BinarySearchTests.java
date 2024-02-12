package edu.auburn.bmb0136.comp2210.module3.assignment3;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public final class BinarySearchTests {

    private static void assert_correctResult(int r, int t, Integer[] a, boolean last) {
        if (last) {
            assertEquals(r, BinarySearch.lastIndexOf(a, t, Integer::compareTo));
        } else {
            assertEquals(r, BinarySearch.firstIndexOf(a, t, Integer::compareTo));
        }
    }

    @Test
    public void firstIndexOf_throws_ifAnyNull() {
        assertThrows(NullPointerException.class, () -> System.out.println(BinarySearch.firstIndexOf(null, 0, Integer::compare)));
        assertThrows(NullPointerException.class, () -> System.out.println(BinarySearch.firstIndexOf(new Integer[0], null, Integer::compare)));
        assertThrows(NullPointerException.class, () -> System.out.println(BinarySearch.firstIndexOf(new Integer[0], 0, null)));
    }

    @Test
    public void lastIndexOf_throws_ifAnyNull() {
        assertThrows(NullPointerException.class, () -> System.out.println(BinarySearch.lastIndexOf(null, 0, Integer::compare)));
        assertThrows(NullPointerException.class, () -> System.out.println(BinarySearch.lastIndexOf(new Integer[0], null, Integer::compare)));
        assertThrows(NullPointerException.class, () -> System.out.println(BinarySearch.lastIndexOf(new Integer[0], 0, null)));
    }

    @Test
    public void firstIndexOf_givesNegativeOne_ifNone() {
        assert_correctResult(-1, 9999, new Integer[]{1, 2, 3, 4, 5}, false);
    }

    @Test
    public void firstIndexOf_givesIndex_ifOnlyOne() {
        assert_correctResult(1, 6, new Integer[]{0, 6, 9}, false);
    }

    @Test
    public void firstIndexOf_givesFirstIndex_ifOnlyDuplicates() {
        assert_correctResult(1, 2, new Integer[]{1, 2, 2, 2, 2, 2, 3}, false);
    }

    @Test
    public void lastIndexOf_givesNegativeOne_ifNone() {
        assert_correctResult(-1, 9999, new Integer[]{1, 2, 3, 4, 5}, true);
    }

    @Test
    public void lastIndexOf_givesIndex_ifOnlyOne() {
        assert_correctResult(1, 6, new Integer[]{0, 6, 9}, true);
    }

    @Test
    public void lastIndexOf_givesFirstIndex_ifOnlyDuplicates() {
        assert_correctResult(5, 2, new Integer[]{1, 2, 2, 2, 2, 2, 3}, true);
    }
}
