/**
 * @author Kevin Jiang (kjbu@bu.edu), Hongwei Xi
 * @version 1.1, 03 Mar 2025
 */
import java.util.Arrays;
import java.util.Comparator;

@SuppressWarnings({"rawtypes", "unchecked"})
public class Assign03_05 <T extends Comparable<T>> implements ArraySorter<T> {
    public static void main(String[] args) {
        String[] strings = {"Gulf", "Foxtrot", "Echo", "Delta", "Charlie", "Bravo", "Alpha"};
        System.out.println(Arrays.toString(strings));
        var test = new Assign03_05();
        test.ArraySorter().sort(strings);
        System.out.println(Arrays.toString(strings));
    }

    @Override
    public void sort(T[] A) {
	// please give a RECURSIVE implementation of INSERTION sort
        recursion(A, A.length);
    }

    private void recursion (T[] A, int len) {
        if (len <= 1) {
            return;
        }
        recursion(A, len - 1);
        T last = A[len - 1];
        int j = len - 2;
        System.out.println(new CompareString<T>().compare(A[j], last));
        while ((j >= 0) && new CompareString<T>().compare(A[j], last) > 0) {
            A[j + 1] = A[j];
            j--;
        }
        A[j + 1] = last;
        System.out.println(Arrays.toString(A));
    }

    public ArraySorter<T> ArraySorter() {
	// [ArraySorter] wrap the method [sort] inside an object
        return Assign03_05.this;
    }
}

class CompareString<T> implements Comparator<T> {

    @Override
    public int compare(T o1, T o2) {
        return o1.toString().compareToIgnoreCase(o2.toString());
    }
}