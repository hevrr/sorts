import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.Stopwatch;

public class IterativeBinarySearch {


    /* runner */
    public static void main(String[] args) {
        StdOut.println("- Testing binary search iteratively");

        StdOut.print("\nEnter sorted array size > 0 to binary search (suggested 10,000,000): ");
        int size = StdIn.readInt();

        int[] array = new int[size];
        for (int i = 0; i < array.length; i++)
            array[i] = i;

        StdOut.println("\nWorst case: search for element not inside array\nSearch for integer which does not exist inside array: " + size);
        Stopwatch worstCase = new Stopwatch();
        for (int i = 0; i < size - 1; i++)
            search(array, size);
        StdOut.println("Index: " + search(array, size) + "\nTime: " + worstCase.elapsedTime() / size + " s");

        StdOut.println("\nBest case: search for element in the middle of array\nSearch for middle integer inside array: " + array[array.length / 2]);
        Stopwatch bestCase = new Stopwatch();
        for (int i = 0; i < size - 1; i++)
            search(array, array[array.length / 2]);
        StdOut.println("Index: " + search(array, array[array.length / 2]) + "\nTime: " + bestCase.elapsedTime() / size + " s");

        StdOut.println("\nAverage case: calculated by averaging time of search for every element in array\nTotal elements searched: " + size);
        Stopwatch averageCase = new Stopwatch();
        for (int i = 0; i < array.length; i++)
            search(array, i);
        StdOut.println("Average time: " + averageCase.elapsedTime() / size + " s");
    }

    /* binary search iterative */
    private static int search(int[] array, int elementToBeFound) {
        int low = 0;
        int high = array.length - 1;
        while (high >= low) {
            int mid = (low + high) / 2;
            if (array[mid] == elementToBeFound)
                return mid;
            else if (array[mid] > elementToBeFound)
                high = mid - 1;
            else
                low = mid + 1;
        }
        return -1;
    }
}
