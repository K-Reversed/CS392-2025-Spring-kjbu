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
@SuppressWarnings({"unchecked", "UnnecessaryBoxing", "removal", "CachedNumberConstructorCall"})
public class Assign04_04 {

    public static void main(String[] argv) {
        // Please provide some testing code here

        Integer[] integers = {
                new Integer(10),
                new Integer(10),
                new Integer(9),
                new Integer(8),
                new Integer(7),
                new Integer(6),
                new Integer(6),
                new Integer(5),
                new Integer(4),
                new Integer(3),
                new Integer(2),
                new Integer(1),
        };
        String[] strings = {
                new String("cat"),
                new String("cat"),
                new String("cat"),
                new String("cat"),
                new String("cat"),
                new String("mat"),
                new String("sat"),
                new String("sat"),
                new String("hat"),
                new String("hat"),
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

        //finds all duplicates in array, stored as identity hash
        for (int i = 0; i < A.length - 1; i++) {
            for (int j = i + 1; j < A.length; j++) {
                if (A[i].toString().equals(A[j].toString())) {
                    boolean iDupe = false;
                    boolean jDupe = false;
                    for (int dupe : dupes) {
                        if (dupe == System.identityHashCode(A[i])) {
                            iDupe = true;
                            break;
                        }
                    }
                    if (!iDupe) {
                        dupes[index] = System.identityHashCode(A[i]);
                        index++;
                    }
                    for (int dupe : dupes) {
                        if (dupe == System.identityHashCode(A[j])) {
                            jDupe = true;
                            break;
                        }
                    }
                    if (!jDupe) {
                        dupes[index] = System.identityHashCode(A[j]);
                        index++;
                    }
                    System.out.println(A[i] + ":" + System.identityHashCode(A[i]) + ", " + A[j] + ":" + System.identityHashCode(A[j]));
                    System.out.println(Arrays.toString(dupes));
                }
            }
        }
        System.out.println(Arrays.toString(dupes));

        //returns a sorted array
        sort.sort(A);

        //compares sorted list to duplicate list, if sorted correctly, each pair should correspond to a section of the duplicate list. A single element marks the end of a list of duplicates. An empty array means no duplicates at that point.
        for (int i = 0; i < A.length - 1; i++) {
            System.out.print("[");
            int tmp = System.identityHashCode(A[i]);
            int tmp1 = System.identityHashCode(A[i + 1]);
            for (int j = 0; j < dupes.length; j++) {
                if (dupes[j] == 0) {
                    System.out.println("]");
                    break;
                }
                if (tmp == dupes[j]) {
                    if (tmp1 == dupes[j + 1]) {
                        System.out.print(tmp + ", ");
                        System.out.print(tmp1);
                    } else {
                        System.out.print(tmp);

                    }
                }
            }

        }

        System.out.println(Arrays.toString(dupes));
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
