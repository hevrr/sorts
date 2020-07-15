import edu.princeton.cs.algs4.StdOut;

public class MergeBottomUp extends Sort {

    private int[] array;

    /* constructor */
    public MergeBottomUp(int[] a) {
        array = a;
    }

    /* sort wrapper method */
    public void sort() {
        sort(array);
    }

    /* sort with merge bottom-up */
    private void sort(int[] array) {
        /* merging array sizes */
        for (int i = 1; i < array.length; i *= 2)
            /* merging index, shifting 2*i */
            for (int j = 0; j < array.length - i; j += (2 * i))
                /* merge from j to i; high is constrained to array length or new index */
                merge(j, j + i - 1, Math.min(j + i + i - 1, array.length - 1));
    }

    /* merges two parts */
    public void merge(int low, int mid, int high) {

        /* calculates left and right size */
        int leftSize = mid - low + 1;
        int rightSize = high - mid;

        /* creates left and right arrays */
        int leftArray[] = new int[leftSize];
        int rightArray[] = new int[rightSize];

        /* copies elements from array from low to high into respective arrays */
        System.arraycopy(array, low, leftArray, 0, leftSize);
        System.arraycopy(array, mid + 1, rightArray, 0, rightSize);

        /* index trackers */
        int leftIndex = 0, rightIndex = 0, arrayIndex = low;

        /* compares until either left or right array is fully copied over */
        while (leftIndex < leftSize && rightIndex < rightSize) {
            /* if left item is smaller, copy in */
            if (leftArray[leftIndex] <= rightArray[rightIndex]) {
                array[arrayIndex] = leftArray[leftIndex];
                leftIndex++;
            } else {
                /* if right item is smaller, copy in */
                array[arrayIndex] = rightArray[rightIndex];
                rightIndex++;
            }
            /* update array index */
            arrayIndex++;
        }

        /* copy rest of left array in */
        while (leftIndex < leftSize) {
            array[arrayIndex] = leftArray[leftIndex];
            leftIndex++;
            arrayIndex++;
        }

        /* copy rest of right array in */
        while (rightIndex < rightSize) {
            array[arrayIndex] = rightArray[rightIndex];
            rightIndex++;
            arrayIndex++;
        }
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
