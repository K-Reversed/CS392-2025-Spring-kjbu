/**
 * @author Kevin Jiang (kjbu@bu.edu), Hongwei Xi
 * @version 1.0, 25 Mar 2025
 */
import java.util.Comparator;

/**
 * @since v1.0
 */
public class Assign04_02 {

        // This class should not be instantiated.
    private Assign04_02() { }

    private static <T extends Comparable<T>> boolean less(T x, T y) {
	return (x.compareTo(y) < 0);
    }

    public static <T extends Comparable<T>> void listSort(LList<T> xs) {
	    // Please implement [quicksort] on a linked list
	    // Note that you can choose to *always* use the first element
	    // to be the pivot for spliting. However, no extra heap memory
	    // is allowed in your implementation of list-quicksort.
    }

    public static void main(String[] argv) {
	    // Please provide some testing code here
    }
}
