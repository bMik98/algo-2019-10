package array.linear;

import array.IArray;

import java.util.Arrays;

abstract class AbstractLinearArray<T> implements IArray<T> {

    private static final int INITIAL_SIZE = 0;

    private T[] array;
    private int size;

    AbstractLinearArray() {
        this(INITIAL_SIZE);
    }

    AbstractLinearArray(int size) {
        this.size = size;
        this.array = createArray(size);
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void add(T item) {
        if (array.length == size) {
            int newSize = allocatedSize() + getArrayIncrementValue();
            array = Arrays.copyOf(array, newSize);
        }
        array[size] = item;
        size++;
    }

    @Override
    public void add(T item, int index) {
        if (index < 0 || index > size) {
            throw new ArrayIndexOutOfBoundsException(index);
        } else if (index == size) {
            add(item);
        } else {
            if (array.length == size) {
                T[] newArray = createArray(allocatedSize() + getArrayIncrementValue());
                System.arraycopy(array, 0, newArray, 0, index);
                System.arraycopy(array, index, newArray, index + 1, size - index);
                array = newArray;
            } else {
                System.arraycopy(array, index, array, index + 1, size - index);
            }
            array[index] = item;
            size++;
        }
    }

    @Override
    public T get(int index) {
        if (index < 0 || index > size - 1) {
            throw new ArrayIndexOutOfBoundsException(index);
        }
        return array[index];
    }

    @Override
    public T remove(int index) {
        T removed = get(index);
        System.arraycopy(array, index + 1, array, index, size - index - 1);
        size--;
        return removed;
    }

    @SuppressWarnings("unchecked")
    private T[] createArray(int size) {
        return (T[]) new Object[size];
    }

    int allocatedSize() {
        return array.length;
    }

    protected abstract int getArrayIncrementValue();
}
