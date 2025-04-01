/**
 * @author Kevin Jiang (kjbu@bu.edu), Hongwei Xi
 * @version 1.0, 25 Mar 2025
 */

import java.util.Arrays;

/**
 * @since v1.0
 */
public class Assign04_01 {
    // This class should not be instantiated.
    private Assign04_01() {}

    private static <T> void exchange(T[] A, int i, int j) {
		T tmp;
		tmp = A[i];
		A[i] = A[j];
		A[j] = tmp;
    }

    private static <T extends Comparable<T>> boolean less(T x, T y) {
		return (x.compareTo(y) < 0);
    }

    public static <T extends Comparable<T>> void sort(T[] A) {
		final int length = A.length;
		sortRecursion(A, 0, length);
    }

    private static <T> int getPivot(int left, int right) {
        return (int) Math.floor(Math.random() * (right - left + 1) + left); // HX: FIXME!!!
    }

    private static <T extends Comparable<T>> int split(T[] A, int left, int right) {
		int pointer = left;
		T pivot = A[right-1];
		while (true) {
			if (less(A[pointer], pivot)) {
				pointer += 1;
			} else break;
		}
		return splitRecursion(A, pointer, pointer + 1, pivot); // HX: we have pvt <= A[p1]
    }

    private static <T extends Comparable<T>> int splitRecursion(T[] A, int left, int right, T pivot) {
		// Please implement it according to the method presented in Lecture-03-18:
		// p1 and p2 are two pointers that both move from the left to the right
		// The entries ahead of p1 are less than the pivot
		// The entries between p1 and p2 are greater than or equal to the pivot
		// And p1 is finally returned once p2 reaches to the right end of the array A
		if (right < A.length - 1) {
			if (less(A[right], pivot)) {
				exchange(A, left, right);
				return splitRecursion(A, left + 1, right + 1, pivot);
			} else {
				return splitRecursion(A, left, right + 1, pivot);
			}
		}
		System.out.println(Arrays.toString(A));
		return left;
    }

    private static <T extends Comparable<T>> void sortRecursion(T[] A, int left, int right) {
		if (right <= left + 1) return;
		final int pivot = getPivot(left, right - 1);
		System.out.println("Pivot: " + pivot + "(" + A[pivot] + ")");
		exchange(A, pivot, right - 1); // HX: r-1 is good since r >= l+2
		final int mid = split(A, left, right);
		exchange(A, mid, right - 1);
		sortRecursion(A, left, mid);
		sortRecursion(A, mid + 1, right);
    }

    public static void main(String[] argv) {
		// Please provide some testing code here
		Integer[] integers = {10, 9, 8, 7, 6, 5, 4, 3, 2, 1, 0};
		System.out.println(Arrays.toString(integers));
		sort(integers);

		System.out.println("\n" + Arrays.toString(integers));
    }

}
