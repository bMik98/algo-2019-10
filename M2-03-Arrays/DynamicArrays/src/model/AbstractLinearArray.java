package model;

abstract class AbstractLinearArray<T> implements IArray<T> {

    static final int INITIAL_SIZE = 0;

    private T[] array;
    private int size;

    @SuppressWarnings("WeakerAccess")
    public AbstractLinearArray() {
        this.size = INITIAL_SIZE;
        this.array = createArray(INITIAL_SIZE);
    }

    @SuppressWarnings("WeakerAccess")
    public AbstractLinearArray(int size) {
        this.size = size;
        this.array = createArray(size);
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void add(T item) {
        if (array.length == size()) {
            extend();
        }
        array[size()] = item;
    }

    @Override
    public void add(T item, int index) {

    }

    @Override
    public T get(int index) {
        if (index < 0 || index > size() - 1) {
            throw new ArrayIndexOutOfBoundsException(index);
        }
        return array[index];
    }

    @Override
    public T remove(int index) {
        if (size() == 0) {
            return null;
        }
        T result = get(index);
        System.arraycopy(array, index + 1, array, index, size() - index - 1);
        size--;
        return result;
    }

    private void extend() {
        T[] newArray = createArray(allocatedSize() + arrayIncrementValue());
        System.arraycopy(array, 0, newArray, 0, size());
        array = newArray;
    }

    @SuppressWarnings("unchecked")
    private T[] createArray(int size) {
        return (T[]) new Object[size];
    }

    @SuppressWarnings("WeakerAccess")
    protected int allocatedSize() {
        return array.length;
    }

    protected abstract int arrayIncrementValue();
}
