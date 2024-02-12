package edu.auburn.bmb0136.comp2210.module2.handson.recursion;

import edu.auburn.bmb0136.comp2210.TestUtils;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SummationTests {

    @Test
    public void sumR_doesNotStackOverflow() {
        TestUtils.assert_noInfiniteRecursion(() -> Summation.sumR(69));
    }

    @Test
    public void sumR_doesNotStackOverflow_givenNLessThan1() {
        TestUtils.assert_noInfiniteRecursion(() -> Summation.sumR(0));
        TestUtils.assert_noInfiniteRecursion(() -> Summation.sumR(-1));
        TestUtils.assert_noInfiniteRecursion(() -> Summation.sumR(-2));
    }

    @Test
    public void sumR_returns0_given0() {
        assertEquals(0, Summation.sumR(0));
    }

    @Test
    public void sumR_returns1_given1() {
        assertEquals(1, Summation.sumR(1));
    }

    @Test
    public void sumR_returnsCorrectSum() {
        assertEquals(3, Summation.sumR(2));
        assertEquals(6, Summation.sumR(3));
        assertEquals(10, Summation.sumR(4));
    }
}
