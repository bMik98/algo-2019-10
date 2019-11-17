package array.matrix;

import array.IArray;
import array.linear.SingleArray;
import array.linear.VectorArray;

public class MatrixArray<T> implements IArray<T> {

    private static final int DEFAULT_VECTOR = 10;

    private int size;
    private int vector;
    private IArray<IArray<T>> array;

    public MatrixArray() {
        this(DEFAULT_VECTOR);
    }

    public MatrixArray(int vector) {
        this.vector = vector;
        array = new SingleArray<>();
        size = 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void add(T item) {
        if (size == array.size() * vector) {
            array.add(new VectorArray<>(vector));
        }
        array.get(size / vector).add(item);
        size++;
    }

    @Override
    public T get(int index) {
        if (index < 0 || index > size - 1) {
            throw new ArrayIndexOutOfBoundsException(index);
        }
        return array.get(index / vector).get(index % vector);
    }

    @Override
    public void add(T item, int index) {
        if (index < 0 || index > size) {
            throw new ArrayIndexOutOfBoundsException(index);
        } else if (index == size) {
            add(item);
        } else if (size < vector) {
            array.get(index / vector).add(item, index % vector);
            size++;
        } else if (size == vector) {
            T shifted = array.get(0).remove(size - 1);
            array.get(0).add(item, index);
            array.add(new VectorArray<>(vector));
            array.get(1).add(shifted);
            size++;
        } else {
            insertAndShift(item, index / vector, index % vector);
            size++;
        }
    }

    private void insertAndShift(T item, int row, int column) {
        int lastColumn = vector - 1;
        T shifted = array.get(row).remove(lastColumn);
        array.get(row).add(item, column);
        int lastRow = array.size() - 1;
        for (int i = row + 1; i < lastRow; i++) {
            T nextShifted = array.get(i).remove(lastColumn);
            array.get(i).add(shifted, 0);
            shifted = nextShifted;
        }
        if (array.get(lastRow).size() < vector) {
            array.get(lastRow).add(shifted, 0);
        } else {
            T next = array.get(lastRow).remove(lastColumn);
            array.get(lastRow).add(shifted, 0);
            array.add(new VectorArray<T>(vector));
            array.get(lastRow + 1).add(next);
        }
    }

    @Override
    public T remove(int index) {
        if (index < 0 || index > size - 1) {
            throw new ArrayIndexOutOfBoundsException(index);
        }
        int row = index / vector;
        T removed = array.get(row).remove(index % vector);
        for (int i = row; i < array.size() - 1; i++) {
            T leftShifted = array.get(i + 1).remove(0);
            array.get(i).add(leftShifted);
            if (array.get(i + 1).size() == 0) {
                array.remove(i + 1);
            }
        }
        size--;
        return removed;
    }
}
