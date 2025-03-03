import edu.princeton.cs.algs4.In;

public class Assign03_01 {
    /*
      HX-2025-02-25:
      The following implementation is recursive
     */
    public static int mystery(int x) {
	    return (x < 100 ? 100 - x : mystery(mystery(x-11)));
    }
    public static void main(String[] argv) {
        int min = 0;
        int max = Integer.MAX_VALUE;
        System.out.println(binarySearch(min, max));

        /*
        int i = 0;
        try {
            for (i = 0; i < max; i++) {
                mystery(i);
            }
        } catch (StackOverflowError e) {
            System.out.println(i);
        }
        */

        //mystery(224327);

    }
    public static int binarySearch(int left, int right) {
        System.out.println(left + ", " + right);
        if (left + 1 >= right) {
            return right;
        }
        final int mid = left + (right - left) / 2;

        if (overFlowTest(mid)) {
                return binarySearch(left, mid);
        } else {
                return binarySearch(mid, right);
        }
    }

    private static boolean overFlowTest(int x) {
        try {
            System.out.println(mystery(x));
            return false;
        } catch (StackOverflowError e) {
            return true;
        }
    }
}
