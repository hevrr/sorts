import edu.princeton.cs.algs4.StdOut;

public class Insertion extends Sort {

    private int[] array;

    /* constructor */
    public Insertion(int[] a) {
        array = a;
    }

    /* sorts through insertion */
    public void sort() {
        /* goes through 1 to n items in array */
        for (int i = 1; i < array.length; i++)
            /* inserts smaller elements */
            for (int j = i; j > 0 && array[j] < array[j - 1]; j--)
                exchange(array, j, j - 1);
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
