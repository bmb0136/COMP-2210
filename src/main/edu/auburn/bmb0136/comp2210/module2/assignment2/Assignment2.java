package edu.auburn.bmb0136.comp2210.module2.assignment2;

import java.util.*;

public final class Assignment2 {

    private Assignment2() {
    }

    public static <T> T min(Collection<T> c, Comparator<T> comp) {
        if (c == null || comp == null) {
            throw new IllegalArgumentException();
        }
        if (c.isEmpty()) {
            throw new NoSuchElementException();
        }
        T min = null;
        for (T t : c) {
            if (min == null || comp.compare(t, min) < 0) {
                min = t;
            }
        }
        return min;
    }

    public static <T> T max(Collection<T> c, Comparator<T> comp) {
        if (c == null || comp == null) {
            throw new IllegalArgumentException();
        }
        if (c.isEmpty()) {
            throw new NoSuchElementException();
        }
        T max = null;
        for (T t : c) {
            if (max == null || comp.compare(t, max) > 0) {
                max = t;
            }
        }
        return max;
    }

    public static <T> T kmin(Collection<T> c, int k, Comparator<T> comp) {
        if (c == null || comp == null) {
            throw new IllegalArgumentException();
        }
        if (c.isEmpty() || k < 1 || k > c.size()) {
            throw new NoSuchElementException();
        }
        ArrayList<T> list = new ArrayList<>(c);
        //noinspection Java8ListSort
        java.util.Collections.sort(list, comp);
        for (int i = 0; i < list.size(); i++) {
            if (i < 1 || comp.compare(list.get(i - 1), list.get(i)) != 0) {
                k--;
            }
            if (k == 0) {
                return list.get(i);
            }
        }
        throw new NoSuchElementException();
    }

    public static <T> T kmax(Collection<T> c, int k, Comparator<T> comp) {
        if (c == null || comp == null) {
            throw new IllegalArgumentException();
        }
        if (c.isEmpty() || k < 1 || k > c.size()) {
            throw new NoSuchElementException();
        }
        ArrayList<T> list = new ArrayList<>(c);
        //noinspection Java8ListSort
        java.util.Collections.sort(list, comp.reversed());
        for (int i = 0; i < list.size(); i++) {
            if (i < 1 || comp.compare(list.get(i - 1), list.get(i)) != 0) {
                k--;
            }
            if (k == 0) {
                return list.get(i);
            }
        }
        throw new NoSuchElementException();
    }

    public static <T> Collection<T> range(Collection<T> c, T low, T high, Comparator<T> comp) {
        if (c == null || comp == null) {
            throw new IllegalArgumentException();
        }
        if (c.isEmpty()) {
            throw new NoSuchElementException();
        }
        ArrayList<T> list = new ArrayList<>();
        for (T t : c) {
            if (comp.compare(t, low) >= 0 && comp.compare(t, high) <= 0) {
                list.add(t);
            }
        }
        if (list.isEmpty()) {
            throw new NoSuchElementException();
        }
        return list;
    }

    public static <T> T ceiling(Collection<T> c, T key, Comparator<T> comp) {
        if (c == null || comp == null) {
            throw new IllegalArgumentException();
        }
        if (c.isEmpty()) {
            throw new NoSuchElementException();
        }
        T result = null;

        for (T t : c) {
            if (comp.compare(t, key) >= 0 && (result == null || comp.compare(t, result) < 0)) {
                result = t;
            }
        }

        if (result == null) {
            throw new NoSuchElementException();
        }
        return result;
    }

    public static <T> T floor(Collection<T> c, T key, Comparator<T> comp) {
        if (c == null || comp == null) {
            throw new IllegalArgumentException();
        }
        if (c.isEmpty()) {
            throw new NoSuchElementException();
        }
        T result = null;

        for (T t : c) {
            if (comp.compare(t, key) <= 0 && (result == null || comp.compare(t, result) > 0)) {
                result = t;
            }
        }

        if (result == null) {
            throw new NoSuchElementException();
        }
        return result;
    }
}
