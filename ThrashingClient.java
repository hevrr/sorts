import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.Stopwatch;

public class ThrashingClient {

    /* test n number of times */
    private static final int CYCLES = 1000;

    /* array size constraints */
    private static final int MIN_SIZE = 256;
    private static final int MAX_SIZE = 262144;

    private static ResizableArray array = new ResizableArray();

    public static void main(String[] args) {

        StdOut.println("- Observes the thrashing phenomenon by pushing/popping at certain sizes to show time increase for thrashing\n");
        /* change shrink constant to cause thrashing */
        array.shrinkBy = 2;

        StdOut.println("THRASHING:");
        StdOut.printf("%-20s%-35s%-25s", "Size of array", "Time to push/pop " + CYCLES + " cycles (s)", "Increase factor");
        StdOut.println();
        time();

        array.clear();

        /* change shrink constant to prevent thrashing */
        array.shrinkBy = 4;

        StdOut.println("\nNON-THRASHING:");
        StdOut.printf("%-20s%-35s%-25s", "Size of array", "Time to push/pop " + CYCLES + " cycles (s)", "Increase factor");
        StdOut.println();
        time();

        StdOut.println("\nFrom these results, we can observe that changing the resizing threshold for pop takes care of the thrashing vulnerability.");
    }

    private static void time() {
        double previous = 0;
        for (int i = MIN_SIZE; i <= MAX_SIZE; i *= 2) {
            for (int j = array.size(); j <= i; j++)
                array.push(j);
            Stopwatch s = new Stopwatch();
            for (int k = 0; k < CYCLES; k++) {
                array.pop();
                array.push(i);
            }
            double thrashTime = s.elapsedTime();
            StdOut.printf("%-20s%-35s%-25s", i, thrashTime, thrashTime / previous);
            previous = thrashTime;
            StdOut.println();
        }
    }
}
