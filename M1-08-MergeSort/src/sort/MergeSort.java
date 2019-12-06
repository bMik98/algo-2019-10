package sort;

public class MergeSort {

    private static final int DEFAULT_TIM_THRESHOLD = 32;

    private MergeSort() {
    }

    public static void sort(int[] array) {
        sort(array, 0, array.length - 1);
    }

    private static void sort(int[] array, int left, int right) {
        if (right > left) {
            int center = left + (right - left) / 2;
            sort(array, left, center);
            sort(array, center + 1, right);
            merge(array, left, center, right);
        }
    }

    public static void timSort(int[] array) {
        timSort(array, 0, array.length - 1, DEFAULT_TIM_THRESHOLD);
    }

    public static void timSort(int[] array, int threshold) {
        timSort(array, 0, array.length - 1, threshold);
    }

    private static void timSort(int[] array, int left, int right, int threshold) {
        if (right - left <= threshold) {
            shellSort(array, left, right);
        } else {
            int center = left + (right - left) / 2;
            timSort(array, left, center, threshold);
            timSort(array, center + 1, right, threshold);
            merge(array, left, center, right);
        }
    }

    private static void merge(int[] array, int left, int center, int right) {
        int[] result = new int[right - left + 1];
        int a = left;
        int b = center + 1;
        int r = 0;
        while (a <= center && b <= right) {
            if (array[a] < array[b]) {
                result[r++] = array[a++];
            } else {
                result[r++] = array[b++];
            }
        }
        while (a <= center) {
            result[r++] = array[a++];
        }
        while (b <= right) {
            result[r++] = array[b++];
        }
        System.arraycopy(result, 0, array, left, right - left + 1);
    }

    private static void shellSort(int[] arr, int left, int right) {
        int length = right - left + 1;
        for (int gap = length / 2; gap > 0; gap /= 2) {
            for (int i = left; i + gap <= right; i++) {
                int j = i + gap;
                int temp = arr[j];
                while (j - gap >= left && arr[j - gap] > temp) {
                    arr[j] = arr[j - gap];
                    j -= gap;
                }
                arr[j] = temp;
            }
        }
    }
}
