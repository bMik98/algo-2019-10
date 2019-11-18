package fib;

public class Matrix2x2 {

    private final long[][] a;

    public Matrix2x2(long a00, long a01, long a10, long a11) {
        this.a = new long[2][2];
        a[0][0] = a00;
        a[0][1] = a01;
        a[1][0] = a10;
        a[1][1] = a11;
    }

    public long get(int row, int col) {
        return a[row][col];
    }

    public Matrix2x2 square() {
        long a00 = a[0][0] * a[0][0] + a[0][1] * a[1][0];
        long a01 = a[0][0] * a[0][1] + a[0][1] * a[1][1];
        long a10 = a[1][0] * a[0][0] + a[1][1] * a[1][0];
        long a11 = a[1][0] * a[1][0] + a[1][1] * a[1][1];
        return new Matrix2x2(a00, a01, a10, a11);
    }
}
