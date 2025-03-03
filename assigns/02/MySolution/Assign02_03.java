/**
 * @author Kevin Jiang (kjbu@bu.edu), Hongwei Xi
 * @version 1.0, 20 Feb 2025
 */
import java.util.Arrays;

@SuppressWarnings({"unchecked", "rawtypes"})
public class Assign02_03<T> implements Deque<T> {
    // Please give an array-based implementation of Deque
    private int arrSize;
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
        System.out.println(Arrays.toString(dqArr.array));
    }

     */

    public Assign02_03(){
        arrSize = 0;
        arrMaxSize = 10;
        array = new Object[10];
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
        T item = (T) array[arrSize - 1];
        array[arrSize - 1] = null;
        arrSize--;
        return item;
    }

    @Override
    public void insert_at_beg(T x) {
        if (isFull()) {
            System.out.println("List is Full!");
            return;
        }
        array[arrSize] = x;
        arrSize++;
    }

    @Override
    public T takeout_at_end() {
        if (isEmpty()) {
            System.out.println("List is Empty!");
            return null;
        }
        T item = (T) array[0];
        array[0] = null;
        for (int i = 0; i < arrSize - 1; i++) {
            array[i] = array[i + 1];
            array[i + 1] = null;
        }

        arrSize--;
        return item;
    }

    @Override
    public void insert_at_end(T x) {
        if (isFull()) {
            System.out.println("List is Full!");
            return;
        }
        for (int i = arrSize; i > 0; i--) {
            array[i] = array[i - 1];
        }

        array[0] = x;
        arrSize++;
    }
}
