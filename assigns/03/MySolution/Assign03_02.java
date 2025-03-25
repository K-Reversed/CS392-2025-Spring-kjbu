/**
 * @author Kevin Jiang (kjbu@bu.edu), Hongwei Xi
 * @version 1.1, 22 Mar 2025
 */
import java.util.*;

/**
 * @since v1.0
 */
public class Assign03_02{
    public static <T> boolean stableSort(ArraySorter<T> sorter, T[] A) {
	    // Please use the given [sorter] to sort T[] A. The return value
	    // (true or false) should indicate whether the sorting done is stable
        int index = 0;
        int[] dupes = new int[A.length * 2];

        boolean isStable = false;
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

        System.out.println(Arrays.toString(dupes));

        sorter.sort(A);

        for (int i = 0; i < A.length - 1; i++) {
            int tmp = System.identityHashCode(A[i]);
            int tmp1 = System.identityHashCode(A[i + 1]);
            for (int j = 0; j < dupes.length; j++) {
                if (dupes[j] == 0) {
                    break;
                }
                if (tmp == dupes[j]) {
                    if (tmp1 == dupes[j + 1]) {
                        isStable = true;
                    }
                }
            }
        }

        if (dupes[0] == 0) {
            System.out.println("No duplicates found, sort is assumed to be stable.");
            isStable = true;
        }

        return isStable;
    }

    public static void main(String[] args){
        var sorter = new ArraySorter<>() {
            @Override
            public void sort(Object[] A) {
                int len = A.length;
                for (int i = 0; i < len; i++) {
                    Object key = A[i];
                    int j = i - 1;
                    while ((j >= 0) && (A[j].toString().hashCode() > key.toString().hashCode())) {
                        A[j + 1] = A[j];
                        j--;
                    }
                    A[j + 1] = key;
                }
            }
        };
        
        OInt[] integers = {
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

        System.out.println(Arrays.toString(integers));
        Collections.shuffle(Arrays.asList(integers));
        System.out.println(Arrays.toString(integers));
        System.out.println(stableSort(sorter, integers));
        System.out.println(Arrays.toString(integers));
    }
}

class OInt{
    private final int id;
    OInt(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return String.valueOf(this.id);
    }
}