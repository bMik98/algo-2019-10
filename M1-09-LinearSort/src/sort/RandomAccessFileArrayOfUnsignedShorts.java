package sort;

import helper.ExecutionRuntimeException;
import sun.misc.Cleaner;
import sun.nio.ch.DirectBuffer;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

public class RandomAccessFileArrayOfUnsignedShorts implements AutoCloseable {

    private final RandomAccessFile ras;
    private MappedByteBuffer buffer;

    private RandomAccessFileArrayOfUnsignedShorts(RandomAccessFile ras, MappedByteBuffer buffer) {
        this.ras = ras;
        this.buffer = buffer;
    }

    public static RandomAccessFileArrayOfUnsignedShorts createSparse(File file, int size) {
        try {
            RandomAccessFile ras = new RandomAccessFile(file, "rw");
            long length = (long) size + size;
            ras.setLength(length);
            MappedByteBuffer buffer = ras.getChannel()
                    .map(FileChannel.MapMode.READ_WRITE, 0, length);
            return new RandomAccessFileArrayOfUnsignedShorts(ras, buffer);
        } catch (IOException e) {
            throw new ExecutionRuntimeException(e.getMessage());
        }
    }

    public static RandomAccessFileArrayOfUnsignedShorts openForReading(File file) {
        try {
            RandomAccessFile ras = new RandomAccessFile(file, "r");
            MappedByteBuffer buffer = ras.getChannel()
                    .map(FileChannel.MapMode.READ_ONLY, 0, file.length());
            return new RandomAccessFileArrayOfUnsignedShorts(ras, buffer);
        } catch (IOException e) {
            throw new ExecutionRuntimeException(e.getMessage());
        }
    }

    public int getUnsignedShort(int index) {
        int position = index + index;
        return buffer.getShort(position) & 0xFFFF;
    }

    public void putUnsignedShort(int index, int value) {
        int position = index + index;
        buffer.putShort(position, (short) value);
    }

    @Override
    public void close() {
        if (buffer != null) {
            buffer.force();
            Cleaner cleaner = ((DirectBuffer) buffer).cleaner();
            if (cleaner != null) {
                cleaner.clean();
            }
        }
        if (ras != null) {
            try {
                ras.close();
            } catch (IOException e) {
                throw new ExecutionRuntimeException(e.getMessage());
            }
        }
    }
}
