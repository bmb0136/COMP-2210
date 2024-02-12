package edu.auburn.bmb0136.comp2210.module2.assignment2;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertIterableEquals;

public class Assignment2CanvasTests {
    @Test
    public void min_canvasTests() {
        assertEquals(2, Assignment2.min(Arrays.asList(2, 8, 7, 3, 4), Integer::compare));
        assertEquals(1, Assignment2.min(Arrays.asList(5, 9, 1, 7, 3), Integer::compare));
        assertEquals(4, Assignment2.min(Arrays.asList(8, 7, 6, 5, 4), Integer::compare));
        assertEquals(2, Assignment2.min(Arrays.asList(8, 2, 8, 7, 3, 3, 4), Integer::compare));
    }

    @Test
    public void max_canvasTests() {
        assertEquals(9, Assignment2.max(Arrays.asList(5, 9, 1, 7, 3), Integer::compare));
        assertEquals(8, Assignment2.max(Arrays.asList(2, 8, 7, 3, 4), Integer::compare));
        assertEquals(8, Assignment2.max(Arrays.asList(8, 7, 6, 5, 4), Integer::compare));
        assertEquals(8, Assignment2.max(Arrays.asList(8, 2, 8, 7, 3, 3, 4), Integer::compare));
    }

    @Test
    public void kmin_canvasTests() {
        assertEquals(2, Assignment2.kmin(Arrays.asList(2, 8, 7, 3, 4), 1, Integer::compare));
        assertEquals(5, Assignment2.kmin(Arrays.asList(5, 9, 1, 7, 3), 3, Integer::compare));
        assertEquals(8, Assignment2.kmin(Arrays.asList(8, 7, 6, 5, 4), 5, Integer::compare));
        assertEquals(4, Assignment2.kmin(Arrays.asList(8, 2, 8, 7, 3, 3, 4), 3, Integer::compare));
    }

    @Test
    public void kmax_canvasTests() {
        assertEquals(8, Assignment2.kmax(Arrays.asList(2, 8, 7, 3, 4), 1, Integer::compare));
        assertEquals(5, Assignment2.kmax(Arrays.asList(5, 9, 1, 7, 3), 3, Integer::compare));
        assertEquals(4, Assignment2.kmax(Arrays.asList(8, 7, 6, 5, 4), 5, Integer::compare));
        assertEquals(4, Assignment2.kmax(Arrays.asList(8, 2, 8, 7, 3, 3, 4), 3, Integer::compare));
    }

    @Test
    public void range_canvasTests() {
        assertIterableEquals(Arrays.asList(2, 3, 4), Assignment2.range(Arrays.asList(2, 8, 7, 3, 4), 1, 5, Integer::compare));
        assertIterableEquals(Arrays.asList(5, 3), Assignment2.range(Arrays.asList(5, 9, 1, 7, 3), 3, 5, Integer::compare));
        assertIterableEquals(Arrays.asList(8, 7, 6, 5, 4), Assignment2.range(Arrays.asList(8, 7, 6, 5, 4), 4, 8, Integer::compare));
        assertIterableEquals(Arrays.asList(7, 3, 3, 4), Assignment2.range(Arrays.asList(8, 2, 8, 7, 3, 3, 4), 3, 7, Integer::compare));
    }

    @Test
    public void ceiling_canvasTests() {
        assertEquals(2, Assignment2.ceiling(Arrays.asList(2, 8, 7, 3, 4), 1, Integer::compare));
        assertEquals(7, Assignment2.ceiling(Arrays.asList(5, 9, 1, 7, 3), 7, Integer::compare));
        assertEquals(4, Assignment2.ceiling(Arrays.asList(8, 7, 6, 5, 4), 0, Integer::compare));
        assertEquals(7, Assignment2.ceiling(Arrays.asList(8, 2, 8, 7, 3, 3, 4), 5, Integer::compare));
    }

    @Test
    public void floor_canvasTests() {
        assertEquals(4, Assignment2.floor(Arrays.asList(2, 8, 7, 3, 4), 6, Integer::compare));
        assertEquals(1, Assignment2.floor(Arrays.asList(5, 9, 1, 7, 3), 1, Integer::compare));
        assertEquals(8, Assignment2.floor(Arrays.asList(8, 7, 6, 5, 4), 9, Integer::compare));
        assertEquals(4, Assignment2.floor(Arrays.asList(8, 2, 8, 7, 3, 3, 4), 5, Integer::compare));
    }
}
