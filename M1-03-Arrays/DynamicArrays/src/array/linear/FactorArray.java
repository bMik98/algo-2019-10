package array.linear;

public class FactorArray<T> extends AbstractLinearArray<T> {

    private static final int DEFAULT_FACTOR = 50;
    private static final int INIT_LENGTH = 0;

    private int factor;

    @SuppressWarnings("WeakerAccess")
    public FactorArray(int factor, int size) {
        super(size);
        this.factor = factor;
    }

    public FactorArray() {
        this(DEFAULT_FACTOR, INIT_LENGTH);
    }

    @Override
    protected int getArrayIncrementValue() {
        return allocatedSize() * factor / 100 + 1;
    }
}
