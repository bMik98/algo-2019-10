package ru.otus.tree.utils;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Random;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.IntConsumer;

public class Helper {

    private Helper() {
    }

    public static void assertThatArrayIsSorted(int[] array) {
        for (int i = 0; i < array.length - 1; i++) {
            if (array[i] > array[i + 1]) {
                throw new ExecutionRuntimeException("Array not sorted at position: " + (i + 1));
            }
        }
    }

    public static void assertThatFileArrayIsSorted(String fileArraySpec) {
        Path path = Paths.get(fileArraySpec);
        long size = path.toFile().length() / 2;
        try (DataInputStream in = new DataInputStream(new BufferedInputStream(Files.newInputStream(path)))) {
            int previous = in.readUnsignedShort();
            for (int i = 1; i < size; i++) {
                int current = in.readUnsignedShort();
                if (previous > current) {
                    throw new ExecutionRuntimeException("File Array not sorted at position: " + i);
                }
                previous = current;
            }
        } catch (IOException e) {
            throw new ExecutionRuntimeException(e.getMessage());
        }
    }

    public static <T> long runAndMeasure(Consumer<T> method, T t) {
        long start = System.currentTimeMillis();
        method.accept(t);
        return System.currentTimeMillis() - start;
    }

    public static <T, U> long runAndMeasure(BiConsumer<T, U> method, T t, U u) {
        long start = System.currentTimeMillis();
        method.accept(t, u);
        return System.currentTimeMillis() - start;
    }

    public static void shuffle(int[] array) {
        Random random = new Random(System.currentTimeMillis());
        int n = array.length;
        for (int i = n - 1; i >= 1; i--) {
            int j = random.nextInt(i + 1);
            swap(array, i, j);
        }
    }

    private static void swap(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    public static int[] createRandomArray(int length) {
        int[] array = createSequentialArray(length);
        shuffle(array);
        return array;
    }

    public static int[] createSequentialArray(int length) {
        int[] array = new int[length];
        for (int i = 0; i < length; i++) {
            array[i] = i;
        }
        return array;
    }
}
