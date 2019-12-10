package sort;

import helper.Helper;

import java.util.function.Consumer;
import java.util.function.IntConsumer;

public class Application {

    private static final String FILE_ARRAY_SPEC = "d:\\test\\data.bin";
    private static final String SORTED_FILE_ARRAY_SPEC = "d:\\test\\sorted.bin";

    private static final IntConsumer createFileArray = size ->
            FileArrayOfUnsignedShorts.createRandom(FILE_ARRAY_SPEC, size);
    private static final Consumer<String> deleteFileArray = FileArrayOfUnsignedShorts::deleteFile;

    private static final Consumer<String> externalMergeSort = resultFile ->
            ExternalMergeSort.sort(FILE_ARRAY_SPEC, resultFile);
    private static final Consumer<String> externalBucketSort100 = resultFile ->
            ExternalBucketSort.sort(FILE_ARRAY_SPEC, resultFile, 100);
    private static final Consumer<String> externalCountingSortForNumbers = resultFile ->
            ExternalCountingSort.sortForNumbers(FILE_ARRAY_SPEC, resultFile);
    private static final Consumer<String> externalCountingSort = resultFile ->
            ExternalCountingSort.sort(FILE_ARRAY_SPEC, resultFile);
    private static final Consumer<String> externalRadixSort256 = resultFile ->
            ExternalRadixSort.sort(FILE_ARRAY_SPEC, resultFile, 256);

    public static void main(String[] args) {
        System.out.println("# 9. Linear Sorting");
        int len = 1_000_000_000;
        System.out.println("\n## 9.1 External sorting a file array \n");
        externalSortOfFileArray(len);
    }

    private static void externalSortOfFileArray(int size) {
        System.out.println("| Name                      | Array size, numbers |   Elapsed time, ms  |");
        System.out.println("|---------------------------|--------------------:|--------------------:|");
        createFileArray.accept(size);
        externalSortingFileArray(
                "MergeSort", size, externalMergeSort, SORTED_FILE_ARRAY_SPEC);
        externalSortingFileArray(
                "BucketSort, 100 buckets", size, externalBucketSort100, SORTED_FILE_ARRAY_SPEC);
        externalSortingFileArray(
                "CountingSort For Numbers", size, externalCountingSortForNumbers, SORTED_FILE_ARRAY_SPEC);
        externalSortingFileArray(
                "CountingSort", size, externalCountingSort, SORTED_FILE_ARRAY_SPEC);
        externalSortingFileArray(
                "RadixSort, base=256", size, externalRadixSort256, SORTED_FILE_ARRAY_SPEC);
        deleteFileArray.accept(FILE_ARRAY_SPEC);
    }

    private static void externalSortingFileArray(String name, int size, Consumer<String> sort, String sortedFile) {
        long elapsedTime = Helper.runAndMeasure(sort, sortedFile);
        System.out.printf("| %-25s | %,19d | %,19d |%n", name, size, elapsedTime);
        Helper.assertThatFileArrayIsSorted(sortedFile);
        deleteFileArray.accept(sortedFile);
    }
}
