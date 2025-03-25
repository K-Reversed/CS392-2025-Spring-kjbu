/**
 * @author Kevin Jiang (kjbu@bu.edu), Hongwei Xi
 * @version 1.1, 22 Mar 2025
 */
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.function.Consumer;

/**
 * @since v1.0
 */
public class Assign03_04<T> extends Assign02_04<T> implements Iterable2<T> {
    // Please extend Assign02_04 with support for Iterable2
    /**
     * @since v1.1
     */
    public static void main(String[] args) {
        var iterableList = new Assign03_04<>();
        iterableList.insert_at_beg("zero");
        iterableList.insert_at_beg(1.0);
        iterableList.insert_at_beg('t');
        iterableList.insert_at_beg("three");
        iterableList.insert_at_beg(4);
        iterableList.insert_at_beg(5.0);
        iterableList.insert_at_beg('s');
        iterableList.insert_at_beg("seven");
        iterableList.insert_at_beg(8);
        iterableList.insert_at_beg(9.0);
        System.out.println(iterableList.getTop().item);
        System.out.println(iterableList.getBottom().item);
        System.out.println(iterableList);

        System.out.println("Top => Bottom");
        iterableList.forEach(System.out::println);

        System.out.println("Bottom => Top");
        iterableList.rforEach(System.out::println);
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<>() {
            private int index = 0;
            private Node node = getTop();
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
                T item = (T) node.item;
                node = node.next;
                index++;
                return item;
            }
        };
    }

    @Override
    public Iterator<T> riterator() {
        return new Iterator<>() {
            private int index = size();
            private Node node = getBottom();
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
                T item = (T) node.item;
                node = node.previous;
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

