import java.util.Arrays;
import java.util.NoSuchElementException;

@SuppressWarnings("rawtypes")
public class DFSforCS392<T> {
    private static class Node<T>{
        private T data;
        private boolean checked;
        private LinkedList<Node<T>> neighbors;
        private Node<T> next;

        public Node() {}

        public Node(T data) {
            this.data = data;
            this.neighbors = new LinkedList<>();
        }

        public void addNeighbor (Node<T> node) {
            this.neighbors.insert(node);
        }
    }

    private static class LinkedList<T> {
        private int size;
        private LList<T> top;

        public void insert(T x) {
            var data = new LList<>(x);
            data.next = null;
            if (top == null) {
                top = data;
            } else {
                LList<T> tmp = top;
                while (tmp.next != null) {
                    tmp = tmp.next;
                }
                tmp.next = data;
            }
            size++;
        }

        public T peek(int index) {
            LList<T> tmp = top;
            for (int i = 0; i < index; i++) {
                tmp = tmp.next;
            }
            return tmp.elem;
        }

        public T[] toArray() {
            T[] array = (T[]) new Object[size];
            for (int i = 0; i < size; i++) {
                array[i] = peek(i);
            }
            return array;
        }
    }

    private static class Stack<T> {
        private Node<T> top;

        public Stack() {
            top = null;
        }

        public boolean isEmpty() {
            return top == null;
        }

        public void push(T data) {
            Node<T> prevTop = top;
            top = new Node<>();
            top.data = data;
            top.next = prevTop;
        }

        public T pop() {
            if (isEmpty()) throw new NoSuchElementException("Stack underflow");
            T data = top.data;        // save item to return
            top = top.next;            // delete first node
            return data;                   // return the saved item
        }
    }

    private final Stack<Node<T>> stack;

    public DFSforCS392(){
        stack = new Stack<>();
    }

    public int depthFirstSearch(int[][] board, int row, int solutions) {
        for (int column = 0; column < board.length; column++) {
            if (checkSafe(board, row, column)) {
                board[row][column] = 1;
                return depthFirstSearch(board, row, solutions);
            } else {
                board[row][column] = 0;
            }
        }
        return solutions;
    }

    private boolean checkSafe(int[][] board, int row, int column) {
        int i, j;

        for (i = 0; i < column; i++)
            if (board[row][i] == 1)
                return false;

        for (i = row, j = column; i >= 0 && j >= 0; i--, j--)
            if (board[i][j] == 1)
                return false;

        for (i = row, j = column; j >= 0 && i < board[0].length; i++, j--)
            if (board[i][j] == 1)
                return false;

        return true;
    }

    private int[] depthFirstSearch(int[] arr) {
        return new int[0];
    }

    @SuppressWarnings("ClassEscapesDefinedScope")
    public T[] depthFirstSearch(LinkedList<Node> nodeList) {
        LinkedList<T> list = new LinkedList<>();
        for (int i = 0; i < nodeList.size; i++) {
            if (!nodeList.peek(i).checked) {
                nodeList.peek(i).checked = true;
                depthFirstSearch(nodeList.peek(i), list);
            }
        }
        return list.toArray();
    }

    private void depthFirstSearch(Node<T> root, LinkedList list) {
        stack.push(root);
        root.checked = true;
        while (!stack.isEmpty()) {
            Node<T> node = stack.pop();
            list.insert(node.data);
            for (int i = 0; i < node.neighbors.size; i++) {
                if (!node.neighbors.peek(i).checked) {
                    node.neighbors.peek(i).checked = true;
                    stack.push(node.neighbors.peek(i));

                }
            }
        }
    }

    public static void main(String[] args) {
        var n1 = new Node<>(1);
        var n2 = new Node<>(2);
        var n3 = new Node<>(3);
        var n4 = new Node<>(4);
        var n5 = new Node<>(5);
        var n6 = new Node<>(6);
        var n7 = new Node<>(7);

        LinkedList<Node> list = new LinkedList<>();

        n1.addNeighbor(n2);
        n1.addNeighbor(n3);
        n2.addNeighbor(n4);
        n3.addNeighbor(n5);
        n3.addNeighbor(n6);
        n5.addNeighbor(n7);

        list.insert(n1);
        list.insert(n2);
        list.insert(n3);
        list.insert(n4);
        list.insert(n5);
        list.insert(n6);
        list.insert(n7);

        System.out.println("List size: " + list.size);

        var dfs = new DFSforCS392<>();
        System.out.println(Arrays.toString(dfs.depthFirstSearch(list)));
    }
}
