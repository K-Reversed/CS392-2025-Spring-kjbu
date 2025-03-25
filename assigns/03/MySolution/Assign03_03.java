/**
 * @author Kevin Jiang (kjbu@bu.edu), Hongwei Xi
 * @version v1.1, 22 Mar 2025
 */
import java.util.Iterator;
import java.util.function.Consumer;
import java.util.NoSuchElementException;

/**
 * @since v1.0
 */
public class Assign03_03<T> extends Assign02_03<T> implements Iterable2<T> {
    // Please extend Assign02_03 with support for Iterable2
    /**
     * @since v1.1
     */
    public static void main(String[] args) {
        var iterableArray = new Assign03_03<>();
        iterableArray.insert_at_beg(10);
        iterableArray.insert_at_beg("test");
        iterableArray.insert_at_beg('c');
        iterableArray.insert_at_beg(1.0);
        iterableArray.insert_at_beg(9);
        iterableArray.insert_at_beg(8);
        iterableArray.insert_at_beg(7);
        iterableArray.insert_at_beg(6);
        iterableArray.insert_at_beg(5);

        System.out.println(iterableArray);
        System.out.println(iterableArray.size());

        System.out.println("Forward");
        iterableArray.forEach(System.out::println);
        System.out.println("Reverse");
        iterableArray.rforEach(System.out::println);
    }

    /**
     * @since v1.0
     */
    @Override
    public Iterator<T> iterator() {
        return new Iterator<>() {
            private int index = 0;
            @Override
            public boolean hasNext() {
                return index <= size() - 1;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    System.out.println("There is no next value.");
                    throw new NoSuchElementException();
                }
                T item = getArray()[(index + getFront()) % getArray().length];
                index++;
                return item;
            }
        };
    }

    @Override
    public Iterator<T> riterator() {
        return new Iterator<>() {
            private int index = size();
            @Override
            public boolean hasNext() {
                return index > 0;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    System.out.println("There is no next value.");
                    throw new NoSuchElementException();
                }
                T item = getArray()[(getFront() + size() + index) % getArray().length];
                index--;
                return item;
            }
        };
    }

    @Override
    public void forEach(Consumer<? super T> action) {
        Iterator<T> it = iterator();
        while (it.hasNext()){
            action.accept(it.next());
        }
    }

    @Override
    public void rforEach(Consumer<? super T> action) {
        Iterator<T> rit = riterator();
        while (rit.hasNext()) {
            action.accept(rit.next());
        }
    }
}