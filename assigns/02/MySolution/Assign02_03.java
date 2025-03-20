/**
 * @author Kevin Jiang (kjbu@bu.edu), Hongwei Xi
 * @version 1.0, 20 Feb 2025
 */
import java.util.Arrays;

@SuppressWarnings({"unchecked", "rawtypes"})
public class Assign02_03<T> implements Deque<T> {
    // Please give an array-based implementation of Deque
    private int arrSize, front;
    private final int arrMaxSize;
    private final Object[] array;

    /*
    public static void main(String[] args) {
        var dqArr = new Assign02_03();
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
     */

    public Assign02_03(){
        arrSize = front = 0;
        arrMaxSize = 10;
        array = new Object[arrMaxSize];
    }

    @Override
    public int size() {
        return arrSize;
    }

    @Override
    public boolean isFull() {
        return arrSize == arrMaxSize;
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
        T item = (T) array[front];
        array[front] = null;
        front = (front + 1) % arrMaxSize;
        arrSize--;
        return item;
    }

    @Override
    public void insert_at_beg(T x) {
        if (isFull()) {
            System.out.println("List is Full!");
            return;
        }
        front = (front - 1 + arrMaxSize) % arrMaxSize;
        array[front] = x;
        arrSize++;
    }

    @Override
    public T takeout_at_end() {
        if (isEmpty()) {
            System.out.println("List is Empty!");
            return null;
        }
        int rear = (front + arrSize - 1) % arrMaxSize;
        T item = (T) array[rear];
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
        int rear = (front + arrSize) % arrMaxSize;
        array[rear] = x;
        arrSize++;
    }
}
