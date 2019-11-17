package array.sequent;

import array.IArray;

import java.util.LinkedList;

public class LinkedListWrapper<T> implements IArray<T> {

    private LinkedList<T> array = new LinkedList<>();

    @Override
    public int size() {
        return array.size();
    }

    @Override
    public void add(T item) {
        array.add(item);
    }

    @Override
    public void add(T item, int index) {
        array.add(index, item);
    }

    @Override
    public T get(int index) {
        return array.get(index);
    }

    @Override
    public T remove(int index) {
        return array.remove(index);
    }
}
