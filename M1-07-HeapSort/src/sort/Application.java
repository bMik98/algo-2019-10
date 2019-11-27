package sort;

import array.ArrayHolder;
import array.MixedArrayHolder;
import array.RandomArrayHolder;

import java.util.function.Consumer;

public class Application {

    private static final int[] SIZES = new int[]{
            100_000,
            200_000,
            300_000,
            400_000,
            500_000,
            600_000,
            700_000,
            800_000,
            900_000,
            1_000_000,
            10_000_000,
            50_000_000
    };
    private static final int PERCENTAGE_TO_MIX = 5;

    public static void main(String[] args) {
        for (int size : SIZES) {
            System.out.printf("%n ## Sorting performance of the %d elements array, ms %n%n", size);
            System.out.printf("|Algorithm/Sequence      |random     | %s%% mixed| %n", PERCENTAGE_TO_MIX);
            System.out.println("|------------------------|----------:|----------:|");
            ArrayHolder[] arrayHolders = createArrayHolders(size);
            runTests(Shell::classicSort, arrayHolders, "Shell / classic");
            runTests(Heap::sort, arrayHolders, "Heap");
        }
    }

    private static ArrayHolder[] createArrayHolders(int size) {
        int numberToMix = size / 100 * PERCENTAGE_TO_MIX;
        return new ArrayHolder[]{
                new RandomArrayHolder(size),
                new MixedArrayHolder(size, 0, numberToMix)
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
