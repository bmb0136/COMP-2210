package edu.auburn.bmb0136.comp2210.module4;

import edu.auburn.bmb0136.comp2210.module4.assignment4.LinkedSet;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertIterableEquals;

public class LinkedSetTests {

    private static final Random rng = new Random(69);

    private static LinkedSet<Integer> createNaturalNumbers(int n) {
        LinkedSet<Integer> set = new LinkedSet<>();
        for (int i = 0; i < n; i++) {
            set.add(i + 1);
        }
        return set;
    }

    private static LinkedSet<Integer> createRandom(int n) {
        LinkedSet<Integer> set = new LinkedSet<>();
        for (int i = 0; i < n; i++) {
            set.add((int) (rng.nextDouble() * 1234));
        }
        return set;
    }

    @Test
    public void isAlwaysSorted() {
        final int N = 100;

        assertIterableEquals((Iterable<Integer>) (() -> Stream.iterate(1, x -> x + 1)
            .limit(N)
            .iterator()), createNaturalNumbers(N));
    }

    @Test
    public void union_isON() {
        //assert_polyTimeEquals(LinkedSetTests::createRandom, LinkedSet::union, 16384, 1);
    }

    @Test
    public void equals_isON() {
//        assert_polyTimeEquals(n -> {
//            LinkedSet<Integer> s = createNaturalNumbers(n);
//            s.remove(n / 2);
//            return s;
//        }, LinkedSet::equals, 16384, 1);
    }

    @Test
    public void intersection_isON() {
        //assert_polyTimeEquals(LinkedSetTests::createRandom, LinkedSet::intersection, 16384, 1);
    }

    @Test
    public void complement_isON() {
        //assert_polyTimeEquals(LinkedSetTests::createRandom, LinkedSet::complement, 16384, 1);
    }

    @Test
    public void powerSetIterator_isCorrect() {
        LinkedSet<String> set = new LinkedSet<>();
        set.add("A");
        set.add("B");
        set.add("C");

        ArrayList<String> result = new ArrayList<>();
        set.powerSetIterator().forEachRemaining(x -> result.add(x.toString()));

        assertIterableEquals(Arrays.asList("[]", "[A]", "[B]", "[A, B]", "[C]", "[A, C]", "[B, C]", "[A, B, C]"), result);
    }
}
