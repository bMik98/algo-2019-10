package array.linear;

import array.IArray;

import java.util.ArrayList;

public class ArrayListWrapper<T> implements IArray<T> {

    private ArrayList<T> array = new ArrayList<>();

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
