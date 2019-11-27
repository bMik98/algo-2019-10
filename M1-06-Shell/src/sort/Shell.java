package sort;

public class Shell {

    public static final int MAX_ARRAY_SIZE = 2_000_000_000;

    private static final int[] HIBBARD_GAPS = new int[]{
            1, 3, 7, 15, 31, 63, 127, 255, 511, 1023,
            2047, 4095, 8191, 16383, 32767, 65535, 131071, 262143, 524287, 1048575,
            2097151, 4194303, 8388607, 16777215, 33554431, 67108863, 134217727, 268435455, 536870911, 1073741823
    };
    private static final int[] PAPERNOV_GAPS = new int[]{
            1, 3, 5, 9, 17, 33, 65, 129, 257, 513,
            1025, 2049, 4097, 8193, 16385, 32769, 65537, 131073, 262145, 524289,
            1048577, 2097153, 4194305, 8388609, 16777217, 33554433, 67108865, 134217729, 268435457, 536870913,
            1073741825
    };
    private static final int[] KNUTH_GAPS = new int[]{
            1, 4, 13, 40, 121, 364, 1093, 3280, 9841, 29524,
            88573, 265720, 797161, 2391484, 7174453, 21523360, 64570081, 193710244, 581130733
    };
    private static final int[] INCERPI_GAPS = new int[]{
            1, 3, 7, 21, 48, 112, 336, 861, 1968, 4592,
            13776, 33936, 86961, 198768, 463792, 1391376, 3402672, 8382192, 21479367, 49095696,
            114556624, 343669872, 852913488
    };
    private static final int[] SEDGEWICK_1982_GAPS = new int[]{
            1, 8, 23, 77, 281, 1073, 4193, 16577, 65921, 262913,
            1050113, 4197377, 16783361, 67121153, 268460033, 1073790977
    };
    private static final int[] SEDGEWICK_1986_GAPS = new int[]{
            1, 5, 19, 41, 109, 209, 505, 929, 2161, 3905,
            8929, 16001, 36289, 64769, 146305, 260609, 587521, 1045505, 2354689, 4188161,
            9427969, 16764929, 37730305, 67084289, 150958081, 268386305, 603906049, 1073643521
    };
    private static final int[] TOKUDA_GAPS = new int[]{
            1, 4, 9, 20, 46, 103, 233, 525, 1182, 2660,
            5985, 13467, 30301, 68178, 153401, 345152, 776591, 1747331, 3931496, 8845866,
            19903198, 44782196, 100759940, 226709866, 510097200, 1147718700
    };

    private Shell() {
    }

    public static void classicSort(int[] arr) {
        for (int gap = arr.length / 2; gap > 0; gap /= 2) {
            innerSort(arr, gap);
        }
    }

    public static void hibbardSort(int[] a) {
        externalGapsShellSort(a, HIBBARD_GAPS, a.length);
    }

    public static void papernovSort(int[] a) {
        externalGapsShellSort(a, PAPERNOV_GAPS, a.length);
    }

    public static void knuthSort(int[] a) {
        int gapLimit = a.length / 3;
        externalGapsShellSort(a, KNUTH_GAPS, gapLimit);
    }

    public static void incerpiSort(int[] a) {
        externalGapsShellSort(a, INCERPI_GAPS, a.length);
    }

    public static void sedgewickSort1982(int[] a) {
        externalGapsShellSort(a, SEDGEWICK_1982_GAPS, a.length);
    }

    public static void sedgewickSort1986(int[] a) {
        externalGapsShellSort(a, SEDGEWICK_1986_GAPS, a.length);
    }

    public static void tokudaSort(int[] a) {
        externalGapsShellSort(a, TOKUDA_GAPS, a.length);
    }

    private static void externalGapsShellSort(int[] a, int[] gaps, int excludingMaxGap) {
        int maxK = gaps.length - 1;
        while (maxK >= 0 && gaps[maxK] >= excludingMaxGap) {
            maxK--;
        }
        for (int k = maxK; k >= 0; k--) {
            innerSort(a, gaps[k]);
        }
    }

    private static void innerSort(int[] arr, int gap) {
        for (int i = 0; i + gap < arr.length; i++) {
            int j = i + gap;
            int temp = arr[j];
            while (j - gap >= 0 && arr[j - gap] > temp) {
                arr[j] = arr[j - gap];
                j -= gap;
            }
            arr[j] = temp;
        }
    }

}
