package fib;

public class Matrix1x2 {

    private final long[] r;

    public Matrix1x2(long r0, long r1) {
        this.r = new long[2];
        r[0] = r0;
        r[1] = r1;
    }

    public long get(int row) {
        return r[row];
    }

    public Matrix1x2 multiply(Matrix2x2 a) {
        long r0 = r[0] * a.get(0, 0) + r[1] * a.get(1, 0);
        long r1 = r[0] * a.get(0, 1) + r[1] * a.get(1, 1);
        return new Matrix1x2(r0, r1);
    }
}
