package edu.auburn.bmb0136.comp2210.module5.assignment5;

import java.util.function.IntToDoubleFunction;

public final class TrieBenchmark {

    public static void main(String[] args) {
        System.out.println("ADD");
        printTimes(TrieBenchmark::testAdd);
        System.out.println("---------");
        System.out.println("CONTAINS");
        printTimes(TrieBenchmark::testContains);
        System.out.println("---------");
    }

    private static void printTimes(IntToDoubleFunction f) {
        final int TIMES = 5;
        int n = 1;
        double totalTime = 0;
        while (n <= (1 << 20) && totalTime < 30_000) {
            double time = 0;
            for (int i = 0; i < TIMES; i++) {
                time += f.applyAsDouble(n);
            }
            totalTime += time;
            time /= TIMES;
            System.out.printf("%.3fms for N=%d%n", time, n);
            n *= 2;
        }
    }

    private static double testAdd(int n) {
        String[] strings = getStrings(n);

        Trie trie = new Trie();
        long t = 0;
        for (String s : strings) {
            long start = System.nanoTime();
            trie.add(s);
            t += System.nanoTime() - start;
        }
        return t / 1.0e6;
    }

    private static double testContains(int n) {
        String[] strings = getStrings(n);

        Trie trie = new Trie();
        long t = 0;
        for (String s : strings) {
            long start = System.nanoTime();
            trie.contains(s);
            t += System.nanoTime() - start;
        }
        return t / 1.0e6;
    }

    private static String[] getStrings(int n) {
        String[] strings = new String[n];
        for (int i = 0; i < strings.length; i++) {
            char[] chars = new char[10];
            for (int j = 0; j < chars.length; j++) {
                chars[j] = (char)((int)(Math.random() * 26) + 'A');
            }
            strings[i] = new String(chars);
        }
        return strings;
    }
}
