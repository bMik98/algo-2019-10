package sort;

import helper.Helper;

import java.io.IOException;

public class Application {

    private static final String FILE_ARRAY_SPEC = "d:\\test\\data.bin";
    private static final int FILE_ARRAY_SIZE = 1_000;

    public static void main(String[] args) throws IOException {
/*
        for (int size : new int[]{100_000, 500_000, 1_000_000, 10_000_000, 50_000_000}) {

            System.out.printf("%n%n## In memory Merge sort vs TimSort with different thresholds, size = %d %n%n", size);
            System.out.println("|   N   |   0   |   8   |   16  |   32  |   64  |  128  |   256 |   512 |  1024 |");
            System.out.println("|------:|------:|------:|------:|------:|------:|------:|------:|------:|------:|");

            for (int j = 0; j < 10; j++) {
                System.out.printf("|%6d ", j + 1);
                ArrayHolder arrayHolder = new RandomArrayHolder(size, 0, 65535);
                int[] array = arrayHolder.copy();
                long start = System.currentTimeMillis();
                MergeSort.sort(array);
                long end = System.currentTimeMillis();
//            assertThatArrayIsSorted(array);
                System.out.printf("|%6d ", end - start);
                for (int threshold : new int[]{8, 16, 32, 64, 128, 256, 512, 1024}) {
                    array = arrayHolder.copy();
                    start = System.currentTimeMillis();
                    MergeSort.timSort(array, threshold);
                    end = System.currentTimeMillis();
//            assertThatArrayIsSorted(array);
                    System.out.printf("|%6d ", end - start);
                }
                System.out.println("|");
            }
        }
*/


        long start = System.currentTimeMillis();
        FileArrayOfUnsignedShorts.createRandom(FILE_ARRAY_SPEC, FILE_ARRAY_SIZE);
        long end = System.currentTimeMillis();
        System.out.println("create time: " + (end - start));

        start = System.currentTimeMillis();
        ExternalMergeSort.sort(FILE_ARRAY_SPEC);
        end = System.currentTimeMillis();
        System.out.println("external sort time: " + (end - start));

        start = System.currentTimeMillis();
        Helper.assertThatFileIsSorted(FILE_ARRAY_SPEC);
        end = System.currentTimeMillis();
        System.out.println("check sort time: " + (end - start));

    }
}