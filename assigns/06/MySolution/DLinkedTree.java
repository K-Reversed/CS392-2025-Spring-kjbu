/**
 * @author Kevin Jiang (kjbu@bu.edu), Hongwei Xi
 * @version 1.0, 26 Apr 2025
 */

import java.util.*;

public class DLinkedTree{

    /* ************************ ************************ */
    // HX-2025-04-17: For Assignment 6-3 (100 points)
    /* ************************ ************************ */

    // An inner class for the nodes in the tree
    private static class Node {
        private int key;         // the key field
	    private int size;        // the size field
        private LLList data;     // list of data for this key
        private Node left, right;       // reference to the left/right child
	    private Node parent;     // reference to the parent node
        
        private Node(int key, Object data, int size) {
            this.key = key;
            this.data = new LLList();
            this.data.addItem(data, 0);
            left = right = parent = null;
            this.size = size;
        }
    }
    
    /* ************************ ************************ */

    private Node root;

    /* ************************ ************************ */

    public void inOrderPrint() {
        inOrderPrintTree(root);
        System.out.println();
    }

    public void preOrderPrint() {
        preOrderPrintTree(root);
        System.out.println();
    }

    public void postOrderPrint() {
        postOrderPrintTree(root);
        System.out.println();
    }

    /* ************************ ************************ */

    private static void inOrderPrintTree(Node root) {
        if (root != null){
            inOrderPrintTree(root.left);
            System.out.print(root.key + "(" + root.size + ") ");
            inOrderPrintTree(root.right);
        }
    }

    private static void preOrderPrintTree(Node root) {
        if (root != null){
            System.out.print(root.key + "(" + root.size + ") ");
            inOrderPrintTree(root.left);
            inOrderPrintTree(root.right);
        }
    }

    private static void postOrderPrintTree(Node root) {
        if (root != null){
            inOrderPrintTree(root.left);
            inOrderPrintTree(root.right);
            System.out.print(root.key + "(" + root.size + ") ");
        }
    }
    /* ************************ ************************ */

    private int size(Node node) {
        if (node == null) return 0;
        return size(node.left) + size(node.right) + 1;
    }

    /* ************************ ************************ */

    public LLList search(int key) {
        Node node = searchTree(root, key);
        if (node == null) {
            return null;
        }
        return node.data;
    }

    private static Node searchTree(Node root, int key) {
        if (root == null) return null;
        if (key == root.key) return root;
        if (key < root.key) return searchTree(root.left, key);
        return searchTree(root.right, key);
    }

    /* ************************ ************************ */

    public void insert(int key, Object item) {
        if (item == null) {
            delete(key);
            return;
        }
        root = insertTree(key, item, root, null);
    }

    private Node insertTree(int key, Object item, Node node, Node parent){
        if (node == null) return new Node(key, item, 1);
        if (key < node.key) {
            node.left = insertTree(key, item, node.left, node);
        } else if (key > node.key) {
            node.right = insertTree(key, item, node.right, node);
        } else {
            node.data = (LLList) item;
            node.parent = parent;
        }
        node.size = size(node.left) + size(node.right) + 1;
        return node;
    }

    /* ************************ ************************ */

    public LLList delete(int key) {
        if (root == null) return null;
        Node dump = deleteTree(key, root, null);
        if (dump == null) {
            return null;
        } else {
            return dump.data;
        }
    }

    private Node deleteTree(int key, Node node, Node parent) {
        if (key < node.key) {
            node.left = deleteTree(key, node.left, node);
        } else if (key > node.key) {
            node.right = deleteTree(key, node.right, node);
        } else {
            if (node.left == null) {
                return node.right;
            } else if (node.right == null) {
                return node.left;
            } else {
                Node temp = node;
                node = min(temp.right);
                node.right = deleteMin(temp.right);
                node.left = temp.left;
                node.parent = parent;
                root = node;
            }
        }
        node.size = size(node);
        return node;
    }

    private Node min (Node node) {
        if (node.left == null) return node;
        return min(node.left);
    }

    private Node deleteMin(Node node) {
        if (node.left == null) return node.right;
        node.left = deleteMin(node.left);
        node.size = size(node);
        return node;
    }

    /* ************************ ************************ */

    public void insertKeys(int[] keys) {
        for (int key : keys) {
            insert(key, "data for key " + key);
        }
    }

    /* ************************ ************************ */

    private Node reRoot(int key) {
        Node newRoot = searchTree(root, key);
        newRoot.parent = null;
        root = reRootTree(newRoot, root);
        return newRoot;
    }

    private Node reRootTree(Node newRoot, Node oldRoot) {
        if (newRoot == null) return null;
        if (newRoot.key != oldRoot.key) {
            insertTree(oldRoot.key, oldRoot.data, newRoot, null);
        }
        if (oldRoot.left != null) {
            reRootTree(newRoot, oldRoot.left);
        }
        if (oldRoot.right != null) {
            reRootTree(newRoot, oldRoot.right);
        }
        return newRoot;
    }

    /* ************************ ************************ */

    public static void main(String[] args) {
        try {
            DLinkedTree tree = new DLinkedTree();
            int[] keys = {37, 26, 42, 13, 35, 56, 30, 47, 70};
            tree.insertKeys(keys);

            tree.inOrderPrint();
            tree.preOrderPrint();
            tree.postOrderPrint();

            tree.delete(37);

            tree.inOrderPrint();
            tree.preOrderPrint();
            tree.postOrderPrint();

            System.out.println(tree.delete(42));

            tree.inOrderPrint();
            tree.preOrderPrint();
            tree.postOrderPrint();

            tree.delete(70);

            tree.inOrderPrint();
            tree.preOrderPrint();
            tree.postOrderPrint();

            tree.reRoot(30);

            tree.inOrderPrint();
            tree.preOrderPrint();
            tree.postOrderPrint();
        } catch (Exception e) {
            System.out.println("Exception thrown: " + e);
        }
    }
}
