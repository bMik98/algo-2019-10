package array.linear;

public class VectorArray<T> extends AbstractLinearArray<T> {

    private static final int DEFAULT_VECTOR = 10;

    private int vector;

    @SuppressWarnings("WeakerAccess")
    public VectorArray(int vector) {
        super();
        this.vector = vector;
    }

    @SuppressWarnings("WeakerAccess")
    public VectorArray() {
        this(DEFAULT_VECTOR);
    }

    @Override
    protected int getArrayIncrementValue() {
        return vector;
    }
}
