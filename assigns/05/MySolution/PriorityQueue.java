import java.util.NoSuchElementException;

/**
 * @author Kevin Jiang (kjbu@bu.edu), Hongwei Xi
 * @version 1.0, 14 Apr 2025
 */

/*
 * PriorityQueue.java
 *
 * Computer Science 392, Boston University
 */

/*
 * A generic class that implements our Queue interface using a linked list.
 */
public class PriorityQueue<T extends Comparable<T>> implements Queue<T> {
    // Please implement heap-based priority queue
    // See PQItem.java for attaching priority to data
    private final T[] queue;
    private int size;


    public PriorityQueue(int capacity) {
        queue = (T[]) new Comparable[capacity];
        size = 0;
    }

    private static class Node<T> {
        private T item;
        private int priority;

        public Node(T item, int priority) {
            this.item = item;
            this.priority = priority;
        }

        public int compareTo(Node x) {
            return (priority - x.priority);
        }

        @Override
        public String toString() {
            if (item == null) {
                return "null(priority=" + priority + ")";
            } else {
                return item + "(priority=" + priority + ")";
            }
        }
    }

    private void exchange(int x, int y) {
        T tmp = queue[x];
        queue[x] = queue[y];
        queue[y] = tmp;
    }

    @Override
    public boolean insert(T item) {
        if (isFull()) {
            System.out.println("Heap is full");
            return false;
        }
        queue[size] = item;
        swim(size++);
        return true;
    }

    @Override
    public T remove() {
        if (isEmpty()) {
            throw new NoSuchElementException("Heap is empty.");
        }
        T root = queue[0];
        queue[0] = queue[size - 1];
        queue[size - 1] = null;
        size--;
        if (!isEmpty()) {
            sink(0);
        }
        return root;
    }

    @Override
    public T peek() {
        return null;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean isFull() {
        return size >= queue.length;
    }

    public void swim(int child) {
        T toSwim = queue[child];

        while (child > 0) {
            int parent = (child - 1) / 2;
            if (toSwim.compareTo(queue[parent]) <= 0) break;
            exchange(parent, child);
            child = parent;
        }
        queue[child] = toSwim;
    }

    public void sink(int parent) {
        T toSink = queue[parent];
        int child = parent * 2 + 1;
        while (child < size) {
            if (child < size - 1 && queue[child].compareTo(queue[child + 1]) < 0) {
                child++;
            }
            if (toSink.compareTo(queue[child]) >= 0) break;
            exchange(parent, child);
            parent = child;
            child = parent * 2 + 1;
        }
        queue[parent] = toSink;
    }

    @SuppressWarnings("StringConcatenationInLoop")
    public String toString() {
        String str = "{ ";

        int start = 0;
        int levelSize = 1;
        while (start < size) {
            str += "( ";
            for (int i = start; i < start + levelSize && i < size; i++)
                str += (queue[i] + " ");
            str += ") ";

            // move down to the next level
            start += levelSize;
            levelSize *= 2;
        }

        str += "}";
        return str;
    }

    public static void main(String[] args) {
        PriorityQueue<Integer> queue = new PriorityQueue<>(10);
        queue.insert(15);
        queue.insert(13);
        queue.insert(11);
        queue.insert(9);
        queue.insert(7);
        queue.insert(5);
        queue.insert(3);
        queue.insert(1);
        queue.insert(-1);
        queue.insert(-3);
        queue.insert(-5);

        System.out.println(queue);

        queue.remove();
        queue.remove();
        queue.remove();
        queue.remove();

        System.out.println(queue);
    }
}
