package edu.auburn.bmb0136.comp2210.module3.assignment3;

import edu.auburn.bmb0136.comp2210.TestUtils;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public final class TermTests {

    @Test
    public void constructor_throws_ifNullQuery() {
        assertThrows(NullPointerException.class, () -> System.out.println(new Term(null, 0)));
    }

    @Test
    public void constructor_throws_ifNegativeWeight() {
        assertThrows(IllegalArgumentException.class, () -> System.out.println(new Term("", -1)));
    }

    @Test
    public void byDescendingWeightOrder_equal_ifWeightsSame() {
        final long W = 69;
        final int RESULT = 0;
        TestUtils.assert_comparatorWorks(Term.byDescendingWeightOrder(), new Term("X", W), new Term("Y", W), RESULT);
    }

    @Test
    public void byDescendingWeightOrder_equal_ifQuerySame() {
        final String Q = "X";
        final long W1 = 69;
        final long W2 = 42;
        TestUtils.assert_inverted(() -> TestUtils.assert_comparatorWorks(Term.byDescendingWeightOrder(), new Term(Q, W1), new Term(Q, W2), 0));
    }

    @Test
    public void byDescendingWeightOrder_negative_ifWeightGreater() {
        final long W1 = 69;
        final long W2 = 42;
        final int RESULT = -1;
        TestUtils.assert_comparatorWorks(Term.byDescendingWeightOrder(), new Term("X", W1), new Term("Y", W2), RESULT);
    }

    @Test
    public void byDescendingWeightOrder_zero_ifWeightEqual() {
        final long W = 69;
        final int RESULT = 0;
        TestUtils.assert_comparatorWorks(Term.byDescendingWeightOrder(), new Term("X", W), new Term("Y", W), RESULT);
    }

    @Test
    public void byDescendingWeightOrder_positive_ifWeightLess() {
        final long W1 = 42;
        final long W2 = 69;
        final int RESULT = 1;
        TestUtils.assert_comparatorWorks(Term.byDescendingWeightOrder(), new Term("X", W1), new Term("Y", W2), RESULT);
    }

    @Test
    public void byPrefixOrder_throws_ifInvalidLength() {
        assertThrows(IllegalArgumentException.class, () -> System.out.println(Term.byPrefixOrder(-1)));
        assertThrows(IllegalArgumentException.class, () -> System.out.println(Term.byPrefixOrder(0)));
    }

    @Test
    public void byPrefixOrder_comparesOnlyFirstN_givenLonger() {
        final String Q1 = "123";
        final String Q2 = "124";
        final long W = 69;
        final int L = 2;
        final int RESULT = 0;
        TestUtils.assert_comparatorWorks(Term.byPrefixOrder(L), new Term(Q1, W), new Term(Q2, W), RESULT);
    }

    @Test
    public void toString_usesFormat() {
        final String QUERY = "X";
        final long WEIGHT = 1;

        String str = new Term(QUERY, WEIGHT).toString();

        assertEquals(QUERY.charAt(0), str.charAt(0));
        assertEquals('\t', str.charAt(1));
        assertEquals((char)(WEIGHT + 0x30), str.charAt(2));
    }


}
