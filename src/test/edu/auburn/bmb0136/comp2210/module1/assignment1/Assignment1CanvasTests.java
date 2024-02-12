package edu.auburn.bmb0136.comp2210.module1.assignment1;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

// Tests from canvas assignment
public final class Assignment1CanvasTests {
    @Test
    public void min_canvasTests() {
        assertEquals(2, Assignment1.min(new int[] {2, 8, 7, 3, 4}));
        assertEquals(1, Assignment1.min(new int[] {5, 9, 1, 7, 3}));
        assertEquals(4, Assignment1.min(new int[] {8, 7, 6, 5, 4}));
        assertEquals(2, Assignment1.min(new int[] {8, 2, 8, 7, 3, 3, 4}));
    }

    @Test
    public void max_canvasTests() {
        assertEquals(9, Assignment1.max(new int[] {5, 9, 1, 7, 3}));
        assertEquals(8, Assignment1.max(new int[] {2, 8, 7, 3, 4}));
        assertEquals(8, Assignment1.max(new int[] {8, 7, 6, 5, 4}));
        assertEquals(8, Assignment1.max(new int[] {8, 2, 8, 7, 3, 3, 4}));
    }

    @Test
    public void kmin_canvasTests() {
        assertEquals(2, Assignment1.kmin(new int[] {2, 8, 7, 3, 4}, 1));
        assertEquals(5, Assignment1.kmin(new int[] {5, 9, 1, 7, 3}, 3));
        assertEquals(8, Assignment1.kmin(new int[] {8, 7, 6, 5, 4}, 5));
        assertEquals(4, Assignment1.kmin(new int[] {8, 2, 8, 7, 3, 3, 4}, 3));
    }

    @Test
    public void kmax_canvasTests() {
        assertEquals(8, Assignment1.kmax(new int[] {2, 8, 7, 3, 4}, 1));
        assertEquals(5, Assignment1.kmax(new int[] {5, 9, 1, 7, 3}, 3));
        assertEquals(4, Assignment1.kmax(new int[] {8, 7, 6, 5, 4}, 5));
        assertEquals(4, Assignment1.kmax(new int[] {8, 2, 8, 7, 3, 3, 4}, 3));
    }

    @Test
    public void range_canvasTests() {
        assertArrayEquals(new int[] {2, 3, 4}, Assignment1.range(new int[] {2, 8, 7, 3, 4}, 1, 5));
        assertArrayEquals(new int[] {5, 3}, Assignment1.range(new int[] {5, 9, 1, 7, 3}, 3, 5));
        assertArrayEquals(new int[] {8, 7, 6, 5, 4}, Assignment1.range(new int[] {8, 7, 6, 5, 4}, 4, 8));
        assertArrayEquals(new int[] {7, 3, 3, 4}, Assignment1.range(new int[] {8, 2, 8, 7, 3, 3, 4}, 3, 7));
    }

    @Test
    public void ceiling_canvasTests() {
        assertEquals(2, Assignment1.ceiling(new int[] {2, 8, 7, 3, 4}, 1));
        assertEquals(7, Assignment1.ceiling(new int[] {5, 9, 1, 7, 3}, 7));
        assertEquals(4, Assignment1.ceiling(new int[] {8, 7, 6, 5, 4}, 0));
        assertEquals(7, Assignment1.ceiling(new int[] {8, 2, 8, 7, 3, 3, 4}, 5));
    }

    @Test
    public void floor_canvasTests() {
        assertEquals(4, Assignment1.floor(new int[] {2, 8, 7, 3, 4}, 6));
        assertEquals(1, Assignment1.floor(new int[] {5, 9, 1, 7, 3}, 1));
        assertEquals(8, Assignment1.floor(new int[] {8, 7, 6, 5, 4}, 9));
        assertEquals(4, Assignment1.floor(new int[] {8, 2, 8, 7, 3, 3, 4}, 5));
    }
}
