package sort;

import helper.ExecutionRuntimeException;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.function.Consumer;

public class ExternalMergeSort {

    private static final Consumer<int[]> mergeSort = MergeSort::sort;
    private static final Consumer<int[]> timSort = MergeSort::timSort;

    private static final int CHUNK_SIZE = 10_000_000;

    private ExternalMergeSort() {
    }

    public static void sort(String fileSpec) {
        File sourceFile = Paths.get(fileSpec).toFile();
        sort(sourceFile, sourceFile, mergeSort);
    }

    public static void sort(String sourceFileSpec, String destinationFileSpec) {
        File sourceFile = Paths.get(sourceFileSpec).toFile();
        File resultFile = Paths.get(destinationFileSpec).toFile();
        sort(sourceFile, resultFile, mergeSort);
    }

    public static void timSort(String fileSpec) {
        File sourceFile = Paths.get(fileSpec).toFile();
        sort(sourceFile, sourceFile, timSort);
    }

    public static void timSort(String sourceFileSpec, String destinationFileSpec) {
        File sourceFile = Paths.get(sourceFileSpec).toFile();
        File resultFile = Paths.get(destinationFileSpec).toFile();
        sort(sourceFile, resultFile, timSort);
    }

    public static void sort(File sourceFile, File destinationFile, Consumer<int[]> method) {
        String workDir = sourceFile.getParent();
        long arraySize = sourceFile.length() / 2;
        File resultFile;
        try (DataInputStream source = getDataInputStream(sourceFile)) {
            resultFile = sort(source, arraySize, workDir, 0, method);
            Files.deleteIfExists(destinationFile.toPath());
            boolean renamed = resultFile.renameTo(destinationFile);
            if (!renamed) {
                throw new ExecutionRuntimeException("Impossible to rename the result file to the specified name.");
            }
        } catch (IOException e) {
            throw new ExecutionRuntimeException(e.getMessage());
        }
    }

    private static File sort(DataInputStream source, long size, String workDir, int fileId, Consumer<int[]> method)
            throws IOException {
        File resultFile = new File(workDir, "" + fileId);
        if (size > CHUNK_SIZE) {
            long leftSize = size / 2;
            long rightSize = size - leftSize;
            int leftFileId = 2 * fileId + 1;
            int rightFileId = leftFileId + 1;
            File leftFile = sort(source, leftSize, workDir, leftFileId, method);
            File rightFile = sort(source, rightSize, workDir, rightFileId, method);
            merge(leftFile, rightFile, resultFile);
            Files.deleteIfExists(leftFile.toPath());
            Files.deleteIfExists(rightFile.toPath());
        } else {
            int[] array = new int[(int) size];
            for (int i = 0; i < size; i++) {
                array[i] = source.readUnsignedShort();
            }
            method.accept(array);
            try (DataOutputStream result = getDataOutputStream(resultFile)) {
                for (int i = 0; i < size; i++) {
                    result.writeShort(array[i]);
                }
            }
        }
        return resultFile;
    }

    private static void merge(File leftFile, File rightFile, File resultFile) {
        boolean readA = true;
        boolean readB = true;
        int valueA = -1;
        int valueB = -1;
        long sizeA = leftFile.length() / 2;
        long sizeB = rightFile.length() / 2;
        try (DataInputStream a = getDataInputStream(leftFile);
             DataInputStream b = getDataInputStream(rightFile);
             DataOutputStream r = getDataOutputStream(resultFile)) {
            while (sizeA > 0 && sizeB > 0) {
                if (readA) {
                    valueA = a.readUnsignedShort();
                }
                if (readB) {
                    valueB = b.readUnsignedShort();
                }
                if (valueA < valueB) {
                    r.writeShort(valueA);
                    sizeA--;
                    readA = true;
                    readB = false;
                } else {
                    r.writeShort(valueB);
                    sizeB--;
                    readA = false;
                    readB = true;
                }
            }
            if (sizeA > 0) {
                r.writeShort(valueA);
                sizeA--;
                while (sizeA > 0) {
                    valueA = a.readUnsignedShort();
                    r.writeShort(valueA);
                    sizeA--;
                }
            }
            if (sizeB > 0) {
                r.writeShort(valueB);
                sizeB--;
                while (sizeB > 0) {
                    valueB = b.readUnsignedShort();
                    r.writeShort(valueB);
                    sizeB--;
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
