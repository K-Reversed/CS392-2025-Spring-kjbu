/**
 * @author Kevin Jiang (kjbu@bu.edu), Hongwei Xi
 * @version 1.0, 26 Apr 2025
 */

public class Assign06_01 {
    final int R = 0; // for red
    final int B = 1; // for black
    private class Node {
	    private int color;
	    private Node left;
	    private Node right;
    }
    public boolean isRBT(Node root) {
	    // please give a recursive implementation
	    // of [isRBT] which test if a tree is indeed
	    // a valid red-black-tree.
        if (root.color == R) return false;
        return checkRecursion(root);
    }

    private boolean checkRecursion(Node root) {
        if (root == null) {
            return true;
        }
        if (root.color == R) {
            if (root.left.color == R || root.right.color == R) {
                return false;
            }
        }
        return checkRecursion(root.left) && checkRecursion(root.right);
    }
}
