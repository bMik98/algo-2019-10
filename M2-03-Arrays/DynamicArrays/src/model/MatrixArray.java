package model;

public class MatrixArray<T> implements IArray<T> {

    private static final int DEFAULT_VECTOR = 10;

    private int size;
    private int vector;
    private IArray<IArray<T>> array;

    @SuppressWarnings("WeakerAccess")
    public MatrixArray(int vector) {
        this.vector = vector;
        array = new SingleArray<>();
        size = 0;
    }

    public MatrixArray() {
        this(DEFAULT_VECTOR);
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void add(T item) {
        if (size == array.size() * vector) {
            array.add(new VectorArray<T>(vector));
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
        } else {
            insert(item, size / vector, index % vector);
            size++;
        }
    }

    private void insert(T item, int row, int column) {
        if (array.get(row).size() < vector) {
            array.get(row).add(item, column);
        } else {
            int lastColumn = vector - 1;
            T rightShifted = array.get(row).remove(lastColumn);
            array.get(row).add(item, column);
            int lastRow = array.size() - 1;
            for (int i = row + 1; i < lastRow - 1; i++) {
                T next = array.get(i).remove(lastColumn);
                array.get(i).add(rightShifted, 0);
                rightShifted = next;
            }
            if (array.get(lastRow).size() < vector) {
                array.get(lastRow).add(rightShifted, 0);
            } else {
                array.add(new VectorArray<T>(vector));
                array.get(lastRow + 1).add(rightShifted);
            }
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
