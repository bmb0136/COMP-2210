package edu.auburn.bmb0136.comp2210;

import org.junit.jupiter.api.Assertions;
import org.opentest4j.AssertionFailedError;
import org.opentest4j.TestAbortedException;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Array;
import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.IntFunction;

import static org.junit.jupiter.api.Assertions.*;

@SuppressWarnings("unchecked")
public final class TestUtils {

    private TestUtils() {
    }

    @SuppressWarnings("unchecked")
    public static <T> void assert_polyTimeEquals(IntFunction<T> f, BiConsumer<T, T> f2, int n, int expected) {
        assert_polyTimeEquals(x -> (T[]) new Object[]{
            f.apply(x),
            f.apply(x)
        }, a -> f2.accept(a[0], a[1]), n, expected);
    }

    public static void assertThrows_ifNullOrEmptyIntArray(Consumer<int[]> func) {
        assertThrows(IllegalArgumentException.class, () -> func.accept(null), "Null Array");
        assertThrows(IllegalArgumentException.class, () -> func.accept(new int[0]), "Empty Array");
    }

    public static <T> void assertThrows_ifNullOrEmptyArray(Class<T> clazz, Consumer<T[]> func) {
        assertThrows(IllegalArgumentException.class, () -> func.accept(null), "Null Array");
        //noinspection unchecked
        assertThrows(IllegalArgumentException.class, () -> func.accept((T[]) Array.newInstance(clazz, 0)), "Empty Array");
    }

    public static void assert_doesNotChange(int[] input, Consumer<int[]> func) {
        int[] copy = Arrays.copyOf(input, input.length);
        func.accept(input);
        assertArrayEquals(copy, input);
    }

    public static <T> void assertThrows_ifNull(Consumer<T> func) {
        assertThrows(IllegalArgumentException.class, () -> func.accept(null));
    }

    public static <T> void assertThrows_ifEmpty(Consumer<Collection<T>> func) {
        assertThrows(NoSuchElementException.class, () -> func.accept(new ArrayList<>()));
    }

    public static <T> void assert_doesNotChange(Collection<T> col, Consumer<Collection<T>> func) {
        Collection<T> copy = new ArrayList<>(col);
        func.accept(col);
        Assertions.assertIterableEquals(copy, col);
    }

    public static void assert_noInfiniteRecursion(Runnable r) {
        try {
            r.run();
        } catch (StackOverflowError e) {
            // Don't print a stack trace the size of the eiffel tower
            e.setStackTrace(Arrays.copyOf(e.getStackTrace(), 5));
            fail(e);
        }
    }

    public static <T> void assert_comparatorWorks(Comparator<T> cmp, T a, T b, int expected) {
        Objects.requireNonNull(a);
        Objects.requireNonNull(b);
        Objects.requireNonNull(cmp);
        int res = cmp.compare(a, b);

        if (expected == 0) {
            assertEquals(0, res);
        } else if (expected > 0) {
            assertTrue(res > 0);
        } else { // if (expected < 0) {
            assertTrue(res < 0);
        }
    }

    public static void assert_inverted(Runnable r) {
        boolean failed = false;
        try {
            r.run();
        } catch (AssertionFailedError ignored) {
            failed = true;
        }
        if (!failed) {
            fail();
        }
    }

    public static <T> void assert_polyTimeEquals(IntFunction<T> factory, Consumer<T> toTest, int n, int expected) {
        final int RUNS = 50;

        double[][] data = new double[RUNS][2];

        for (int i = 0; i < RUNS; i++) {
            T sut1 = factory.apply(n);
            T sut2 = factory.apply(n * 2);

            long start = System.nanoTime();
            toTest.accept(sut1);
            double t1 = (System.nanoTime() - start) / 1.0e9;
            start = System.nanoTime();
            toTest.accept(sut2);
            double t2 = (System.nanoTime() - start) / 1.0e9;

            double o = Math.log(t2 / t1) / Math.log(2);

            if (o < 0 || !Double.isFinite(o)) {
                i--;
                continue;
            }

            double err = Math.abs(Math.round(o) - o);


            data[i] = new double[2];
            data[i][0] = o;
            data[i][1] = err;
        }


        Arrays.sort(data, Comparator.comparingDouble(arr -> arr[1]));

        double totalErr = 0;
        for (double[] arr : data) {
            totalErr += arr[1];
        }


        double sum = 0;
        for (double[] arr : data) {
            double weight = 1 - (arr[1] / totalErr);
            if (!Double.isFinite(weight)) {
                weight = 1;
            }
            sum += weight * arr[0];
        }
        sum /= RUNS;

        double e = Math.abs(sum - expected);
        assertTrue(e < 0.75, String.format("Expected %d, got %.2f [error = %.2f]", expected, sum, e));
    }

    public static <T> void assert_iteratorEquals(Iterator<T> exp, Iterable<T> act) {
        ArrayList<T> e = new ArrayList<>();
        exp.forEachRemaining(e::add);
        assertIterableEquals(e, act, "Got " + e + ", expected " + act);
    }

    public static <T> String iterToString(Iterable<T> obj) {
        StringBuilder sb = new StringBuilder();

        sb.append('[');
        Iterator<T> it = obj.iterator();
        while (it.hasNext()) {
            sb.append(it.next());
            if (it.hasNext()) {
                sb.append(", ");
            }
        }
        sb.append(']');

        return sb.toString();
    }

    public static TempFile loadResource(String name) {
        try {
            File f = File.createTempFile("resource", null);
            FileOutputStream fs = new FileOutputStream(f);
            InputStream res = Objects.requireNonNull(TestUtils.class.getClassLoader()
                .getResourceAsStream(name), "Missing resource: " + name);
            byte[] buf = new byte[1024];
            while (res.available() > 0) {
                int n = res.read(buf);
                fs.write(buf, 0, n);
            }
            res.close();
            fs.close();
            return new TempFile(f);
        } catch (IOException e) {
            throw new TestAbortedException("Failed to load resource " + name, e);
        }
    }
}
