import edu.princeton.cs.algs4.StdOut;

public class Quick extends Sort {

    private int[] array;

    /* constructor */
    public Quick(int[] a) {
        array = a;
    }

    /* wrapper sort */
    public void sort() {
        sort(array, 0, array.length - 1);
    }

    /* sorts through quick */
    private void sort(int[] array, int low, int high) {
        /* if the low counter has not crossed the high counter */
        if (low < high) {
            /* finds index to partition */
            int index = partition(array, low, high);
            /* sorts two sub-arrays */
            sort(array, low, index - 1);
            sort(array, index + 1, high);
        }
    }

    /* partitions array */
    private int partition(int[] array, int low, int high) {
        /* left index tracker */
        int leftIndex = low - 1;

        /* goes through elements from low to high */
        for (int i = low; i < high; i++)
            /* swaps element i with element leftIndex if element i is less than pivot */
            /* increment left index tracker */
            if (array[i] < array[high]) {
                leftIndex++;
                exchange(array, leftIndex, i);
            }
        exchange(array, leftIndex + 1, high);
        /* returns partition index */
        return leftIndex + 1;
    }

    /* exchanges two elements at specified indices */
    public void exchange(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    /* prints array */
    public void printArray() {
        for (int value : array) StdOut.println(value + " ");
    }

    /* toString */
    public String toString() {
        String s = "";
        for (int value : array) s += value + " ";
        return s;
    }
}
