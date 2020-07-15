import java.util.Iterator;

public class ResizableArray {

    /* variables */
    private int[] array;
    private int size;

    /* constructor to initialize array and size */
    public ResizableArray() {
        array = new int[2];
        size = 0;
    }

    public void clear() {
        array = new int[2];
        size = 0;
    }

    /* returns size */
    public int size() {
        return size;
    }

    /* peeks item */
    public int peek() {
        return array[size - 1];
    }

    /* pushes item */
    public void push(int n) {
        /* if at threshold, increase array by 2 times */
        if (size == array.length) resize(2 * array.length);
        /* push item to array at current size and increase size */
        array[size++] = n;
    }

    public int shrinkBy = 4;

    /* pops item */
    public int pop() {
        /* if array has items */
        if (size > 0) {
            /* store popped value and decrease size */
            int popped = array[size - 1];
            size--;
            /* if at lower threshold, decrease array by 4 times */
            if (size > 0 && size == array.length / shrinkBy) resize(array.length / 2);
            /* return popped value */
            return popped;
            /* if nothing in array */
        } else
            return Integer.parseInt(null);
    }

    /* resize array to given capacity */
    private void resize(int capacity) {
        array = java.util.Arrays.copyOf(array, capacity);
    }

    /* iterator */
    public Iterator<Integer> iterator() {
        return new ReverseArrayIterator();
    }

    private class ReverseArrayIterator implements Iterator<Integer> {
        private int i;

        public ReverseArrayIterator() {
            i = size - 1;
        }

        public boolean hasNext() {
            return i >= 0;
        }

        public void remove() {
        }

        public Integer next() {
            return array[i--];
        }
    }
}
