/**
 * @author Kevin Jiang (kjbu@bu.edu), Hongwei Xi
 * @version 1.0, 14 Apr 2025
 */

/*
 * ChainedHashTable.java
 *
 * Computer Science 392, Boston University
 * 
 * Modifications and additions by:
 *     name:
 *     email:
 */

// to allow for the use of Arrays.toString() in testing

/*
 * A class that implements a hash table using separate chaining.
 */
@SuppressWarnings("unchecked")
public class ChainedHashTable<K, V> implements HashTable<K, V> {
    /* 
     * Private inner class for a node in a linked list
     * for a given position of the hash table
     */
    private static class Node<K, V> {
        private final K key;//to generics
        private final LLQueue<V> values;
        private Node<K, V> next;
        
        private Node(K key, V value) {
            this.key = key;
            values = new LLQueue<>();
            values.insert(value);
            next = null;
        }
    }
    
    private final Node<K, V>[] table;       // the hash table itself
    private int numKeys;        // the total number of keys in the table
    private final int maximum;  //table maximum capacity
        
    /* hash function */
    public int h1 (K key) {
        int h1 = key.hashCode() % table.length;
        if (h1 < 0) {
            h1 += table.length;
        }
        return h1;
    }
    
    /*** Add your constructor here ***/
    public ChainedHashTable(int maximum) {
        this.maximum = maximum;
        numKeys = 0;
        table = new Node[maximum];
    }
    
    
    /*
     * insert - insert the specified (key, value) pair in the hash table.
     * Returns true if the pair can be added and false if there is overflow.
     */
    @Override
    public boolean insert(K key, V value) {
        /* Replace the following line with your implementation. */
        if (key == null) {
            throw new IllegalArgumentException("Key is null.");
        } else if (numKeys >= maximum){
            System.out.println("Table is full, key {" + key + "} with value {" + value + "} was not inserted.");
            return false;
        } else {
            Node<K, V> node = new Node<>(key, value);
            node.next = table[h1(key)];
            table[h1(key)] = node;
            numKeys++;
            System.out.println("Key {" + key + "} with value {" + value + "} has been inserted.");
            return true;
        }
    }
    
    /*
     * search - search for the specified key and return the
     * associated collection of values, or null if the key 
     * is not in the table
     */
    @Override
    public Queue<V> search(K key) {
        /* Replace the following line with your implementation. */
        Node<K, V> search = table[h1(key)];

        while (search != null) {
            if (search.key.equals(key)) {
                return search.values;
            }
            search = search.next;
        }

        System.out.println("Key {" + key +"} not found.");
        return null;
    }
    
    /* 
     * remove - remove from the table the entry for the specified key
     * and return the associated collection of values, or null if the key 
     * is not in the table
     */
    @Override
    public Queue<V> remove(K key) {
        /* Replace the following line with your implementation. **/
        Node<K, V> remove = table[h1(key)];
        Node<K, V> replace = null;

        while (remove != null) {
            if (remove.key.equals(key)){
                Queue<V> dump = remove.values;
                if (replace == null) {
                    table[h1(key)] = remove.next;
                } else {
                    replace.next = remove.next;
                }
                numKeys--;
                System.out.println("Key {" + key + "} with value " + dump + " has been removed.");
                return dump;
            }
            replace = remove;
            remove = remove.next;
        }

        System.out.println("Key {" + key +"} not found.");
        return null;
    }


    /*** Add the other required methods here ***/
    public int size () {
        return numKeys;
    }

    
    /*
     * toString - returns a string representation of this ChainedHashTable
     * object. *** You should NOT change this method. ***
     */
    @SuppressWarnings({"rawtypes", "StringConcatenationInLoop", "SpellCheckingInspection"})
    public String toString() {
        String s = "[";
        
        for (int i = 0; i < table.length; i++) {
            if (table[i] == null) {
                s += "null";
            } else {
                String keys = "{";
                Node trav = table[i];
                while (trav != null) {
                    keys += trav.key;
                    if (trav.next != null) {
                        keys += "; ";
                    }
                    trav = trav.next;
                }
                keys += "}";
                s += keys;
            }
        
            if (i < table.length - 1) {
                s += ", ";
            }
        }       
        
        s += "]";
        return s;
    }

    public static void main(String[] args) {
        /* Add your unit tests here */
        ChainedHashTable<Integer, String> hashTable = new ChainedHashTable<>(10);
        hashTable.insert(0, "Sun");
        hashTable.insert(1, "Mercury");
        hashTable.insert(2, "Venus");
        hashTable.insert(3, "Earth");
        hashTable.insert(4, "Mars");
        hashTable.insert(5, "Jupiter");
        hashTable.insert(6, "Saturn");
        hashTable.insert(7, "Uranus");
        hashTable.insert(8, "Neptune");
        hashTable.insert(9, "Pluto");
        hashTable.insert(10, "Moon");

        System.out.println(hashTable);

        for (int i = 0; i < hashTable.size() + 1; i++) {
            System.out.println(hashTable.search(i));
        }

        hashTable.remove(5);

        System.out.println(hashTable);

        hashTable.insert(5, "Jupiter");
        System.out.println(hashTable);
    }
}
