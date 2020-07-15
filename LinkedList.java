import java.util.Iterator;

public class LinkedList<Item> implements Iterable<Item> {

    /* variables */
    private Node top;
    private int size;

    /* constructor */
    public LinkedList() {
        top = null;
        size = 0;
    }

    /* adds item */
    public void add(Item item) {
        if (top == null) {
            top = new Node();
            top.item = item;
        } else {
            Node temp = top;
            while (temp.next != null) {
                temp = temp.next;
            }
            temp.next = new Node();
            temp.next.item = item;
        }
        size++;
    }

    /* remove */
    public void remove(int index) {
        if (top != null && index != 0) {

            Node temp = top;

            for (int i = 0; temp != null && i < index - 1; i++)
                temp = temp.next;

            if (temp == null || temp.next == null)
                return;

            Node next = temp.next.next;
            temp.next = next;
            size--;

        } else if (index == 0) {
            top = top.next;
            size--;
        }
    }

    /* gets item at index */
    public Item get(int index) {
        if (size() - 1 < index) {
            System.out.println("Out of bounds.");
        } else {
            Node temp = top;
            for (int i = 0; i < index; i++)
                temp = temp.next;
            return temp.item;
        }
        return null;
    }

    /* pushes item */
    public void push(Item item) {
        if (top == null) {
            top = new Node();
            top.item = item;
        } else {
            Node temp = top;
            top = new Node();
            top.item = item;
            top.next = temp;
        }
        size++;
    }

    /* pops item */
    public Item pop() {
        if (top == null)
            return null;

        Node temp = top;
        top = top.next;
        size--;

        return temp.item;
    }

    /* clears list */
    public void clear() {
        top = null;
    }

    /* returns size */
    public int size() {
        return size;
    }

    /* if is empty */
    public Boolean isEmpty() {
        return top == null;
    }

    /* iterator */
    @Override
    public Iterator<Item> iterator() {
        return new ListIterator();
    }

    private class ListIterator implements Iterator<Item> {

        private Node temp = top;

        public boolean hasNext() {
            return temp != null;
        }

        public void remove() {
        }

        public Item next() {
            Item item = temp.item;
            temp = temp.next;
            return item;
        }
    }

    /* node */
    private class Node {
        Item item;
        Node next;
    }
}
