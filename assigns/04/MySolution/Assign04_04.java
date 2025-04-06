/**
 * @author Kevin Jiang (kjbu@bu.edu), Hongwei Xi
 * @version 1.0, 25 Mar 2025
 */
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

/**
 * @since v1.0
 */
@SuppressWarnings({"unchecked", "UnnecessaryBoxing"})
public class Assign04_04 {

    public static void main(String[] argv) {
        // Please provide some testing code here

        Integer[] integers = new Integer[]{
                Integer.valueOf(10),
                Integer.valueOf(10),
                Integer.valueOf(9),
                Integer.valueOf(8),
                Integer.valueOf(7),
                Integer.valueOf(6),
                Integer.valueOf(6),
                Integer.valueOf(5),
                Integer.valueOf(4),
                Integer.valueOf(3),
                Integer.valueOf(2),
                Integer.valueOf(1),
        };
        String[] strings = {
                new String("z"),
                new String("cat"),
                new String("cat"),
                new String("cat"),
                new String("r"),
                new String("y"),
                new String("p"),
                new String("p"),
                new String("q"),
                new String("g"),
                };
        System.out.println(Arrays.toString(integers));
        Collections.shuffle(Arrays.asList(integers));
        System.out.println(Arrays.toString(integers));
        stableSort(integers);
        System.out.println(Arrays.toString(strings));
        Collections.shuffle(Arrays.asList(strings));
        System.out.println(Arrays.toString(strings));
        stableSort(strings);

        System.out.println(Arrays.toString(integers));
        System.out.println(Arrays.toString(strings));
    }

    // This class should not be instantiated.
    private Assign04_04() { }

    public static <T extends Comparable<T>> void stableSort(T[] A) {
	// Note that ArraySorter.sort may not be stable.
	// Please implement a stable sorting function by calling
	// the sort function in ArraySorter. You need to briefly
	// explain as to why your implementation actually works (that is,
	// it does stable sorting).
	// You must use ArraySorter.sort (instead of implementing your own
	// stable sorting function directly.)

        var sort = new ArrSort<T>();
        int index = 0;
        int[] dupes = new int[A.length * 2];

        for (int i = 0; i < A.length - 1; i++) {
            for (int j = i + 1; j < A.length; j++) {
                if (A[i].toString().equals(A[j].toString())) {
                    dupes[index] = System.identityHashCode(A[i]);
                    index++;
                    dupes[index] = System.identityHashCode(A[j]);
                    index++;
                    System.out.println(A[i] + ":" + System.identityHashCode(A[i]) + ", " + A[j] + ":" + System.identityHashCode(A[j]));
                }
            }
        }

        //returns a sorted array
        sort.sort(A);

        for (int i = 0; i < A.length - 1; i++) {
            int tmp = System.identityHashCode(A[i]);
            int tmp1 = System.identityHashCode(A[i + 1]);
            for (int j = 0; j < dupes.length; j++) {
                if (dupes[j] == 0) {
                    break;
                }
                if (tmp == dupes[j]) {
                    if (tmp1 == dupes[j + 1]) {
                        System.out.println(A[i] + " " + tmp1 + " : " + A[i + 1] + " " + dupes[j + 1]);
                    }
                }
            }
        }
    }

    @SuppressWarnings("unchecked")
    static class LinkedList<T> {
        private int size;
        private LList<T> top;

        public void insert(T x) {
            var data = new LList<>(x);
            data.next = null;
            if (top == null) {
                top = data;
            } else {
                LList<T> tmp = top;
                while (tmp.next != null) {
                    tmp = tmp.next;
                }
                tmp.next = data;
            }
            size++;
        }

        public T peek(int index) {
            LList<T> tmp = top;
            for (int i = 0; i < index; i++) {
                tmp = tmp.next;
            }
            return tmp.elem;
        }

        public T[] toArray() {
            T[] array = (T[]) new Object[size];
            for (int i = 0; i < size; i++) {
                array[i] = peek(i);
            }
            return array;
        }
    }

    @SuppressWarnings("unchecked")
    static class ArrSort<T extends Comparable<T>> implements ArraySorter04<T> {
        @Override
        public void sort(T[] A) {
            if (A.length <= 1) return;
            boolean sorted = false;
            for (int i = 0; i < A.length - 1; i++) {
                if ((A[i].compareTo(A[i + 1]) <= 0)) {
                    sorted = true;
                } else {
                    sorted = false;
                    break;
                }
            }
            if (sorted) {
                return;
            }
            final int mid = getPivot(0, A.length - 1);
            final T pivot = A[mid];
            System.out.println(mid + ", " + pivot);
            var lesser = new LinkedList<>();
            var greater = new LinkedList<>();

            for (int i = 0; i < A.length; i++) {
                if (i != mid) {
                    if (A[i].compareTo(pivot) < 0) {
                        lesser.insert(A[i]);

                    } else if (A[i].compareTo(pivot) > 0) {
                        greater.insert(A[i]);

                    } else {
                        if (i < mid) {
                            lesser.insert(A[i]);
                        } else {
                            greater.insert(A[i]);
                        }
                    }
                    System.out.println(Arrays.toString(lesser.toArray()) + " " + pivot + " " + Arrays.toString(greater.toArray()));
                }
            }

            int size = 0;
            for (int i = 0; i < lesser.toArray().length; i++) {
                A[size] = (T) lesser.toArray()[i];
                size ++;
            }
            A[size] = pivot;
            size ++;
            for (int i = 0; i < greater.toArray().length; i++) {
                A[size] = (T) greater.toArray()[i];
                size ++;
            }

            sort(A);
        }

        private int getPivot(int left, int right) {
            return (int) Math.floor(Math.random() * (right - left + 1) + left);
        }
    }
}
