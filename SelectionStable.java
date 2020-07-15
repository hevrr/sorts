import edu.princeton.cs.algs4.StdOut;

public class SelectionStable extends Sort{

    private int[] array;

    /* constructor */
    public SelectionStable(int[] a) {
        array = a;
    }

    /* stable selection sort */
    public void sort() {

        /* goes through n-1 items in array */
        for (int i = 0; i < array.length - 1; i++) {

            /* finds minimum index */
            int min = i;
            for (int j = i + 1; j < array.length; j++)
                if (array[min] > array[j]) min = j;

                /* shifts array until reaches current index */
            int temp = array[min];
            while (min > i) {
                array[min] = array[min - 1];
                min--;
            }

            /* shifts minimum */
            array[i] = temp;
        }
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
