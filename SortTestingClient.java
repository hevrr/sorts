import edu.princeton.cs.algs4.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class SortTestingClient {

    private static int size;
    private static int range;
    private static SortType[] sorts;

    private enum SortType {
        Insertion, Selection, SelectionStable, MergeTopDown, MergeBottomUp, Quick, QuickMedian, Radix, Shell
    }

    public static void main(String[] args) {
        instructions();

        for (SortType sortType : sorts) {

            Sort s = makeSort(sortType);

            if (size < 20)
                StdOut.println("Generated list:\t" + s);

            Stopwatch stopwatch = new Stopwatch();

            s.sort();

            StdOut.println(sortType + ": " + stopwatch.elapsedTime() + " s");

            if (size < 20)
                StdOut.println("Sorted list:\t" + s);

            StdOut.println();
        }
        StdOut.println("Sort(s) finished!");
    }

    private static Sort makeSort(SortType sortType) {
        int[] array = new int[size];
        for (int i = 0; i < size; i++)
            array[i] = (int) (Math.random() * range);

        Sort s;
        switch (sortType) {
            case Insertion:
                s = new Insertion(array);
                break;
            case Selection:
                s = new Selection(array);
                break;
            case SelectionStable:
                s = new SelectionStable(array);
                break;
            case MergeTopDown:
                s = new MergeTopDown(array);
                break;
            case MergeBottomUp:
                s = new MergeBottomUp(array);
                break;
            case Quick:
                s = new Quick(array);
                break;
            case QuickMedian:
                s = new QuickMedian(array);
                break;
            case Shell:
                s = new Shell(array);
                break;
            default:
                s = new Radix(array);
                break;
        }
        return s;
    }

    private static void instructions() {
        StdOut.println("- Tests time efficiency for sorting algorithms\n");

        StdOut.print("Enter number of elements to generate in array for sorting\nIf size < 20, the program will print the generated + sorted lists: ");
        size = StdIn.readInt();

        StdOut.print("\nEnter number range for random generation (i.e 100 for random integers from 0-100): ");
        range = StdIn.readInt();

        StdOut.println("\nInstructions - input the value indicated to select a specific sort:");
        StdOut.println("0 : Insertion");
        StdOut.println("1 : Selection");
        StdOut.println("2 : Selection (stable)");
        StdOut.println("3 : Merge (top-down)");
        StdOut.println("4 : Merge (bottom-up)");
        StdOut.println("5 : Quick");
        StdOut.println("6 : Quick (median)");
        StdOut.println("7 : Radix");
        StdOut.println("8 : Shell");
        StdOut.println("9 : Test all");

        int select;
        do {
            StdOut.print("\nEnter valid value to simulate: ");
            select = StdIn.readInt();
        } while (select < 0 || select > 9);

        sorts = new SortType[1];

        switch (select) {
            case 0:
                sorts[0] = SortType.Insertion;
                break;
            case 1:
                sorts[0] = SortType.Selection;
                break;
            case 2:
                sorts[0] = SortType.SelectionStable;
                break;
            case 3:
                sorts[0] = SortType.MergeTopDown;
                break;
            case 4:
                sorts[0] = SortType.MergeBottomUp;
                break;
            case 5:
                sorts[0] = SortType.Quick;
                break;
            case 6:
                sorts[0] = SortType.QuickMedian;
                break;
            case 7:
                sorts[0] = SortType.Radix;
                break;
            case 8:
                sorts[0] = SortType.Shell;
                break;
            case 9:
                sorts = new SortType[9];
                sorts[0] = SortType.Insertion;
                sorts[1] = SortType.Selection;
                sorts[2] = SortType.SelectionStable;
                sorts[3] = SortType.MergeTopDown;
                sorts[4] = SortType.MergeBottomUp;
                sorts[5] = SortType.Quick;
                sorts[6] = SortType.QuickMedian;
                sorts[7] = SortType.Radix;
                sorts[8] = SortType.Shell;
                break;
        }
        StdOut.println();
    }

    /* prints array */
    public static void printArray(int[] array) {
        for (int value : array) StdOut.print(value + " ");
    }
}
