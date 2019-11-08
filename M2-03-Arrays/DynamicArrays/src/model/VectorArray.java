package model;

public class VectorArray<T> extends AbstractLinearArray<T> {

    private static final int DEFAULT_VECTOR = 10;

    private int vector;

    public VectorArray(int vector) {
        super();
        this.vector = vector;
    }

    public VectorArray() {
        this(DEFAULT_VECTOR);
    }

    @Override
    protected int arrayIncrementValue() {
        return vector;
    }
}
