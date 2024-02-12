package edu.auburn.bmb0136.comp2210.module4;

import edu.auburn.bmb0136.comp2210.TestUtils;
import edu.auburn.bmb0136.comp2210.module4.assignment4.LinkedSet;
import edu.auburn.bmb0136.comp2210.module4.assignment4.Set;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.*;

public class SetTests {
    private static Set<Integer> createSet() {
        return new LinkedSet<>();
    }

    @Test
    public void add_notIn_returnsTrue() {
        final int X = 5;
        Set<Integer> set = createSet();

        boolean result = set.add(X);

        assertTrue(result);
    }

    @Test
    public void add_alreadyIn_returnsFalse() {
        final int X = 5;
        Set<Integer> set = createSet();

        set.add(X);
        boolean result = set.add(X);

        assertFalse(result);
    }

    @Test
    public void add_null_returnsFalse() {
        Set<Integer> set = createSet();

        boolean result = set.add(null);

        assertFalse(result);
    }

    @Test
    public void remove_notIn_returnsFalse() {
        final int X = 5;
        Set<Integer> set = createSet();

        boolean result = set.remove(X);

        assertFalse(result);
    }

    @Test
    public void remove_alreadyIn_returnsTrue() {
        final int X = 5;
        Set<Integer> set = createSet();

        set.add(X);
        boolean result = set.remove(X);

        assertTrue(result);
    }

    @Test
    public void contains_notExists_returnsFalse() {
        final int X = 5;
        Set<Integer> set = createSet();

        boolean result = set.contains(X);

        assertFalse(result);
    }

    @Test
    public void contains_exists_returnsTrue() {
        final int X = 5;
        Set<Integer> set = createSet();

        set.add(X);
        boolean result = set.contains(X);

        assertTrue(result);
    }

    @Test
    public void size_initially_isZero() {
        Set<Integer> set = createSet();

        int result = set.size();

        assertEquals(0, result);
    }

    @Test
    public void size_onAdd_increments() {
        Set<Integer> set = createSet();

        set.add(69);
        int result = set.size();

        assertEquals(1, result);
    }

    @Test
    public void size_onRemoved_decrements() {
        Set<Integer> set = createSet();

        set.add(69);
        set.remove(69);
        int result = set.size();

        assertEquals(0, result);
    }

    @Test
    public void isEmpty_initially_isTrue() {
        Set<Integer> set = createSet();

        boolean result = set.isEmpty();

        assertTrue(result);
    }

    @Test
    public void isEmpty_anyAdded_isFalse() {
        Set<Integer> set = createSet();

        set.add(69);
        boolean result = set.isEmpty();

        assertFalse(result);
    }

    @Test
    public void isEmpty_allRemoved_isTrue() {
        Set<Integer> set = createSet();

        set.add(69);
        set.remove(69);
        boolean result = set.isEmpty();

        assertTrue(result);
    }

    @Test
    public void equals_differentSizes_returnsFalse() {
        Set<Integer> set1 = createSet();
        set1.add(1);
        set1.add(2);
        Set<Integer> set2 = createSet();
        set2.add(3);

        boolean result = set1.equals(set2);

        assertFalse(result);
    }

    @Test
    public void equals_differentElements_returnsFalse() {
        Set<Integer> set1 = createSet();
        set1.add(1);
        Set<Integer> set2 = createSet();
        set2.add(2);

        boolean result = set1.equals(set2);

        assertFalse(result);
    }

    @Test
    public void equals_differentOrder_returnsTrue() {
        Set<Integer> set1 = createSet();
        set1.add(1);
        set1.add(2);
        Set<Integer> set2 = createSet();
        set2.add(2);
        set2.add(1);

        boolean result = set1.equals(set2);

        assertTrue(result);
    }

    @Test
    public void union_discardsDuplicates() {
        Set<Integer> set1 = createSet();
        set1.add(1);
        Set<Integer> set2 = createSet();
        set2.add(1);

        Set<Integer> result = set1.union(set2);

        assertIterableEquals(Arrays.asList(1), result);
    }

    @Test
    public void union_containsAll() {
        Set<Integer> set1 = createSet();
        set1.add(1);
        Set<Integer> set2 = createSet();
        set2.add(2);

        Set<Integer> result = set1.union(set2);

        ArrayList<Integer> sorted = new ArrayList<>();
        result.iterator().forEachRemaining(sorted::add);
        Collections.sort(sorted);
        assertIterableEquals(Arrays.asList(1, 2), sorted);
    }

    @Test
    public void intersection_keepsOnlyDuplicates() {
        Set<Integer> set1 = createSet();
        set1.add(1);
        set1.add(2);
        set1.add(3);
        set1.add(4);
        Set<Integer> set2 = createSet();
        set2.add(1);
        set2.add(2);
        set2.add(10);

        Set<Integer> result = set1.intersection(set2);

        ArrayList<Integer> sorted = new ArrayList<>();
        result.iterator().forEachRemaining(sorted::add);
        Collections.sort(sorted);
        assertIterableEquals(Arrays.asList(1, 2), sorted);
    }

    @Test
    public void complement_hasAll_ifEmpty() {
        Set<Integer> set = createSet();
        set.add(1);
        set.add(2);
        set.add(3);

        Set<Integer> result = set.complement(createSet());

        ArrayList<Integer> sorted = new ArrayList<>();
        result.iterator().forEachRemaining(sorted::add);
        Collections.sort(sorted);
        assertIterableEquals(Arrays.asList(1, 2, 3), sorted);
    }

    @Test
    public void complement_hasNone_ifEqual() {
        Set<Integer> set = createSet();
        set.add(1);
        set.add(2);
        set.add(3);

        Set<Integer> result = set.complement(set);

        ArrayList<Integer> sorted = new ArrayList<>();
        result.iterator().forEachRemaining(sorted::add);
        Collections.sort(sorted);
        assertIterableEquals(Arrays.asList(), sorted);
    }
    @Test
    public void complement_excludesProperly() {
        Set<Integer> set1 = createSet();
        set1.add(1);
        set1.add(2);
        set1.add(3);
        Set<Integer> set2 = createSet();
        set2.add(2);
        set2.add(4);

        Set<Integer> result = set1.complement(set2);

        ArrayList<Integer> sorted = new ArrayList<>();
        result.iterator().forEachRemaining(sorted::add);
        Collections.sort(sorted);
        assertIterableEquals(Arrays.asList(1, 3), sorted);
    }

    @Test
    public void iterator_nonNull() {
        Set<Integer> set = createSet();

        Iterator<Integer> result = set.iterator();

        assertNotNull(result);
    }

    @Test
    public void iterator_getsAll() {
        Set<Integer> set = createSet();
        set.add(1);
        set.add(2);
        set.add(3);
        set.add(4);
        set.add(5);

        Iterator<Integer> result = set.iterator();

        ArrayList<Integer> sorted = new ArrayList<>();
        result.forEachRemaining(sorted::add);
        Collections.sort(sorted);
        assertIterableEquals(Arrays.asList(1, 2, 3, 4, 5), sorted);
    }

    @Test
    public void iterator_isO1() {
        TestUtils.assert_polyTimeEquals(n -> {
            Set<Integer> s = new LinkedSet<>();
            for (int i = 0; i < n; i++) {
                s.add(i);
            }
            return s;
        }, Set::iterator, 10000, 0);
    }
}
