package sort;

import helper.ExecutionRuntimeException;

import java.io.BufferedOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Random;

public class FileArrayOfUnsignedShorts {

    private static final int ARRAY_VALUE_UPPER_BOUND = 65536;

    private FileArrayOfUnsignedShorts() {
    }

    public static void createRandom(String fileSpec, int size) {
        Random random = new Random(System.currentTimeMillis());
        Path filePath = Paths.get(fileSpec);
        try (DataOutputStream out = createDataOutputStream(filePath)) {
            for (int i = 0; i < size; i++) {
                out.writeShort(random.nextInt(ARRAY_VALUE_UPPER_BOUND));
            }
        } catch (IOException e) {
            throw new ExecutionRuntimeException(e.getMessage());
        }
    }

    public static void saveArray(String fileSpec, int[] array) throws IOException {
        Path filePath = Paths.get(fileSpec);
        try (DataOutputStream out = createDataOutputStream(filePath)) {
            for (int value : array) {
                out.writeShort(value);
            }
        }
    }

    public static void deleteFile(String fileSpec) {
        try {
            Files.deleteIfExists(Paths.get(fileSpec));
        } catch (IOException e) {
            throw new ExecutionRuntimeException(e.getMessage());
        }
    }

    private static DataOutputStream createDataOutputStream(Path filePath) throws IOException {
        return new DataOutputStream(new BufferedOutputStream(Files.newOutputStream(filePath)));
    }
}
