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
        return array.get(index / vector).get(index % vector);
    }

    @Override
    public void add(T item, int index) {

    }

    @Override
    public T remove(int index) {
        return null;
    }
}
