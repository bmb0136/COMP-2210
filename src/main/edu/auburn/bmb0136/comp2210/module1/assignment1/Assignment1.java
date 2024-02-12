package edu.auburn.bmb0136.comp2210.module1.assignment1;

import java.util.Arrays;

public final class Assignment1 {

    private Assignment1() {}

    public static int min(int[] arr) {
        if (arr == null || arr.length == 0) {
            throw new IllegalArgumentException();
        }
        int min = arr[0];
        for (int i : arr) {
            if (i < min) {
                min = i;
            }
        }
        return min;
    }

    public static int max(int[] arr) {
        if (arr == null || arr.length == 0) {
            throw new IllegalArgumentException();
        }
        int max = arr[0];
        for (int i : arr) {
            if (i > max) {
                max = i;
            }
        }
        return max;
    }

    public static int[] range(int[] arr, int low, int high) {
        if (arr == null || arr.length == 0) {
            throw new IllegalArgumentException();
        }
        int[] newArr = new int[arr.length];
        int pointer = 0;
        for (int i : arr) {
            if (i >= low && i <= high) {
                newArr[pointer] = i;
                pointer++;
            }
        }
        return Arrays.copyOf(newArr, pointer);
    }

    public static int floor(int[] arr, int key) {
        if (arr == null || arr.length == 0) {
            throw new IllegalArgumentException();
        }
        return max(range(arr, Integer.MIN_VALUE, key));
    }

    public static int ceiling(int[] arr, int key) {
        if (arr == null || arr.length == 0) {
            throw new IllegalArgumentException();
        }
        return min(range(arr, key, Integer.MAX_VALUE));
    }

    public static int kmin(int[] arr, int k) {
        if (arr == null || arr.length == 0 || k < 1 || k > arr.length) {
            throw new IllegalArgumentException();
        }
        int min = min(arr);
        if (k == 1) {
            return min;
        }
        return kmin(range(arr, min + 1, Integer.MAX_VALUE), k - 1);
    }

    public static int kmax(int[] arr, int k) {
        if (arr == null || arr.length == 0 || k < 1 || k > arr.length) {
            throw new IllegalArgumentException();
        }
        int max = max(arr);
        if (k == 1) {
            return max;
        }
        return kmax(range(arr, Integer.MIN_VALUE, max - 1), k - 1);
    }
}
