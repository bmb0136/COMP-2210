package edu.auburn.bmb0136.comp2210.module3.assignment3;

import java.util.Arrays;
import java.util.Comparator;

/**
 * Binary search.
 */
public class BinarySearch {

    /**
     * Returns the index of the first t in a[] that equals the search t,
     * or -1 if no such t exists. This method throws a NullPointerException
     * if any parameter is null.
     */
    public static <T> int firstIndexOf(T[] a, T t, Comparator<T> comparator) {
        if (a == null || t == null || comparator == null) {
            throw new NullPointerException();
        }
        // a = Arrays.copyOf(a, a.length);
        Arrays.sort(a, comparator);
        int left = 0;
        int right = a.length - 1;
        while (left <= right) {
            int middle = (left + right) / 2;
            T value = a[middle];
            int order = comparator.compare(value, t);
            if (order == 0) {
                // Find first that is not the same
                while (middle >= 0 && comparator.compare(a[middle], t) == 0) {
                    middle--;
                }
                return middle + 1;
            } else if (order > 0) {
                right = middle - 1;
            } else { // if (order < 0) {
                left = middle + 1;
            }
        }
        return -1;
    }

    /**
     * Returns the index of the last t in a[] that equals the search t,
     * or -1 if no such t exists. This method throws a NullPointerException
     * if any parameter is null.
     */
    public static <T> int lastIndexOf(T[] a, T t, Comparator<T> comparator) {
        if (a == null || t == null || comparator == null) {
            throw new NullPointerException();
        }
        // a = Arrays.copyOf(a, a.length);
        Arrays.sort(a, comparator);
        int left = 0;
        int right = a.length - 1;
        while (left <= right) {
            int middle = (left + right) / 2;
            T value = a[middle];
            int order = comparator.compare(value, t);
            if (order == 0) {
                // Find first that is not the same
                while (middle < a.length && comparator.compare(a[middle], t) == 0) {
                    middle++;
                }
                return middle - 1;
            } else if (order > 0) {
                right = middle - 1;
            } else { // if (order < 0) {
                left = middle + 1;
            }
        }
        return -1;
    }

}
