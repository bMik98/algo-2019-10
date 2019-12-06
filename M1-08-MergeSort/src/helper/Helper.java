package helper;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

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

    public static void assertThatFileIsSorted(String fileSpec) throws IOException {
        Path path = Paths.get(fileSpec);
        try (DataInputStream in = new DataInputStream(new BufferedInputStream(Files.newInputStream(path)))) {
            int a = in.readUnsignedShort();
            long pos = 0;
            while (in.available() > 0) {
                int b = in.readUnsignedShort();
                pos++;
                if (a > b) {
                    throw new IndexOutOfBoundsException("File Array not sorted at position: " + pos);
                }
                a = b;
            }
        }
    }
}
