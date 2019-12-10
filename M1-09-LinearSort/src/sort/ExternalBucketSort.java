package sort;

import helper.ExecutionRuntimeException;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

public class ExternalBucketSort {

    private ExternalBucketSort() {
    }

    public static void sort(String fileSpec, int numberOfBuckets) {
        sort(fileSpec, fileSpec, numberOfBuckets);
    }

    public static void sort(String sourceFileSpec, String destinationFileSpec, int numberOfBuckets) {
        File sourceFile = Paths.get(sourceFileSpec).toFile();
        File resultFile = Paths.get(destinationFileSpec).toFile();
        sort(sourceFile, resultFile, numberOfBuckets);
    }

    private static void sort(File sourceFile, File destinationFile, int numberOfBuckets) {
        int maxValue = findMaxValue(sourceFile) + 1;
        File[] bucketFiles = separateByBuckets(sourceFile, maxValue, numberOfBuckets);
        sortAndMergeBuckets(bucketFiles, destinationFile);
//        sortBuckets(bucketFiles);
//        mergeBuckets(bucketFiles, destinationFile);
    }

    private static int findMaxValue(File sourceFile) {
        int result = -1;
        long size = sourceFile.length() / 2;
        try (DataInputStream source = getDataInputStream(sourceFile)) {
            for (int i = 0; i < size; i++) {
                int value = source.readUnsignedShort();
                if (value > result) {
                    result = value;
                }
            }
        } catch (IOException e) {
            throw new ExecutionRuntimeException(e.getMessage());
        }
        return result;
    }

    private static File[] separateByBuckets(File sourceFile, int maxValue, int numberOfBuckets) {
        String workDir = sourceFile.getParent();
        long size = sourceFile.length() / 2;
        DataOutputStream[] buckets = new DataOutputStream[numberOfBuckets];
        File[] bucketFiles = new File[numberOfBuckets];
        try (DataInputStream source = getDataInputStream(sourceFile)) {
            for (int i = 0; i < numberOfBuckets; i++) {
                bucketFiles[i] = new File(workDir, String.valueOf(i));
                buckets[i] = getDataOutputStream(bucketFiles[i]);
            }
            for (int i = 0; i < size; i++) {
                int value = source.readUnsignedShort();
                int bucketIndex = numberOfBuckets * value / maxValue;
                buckets[bucketIndex].writeShort(value);
            }
        } catch (IOException e) {
            throw new ExecutionRuntimeException(e.getMessage());
        } finally {
            for (int i = 0; i < numberOfBuckets; i++) {
                try {
                    if (buckets[i] != null) {
                        buckets[i].close();
                    }
                } catch (IOException e) {
                    // NOTHING TO DO
                }
            }
        }
        return bucketFiles;
    }

    private static void sortBuckets(File[] bucketFiles) {
        for (File bucketFile : bucketFiles) {
            int[] array = readBucketFile(bucketFile);
            if (array.length > 0) {
                MergeSort.timSort(array);
                saveArrayToFile(array, bucketFile);
            }
        }
    }

    private static void sortAndMergeBuckets(File[] bucketFiles, File destinationFile) {
        try (DataOutputStream destination = getDataOutputStream(destinationFile)) {
            for (File bucketFile : bucketFiles) {
                int[] array = readBucketFile(bucketFile);
                if (array.length > 0) {
                    MergeSort.timSort(array);
                    writeArray(array, destination);
                }
                bucketFile.delete();
            }
        } catch (IOException e) {
            throw new ExecutionRuntimeException(e.getMessage());
        }
    }

    private static int[] readBucketFile(File bucketFile) {
        int size = (int) (bucketFile.length() / 2);
        int[] result = new int[size];
        if (size > 0) {
            try (DataInputStream bucket = getDataInputStream(bucketFile)) {
                for (int i = 0; i < size; i++) {
                    result[i] = bucket.readUnsignedShort();
                }
            } catch (IOException e) {
                throw new ExecutionRuntimeException(e.getMessage());
            }
        }
        return result;
    }

    private static void saveArrayToFile(int[] array, File file) {
        try (DataOutputStream bucket = getDataOutputStream(file)) {
            writeArray(array, bucket);
        } catch (IOException e) {
            throw new ExecutionRuntimeException(e.getMessage());
        }
    }

    private static void mergeBuckets(File[] bucketFiles, File destinationFile) {
        try (DataOutputStream destination = getDataOutputStream(destinationFile)) {
            for (File bucketFile : bucketFiles) {
                int[] array = readBucketFile(bucketFile);
                if (array.length > 0) {
                    writeArray(array, destination);
                }
                bucketFile.delete();
            }
        } catch (IOException e) {
            throw new ExecutionRuntimeException(e.getMessage());
        }
    }

    private static void writeArray(int[] array, DataOutputStream dataOutputStream) throws IOException {
        for (int value : array) {
            dataOutputStream.writeShort(value);
        }
    }

    private static DataInputStream getDataInputStream(File file) throws IOException {
        return new DataInputStream(new BufferedInputStream(Files.newInputStream(file.toPath())));
    }

    private static DataOutputStream getDataOutputStream(File file) throws IOException {
        return new DataOutputStream(new BufferedOutputStream(Files.newOutputStream(file.toPath())));
    }
}
