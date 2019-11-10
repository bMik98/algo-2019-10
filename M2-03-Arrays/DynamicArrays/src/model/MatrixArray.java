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
            array.get(index / vector).add(item, index % vector);
        }
    }

    @Override
    public T remove(int index) {
        if (index < 0 || index > size - 1) {
            throw new ArrayIndexOutOfBoundsException(index);
        }
        int row = index / vector;
        T removed = array.get(row).remove(index % vector);
        for (int i = row; i < array.size() - row - 1; i++) {
            T shifted = array.get(i + 1).remove(0);
            array.get(i).add(shifted);
            if (array.get(i + 1).size() == 0) {
                array.remove(i + 1);
            }
        }
        size--;
        return removed;
    }
}
