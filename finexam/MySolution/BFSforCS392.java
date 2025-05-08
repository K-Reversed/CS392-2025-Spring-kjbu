import java.util.NoSuchElementException;

@SuppressWarnings("rawtypes")
public class BFSforCS392<T> {
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

        public void addNeighbor(Node<T> node) {
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
    }

    private static class Queue<T> {
        private Node<T> top, bottom;

        public Queue() {
            top = bottom = null;
        }

        public boolean isEmpty() {
            return top == null;
        }

        public void enqueue(T item) {
            Node<T> oldBottom = bottom;
            bottom = new Node<>();
            bottom.data = item;
            bottom.next = null;
            if (isEmpty()) {
                top = bottom;
            } else {
                oldBottom.next = bottom;
            }
        }

        public T dequeue() {
            if (isEmpty()) throw new NoSuchElementException("Queue underflow");
            T data = top.data;
            top = top.next;
            if (isEmpty()) bottom = null;   // to avoid loitering
            return data;
        }
    }

    private final Queue<Node<T>> queue;

    public BFSforCS392() {
        queue = new Queue<>();
    }

    private void breadthFirstSearch(LinkedList<Node> nodeList) {
        for (int i = 0; i < nodeList.size; i++) {
            if (!nodeList.peek(i).checked) {
                nodeList.peek(i).checked = true;
                breadthFirstSearch(nodeList.peek(i));
            }
        }
    }

    private void breadthFirstSearch(Node<T> root) {
        queue.enqueue(root);
        root.checked = true;
        while (!queue.isEmpty()) {
            Node<T> node = queue.dequeue();
            System.out.print(node.data + " ");
            for (int i = 0; i < node.neighbors.size; i++) {
                if (!node.neighbors.peek(i).checked) {
                    node.neighbors.peek(i).checked = true;
                    queue.enqueue(node.neighbors.peek(i));
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

        var bfs = new BFSforCS392<>();
        bfs.breadthFirstSearch(list);
    }
}
