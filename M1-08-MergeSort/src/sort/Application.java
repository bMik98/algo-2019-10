package sort;

import helper.ArrayHolder;
import helper.Helper;
import helper.RandomArrayHolder;

import java.util.function.Consumer;
import java.util.function.IntConsumer;

public class Application {

    private static final String FILE_ARRAY_SPEC = "d:\\test\\data.bin";
    private static final String SORTED_FILE_ARRAY_SPEC = "d:\\test\\sorted.bin";
    private static final String TIM_SORTED_FILE_ARRAY_SPEC = "d:\\test\\tim_sorted.bin";

    private static final IntConsumer createFileArray = size ->
            FileArrayOfUnsignedShorts.createRandom(FILE_ARRAY_SPEC, size);
    private static final Consumer<String> externalMergeSort = resultFile ->
            ExternalMergeSort.sort(FILE_ARRAY_SPEC, resultFile);
    private static final Consumer<String> externalTimSort = resultFile ->
            ExternalMergeSort.timSort(FILE_ARRAY_SPEC, resultFile);

    public static void main(String[] args) {

        System.out.println("#8. MergeSort, TimSort");

        System.out.println("\n##8.1 Creating a large file array of unsigned shorts\n");
        creatingFileArrays(1_000_000, 10_000_000, 100_000_000, 1_000_000_000);

        System.out.println("\n##8.2 External sorting a file array \n");
        externalSortingFileArrays(50_000_000, 500_000_000, 1_000_000_000);

        System.out.println("\n##8.3 Comparison between in-memory methods MergeSort and TimSort (32..1024), ms\n");
        comparisonInMemorySorts(50_000, 100_000, 500_000, 1_000_000, 5_000_000, 10_000_000, 50_000_000);
    }

    private static void creatingFileArrays(int... sizes) {
        System.out.println("| Array size, numbers |  File length, bytes |   Elapsed time, ms  |");
        System.out.println("|--------------------:|--------------------:|--------------------:|");
        for (int size : sizes) {
            long elapsedTime = Helper.runAndMeasure(createFileArray, size);
            System.out.printf("| %,19d | %,19d | %,19d |%n", size, size * 2, elapsedTime);
            FileArrayOfUnsignedShorts.deleteFile(FILE_ARRAY_SPEC);
        }
    }

    private static void externalSortingFileArrays(int... sizes) {
        System.out.println("| Name                | Array size, numbers |   Elapsed time, ms  |");
        System.out.println("|---------------------|--------------------:|--------------------:|");
        for (int size : sizes) {
            createFileArray.accept(size);
            externalSortingFileArray("External MergeSort", size, externalMergeSort, SORTED_FILE_ARRAY_SPEC);
            externalSortingFileArray("External TimSort", size, externalTimSort, TIM_SORTED_FILE_ARRAY_SPEC);
            FileArrayOfUnsignedShorts.deleteFile(FILE_ARRAY_SPEC);
        }
    }

    private static void externalSortingFileArray(String name, int size, Consumer<String> sort, String sortedFile) {
        long elapsedTime = Helper.runAndMeasure(sort, sortedFile);
        System.out.printf("| %-19s | %,19d | %,19d |%n", name, size, elapsedTime);
        Helper.assertThatFileArrayIsSorted(sortedFile);
        FileArrayOfUnsignedShorts.deleteFile(sortedFile);
    }

    private static void comparisonInMemorySorts(int... sizes) {
        System.out.println("| Array size, numbers | Merge  | Tim-32 | Tim-64 | Tim-128| Tim-256| Tim-512|Tim-1024|");
        System.out.println("|--------------------:|-------:|-------:|-------:|-------:|-------:|-------:|-------:|");
        for (int size : sizes) {
            System.out.printf("| %,19d ", size);
            inMemorySorts(size);
            System.out.println("|");
        }
    }

    private static void inMemorySorts(int size) {
        ArrayHolder arrayHolder = new RandomArrayHolder(size, 0, 65535);
        int[] array = arrayHolder.copy();
        inMemorySort(MergeSort::sort, array);
        for (int threshold : new int[]{32, 64, 128, 256, 512, 1024}) {
            array = arrayHolder.copy();
            inMemorySort(arr -> MergeSort.timSort(arr, threshold), array);
        }
    }

    private static void inMemorySort(Consumer<int[]> sort, int[] array) {
        long elapsedTime = Helper.runAndMeasure(sort, array);
        Helper.assertThatArrayIsSorted(array);
        System.out.printf("| %,6d ", elapsedTime);
    }
}
