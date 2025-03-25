/**
 * @author Kevin Jiang (kjbu@bu.edu), Hongwei Xi
 * @version v1.0, 20 Feb 2025
 */
public class Assign02_05 {

    /** Takes an ordered array and determines whether there exist indices i and j and k such that A[i] + A[j] = A[k]
     * @param A An array of ordered integers
     * @return True if A[i] + A[j] = A[k]
     */
    public static boolean solve_3sum(Integer[] A) {
        int k;
        for (int i = 0; i < A.length - 1; i++) {
            for (int j = 0; j < A.length - 1; j++) {
                k = A[i] + A[j];
                System.out.println(A[i] + " + " + A[j] + ", " + k);
                if (binarySearch(A, 0, A.length - 1, k)) {
                    return true;
                }

            }
        }
        return false;
    }

    /** Performs a binary search to find the key
     * @param arr An array of ordered integers
     * @param lower The lower bound of the array
     * @param upper The upper bound of the array
     * @param key The number being searched for
     * @return True if key is found within the array
     */
    public static boolean binarySearch (Integer[] arr, int lower, int upper, int key) {
        if (lower <= upper) {
            int mid = (upper + lower) / 2;
            System.out.println(arr[mid] + ", " + key);
            if (arr[mid] == key) {
                return true;
            } else {
                if (arr[mid] > key) {
                    System.out.println("Lower");
                    return binarySearch(arr, lower, mid - 1, key);
                } else {
                    System.out.println("Higher");
                    return binarySearch(arr, mid + 1, upper, key);
                }
            }
        }
        System.out.println("Result not found.");
        return false;
    }

    public static void main(String[] argv) {
        Integer[] integers = {1, 3, 5, 7, 9, 11, 13, 15, 17, 19};
        System.out.println(solve_3sum(integers));
    }
}
