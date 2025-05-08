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
    private final Stack tStack;

    public DFSforCS392(){
        stack = new Stack<>();
        tStack = new Stack<Integer[]>();
    }

    public int depthFirstSearchNQueens(int[] arr) {
        int solutions = 0;
        tStack.push(new int[]{0, 0});
        while (!tStack.isEmpty()) {
            int[] tile = (int[]) tStack.pop();
            int row = tile[0];
            int column = tile[1];

            if (column < arr.length) {
                if (checkSafe(arr, row, column)) {
                    if (row + 1 == arr.length) {
                        solutions++;
                    }
                    arr[row] = column;
                    tStack.push(new int[]{row + 1, 0});
                } else {
                    tStack.push(new int[]{row, column + 1});
                }
            } else if (row > 0) {
                int prevColumn = arr[row - 1];
                tStack.push(new int[]{row - 1, prevColumn + 1});
            }
        }
        return solutions;
    }

    private boolean checkSafe(int[] positions, int row, int column) {
        for (int i = 0; i < row; i++) {
            if (column == positions[i] || Math.abs(row - i) == Math.abs(column - positions[i])) {
                return false;
            }
        }
        return true;
    }

    public String depthFirstSearchG24(int[] numbers) {
        return "";
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
