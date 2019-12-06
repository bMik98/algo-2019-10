package helper;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.function.Consumer;
import java.util.function.IntConsumer;

public class Helper {

    private Helper() {
    }

    public static void assertThatArrayIsSorted(int[] array) {
        for (int i = 0; i < array.length - 1; i++) {
            if (array[i] > array[i + 1]) {
                throw new IndexOutOfBoundsException("Array not sorted at position: " + (i + 1));
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
                    throw new IndexOutOfBoundsException("File Array not sorted at position: " + i);
                }
                previous = current;
            }
        } catch (IOException e) {
            throw new ExecutionRuntimeException(e.getMessage());
        }
    }

    public static long runAndMeasure(IntConsumer method, int size) {
        long start = System.currentTimeMillis();
        method.accept(size);
        return System.currentTimeMillis() - start;
    }

    public static long runAndMeasure(Consumer<String> method, String name) {
        long start = System.currentTimeMillis();
        method.accept(name);
        return System.currentTimeMillis() - start;
    }

    public static long runAndMeasure(Consumer<int[]> method, int[] array) {
        long start = System.currentTimeMillis();
        method.accept(array);
        return System.currentTimeMillis() - start;
    }
}
