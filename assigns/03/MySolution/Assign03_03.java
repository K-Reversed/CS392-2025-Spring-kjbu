import java.util.Iterator;
import java.util.function.Consumer;

public class Assign03_03<T> extends Assign02_03<T> implements Iterable2<T> {
    // Please extend Assign02_03 with support for Iterable2
    private T[] item;

    @Override
    public Iterator<T> iterator() {
        return new ArrayIteratorF<>(item);
    }

    @Override
    public Iterator<T> riterator() {
        return new ArrayIteratorR<>(item);
    }

    @Override
    public void forEach(Consumer<? super T> action) {
        while (iterator().hasNext()){
            action.accept(iterator().next());
        }
    }

    @Override
    public void rforEach(Consumer<? super T> action) {
        while (riterator().hasNext()) {
            action.accept(riterator().next());
        }
    }
}

class ArrayIteratorF<T> implements Iterator<T> {
    private final T[] arr;
    private int index;

    public ArrayIteratorF(T[] arr){
        this.arr = arr;
        index = 0;
    }

    @Override
    public boolean hasNext() { return index < arr.length; }

    @Override
    public T next() {
        if (!hasNext()) {
            System.out.println("There is no next value.");
            return null;
        }
        return arr[index++];
    }

}

class ArrayIteratorR<T> implements Iterator<T> {
    private final T[] arr;
    private int index;

    public ArrayIteratorR(T[] arr){
        this.arr = arr;
        index = 0;
    }

    @Override
    public boolean hasNext() { return index > 0; }

    @Override
    public T next() {
        if (!hasNext()) {
            System.out.println("There is no previous value");
            return null;
        }
        return arr[index--];
    }
}