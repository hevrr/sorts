import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class Birthday {

    public static void main(String[] args) {

        StdOut.println("- Takes an integer N and generates integers between 0 and N-1");
        StdOut.println("- Runs experiments to show that number of integers generated before first repeated value is sqrt(pi*N/2)");

        StdOut.println("\nEnter int N to generate integers between 0 and N-1 to find number of integers needed to generate before first repeated value.");
        StdOut.print("Enter N: ");
        int N = StdIn.readInt();

        StdOut.print("\nEnter number of cycles to simulate: ");
        int cycles = StdIn.readInt();

        /* counts number of integers generated */
        int counter = 0;

        /* iterates number of cycles */
        for (int i = 0; i < cycles; i++) {
            boolean[] exists = new boolean[N];
            boolean repeats = false;
            /* keep cycling until there is a repeat birthday */
            while (!repeats) {
                counter++;
                /* randomly generated birthday */
                int birthday = (int) (Math.random() * N);
                /* if already exists, it is a repeat */
                if (exists[birthday])
                    repeats = true;
                    /* if birthday does not currently exist, track */
                else
                    exists[birthday] = true;
            }
        }

        /* prints comparison */
        StdOut.println("\nThe average case for " + cycles + " cycles took " + (double) counter / cycles + " integers generated.");
        StdOut.println("The hypothesized value is sqrt(pi*N/2): " + Math.sqrt(N * Math.PI / 2));
        StdOut.println("Error (inspired by physics class): " + Math.abs((Math.sqrt(N * Math.PI / 2) - (double) counter / cycles) / Math.sqrt(N * Math.PI / 2)) * 100 + "%.");
    }
}
