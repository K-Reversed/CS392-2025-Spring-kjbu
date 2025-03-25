/**
 * @author Kevin Jiang (kjbu@bu.edu), Hongwei Xi
 * @version v1.2, 22 Mar 2025
 */
import java.util.Arrays;



/**
 * @since v1.0
 */
@SuppressWarnings({"unchecked"})
public class Assign02_03<T> implements Deque<T> {
    // Please give an array-based implementation of Deque
    private int arrSize, front;
    private final int arrayMax;
    private final T[] array;

    public static void main(String[] args) {
        var dqArr = new Assign02_03<>();
        System.out.println(Arrays.toString(dqArr.array));
        dqArr.insert_at_beg("Slug");
        dqArr.insert_at_beg(57);
        dqArr.insert_at_beg(58);
        dqArr.insert_at_beg(59);
        dqArr.insert_at_beg(60);
        dqArr.insert_at_beg(61);
        dqArr.insert_at_beg(62);
        dqArr.insert_at_beg(63);
        dqArr.insert_at_beg(64);
        dqArr.insert_at_end('x');
        System.out.println(dqArr.size());
        System.out.println(Arrays.toString(dqArr.array));
        for (int i = 0; i < 3; i++) {
            System.out.println(dqArr.takeout_at_end());
            System.out.println(dqArr.takeout_at_beg());
        }
        System.out.println(Arrays.toString(dqArr.array));
        dqArr.insert_at_beg(65);
        dqArr.insert_at_end(66);
        dqArr.insert_at_end(67);
        dqArr.insert_at_end(68);
        dqArr.insert_at_end(69);
        dqArr.insert_at_end(70);
        dqArr.insert_at_end(71);
        System.out.println(Arrays.toString(dqArr.array));
    }

    public Assign02_03(){
        arrSize = front = 0;
        arrayMax = 10;
        array = (T[]) new Object[arrayMax];

    }

    @Override
    public int size() {
        return arrSize;
    }

    @Override
    public boolean isFull() {
        return arrSize == arrayMax;
    }

    @Override
    public boolean isEmpty() {
        return arrSize == 0;
    }

    @Override
    public T takeout_at_beg() {
        if (isEmpty()) {
            System.out.println("List is Empty!");
            return null;
        }
        T item = array[front];
        array[front] = null;
        front = (front + 1) % arrayMax;
        arrSize--;
        return item;
    }

    @Override
    public void insert_at_beg(T x) {
        if (isFull()) {
            System.out.println("List is Full!");
            return;
        }
        front = (front - 1 + arrayMax) % arrayMax;
        array[front] = x;
        arrSize++;
    }

    @Override
    public T takeout_at_end() {
        if (isEmpty()) {
            System.out.println("List is Empty!");
            return null;
        }
        int rear = (front + arrSize - 1) % arrayMax;
        T item = array[rear];
        array[rear] = null;
        arrSize--;
        return item;
    }

    @Override
    public void insert_at_end(T x) {
        if (isFull()) {
            System.out.println("List is Full!");
            return;
        }
        int rear = (front + arrSize) % arrayMax;
        array[rear] = x;
        arrSize++;
    }

    /**
     * @since v1.2
     */
    public String toString() {
        return Arrays.toString(array);
    }

    /**
     * @since v1.2
     */
    public T[] getArray() {
        return array;
    }

    /**
     * @since v1.2
     */
    public int getFront() {
        return front;
    }
}