package helper;

import java.util.Arrays;
import java.util.Random;

public class RandomArrayHolder implements ArrayHolder {

    private final Random random;
    private final int[] array;

    public RandomArrayHolder(int size) {
        this(size, 0, Integer.MAX_VALUE / 2 - 1);
    }

    public RandomArrayHolder(int size, int minElementValue, int maxElementValue) {
        this.random = new Random(System.currentTimeMillis());
        int min = Math.min(minElementValue, maxElementValue);
        int max = Math.max(minElementValue, maxElementValue);
        this.array = generateRandomArray(size, min, max);
    }

    private int[] generateRandomArray(int size, int minElementValue, int maxElementValue) {
        int[] result = new int[size];
        for (int i = 0; i < size; i++) {
            result[i] = randomElementValue(minElementValue, maxElementValue);
        }
        return result;
    }

    private int randomElementValue(int minElementValue, int maxElementValue) {
        return random.nextInt(maxElementValue - minElementValue + 1) + minElementValue;
    }

    @Override
    public int[] copy() {
        return Arrays.copyOf(array, array.length);
    }
}
