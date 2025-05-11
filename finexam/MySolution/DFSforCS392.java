/**
 * @author Kevin Jiang (kjbu@bu.edu), Hongwei Xi
 * @version 1.1, 10 May 2025
 */
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
        private T data;
        private int size;
        private LinkedList<T> top;
        private LinkedList<T> next;

        public LinkedList() {}

        public LinkedList(T data) {
            this.data = data;
        }

        public void insert(T x) {
            var data = new LinkedList<>(x);
            data.next = null;
            if (top == null) {
                top = data;
            } else {
                LinkedList<T> tmp = top;
                while (tmp.next != null) {
                    tmp = tmp.next;
                }
                tmp.next = data;
            }
            size++;
        }

        public T peek(int index) {
            LinkedList<T> tmp = top;
            for (int i = 0; i < index; i++) {
                tmp = tmp.next;
            }
            return tmp.data;
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
    private Stack tStack;

    public DFSforCS392(){
        stack = new Stack<>();
    }

    public int depthFirstSearchNQueens(int[] queenPos) {
        int solutions = 0;
        tStack = new Stack<Integer[]>();
        tStack.push(new int[]{0, 0});
        while (!tStack.isEmpty()) {
            int[] tile = (int[]) tStack.pop();
            int row = tile[0];
            int column = tile[1];

            if (column < queenPos.length) {
                if (checkSafe(queenPos, row, column)) {
                    if (row + 1 == queenPos.length) {
                        solutions++;
                    }
                    queenPos[row] = column;
                    tStack.push(new int[]{row + 1, 0});
                } else {
                    tStack.push(new int[]{row, column + 1});
                }
            } else if (row > 0) {
                int prevColumn = queenPos[row - 1];
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

    public int depthFirstSearchG24(int[] inputs) {
        int solutions = 0;
        tStack = new Stack<double[]>();
        double[] inputConvert = new double[inputs.length];
        for (int i = 0; i < inputs.length; i++) {
            inputConvert[i] = inputs[i];
        }
        tStack.push(inputConvert);
        double precision = 1e-6;
        while (!tStack.isEmpty()) {
            double[] preciseCalc = (double[]) tStack.pop();
            int start = preciseCalc.length;

            if (start <= 1) {
                if (Math.abs(preciseCalc[0] - 24) < precision) {
                    solutions++;
                }
                continue;
            }
            for (int i = 0; i < start; i++) {
                for (int j = i; j < start; j++) {
                    double[] newCalc = new double[start - 1];
                    for (int k = 0, index = 0; k < start; k++) {
                        if (k != i && k != j) {
                            newCalc[index++] = preciseCalc[k];
                        }
                    }
                    double[] results = operations(preciseCalc, i, j);
                    for (double result : results) {
                        newCalc[start - 2] = result;
                        tStack.push(newCalc);
                    }
                }
            }
        }
        return solutions;
    }

    private double[] operations(double[] arr, int i, int j) {
        if (j == 0) {
            return new double[] {
                    (arr[j] / arr[i]),
                    (arr[i] - arr[j]),
                    (arr[j] - arr[i]),
                    (arr[i] * arr[j]),
                    (arr[i] + arr[j])
            };
        }
        if (i == 0) {
            return new double[] {
                    (arr[i] / arr[j]),
                    (arr[i] - arr[j]),
                    (arr[j] - arr[i]),
                    (arr[i] * arr[j]),
                    (arr[i] + arr[j])
            };
        }
        return new double[] {
                (arr[i] / arr[j]),
                (arr[j] / arr[i]),
                (arr[i] - arr[j]),
                (arr[j] - arr[i]),
                (arr[i] * arr[j]),
                (arr[i] + arr[j])
        };
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
