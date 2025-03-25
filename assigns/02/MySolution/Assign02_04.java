/**
 * @author Kevin Jiang (kjbu@bu.edu), Hongwei Xi
 * @version v1.2, 22 Feb 2025
 */

/**
 * @since v1.0
 */
class Node {
    Object item;
    Node previous, next;
}

/**
 * @since v1.0
 */
@SuppressWarnings({"unchecked", "rawtypes"})
public class Assign02_04<T> implements Deque<T> {
    // Please give a list-based implementation of Deque
    // Note that the underlying list needs to be doubly-linked!
    private int listSize;
    private final int listMax;
    private Node top;
    private Node bottom;

    public static void main(String[] args) {
        var dqList = new Assign02_04();
        dqList.insert_at_beg("bob");
        System.out.println(dqList.top.item);
        System.out.println(dqList.bottom.item);
        dqList.insert_at_beg(7.77);
        System.out.println(dqList.top.item);
        System.out.println(dqList.bottom.item);
        dqList.insert_at_end(57);
        System.out.println(dqList.top.item);
        System.out.println(dqList.bottom.item);
        dqList.insert_at_end('a');
        System.out.println(dqList.top.item);
        System.out.println(dqList.bottom.item);
        dqList.insert_at_end("hmm");
        System.out.println(dqList.top.item);
        System.out.println(dqList.bottom.item);
        dqList.takeout_at_beg();
        System.out.println(dqList.top.item);
        System.out.println(dqList.bottom.item);
        dqList.takeout_at_end();
        System.out.println(dqList.top.item);
        System.out.println(dqList.bottom.item);
        System.out.println(dqList.size());
    }

    /**
     * @since v1.0
     */
    public Assign02_04() {
        listSize = 0;
        listMax = 10;
        top = bottom = null;
    }

    @Override
    public int size() {
        return listSize;
    }

    @Override
    public boolean isFull() {
        return listSize == listMax;
    }

    @Override
    public boolean isEmpty() {
        return top == null || bottom == null;
    }

    @Override
    public T takeout_at_beg() {
        if (isEmpty()) {
            System.out.println("List is Empty!");
            return null;
        }
        Node delete = top;
        top = top.next;
        if (isEmpty()) {
            top = bottom = null;
        }
        top.previous = null;
        listSize--;
        return (T) delete.item;
    }

    @Override
    public void insert_at_beg(T x) {
        if (isFull()) {
            System.out.println("List is Full!");
            return;
        }
        Node data = new Node();
        data.item = x;
        if (isEmpty()) {
            top = bottom = data;
        } else {
            data.next = top;
            top.previous = data;
            top = data;
        }
        listSize++;
    }

    @Override
    public T takeout_at_end() {
        if (isEmpty()) {
            System.out.println("List is Empty!");
            return null;
        }
        Node delete = bottom;
        bottom = bottom.previous;
        if (isEmpty()) {
            top = bottom = null;
        }
        bottom.next = null;
        listSize--;
        return (T) delete.item;
    }

    @Override
    public void insert_at_end(T x) {
        if (isFull()) {
            System.out.println("List is Full!");
            return;
        }
        Node data = new Node();
        data.item = x;
        if (isEmpty()) {
            top = bottom = data;
        } else {
            data.previous = bottom;
            bottom.next = data;
            bottom = data;
        }
        listSize++;
    }

    public String toString() {
        String result = "[";
        Node current = top;
        while (current.next != null) {
            result += current.item + ", ";
            current = current.next;
            if (current.next == null) {
                result += current.item;
            }
        }
        result += "]";
        return result;
    }

    public Node getTop() {
        return top;
    }

    public Node getBottom() {
        return bottom;
    }
}

