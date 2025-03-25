/**
 * @author Kevin Jiang (kjbu@bu.edu), Hongwei Xi
 * @version v1.0, 20 Feb 2025
 */
import java.util.Arrays;

/**
 * @since v1.0
 */
public class Assign02_02 {
    /*
      HX-2025-02-13: 10 points
      Recursion is a fundamental concept in programming.
      However, the support for recursion in Java is very limited.
      Nonetheless, we will be making extensive use of recursion in
      this class.
     */

    /*
    // This is a so-called iterative implementation:
    public static <T extends Comparable<T> > int indexOf(T[] a, T key) {
        int lo = 0;
        int hi = a.length - 1;
        while (lo <= hi) {
            // Key is in a[lo...hi] or not present.
            final int mid = lo + (hi - lo) / 2;
	    final int sign = key.compareTo(a[mid]);
            if      (sign < 0) hi = mid - 1;
            else if (sign > 0) lo = mid + 1;
            else return mid;
        }
        return -1;
    }
    */
    private static int lower;
    private static int upper;

    public static <T extends Comparable<T> > int indexOf(T[] a, T key) {
        if (lower <= upper) {
            int mid = lower + (upper - lower) / 2;
            int sign = key.compareTo(a[mid]);
            System.out.println("Lower limit: " + lower + ", Upper limit: " + upper + ", Midpoint: " + mid + ", Deviation: " + sign);
            if (sign == 0) {
                return mid;
            }
            if (sign < 0) {
                upper = mid - 1;
            } else {
                lower = mid + 1;
            }
            return indexOf(a, key);
        }
        return -1;
    }

    public static void main(String[] argv) {
	// Please write some testing code for your implementation of 'indexOf'
        String[] a = Arrays.asList("Alpha", "Bravo", "Charlie", "Delta", "Echo", "Foxtrot", "Gulf").toArray(new String[0]);
        System.out.println(a.length);
        lower = 0;
        upper = a.length - 1;
        System.out.println(indexOf(a, "Alpha"));
    }
}
