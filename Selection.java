import edu.princeton.cs.algs4.StdOut;

public class Selection extends Sort {

    private int[] array;

    /* constructor */
    public Selection(int[] a) {
        array = a;
    }

    /* selection sort */
    public void sort() {

        /* goes through entire array */
        for (int i = 0; i < array.length; i++) {

            /* finds minimum index */
            int min = i;
            for (int j = i + 1; j < array.length; j++)
                if (array[j] < array[min]) min = j;

            /* exchanges current and found minimum in array */
            exchange(array, i, min);
        }
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
