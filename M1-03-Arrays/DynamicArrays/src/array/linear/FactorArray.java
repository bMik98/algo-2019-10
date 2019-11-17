package array.linear;

public class FactorArray<T> extends AbstractLinearArray<T> {

    private static final int DEFAULT_FACTOR = 50;
    private static final int INITIAL_SIZE = 0;

    private int factor;

    public FactorArray() {
        this(DEFAULT_FACTOR, INITIAL_SIZE);
    }

    @SuppressWarnings("WeakerAccess")
    public FactorArray(int factor, int size) {
        super(size);
        this.factor = factor;
    }

    @Override
    protected int getArrayIncrementValue() {
        return allocatedSize() * factor / 100 + 1;
    }
}
