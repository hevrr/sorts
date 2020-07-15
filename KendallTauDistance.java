import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class KendallTauDistance {

    public static void main(String[] args) {

        StdOut.println("- Finds the Kendall tau rank distance between an inputted list and a permutation of that list");
        StdOut.println("- Test case (from wikipedia link):");
        StdOut.println("- List one: 1, 2, 3, 4, 5");
        StdOut.println("- List two: 3, 4, 1, 2, 5");
        StdOut.println("- Distance: 4; normalized distance: 0.4");

        StdOut.print("\nEnter size for list: ");
        int size = StdIn.readInt();

        int[] listOne = new int[size];

        StdOut.println("\nEnter integer values for list:");
        for (int i = 0; i < size; i++)
            listOne[i] = StdIn.readInt();

        int[] listTwo = generatePermutation(listOne);

        StdOut.println("List one:");
        printArray(listOne);
        StdOut.println("\nList two:");
        printArray(listTwo);

        /* counter for distance */
        int distanceCount = 0;

        /* compares two values within each list */
        for (int i = 0; i < listOne.length - 1; i++)
            for (int j = i + 1; j < listOne.length; j++)
                /* if they are inverted, increase distance count */
                if (listOne[i] < listOne[j] != listTwo[i] < listTwo[j])
                    distanceCount++;

        StdOut.println("\nCalculated Kendall tau distance: " + distanceCount);
        StdOut.println("The normalized distance: " + (double) distanceCount / (size * (size - 1) / 2));
    }

    private static int[] generatePermutation(int[] a) {
        int[] array = new int[a.length];
        LinkedList<Integer> list = new LinkedList<>();

        for (int i = 0; i < a.length; i++)
            list.add(a[i]);

        int index = 0;
        while (list.size() > 0) {
            int i = (int) (Math.random() * list.size());
            int r = list.get(i);
            list.remove(i);
            array[index++] = r;
        }

        /* FOR TESTING */
//        array = new int[]{3, 4, 1, 2, 5};

        return array;
    }

    private static void printArray(int[] array) {
        for (int value : array) StdOut.println(value + " ");
    }
}
