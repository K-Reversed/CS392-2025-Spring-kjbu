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
public class Assign04_04 {

    public static void main(String[] argv) {
        // Please provide some testing code here
        OInt[] oInts = {
                new OInt(10),
                new OInt(10),
                new OInt(9),
                new OInt(8),
                new OInt(7),
                new OInt(6),
                new OInt(6),
                new OInt(5),
                new OInt(4),
                new OInt(3),
                new OInt(2),
                new OInt(1)
        };

        Integer[] integers = {10, 10, 9, 8, 7, 6, 6, 5, 4, 3, 2, 1};
        System.out.println(Arrays.toString(integers));
        Collections.shuffle(Arrays.asList(integers));
        System.out.println(Arrays.toString(integers));
        stableSort(integers);
        //stableSort(oInts);

        System.out.println(Arrays.toString(integers));
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

        //returns an unstable sorted array
        sort.sort(A);


    }

    static class OInt {
        private final Integer id;
        OInt(Integer id) {
            this.id = id;
        }

        public String toString() {
            return String.valueOf(this.id);
        }
    }

    static class ArrSort<T extends Comparable<T>> implements ArraySorter04<T> {
        @Override
        public void sort(T[] A) {
            int right = A.length;
            sortRecursion(A, 0, right);
        }

        private void sortRecursion (T[] A, int left, int right) {
            if (right <= left + 1) return;
            final int pivot = getPivot(left, right - 1);
            exchange(A, pivot, right - 1);
            final int mid = split(A, left, right);
            exchange(A, mid, right - 1);
            sortRecursion(A, left, mid);
            sortRecursion(A, mid + 1, right);
        }

        private int getPivot(int left, int right) {
            return (int) Math.floor(Math.random() * (right - left + 1) + left);
        }

        private static <T extends Comparable<T>> void exchange(T[] A, int i, int j) {
            T tmp;
            tmp = A[i];
            A[i] = A[j];
            A[j] = tmp;
        }

        private static <T extends Comparable<T>> int split(T[] A, int left, int right) {
            int pointer = left;
            T pivot = A[right - 1];
            for (int j = left; j <= right; j++) {
                if (less(A[pointer], pivot)) {
                    pointer += 1;
                } else break;
            }
            return splitRecursion(A, pointer, pointer + 1, pivot);
        }

        private static <T extends Comparable<T>> int splitRecursion(T[] A, int left, int right, T pivot) {
            var c = new Comparator<T>() {
                @Override
                public int compare(T o1, T o2) {
                    return o1.compareTo(o2);
                }
            };

            if (right < A.length - 1) {
                if (less(A[right], pivot)) {
                    System.out.println("less");
                    exchange(A, left, right);
                    return splitRecursion(A, left + 1, right + 1, pivot);
                } else if (c.compare(A[right], pivot) == 0) {
                    System.out.println("equal: " + A[left]);
                    exchange(A, left, right);
                    return splitRecursion(A, left + 1, right + 1, pivot);
                } else {
                    System.out.println("more");
                    return splitRecursion(A, left, right + 1, pivot);
                }
            }
            System.out.println(Arrays.toString(A));
            return left;
        }

        private static <T extends Comparable<T>> boolean less (T x, T y) {
            return (x.compareTo(y) < 0);
        }
    }
}
