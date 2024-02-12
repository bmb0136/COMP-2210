package edu.auburn.bmb0136.comp2210.module3.assignment3;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

public final class AutocompleteTests {
    @Test
    public void test() {
        Assertions.assertEquals("abc\t10,ab\t8,abcde\t6,a\t4,abcd\t2", f(""));
        Assertions.assertEquals("abc\t10,ab\t8,abcde\t6,a\t4,abcd\t2", f("a"));
        Assertions.assertEquals("abc\t10,ab\t8,abcde\t6,abcd\t2", f("ab"));
        Assertions.assertEquals("abc\t10,abcde\t6,abcd\t2", f("abc"));
        Assertions.assertEquals("abcde\t6,abcd\t2", f("abcd"));
        Assertions.assertEquals("abcde\t6", f("abcde"));
        Assertions.assertEquals("", f("abcdef"));
    }

    private static String f(String s) {
        return Arrays.toString(new Autocomplete(new Term[]{
                new Term("a", 4),
                new Term("ab", 8),
                new Term("abc", 10),
                new Term("abcd", 2),
                new Term("abcde", 6),
        }).allMatches(s)).replaceAll("[\\[\\] ]", "");
    }
}
