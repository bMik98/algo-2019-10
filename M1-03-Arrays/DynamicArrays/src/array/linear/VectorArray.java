package array.linear;

public class VectorArray<T> extends AbstractLinearArray<T> {

    private static final int DEFAULT_VECTOR = 10;
    private static final int INITIAL_SIZE = 0;

    private int vector;

    public VectorArray() {
        this(DEFAULT_VECTOR);
    }

    @SuppressWarnings("WeakerAccess")
    public VectorArray(int vector) {
        super(INITIAL_SIZE);
        this.vector = vector;
    }

    @Override
    protected int getArrayIncrementValue() {
        return vector;
    }
}
