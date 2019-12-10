package sort;

import helper.ExecutionRuntimeException;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

public class ExternalCountingSort {

    private static final int UPPER_BOUND = 65536;

    private ExternalCountingSort() {
    }

    public static void sortForNumbers(String fileSpec) {
        sortForNumbers(fileSpec, fileSpec);
    }

    public static void sortForNumbers(String sourceFileSpec, String destinationFileSpec) {
        File sourceFile = Paths.get(sourceFileSpec).toFile();
        File resultFile = Paths.get(destinationFileSpec).toFile();
        int[] counters = countNumbers(sourceFile);
        expandToFile(counters, resultFile);
    }

    public static void sort(String fileSpec) {
        sort(fileSpec, fileSpec);
    }

    public static void sort(String sourceFileSpec, String resultFileSpec) {
        File sourceFile = Paths.get(sourceFileSpec).toFile();
        File resultFile = Paths.get(resultFileSpec).toFile();
        int[] counters = countNumbers(sourceFile);
        normalizeCounters(counters);
        File tempFile = new File(resultFile.getParent(), "temp-file");
        countingSort(sourceFile, counters, tempFile);
        moveFile(tempFile, resultFile);
    }

    private static void normalizeCounters(int[] counters) {
        for (int i = 1; i < counters.length; i++) {
            counters[i] += counters[i - 1];
        }
    }

    private static void countingSort(File sourceFile, int[] counters, File tempFile) {
        int size = (int) (sourceFile.length() / 2);
        try (RandomAccessFileArrayOfUnsignedShorts in = RandomAccessFileArrayOfUnsignedShorts.openForReading(sourceFile);
             RandomAccessFileArrayOfUnsignedShorts out = RandomAccessFileArrayOfUnsignedShorts.createSparse(tempFile, size)) {
            for (int i = size - 1; i >= 0; i--) {
                int value = in.getUnsignedShort(i);
                counters[value]--;
                out.putUnsignedShort(counters[value], value);
            }
        }
    }

    private static void moveFile(File from, File to) {
        try {
            Files.move(from.toPath(), to.toPath(), StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            throw new ExecutionRuntimeException(e.getMessage());
        }
    }

    private static int[] countNumbers(File sourceFile) {
        int[] result = new int[UPPER_BOUND];
        int size = (int) (sourceFile.length() / 2);
        try (DataInputStream source = getDataInputStream(sourceFile)) {
            for (int i = 0; i < size; i++) {
                int value = source.readUnsignedShort();
                result[value]++;
            }
        } catch (IOException e) {
            throw new ExecutionRuntimeException(e.getMessage());
        }
        return result;
    }

    private static void expandToFile(int[] counters, File destinationFile) {
        try (DataOutputStream destination = getDataOutputStream(destinationFile)) {
            for (int i = 0; i < counters.length; i++) {
                for (int j = 0; j < counters[i]; j++) {
                    destination.writeShort(i);
                }
            }
        } catch (IOException e) {
            throw new ExecutionRuntimeException(e.getMessage());
        }
    }

    private static DataInputStream getDataInputStream(File file) throws IOException {
        return new DataInputStream(new BufferedInputStream(Files.newInputStream(file.toPath())));
    }

    private static DataOutputStream getDataOutputStream(File file) throws IOException {
        return new DataOutputStream(new BufferedOutputStream(Files.newOutputStream(file.toPath())));
    }
}
