package sort;

import helper.ExecutionRuntimeException;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

public class ExternalRadixSort {

    private static final int UPPER_BOUND = 65536;

    private ExternalRadixSort() {
    }

    public static void sort(String fileSpec, int base) {
        sort(fileSpec, fileSpec, base);
    }

    public static void sort(String sourceFileSpec, String destinationFileSpec, int base) {
        File sourceFile = Paths.get(sourceFileSpec).toFile();
        File resultFile = Paths.get(destinationFileSpec).toFile();
        countingSortForRadix(sourceFile, resultFile, base, 1);
        for (int exp = base; exp < UPPER_BOUND; exp *= base) {
            countingSortForRadix(resultFile, resultFile, base, exp);
        }
    }

    private static void countingSortForRadix(File sourceFile, File resultFile, int base, int exp) {
        String workDir = resultFile.getParent();
        int[] counters = countRadix(sourceFile, base, exp);
        normalizeCounters(counters);
        File tempFile = new File(workDir, "temp-file");
        long length = sourceFile.length();
        int size = (int) length / 2;
        try (RandomAccessFileArrayOfUnsignedShorts in = RandomAccessFileArrayOfUnsignedShorts.openForReading(sourceFile);
             RandomAccessFileArrayOfUnsignedShorts out = RandomAccessFileArrayOfUnsignedShorts.createSparse(tempFile, size)) {
            for (int i = size - 1; i >= 0; i--) {
                int value = in.getUnsignedShort(i);
                counters[index(value, base, exp)]--;
                out.putUnsignedShort(counters[index(value, base, exp)], value);
            }
        }
        moveFile(tempFile, resultFile);
    }

    private static void normalizeCounters(int[] counters) {
        for (int i = 1; i < counters.length; i++) {
            counters[i] += counters[i - 1];
        }
    }

    private static void moveFile(File from, File to) {
        try {
            Files.move(from.toPath(), to.toPath(), StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            throw new ExecutionRuntimeException(e.getMessage());
        }
    }

    private static int[] countRadix(File sourceFile, int base, int exp) {
        int[] result = new int[base];
        int size = (int) (sourceFile.length() / 2);
        try (DataInputStream source = getDataInputStream(sourceFile)) {
            for (int i = 0; i < size; i++) {
                int value = source.readUnsignedShort();
                int idx = index(value, base, exp);
                result[idx]++;
            }
        } catch (IOException e) {
            throw new ExecutionRuntimeException(e.getMessage());
        }
        return result;
    }

    private static int index(int value, int base, int exp) {
        return value / exp % base;
    }

    private static DataInputStream getDataInputStream(File file) throws IOException {
        return new DataInputStream(new BufferedInputStream(Files.newInputStream(file.toPath())));
    }
}
