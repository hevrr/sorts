import edu.princeton.cs.algs4.StdOut;

public class Radix extends Sort {

    private int[] array;

    /* constructor */
    public Radix(int[] a) {
        array = a;
    }

    /* sorts through radix */
    public void sort() {
        /* finds max */
        int maxIndex = 0;
        for (int i = 0; i < array.length; i++)
            if (array[i] > array[maxIndex])
                maxIndex = i;
        int max = array[maxIndex];

        /* count sort for each digit of max */
        for (int n = 1; max / n > 0; n *= 10)
            array = countingSort(array, n);
    }

    /* counting sort */
    private int[] countingSort(int[] array, int n) {
        /* tracker for number of occurrences for each digit */
        int[] countingArray = new int[10];
        /* creates new array to be returned */
        int[] newArray = new int[array.length];

        /* tracks number of occurrences for each digit */
        for (int i = 0; i < array.length; i++)
            countingArray[(array[i] / n) % 10]++;

        /* sums all occurrences before for each digit */
        for (int i = 1; i < 10; i++)
            countingArray[i] += countingArray[i - 1];

        /* stable + iterates through array */
        for (int i = array.length - 1; i >= 0; i--) {
            /* finds index to move to in counting array */
            newArray[countingArray[(array[i] / n) % 10] - 1] = array[i];
            /* removes occurrence */
            countingArray[(array[i] / n) % 10]--;
        }

        return newArray;
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
