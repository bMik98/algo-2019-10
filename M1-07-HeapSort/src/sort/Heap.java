package sort;

public class Heap {

    private Heap() {
    }

    public static void sort(int[] array) {
        for (int node = array.length / 2 + 1; node >= 0  ; node--) {
            lower(array, array.length, node);
        }
        for (int size = array.length - 1; size >=0 ; size--) {
//            swap(array, 0, size);
            int temp = array[size];
            array[size] = array[0];
            array[0] = temp;
            lower(array,size, 0);
        }
    }

    private static void lower(int[] array, int size, int root) {
        int left = (root << 1) + 1;
        int right = left + 1;
        int x = root;
        if(left < size && array[left] > array[x]) {
            x = left;
        }
        if(right < size && array[right] > array[x]) {
            x = right;
        }
        if(x != root) {
//            swap(array, root, x);
            int temp = array[x];
            array[x] = array[root];
            array[root] = temp;
            lower(array, size, x);
        }
    }

    private static void swap(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }
}
