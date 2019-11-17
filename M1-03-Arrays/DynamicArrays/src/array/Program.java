package array;

import array.linear.ArrayListWrapper;
import array.linear.FactorArray;
import array.linear.SingleArray;
import array.linear.VectorArray;
import array.matrix.MatrixArray;
import array.matrix.SpaceArray;
import array.sequent.LinkedArray;
import array.sequent.LinkedListWrapper;

public class Program {

    private static final int REPEATS = 50_000;

    public static void main(String[] args) {

        IArray<Integer>[] arrays = new IArray[]{
                new SingleArray<>(),
                new VectorArray<>(),
                new FactorArray<>(),
                new ArrayListWrapper<>(),
                new MatrixArray<>(),
                new SpaceArray<>(),
                new LinkedArray<>(),
                new LinkedListWrapper<>()

        };
        System.out.println("-------------------");
        for (IArray<Integer> array : arrays) {
            testAdd(array, REPEATS);
        }
        System.out.println("-------------------");
        for (IArray<Integer> array : arrays) {
            testAddToFirst(array, REPEATS);
        }
        System.out.println("-------------------");
        for (IArray<Integer> array : arrays) {
            testAddToMiddle(array, REPEATS);
        }
        System.out.println("-------------------");
        for (IArray<Integer> array : arrays) {
            testGetFirst(array, REPEATS);
        }
        System.out.println("-------------------");
        for (IArray<Integer> array : arrays) {
            testGetMiddle(array, REPEATS);
        }
        System.out.println("-------------------");
        for (IArray<Integer> array : arrays) {
            testGetLast(array, REPEATS);
        }
        System.out.println("-------------------");
        for (IArray<Integer> array : arrays) {
            testRemoveMiddle(array, REPEATS);
        }
        System.out.println("-------------------");
        for (IArray<Integer> array : arrays) {
            testRemoveFirst(array, REPEATS);
        }
        System.out.println("-------------------");
        for (IArray<Integer> array : arrays) {
            testRemoveLast(array, REPEATS);
        }
        System.out.println("-------------------");
    }

    private static void testAdd(IArray<Integer> data, int repeats) {
        long start = System.currentTimeMillis();
        for (int j = 0; j < repeats; j++) {
            data.add(j);
        }
        long end = System.currentTimeMillis();
        System.out.printf("%-20s - Add to the end: %d ms %n", data.getClass().getSimpleName(), end - start);
    }

    private static void testAddToFirst(IArray<Integer> data, int repeats) {
        long start = System.currentTimeMillis();
        for (int j = 0; j < repeats; j++) {
            data.add(j, 0);
        }
        long end = System.currentTimeMillis();
        System.out.printf("%-20s - Add to the first: %d ms %n", data.getClass().getSimpleName(), end - start);
    }

    private static void testAddToMiddle(IArray<Integer> data, int repeats) {
        long start = System.currentTimeMillis();
        int index = data.size() / 4;
        for (int j = 0; j < repeats; j++) {
            data.add(j, index);
        }
        long end = System.currentTimeMillis();
        System.out.printf("%-20s - Add to the middle: %d ms %n", data.getClass().getSimpleName(), end - start);
    }

    private static void testGetFirst(IArray<Integer> data, int repeats) {
        long start = System.currentTimeMillis();
        int index = 0;
        for (int j = 0; j < repeats; j++) {
            data.get(index);
        }
        long end = System.currentTimeMillis();
        System.out.printf("%-20s - Get the first: %d ms %n", data.getClass().getSimpleName(), end - start);
    }

    private static void testGetMiddle(IArray<Integer> data, int repeats) {
        long start = System.currentTimeMillis();
        int index = data.size() / 4;
        for (int j = 0; j < repeats; j++) {
            data.get(index);
        }
        long end = System.currentTimeMillis();
        System.out.printf("%-20s - Get the middle: %d ms %n", data.getClass().getSimpleName(), end - start);
    }

    private static void testGetLast(IArray<Integer> data, int repeats) {
        long start = System.currentTimeMillis();
        int index = data.size() - 1;
        for (int j = 0; j < repeats; j++) {
            data.get(index);
        }
        long end = System.currentTimeMillis();
        System.out.printf("%-20s - Get the last: %d ms %n", data.getClass().getSimpleName(), end - start);
    }

    private static void testRemoveFirst(IArray<Integer> data, int repeats) {
        long start = System.currentTimeMillis();
        int index = 0;
        for (int j = 0; j < repeats; j++) {
            data.remove(index);
        }
        long end = System.currentTimeMillis();
        System.out.printf("%-20s - Remove the first: %d ms %n", data.getClass().getSimpleName(), end - start);
    }

    private static void testRemoveMiddle(IArray<Integer> data, int repeats) {
        long start = System.currentTimeMillis();
        int index = data.size() / 4;
        for (int j = 0; j < repeats; j++) {
            data.remove(index);
        }
        long end = System.currentTimeMillis();
        System.out.printf("%-20s - Remove the middle: %d ms %n", data.getClass().getSimpleName(), end - start);
    }

    private static void testRemoveLast(IArray<Integer> data, int repeats) {
        long start = System.currentTimeMillis();
        for (int j = 0; j < repeats; j++) {
            int index = data.size() - 1;
            data.remove(index);
        }
        long end = System.currentTimeMillis();
        System.out.printf("%-20s - Remove the end: %d ms %n", data.getClass().getSimpleName(), end - start);
    }
}
