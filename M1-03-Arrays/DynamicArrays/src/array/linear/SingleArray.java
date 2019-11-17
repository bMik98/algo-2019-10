package array.linear;

public class SingleArray<T> extends AbstractLinearArray<T> {

    public SingleArray() {
        super();
    }

    public SingleArray(int size) {
        super(size);
    }

    @Override
    protected int getArrayIncrementValue() {
        return 1;
    }
}
