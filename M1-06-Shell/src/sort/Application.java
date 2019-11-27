package sort;

import array.ArrayHolder;
import array.MixedArrayHolder;
import array.RandomArrayHolder;

import java.util.Arrays;
import java.util.function.Consumer;

public class Application {

    private static final int[] SIZES = new int[]{
            300_000,
            500_000,
            20_000_000,
            30_000_000,
            50_000_000
    };
    private static final int MAX_INSERTION_ARRAY_SIZE = 600_000;
    private static final int PERCENTAGE_TO_MIX = 10;
    private static final int NUMBER_TO_MIX = 5;

    public static void main(String[] args) {
        for (int size : SIZES) {
            System.out.printf("%n ## Sorting performance of the %d elements array, ms %n%n", size);
            System.out.println("|Algorithm/Sequence      |random     | 10% random|5 items mix|");
            System.out.println("|------------------------|----------:|----------:|----------:|");
            ArrayHolder[] arrayHolders = createArrayHolders(size);
            if (size < MAX_INSERTION_ARRAY_SIZE) {
                runTests(Insertion::sort, arrayHolders, "Insertion");
            } else {
                System.out.println("| Insertion skipped      ||||");
            }
            runTests(Shell::classicSort, arrayHolders, "Shell / classic");
            runTests(Shell::hibbardSort, arrayHolders, "Shell / Hibbard");
            runTests(Shell::papernovSort, arrayHolders, "Shell / Papernov");
            runTests(Shell::knuthSort, arrayHolders, "Shell / Knuth");
            runTests(Shell::incerpiSort, arrayHolders, "Shell / Incerpi");
            runTests(Shell::sedgewickSort1982, arrayHolders, "Shell / Sedgewick 1982");
            runTests(Shell::sedgewickSort1986, arrayHolders, "Shell / Sedgewick 1986");
            runTests(Shell::tokudaSort, arrayHolders, "Shell / Tokuda");
        }
    }

    private static ArrayHolder[] createArrayHolders(int size) {
        int numberToMix = size / 100 * PERCENTAGE_TO_MIX;
        ArrayHolder visiblyMixedArray = new MixedArrayHolder(size, 0, numberToMix);
        ArrayHolder insensiblyMixedArray = new MixedArrayHolder(size, 0, NUMBER_TO_MIX);
        return new ArrayHolder[]{
                new RandomArrayHolder(size),
                visiblyMixedArray,
                insensiblyMixedArray
        };
    }

    private static void runTests(Consumer<int[]> sortAlgorithm, ArrayHolder[] arrayHolders, String name) {
        System.out.printf("| %-22s |", name);
        for (ArrayHolder arrayHolder : arrayHolders) {
            runTest(sortAlgorithm, arrayHolder);
        }
        System.out.println("");
    }

    private static void runTest(Consumer<int[]> sortAlgorithm, ArrayHolder arrayHolder) {
        int[] array = arrayHolder.copy();
        long start = System.currentTimeMillis();
        sortAlgorithm.accept(array);
        long end = System.currentTimeMillis();
        assertThatArrayIsSorted(array);
        System.out.printf("%10d |", (end - start));
    }

    private static void assertThatArrayIsSorted(int[] array) {
        for (int i = 0; i < array.length - 1; i++) {
            if (array[i] > array[i + 1]) {
                throw new IndexOutOfBoundsException("Array was not sorted after position: " + i);
            }
        }
    }
}
