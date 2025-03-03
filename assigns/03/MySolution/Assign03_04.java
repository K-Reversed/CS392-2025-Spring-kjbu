import java.util.Iterator;
import java.util.function.Consumer;

public class Assign03_04<T> extends Assign02_04<T> implements Iterable2<T> {
    // Please extend Assign02_04 with support for Iterable2
    private Node item;

    @Override
    public Iterator<T> iterator() {
        return new ListIteratorF<>(item);
    }

    @Override
    public Iterator<T> riterator() {
        return new ListIteratorR<>(item);
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

class ListIteratorF<T> implements Iterator<T> {
    private Node current;

    public ListIteratorF(Node list){
        current = list;
    }

    @Override
    public boolean hasNext() { return current != null; }

    @Override
    public T next() {
        if (!hasNext()) {
            System.out.println("There is no next value.");
            return null;
        }
        T item = (T) current.item;
        current = current.next;
        return item;
    }
}

class ListIteratorR<T> implements Iterator<T> {
    private Node current;

    public ListIteratorR(Node list){
        current = list;
    }

    @Override
    public boolean hasNext() { return current != null; }

    @Override
    public T next() {
        if (!hasNext()) {
            System.out.println("There is no previous value.");
            return null;
        }
        T item = (T) current.item;
        current = current.previous;
        return item;
    }
}
