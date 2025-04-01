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

    private static <T extends Comparable<T>> void exchange(LList<T> left, LList<T> right) {
        System.out.println(left.elem + " exchanged with " + right.elem);
        T tmp = right.elem;
        right.elem = left.next.elem;
        left.next.elem = tmp;
    }

    private static <T extends Comparable<T>> LList<T> getEnd(LList<T> list) {
        while (list != null && list.next != null) {
            list = list.next;
        }
        return list;
    }

    public static <T extends Comparable<T>> void listSort(LList<T> xs) {
	    // Please implement [quicksort] on a linked list
	    // Note that you can choose to *always* use the first element
	    // to be the pivot for splitting. However, no extra heap memory
	    // is allowed in your implementation of list-quicksort.
        LList<T> right = getEnd(xs);
        System.out.println(xs.elem);
        System.out.println(right.elem);
        var c = new Comparator<T>() {
            @Override
            public int compare(T o1, T o2) {
                return o1.compareTo(o2);
            }
        };
        quickSort(c, xs, right);
        System.out.println(xs);
    }

    private static <T extends Comparable<T>> void quickSort(Comparator<T> c, LList<T> left, LList<T> right) {
        if (left == null || left == right) {
            return;
        }
        LList<T> pivot = partition(c, left, right);
        System.out.println("Pivot: " + pivot.elem);
        quickSort(c, left, pivot);
        quickSort(c, pivot.next, right);
    }

    private static <T extends Comparable<T>> LList<T> partition(Comparator<T> c, LList<T> left, LList<T> right) {
        System.out.println("Pivot: " + left.elem);
        LList<T> i = left;
        LList<T> j = left;
        while (j != right.next) {
            if (c.compare(j.elem, left.elem) < 0) {
                exchange(i, j);
                i = i.next;
            }
            j = j.next;
        }
        T tmp = left.elem;
        left.elem = i.elem;
        i.elem = tmp;
        System.out.println(left);
        return i;
    }

    public static void main(String[] argv) {
	    // Please provide some testing code here
            var nodeList = new LList<>(10);
            nodeList.next = new LList<>(9);
            nodeList.next.next = new LList<>(8);
            nodeList.next.next.next = new LList<>(7);
            nodeList.next.next.next.next = new LList<>(6);
            nodeList.next.next.next.next.next = new LList<>(5);
            nodeList.next.next.next.next.next.next = new LList<>(4);
            nodeList.next.next.next.next.next.next.next = new LList<>(3);
            nodeList.next.next.next.next.next.next.next.next = new LList<>(2);
            nodeList.next.next.next.next.next.next.next.next.next = new LList<>(1);
            nodeList.next.next.next.next.next.next.next.next.next.next= new LList<>(0);
            System.out.println(nodeList);
            listSort(nodeList);
            System.out.println("\n"+ nodeList);
    }
}
