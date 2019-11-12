import model.*;

public class Program {

    private static final int TRIES = 100_000;

    public static void main(String[] args) {
        IArray<Integer> singleArray = new SingleArray<>();
        IArray<Integer> vectorArray = new FactorArray<>();
        IArray<Integer> factorArray = new FactorArray<>();
        IArray<Integer> matrixArray = new MatrixArray<>();
        IArray<Integer> linkedArray = new LinkedArray<>();
        IArray<Integer> spaceArray = new SpaceArray<>();

        testAddArray(singleArray, TRIES);
        testAddArray(vectorArray, TRIES);
        testAddArray(factorArray, TRIES);
        testAddArray(matrixArray, TRIES);
        testAddArray(linkedArray, TRIES);
        testAddArray(spaceArray, TRIES);
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
