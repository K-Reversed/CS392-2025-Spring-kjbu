/**
 * @author Kevin Jiang (kjbu@bu.edu), Hongwei Xi
 * @version 1.0, 25 Mar 2025
 */
import java.util.Comparator;

/**
 * @since v1.0
 */
public class Assign04_03 {

    // This class should not be instantiated.
    private Assign04_03() { }

    private static <T extends Comparable<T>> boolean less(T x, T y) {
	return (x.compareTo(y) < 0);
    }

    public static <T extends Comparable<T>> void listSort(LList<T> xs) {
	    // Please implement [mergesort] on a linked list
	    // Note that no extra heap memory is needed for list-mergesort
        var c = new Comparator<T>() {
            @Override
            public int compare(T o1, T o2) {
                return o1.compareTo(o2);
            }
        };

        System.out.println(mergesort(c, xs));
    }

    private static <T extends Comparable<T>> LList<T> mergesort(Comparator<T> c, LList<T> list) {
        if (list == null || list.next == null) {
            return list;
        }

        LList<T> right = split(list);
        System.out.println(list + " : " + right);

        list = mergesort(c, list);
        right = mergesort(c, right);

        return merge(c, list, right);
    }

    private static <T extends Comparable<T>> LList<T> split (LList<T> list) {
        LList<T> i, j, k;
        i = j = list;

        while (i != null && i.next != null) {
            i = i.next.next;
            if (i != null) {
                j = j.next;
            }
        }
        k = j.next;
        j.next = null;
        return k;
    }

    private static <T extends Comparable<T>> LList<T> merge (Comparator<T> c, LList<T> left, LList<T> right) {
        if (left == null) {
            return right;
        }
        if (right == null) {
            return left;
        }
        if (c.compare(left.elem, right.elem) < 0) {
            left.next = merge(c, left.next, right);
            return left;
        } else {
            right.next = merge(c, left, right.next);
            return right;
        }
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
