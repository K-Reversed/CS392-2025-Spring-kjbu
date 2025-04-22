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
public class PriorityQueue<T extends Comparable<T>> implements Queue<T>{
    // Please implement heap-based priority queue
    // See PQItem.java for attaching priority to data

    private record PQItem<T>(T data, int priority) implements Comparable<PQItem<T>> {

        @Override
            public int compareTo(PQItem o) {
                return priority - o.priority;
            }

            public String toString() {
                if (data == null) {
                    return "null(priority = " + priority + ")";
                } else {
                    return data + "(priority = " + priority + ")";
                }
            }
        }

    private final Heap<PQItem<T>> heap;

    public PriorityQueue(int capacity) {
        heap = new Heap<>(capacity);
    }

    @Override
    public boolean insert(T item) {
        if (isFull()) {
            System.out.println("Heap is full");
            return false;
        }
        return heap.insert(new PQItem<>(item, 0));
    }

    @Override
    public T remove() {
        if (isEmpty()) {
            throw new NoSuchElementException("Heap is empty.");
        }
        return heap.remove().data;
    }

    @Override
    public T peek() {
        if (isEmpty()) {
            throw new NoSuchElementException("Heap is empty.");
        }
        return heap.peek().data;
    }

    @Override
    public boolean isEmpty() {
        return heap.isEmpty();
    }

    @Override
    public boolean isFull() {
        return heap.isFull();
    }

    public String toString() {
        return heap.toString();
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
        System.out.println(queue);

        queue.remove();
        System.out.println(queue);

        queue.remove();
        System.out.println(queue);

        queue.remove();
        System.out.println(queue);
    }
}
