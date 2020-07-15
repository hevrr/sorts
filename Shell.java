import edu.princeton.cs.algs4.StdOut;

public class Shell extends Sort {

    private int[] array;

    /* constructor */
    public Shell(int[] a) {
        array = a;
    }

    /* shell sort */
    public void sort() {
        /* gap size */
        for (int g = array.length / 2; g > 0; g /= 2)
            /* index moving up */
            for (int i = g; i < array.length; i++)
                /* sorting by swapping items with gap size apart, moving backwards if not in order */
                for (int j = i; j >= g && array[j] < array[j - g]; j -= g)
                    exchange(array, j, j - g);
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
