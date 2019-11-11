import model.FactorArray;
import model.IArray;
import model.MatrixArray;
import model.SingleArray;

public class Program {

    public static void main(String[] args) {
        IArray<Integer> singleArray = new SingleArray<>();
        IArray vectorArray = new FactorArray();
        IArray factorArray = new FactorArray();
        IArray matrixArray = new MatrixArray();
        testAddArray(singleArray, 10_000);
        testAddArray(vectorArray, 100_000);
        testAddArray(factorArray, 100_000);
        testAddArray(matrixArray, 100_000);
        testDelArray(singleArray, 30);
    }

    private static void testAddArray(IArray<Integer> data, int total) {
        long start = System.currentTimeMillis();

        for (int j = 0; j < total; j++) {
            data.add(j);
        }

        System.out.println(data + " testAddArray: " +
                (System.currentTimeMillis() - start));
    }

    private static void testDelArray(IArray<Integer> data, int index) {
        long start = System.currentTimeMillis();
        Integer removed = data.remove(index);
        System.out.println("removed: " + removed + " size: " + data.size());
        System.out.println(data + " testDelArray: " +
                (System.currentTimeMillis() - start));
    }
}
