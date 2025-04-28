/**
 * @author Kevin Jiang (kjbu@bu.edu), Hongwei Xi
 * @version 1.0, 26 Apr 2025
 */

public class Assign06_02 {
    private static class Node<T> {
	    // please declare Node for 2-3-trees
	    // Note that there two kinds of nodes:
	    // a 2-node (with 2 children) and a 3-node
	    // (with 3 children)
        private T left, right;
        private Node<T> nLeft, nCenter, nRight;
    }
    public boolean is23T(Node root) {
	    // please give a recursive implementation
	    // of [is23T] which test if a tree is indeed
	    // a valid 2-3-tree.
        if (root == null) return true;
        if (root.right == null) {
            if (root.nRight != null) return false;
            return is23T(root.nLeft) && is23T(root.nCenter);
        }
        return is23T(root.nLeft) && is23T(root.nCenter) && is23T(root.nRight);
    }
}
