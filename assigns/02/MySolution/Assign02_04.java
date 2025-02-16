public class Assign02_04<T> implements Deque<T> {

    @Override
    public int size() {
        return 0;
    }

    @Override
    public boolean isFull() {
        return false;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public T takeout_at_beg() {
        return null;
    }

    @Override
    public void insert_at_beg(T x) {

    }

    @Override
    public T takeout_at_end() {
        return null;
    }

    @Override
    public void insert_at_end(T x) {

    }
    // Please give a list-based implementation of Deque
    // Note that the underlying list needs to be doubly-linked!
}
