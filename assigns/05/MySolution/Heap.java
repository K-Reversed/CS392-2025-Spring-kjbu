/**
 * @author Kevin Jiang (kjbu@bu.edu), Hongwei Xi
 * @version 1.0, 13 Mar 2025
 */

/*
 * BU CAS CS 392
 * For generic array-based heap implementation
 * Please finish the code so that HeapTest (given)
 * can use to code here to run.
 */

import java.util.NoSuchElementException;

/**
 * Heap - a generic collection class that implements 
 * a max-at-top heap using an array
 */
@SuppressWarnings({"StringConcatenationInLoop", "unchecked"})
public class Heap<T extends Comparable<T>> {
    private final T[] contents;
    private int numItems;
    
    public Heap(int maxSize) {
        contents = (T[]) new Comparable[maxSize];
        numItems = 0;
    }
    
    public Heap(T[] arr) {
        // Note that we don't copy the array, so that heapsort can
        // sort the array in place.
        contents = arr;
        numItems = arr.length;
        makeHeap();
    }
    
    /* 
     * makeHeap - turn the elements in the contents array into a
     * representation of a max-at-top heap.

    private void makeHeap() {
        // Please give a recursion-based implementation
        // You may need to introduce a private helper method
        // for this.
    }
     */
    
    /** 
     * insert - add the specified item to the heap and sift it
     * up into its proper position. It returns true if inserted
     * and false if no more room for insertion
     */
    public boolean insert(T item) {
        if (numItems >= contents.length){
            System.out.println("Heap is full.");
            return false;
        } else {
            contents[numItems] = item;
            swim(numItems);
            numItems++;
            return true;
        }
    }
    
    /**
     * remove and return the item at the top of the heap, and adjust
     * the remaining items so that we still have a heap.
     */
    public T remove() {
        if (isEmpty()){
            throw new NoSuchElementException("Heap is empty.");
        }
        T root = contents[0];
        contents[0] = contents[numItems - 1];
        contents[numItems - 1] = null;
        numItems--;
        if (!isEmpty()) {
            sink(0);
        }
        return root;
    }

    public T peek() {
        if (isEmpty()){
            throw new NoSuchElementException("Heap is empty.");
        }
        return contents[0];
    }
    
    /**
     * isEmpty - does the heap currently have no items?
     */
    public boolean isEmpty() {
        return (numItems == 0);
    }

    public boolean isFull() {
        return (numItems >= contents.length);
    }
    
    /**
     * toString - create a string representation of the heap of the form
     * { ( root ) ( items in level 1 ) ( items in level 2 ) ... }
     */
    public String toString() {
        String str = "{ ";
        
        int start = 0;
        int levelSize = 1;
        while (start < numItems) {
            // print all of the items at the current level of the tree
            str += "( ";
            for (int i = start; i < start + levelSize && i < numItems; i++)
                str += (contents[i] + " ");
            str += ") ";
            
            // move down to the next level
            start += levelSize;
            levelSize *= 2;
        }
        
        str += "}";
        return str;
    }

    private void swim(int i) {
	    // Please give a loop-based implementation of sift-up
        T toSwim = contents[i];

        int child = i;
        while (child > 0) {
            int parent = (child - 1) / 2;
            if (toSwim.compareTo(contents[parent]) <= 0) {
                break;
            }

            contents[child] = contents[parent];
            child = parent;
        }
        contents[child] = toSwim;
    }

    private void sink(int i) {
	    // Please give a loop-based implementation of sift-down
        T toSink = contents[i];
        int parent = i;
        int child = 2 * parent + 1;
        while (child < numItems) {
            if (child < numItems - 1 && contents[child].compareTo(contents[child + 1]) < 0) {
                child++;
            }
            if (toSink.compareTo(contents[child]) >= 0) {
                break;
            }
            contents[parent] = contents[child];
            parent = child;
            child = 2 * parent + 1;
        }
        contents[parent] = toSink;
    }

    private void makeHeap() {
        // Please give a loop-based implementation
	    // This is just heapfication: turning an array into a heap
        int end = contents.length - 1;
        int endParent = (end - 1) / 2;
        for (int i = endParent; i >= 0; i--) {
            sink(i);
        }
    }
}
