package edu.auburn.bmb0136.comp2210.module4;

import edu.auburn.bmb0136.comp2210.module4.assignment4.LinkedSet;
import edu.auburn.bmb0136.comp2210.module4.assignment4.Set;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

// Tests that failed in vocareum so I copied them
public class StolenVocareumTests {

    @Test
    public void testAdd() {
        LinkedSet<Integer> set = new LinkedSet<>();
        boolean r1 = set.add(4);
        boolean r2 = set.add(1);
        boolean r3 = set.add(5);
        boolean r4 = set.add(3);
        boolean r5 = set.add(1);
        boolean r6 = set.add(2);

        assertTrue(r1);
        assertTrue(r2);
        assertTrue(r3);
        assertTrue(r4);
        assertFalse(r5);
        assertTrue(r6);

        assertIterableEquals(Arrays.asList(1, 2, 3, 4, 5), set);
    }

    @Test
    public void iteratorWhenEmpty() {
        assertFalse(new LinkedSet<Integer>().iterator().hasNext());
        assertFalse(new LinkedSet<Integer>().descendingIterator().hasNext());
    }

    @Test
    public void testLinkedSetUnion() {
        LinkedSet<Integer> s1 = new LinkedSet<>();
        assertTrue(s1.add(1));
        assertTrue(s1.add(2));
        assertTrue(s1.add(3));
        LinkedSet<Integer> s2 = new LinkedSet<>();
        assertTrue(s2.add(6));
        assertTrue(s2.add(4));
        assertTrue(s2.add(5));

        Set<Integer> res = s1.union(s2);

        assertIterableEquals(Arrays.asList(1, 2, 3, 4, 5, 6), res);
    }

    @Test
    public void testContains() {
        Set<Integer> set = new LinkedSet<>();

        System.out.println();
        assertFalse(set.contains(4), "contains(4) before add: " + set);
        set.add(4);
        assertTrue(set.contains(4), "contains(4) after add: " + set);
        set.add(1);
        set.add(5);
        set.add(3);
        set.add(1);
        set.add(2);
        assertTrue(set.contains(3), "contains(3) after many adds: " + set);
        set.remove(4);
        assertFalse(set.contains(4), "contains(4) after remove: " + set);
        set.remove(1);
        set.remove(5);
        assertFalse(set.contains(5), "contains(5) after double remove: " + set);

        assertIterableEquals(Arrays.asList(2, 3), set);
    }

    @Test
    public void coreMethodsStructuralTest() {
        // Test strategy:
        // 1. add 100 values
        // 2. remove all 100 values
        // 3. leaving the set empty.
        // 4. add 100 values and then
        // 5. attempt to add the same 100 values again.
        // The list structure is verified at each step
        Set<Integer> set = new LinkedSet<>();
        for (int i = 0; i < 100; i++) {
            assertTrue(set.add(i), "Part 1: add " + i);
            assertTrue(set.contains(i), "Part 1: should have " + i);
            assertEquals(i + 1, set.size(), "Part 1: size should be " + (i + 1));
            verify(set, "Part 1 verification > " + set);
        }
        assertEquals(100, set.size());

        for (int i = 0; i < 100; i++) {
            assertTrue(set.remove(i), "Part 2: remove " + i);
            assertFalse(set.contains(i), "Part 2: should not have " + i);
            assertEquals(100 - i - 1, set.size(), "Part 2: size should be " + (100 - i - 1));
            verify(set, "Part 2 verification > " + set);
        }

        assertEquals(0, set.size());
        assertTrue(set.isEmpty(), "Part 3: empty");
        verify(set, "Part 3 verification > " + set);

        for (int i = 0; i < 100; i++) {
            int j = i + 100;
            assertTrue(set.add(j), "Part 4: add " + j);
            assertTrue(set.contains(j), "Part 4: should have " + j);
            assertEquals(i + 1, set.size(), "Part 4: size should be " + (i + 1));
            verify(set, "Part 4 verification > " + set);
        }
        assertEquals(100, set.size());


        for (int i = 100; i < 200; i++) {
            assertFalse(set.add(i), "Part 5: re-add " + i);
            assertEquals(100, set.size(), "Part 5: size should be 100");
            verify(set, "Part 5 verification > " + set);
        }
        assertEquals(100, set.size());

        verify(set, "End verification > " + set);
    }

