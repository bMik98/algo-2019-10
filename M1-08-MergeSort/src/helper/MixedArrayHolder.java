package helper;

import java.util.Arrays;
import java.util.Random;

public class MixedArrayHolder implements ArrayHolder {

    private final int[] array;
    private final Random random;

    public MixedArrayHolder(int size, int startValue, int elementsToMix) {
        this.random = new Random(System.currentTimeMillis());
        this.array = createSequentialArray(size, startValue);
        mixArrayElements(array, elementsToMix);
    }

    private int[] createSequentialArray(int size, int startValue) {
        int[] result = new int[size];
        for (int i = 0; i < size; i++) {
            result[i] = i + startValue;
        }
        return result;
    }

    private void mixArrayElements(int[] arr, int elementsToMix) {
        int step = arr.length / elementsToMix;
        int index = random.nextInt(step);
        for (int i = 0; i < elementsToMix; i++) {
            int randomValue = randomElementValue(arr.length);
            while (randomValue == arr[index]) {
                randomValue = randomElementValue(arr.length);
            }
            arr[index] = randomValue;
            index = index + random.nextInt(step) + 1;
        }
    }

    private int randomElementValue(int maxElementValue) {
        return random.nextInt(maxElementValue + 1);
    }

    @Override
    public int[] copy() {
        return Arrays.copyOf(array, array.length);
    }
}
