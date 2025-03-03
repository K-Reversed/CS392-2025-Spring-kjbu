import java.util.Comparator;

public
class
Assign03_05 <T extends Comparable<T>> implements ArraySorter<T> {
    private static int n;

    public static void main(String[] args) {
        String[] strings = {"Alpha", "Bravo", "Charlie", "Delta", "Echo", "Foxtrot", "Gulf"};
        n = strings.length;
    }

    @Override
    public void sort(T[] A) {
	// please give a RECURSIVE implementation of INSERTION sort

        if (n <= 1) {
            return;
        }
        n--;
        sort(A);

        T bottom = A[n];
        int j = n - 1;

        while (j >= 0 && A[j].compareTo(bottom) > 0) {
            A[j + 1] = A[j];
            j--;
        }
        A[j + 1] = bottom;
    }

    public ArraySorter<T> ArraySorter() {
	// [ArraySorter] wrap the method [sort] inside an object
        return null;
    }
}