    @Test
    public void testRemove() {
        LinkedSet<Integer> set = new LinkedSet<>();

        assertTrue(set.add(1), "Add 1");
        assertEquals(1, set.size(), "Added 1");
        assertFalse(set.remove(2), "Remove 2");
        assertEquals(1, set.size(), "Should not change");
        assertTrue(set.remove(1), "Remove 1");
        assertEquals(0, set.size(), "Removed 1");

        assertNull(getFront(set), "front");
        assertNull(getRear(set), "rear");
        assertIterableEquals(Arrays.asList(), set, "iterator equals");
        assertTrue(set.isEmpty(), "isEmpty");
    }

    @Test
    public void testSetUnion() {
        Set<Integer> s1 = new LinkedSet<>();
        s1.add(1);
        s1.add(2);
        s1.add(3);
        Set<Integer> s2 = new LinkedSet<>();
        s2.add(3);
        s2.add(4);
        s2.add(2);

        Set<Integer> res = s1.union(s2);

        assertIterableEquals(Arrays.asList(1, 2, 3, 4), res);
    }

    private static void verify(Set<?> set, String message) {
        if (set.isEmpty()) {
            assertNull(getFront(set), "isEmpty > Empty means null front > " + message);
            assertNull(getRear(set), "isEmpty > Empty means null rear > " + message);
            assertEquals(0, set.size(), "isEmpty > Empty means 0 size > " + message);
        }
        //noinspection SizeReplaceableByIsEmpty
        if (set.size() == 0) {
            assertNull(getFront(set), "s == 0 > Empty means null front > " + message);
            assertNull(getRear(set), "s == 0 > Empty means null rear > " + message);
            assertTrue(set.isEmpty(), "s == 0 > Empty means isEmpty() == true > " + message);
        }

        Object n = getFront(set);
        int i = 0;
        while (n != null) {
            boolean hasPrev = i > 0;
            boolean hasNext = i < set.size() - 1;
            assertEquals(hasPrev, getPrev(n) != null, "Should have prev? > " + hasPrev + " > " + message);
            assertEquals(hasNext, getNext(n) != null, "Should have next? > " + hasNext + " > " + message);
            assertNotNull(getValue(n), "Null value");

            if (hasPrev) {
                assertSame(n, getNext(getPrev(n)), "n should be n.prev.next");
            }
            if (hasNext) {
                assertSame(n, getPrev(getNext(n)), "n should be n.next.prev");
            }

            n = getNext(n);
            i++;
        }
        assertEquals(set.size(), i, "# nodes found != set size");
    }

    private static Object getNext(Object n) {
        try {
            Field f = n.getClass().getDeclaredField("next");
            f.setAccessible(true);
            if (!f.isAccessible()) {
                throw new RuntimeException("Failed to set accessible");
            }
            return f.get(n);
        } catch (Throwable e) {
            throw new RuntimeException("REFLECTION BROKE!", e);
        }
    }

    private static Object getValue(Object n) {
        try {
            Field f = n.getClass().getDeclaredField("element");
            f.setAccessible(true);
            if (!f.isAccessible()) {
                throw new RuntimeException("Failed to set accessible");
            }
            return f.get(n);
        } catch (Throwable e) {
            throw new RuntimeException("REFLECTION BROKE!", e);
        }
    }

    private static Object getPrev(Object n) {
        try {
            Field f = n.getClass().getDeclaredField("prev");
            f.setAccessible(true);
            if (!f.isAccessible()) {
                throw new RuntimeException("Failed to set accessible");
            }
            return f.get(n);
        } catch (Throwable e) {
            throw new RuntimeException("REFLECTION BROKE!", e);
        }
    }

    private static Object getFront(Object set) {
        try {
            Field f = set.getClass().getDeclaredField("front");
            f.setAccessible(true);
            if (!f.isAccessible()) {
                throw new RuntimeException("Failed to set accessible");
            }
            return f.get(set);
        } catch (Throwable e) {
            throw new RuntimeException("REFLECTION BROKE!", e);
        }
    }

    private static Object getRear(Object set) {
        try {
            Field f = set.getClass().getDeclaredField("rear");
            f.setAccessible(true);
            if (!f.isAccessible()) {
                throw new RuntimeException("Failed to set accessible");
            }
            return f.get(set);
        } catch (Throwable e) {
            throw new RuntimeException("REFLECTION BROKE!", e);
        }
    }
}
