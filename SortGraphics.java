import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class SortGraphics {

    /* array size for generation */
    private static final int ARRAY_SIZE = 15;

    public static void main(String[] args) {

        StdOut.println("- Shows sorting mechanisms for insertion, selection, selection (stable), merge top-down, merge bottom-up, quick, radix, and shell sorts");

        int input;
        do {
            int[] array = randomizeValues(ARRAY_SIZE);

            StdOut.print("\nOptions:\n0: Insertion\n1: Selection\n2: Selection (stable)\n3: Merge TD\n4: Merge BU\n5: Quick\n6: Radix\n7: Shell\n8: All\nOther integer: Exit program\n\nEnter input: ");
            input = StdIn.readInt();
            switch (input) {
                case 0:
                    runThroughArray(insertion(array), "Insertion");
                    break;
                case 1:
                    runThroughArray(selection(array), "Selection");
                    break;
                case 2:
                    runThroughArray(selectionStable(array), "Selection (stable)");
                    break;
                case 3:
                    runThroughArray(msortTD(array, 0, array.length - 1), "Merge TD");
                    break;
                case 4:
                    runThroughArray(msortBU(array), "Merge BU");
                    break;
                case 5:
                    runThroughArray(quick(array, 0, array.length - 1), "Quick");
                    break;
                case 6:
                    runThroughArray(radix(array), "Radix");
                    break;
                case 7:
                    runThroughArray(shell(array), "Shell");
                    break;
                case 8:
                    runThroughArray(insertion(array), "Insertion");
                    runThroughArray(selection(array), "Selection");
                    runThroughArray(selectionStable(array), "Selection (stable)");
                    runThroughArray(msortTD(array, 0, array.length - 1), "Merge TD");
                    runThroughArray(msortBU(array), "Merge BU");
                    runThroughArray(quick(array, 0, array.length - 1), "Quick");
                    runThroughArray(radix(array), "Radix");
                    runThroughArray(shell(array), "Shell");
                    break;
            }
        } while (input >= 0 && input < 9);
        System.exit(1);
    }

    /* randomizes integer array from 1-n with given size n */
    private static int[] randomizeValues(int n) {
        int[] array = new int[n];

        LinkedList<Integer> list = new LinkedList<>();
        for (int i = 1; i <= n; i++)
            list.add(i);

        int index = 0;
        while (list.size() > 0) {
            int i = (int) (Math.random() * list.size());
            int r = list.get(i);
            list.remove(i);
            array[index++] = r;
        }
        return array;
    }

    /* insertion sort */
    private static int[] insertion(int[] a) {
        int[] array = new int[a.length];
        System.arraycopy(a, 0, array, 0, a.length);
        for (int i = 1; i < array.length; i++) {
            for (int j = i; j > 0 && array[j] < array[j - 1]; j--) {
                exchange(array, j, j - 1);
                drawArray(array, i, j, -1, "Insertion");
            }
        }
        return array;
    }

    /* selection sort */
    private static int[] selection(int[] a) {
        int[] array = new int[a.length];
        System.arraycopy(a, 0, array, 0, a.length);
        for (int i = 0; i < array.length; i++) {
            int min = i;
            for (int j = i + 1; j < array.length; j++) {
                if (array[j] < array[min]) min = j;
                drawArray(array, i, min, j, "Selection");
            }
            exchange(array, i, min);
        }
        return array;
    }

    /* selection stable sort */
    private static int[] selectionStable(int[] a) {
        int[] array = new int[a.length];
        System.arraycopy(a, 0, array, 0, a.length);

        for (int i = 0; i < array.length - 1; i++) {

            int min = i;
            for (int j = i + 1; j < array.length; j++) {
                if (array[min] > array[j]) min = j;
                drawArray(array, i, min, j, "Selection (stable)");
            }

            int temp = array[min];
            while (min > i) {
                drawArray(array, i, -1, min, "Selection (stable)");
                array[min] = array[min - 1];
                min--;
            }

            array[i] = temp;
        }

        return array;
    }

    /* merge sort top-down */
    private static int[] msortTD(int[] a, int low, int high) {
        int[] array = new int[a.length];
        System.arraycopy(a, 0, array, 0, a.length);
        if (high > low) {
            int mid = low + (high - low) / 2;
            array = msortTD(array, low, mid);
            array = msortTD(array, mid + 1, high);
            array = merge(array, low, mid, high, "Merge TD");
        }
        return array;
    }

    /* merge sort bottom-up */
    private static int[] msortBU(int[] a) {
        int[] array = new int[a.length];
        System.arraycopy(a, 0, array, 0, a.length);
        for (int i = 1; i < array.length; i *= 2)
            for (int j = 0; j < array.length - i; j += (2 * i))
                array = merge(array, j, j + i - 1, Math.min(j + i + i - 1, array.length - 1), "Merge BU");
        return array;
    }

    /* merges */
    private static int[] merge(int[] a, int low, int mid, int high, String name) {
        int[] array = new int[a.length];
        System.arraycopy(a, 0, array, 0, a.length);

        int leftSize = mid - low + 1;
        int rightSize = high - mid;

        int leftArray[] = new int[leftSize];
        int rightArray[] = new int[rightSize];

        System.arraycopy(array, low, leftArray, 0, leftSize);
        System.arraycopy(array, mid + 1, rightArray, 0, rightSize);

        int leftIndex = 0, rightIndex = 0, arrayIndex = low;

        while (leftIndex < leftSize && rightIndex < rightSize) {
            drawArray(array, low + leftIndex, mid + 1 + rightIndex, -1, name);
            if (leftArray[leftIndex] <= rightArray[rightIndex]) {
                array[arrayIndex] = leftArray[leftIndex];
                leftIndex++;
            } else {
                array[arrayIndex] = rightArray[rightIndex];
                rightIndex++;
            }
            arrayIndex++;
        }

        while (leftIndex < leftSize) {
            drawArray(array, low + leftIndex, mid + 1 + rightIndex, -1, name);
            array[arrayIndex] = leftArray[leftIndex];
            leftIndex++;
            arrayIndex++;
        }

        while (rightIndex < rightSize) {
            drawArray(array, low + leftIndex, mid + 1 + rightIndex, -1, name);
            array[arrayIndex] = rightArray[rightIndex];
            rightIndex++;
            arrayIndex++;
        }
        return array;
    }

    /* quick sort */
    private static int[] quick(int[] a, int low, int high) {
        int[] array = new int[a.length];
        System.arraycopy(a, 0, array, 0, a.length);
        if (low < high) {
            int index = partition(array, low, high);
            array = quick(array, low, index - 1);
            array = quick(array, index + 1, high);
        }
        return array;
    }

    /* partitions */
    private static int partition(int[] array, int low, int high) {
        int pivot = array[high];
        int i = low - 1;
        for (int j = low; j < high; j++) {
            if (array[j] < pivot) {
                i++;
                exchange(array, i, j);
            }
            drawArray(array, i, j, high, "Quick");
        }
        exchange(array, i + 1, high);
        drawArray(array, pivot, i, -1, "Quick");
        return i + 1;
    }

    /* radix sort */
    private static int[] radix(int[] a) {
        int[] array = new int[a.length];
        System.arraycopy(a, 0, array, 0, a.length);

        int maxIndex = 0;
        for (int i = 0; i < array.length; i++) {
            if (array[i] > array[maxIndex])
                maxIndex = i;
            drawArray(array, i, -1, maxIndex, "Radix");
        }
        int max = array[maxIndex];

        for (int n = 1; max / n > 0; n *= 10)
            array = countingSort(array, n);

        return array;
    }

    /* shell sort */
    public static int[] shell(int[] a) {
        int[] array = new int[a.length];
        System.arraycopy(a, 0, array, 0, a.length);

        for (int g = array.length / 2; g > 0; g /= 2)
            for (int i = g; i < array.length; i++)
                for (int j = i; j >= g && array[j] < array[j - g]; j -= g) {
                    drawArray(array, j, j - g, -1, "Shell");
                    exchange(array, j, j - g);
                }
        return array;
    }

    /* counting sort */
    private static int[] countingSort(int[] array, int n) {
        int[] countingArray = new int[10];
        int[] newArray = new int[array.length];

        for (int i = 0; i < array.length; i++)
            countingArray[(array[i] / n) % 10]++;

        for (int i = 1; i < 10; i++)
            countingArray[i] += countingArray[i - 1];

        for (int i = array.length - 1; i >= 0; i--) {
            newArray[countingArray[(array[i] / n) % 10] - 1] = array[i];
            drawArray(array, i, -1, -1, "Quick");
            countingArray[(array[i] / n) % 10]--;
        }

        return newArray;
    }

    /* exchanges two values */
    private static void exchange(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    /* graphics variables */
    private static final int UNSORTED_PAUSE = 100;
    private static final int SORTED_PAUSE = 500;
    private static final double[] TEXT_POSITION = {0.03, 0.95};
    private static final double GRAPHICS_WIDTH = 1 / (double) ARRAY_SIZE;

    /* runs through sorted array */
    private static void runThroughArray(int[] array, String name) {
        for (int i = 0; i < array.length; i++)
            drawArray(array, i, -1, -1, name);
        drawArray(array, -1, -1, -1, name);
        StdDraw.pause(SORTED_PAUSE);
    }

    /* draws array with color changes at 3 specified indices and draws sort name */
    private static void drawArray(int[] array, int index1, int index2, int index3, String name) {
        StdDraw.clear(StdDraw.BLACK);
        StdDraw.setPenColor(StdDraw.WHITE);

        StdDraw.textLeft(TEXT_POSITION[0], TEXT_POSITION[1], name);

        for (int i = 0; i < array.length; i++) {
            double x = GRAPHICS_WIDTH * i + 0.5 * GRAPHICS_WIDTH;
            double y = (double) array[i] / ARRAY_SIZE / 2;
            StdDraw.rectangle(x, y, GRAPHICS_WIDTH / 2, y);
        }
        if (index2 != -1 && index2 < array.length) {
            StdDraw.setPenColor(StdDraw.RED);
            StdDraw.filledRectangle(GRAPHICS_WIDTH * index2 + 0.5 * GRAPHICS_WIDTH, (double) array[index2] / ARRAY_SIZE / 2, GRAPHICS_WIDTH / 2, (double) array[index2] / ARRAY_SIZE / 2);
        }
        if (index1 != -1 && index1 < array.length) {
            StdDraw.setPenColor(StdDraw.GREEN);
            StdDraw.filledRectangle(GRAPHICS_WIDTH * index1 + 0.5 * GRAPHICS_WIDTH, (double) array[index1] / ARRAY_SIZE / 2, GRAPHICS_WIDTH / 2, (double) array[index1] / ARRAY_SIZE / 2);
        }
        if (index3 != -1 && index3 < array.length) {
            StdDraw.setPenColor(StdDraw.BOOK_LIGHT_BLUE);
            StdDraw.filledRectangle(GRAPHICS_WIDTH * index3 + 0.5 * GRAPHICS_WIDTH, (double) array[index3] / ARRAY_SIZE / 2, GRAPHICS_WIDTH / 2, (double) array[index3] / ARRAY_SIZE / 2);
        }
        StdDraw.pause(UNSORTED_PAUSE);
    }
}
