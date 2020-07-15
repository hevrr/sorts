import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.Stopwatch;

public class ArrayTestingClient {

    /* min and max for data structure size */
    private static final int MIN_SIZE = 64000;
    private static final int MAX_SIZE = 8192000;

    private enum operation {
        push, pop, both
    }

    /* runner */
    public static void main(String[] args) {
        StdOut.println("- Testing client for timings of linked list and resizable array");
        StdOut.println("- Adds/removes items in list and outputs individual time sand time ratio for both data structures");
        StdOut.println("- This might take a couple of seconds. Time for resizable array is significantly shorter than linked list and can be close to 0 s\n");

        StdOut.printf("%-20s%-25s%-25s%-25s", "Size of array/list", "Push resizable (s)", "Push L-List (s)", "Resizable time:list time");
        StdOut.println();
        /* tests for push */
        for (int i = MIN_SIZE; i <= MAX_SIZE; i *= 2)
            time(i, operation.push);
        StdOut.println();
        StdOut.printf("%-20s%-25s%-25s%-25s", "Size of array/list", "Pop resizable (s)", "Pop L-List (s)", "Resizable time:list time");
        StdOut.println();
        /* tests for pop */
        for (int i = MIN_SIZE; i <= MAX_SIZE; i *= 2)
            time(i, operation.pop);
        StdOut.println();
        StdOut.printf("%-20s%-25s%-25s%-25s", "Size of array/list", "Push/pop resizable (s)", "Push/pop L-List (s)", "Resizable time:list time");
        StdOut.println();
        /* tests for push/pop sizes */
        for (int i = MIN_SIZE; i <= MAX_SIZE; i *= 2)
            time(i, operation.both);
    }

    private static void time(int size, operation o) {
        ResizableArray array = new ResizableArray();
        LinkedList list = new LinkedList<>();

        /* pushes and pops n items */
        Stopwatch resizableWatch = new Stopwatch();
        for (int i = 0; i < size; i++)
            array.push(1);
        if (o == operation.pop || o == operation.both)
            for (int i = 0; i < size; i++)
                array.pop();
        double resizableTime = resizableWatch.elapsedTime();

        /* adds and removes n items */
        Stopwatch listWatch = new Stopwatch();
        for (int i = 0; i < size; i++)
            list.push(1);
        if (o == operation.pop || o == operation.both)
            for (int i = 0; i < size; i++)
                list.pop();
        double listTime = listWatch.elapsedTime();

        StdOut.printf("%-20s%-25s%-25s%-25s", size, resizableTime, listTime, resizableTime / listTime);
        StdOut.println();
    }
}
