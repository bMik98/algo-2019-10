package array.matrix;

import array.IArray;
import array.sequent.LinkedArray;
import array.linear.VectorArray;

public class SpaceArray<T> implements IArray<T> {

    private static final int DEFAULT_VECTOR = 16;
    private static final int DEFAULT_FACTOR = 75;

    private final int vector;
    private final int fillLimit;
    private IArray<IArray<T>> array;
    private int size;

    public SpaceArray(int vector, int fillPercents) {
        this.vector = (vector > 0) ? vector : DEFAULT_VECTOR;
        int factor = (fillPercents > 0 && fillPercents <= 100) ? fillPercents : DEFAULT_FACTOR;
        fillLimit = vector * factor / 100;
        array = new LinkedArray<>();
        size = 0;
    }

    public SpaceArray() {
        this(DEFAULT_VECTOR, DEFAULT_FACTOR);
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void add(T item) {
        if (size == 0 || array.get(array.size() - 1).size() >= fillLimit) {
            array.add(new VectorArray<>(vector));
        }
        array.get(array.size() - 1).add(item);
        size++;
    }

    @Override
    public T get(int index) {
        ItemCell itemCell = getRealLocation(index);
        return array.get(itemCell.getRow()).get(itemCell.getCol());
    }

    @Override
    public void add(T item, int index) {
        if (index == size) {
            add(item);
        } else {
            ItemCell itemCell = getRealLocation(index);
            if (array.get(itemCell.getRow()).size() >= vector) {
                splitRow(itemCell.getRow());
                add(item, index);
            }
            array.get(itemCell.getRow()).add(item, itemCell.getCol());
            size++;
        }
    }

    private void splitRow(int row) {
        int startCol = vector / 2;
        IArray<T> newRow = new VectorArray<>(vector);
        for (int i = startCol; i < array.get(row).size(); i++) {
            newRow.add(array.get(row).get(i));
        }
        for (int i = array.get(row).size() - 1; i >= startCol; i--) {
            array.get(row).remove(i);
        }
        array.add(newRow, row + 1);
    }

    @Override
    public T remove(int index) {
        ItemCell itemCell = getRealLocation(index);
        T removed = array.get(itemCell.getRow()).remove(itemCell.getCol());
        size--;
        return removed;
    }

    private ItemCell getRealLocation(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayIndexOutOfBoundsException(index);
        }
        ItemCell result;
        int firstRowSize = array.get(0).size();
        if (index < firstRowSize) {
            result = new ItemCell(0, index);
        } else {
            int row = 1;
            int indexSum = firstRowSize;
            while (index > indexSum + array.get(row).size() - 1) {
                indexSum += array.get(row).size();
                row++;
            }
            int col = index - indexSum;
            result = new ItemCell(row, col);
        }
        return result;
    }
}
