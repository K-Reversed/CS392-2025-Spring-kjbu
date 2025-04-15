/**
 * @author Kevin Jiang (kjbu@bu.edu), Hongwei Xi
 * @version 1.0, 14 Apr 2025
 */

/*
 * OpenHashTable.java
 *
 * Computer Science 112, Boston University
 */

/*
 * A class that implements a hash table that employs open addressing
 * using either linear probing, quadratic probing, or double hashing.
 */
@SuppressWarnings("unchecked")
public class OpenHashTable<K, V> implements HashTable<K, V> {
    /* Private inner class for an entry in the hash table */
    private static class Entry<K, V> {
        private K key;
        private LLQueue<V> values;    // all the values with this key
        
        private Entry(K key, V value) {
            this.key = key;
            values = new LLQueue<>();
            values.insert(value);
        }
    }
    
    // possible types of probing
    public static final int LINEAR = 0;
    public static final int QUADRATIC = 1;
    public static final int DOUBLE_HASHING = 2;
    public static final int NUM_PROBE_TYPES = 3;
    
    private final Entry<K, V>[] table;             // the hash table itself
    private int probeType;    // the type of probing
    
    public OpenHashTable(int size, int probeType) {
        if (size <= 0) {
            throw new IllegalArgumentException("size must be positive");
        }
        if (probeType < 0 || probeType >= NUM_PROBE_TYPES) {
            throw new IllegalArgumentException("invalid probeType: " + probeType);
        }
        
        table = new Entry[size];
        this.probeType = probeType;
    }
    
    /*
     * Constructor for a hash table of the specified size that uses double hashing
     */ 
    public OpenHashTable(int size) {
        // call the other constructor to do the work
        this(size, DOUBLE_HASHING);
    }
    
    /* first hash function */
    public int h1(K key) {
        int h1 = key.hashCode() % table.length;
        if (h1 < 0) {
            h1 += table.length;
        }
        return h1;
    }
    
    /* second hash function */
    public int h2(K key) {
        int h2 = key.hashCode() % 5;
        if (h2 < 0) {
            h2 += 11;
        }
        h2 += 5;
        return h2;
    }
    
    /* 
     * probeIncr - returns the amount by which the current index
     * should be incremented to obtain the next element in the probe
     * sequence if we have already checked numChecked positions
     * and h2 is the value of the second hash function
     */
    @SuppressWarnings("SpellCheckingInspection")
    private int probeIncr(int numChecked, int h2) {
       if (numChecked <= 0) {
          return 0;
       } else if (probeType == LINEAR) {
           return 1;
       } else if (probeType == QUADRATIC) {
           return (2 * numChecked - 1);
       } else {   //  DOUBLE_HASHING:
           return h2;
       }
    }
    
    /*
     * probe - attempt to find a slot in the hash table for the specified key.
     *
     * If key is currently in the table, it returns the index of the entry.
     * If key isn't in the table, it returns the index of the first empty cell
     * in the table.
     * If overflow occurs, it returns -1.
     */
    private int probe(K key) {
        int i = h1(key);    // first hash function
        int h2 = h2(key);   // second hash function
        int numChecked = 1;
        
        // keep probing until we get an empty position or a match
        while (table[i] != null && !key.equals(table[i].key)) {
            if (numChecked == table.length) {
                return -1;
            }
            
            i = (i + probeIncr(numChecked, h2)) % table.length;
            numChecked++;
        }
        
        return i;
    }
    
    /*
     * insert - insert the specified (key, value) pair in the hash table.
     * Returns true if the pair can be added and false if there is overflow.
     */
    @Override
    public boolean insert(K key, V value) {
        if (key == null) {
            throw new IllegalArgumentException("key must be non-null");
        }
        
        int i = h1(key); 
        int h2 = h2(key);
        int numChecked = 1;
        int firstRemoved = -1;
        
        while (table[i] != null && !key.equals(table[i].key)) {
            // record the index of the first removed cell we see
            if (table[i].key == null && firstRemoved == -1) {
                firstRemoved = i;
            }
            
            if (numChecked == table.length) {
                break;
            }
            
            i = (i + probeIncr(numChecked, h2)) % table.length;
            numChecked++;
        }
        
        if (table[i] != null && key.equals(table[i].key)) {
            table[i].values.insert(value);
        } else if (firstRemoved != -1) {
            table[firstRemoved] = new Entry<>(key, value);
        } else if (table[i] == null) {
            table[i] = new Entry<>(key, value);
        } else {
            return false;
        }
        
        return true;
    }
    
    /*
     * search - search for the specified key and return the
     * associated collection of values, or null if the key 
     * is not in the table
     */
    @Override
    public Queue<V> search(K key) {
        if (key == null) {
            throw new IllegalArgumentException("key must be non-null");
        }
        
        int i = probe(key);
        
        if (i == -1 || table[i] == null) {
            return null;
        } else {
            return table[i].values;
        }
    }
    
    /* 
     * remove - remove from the table the entry for the specified key
     * and return the associated collection of values, or null if the key 
     * is not in the table
     */
    @Override
    public Queue<V> remove(K key) {
        if (key == null) {
            throw new IllegalArgumentException("key must be non-null");
        }
            
        int i = probe(key);
        if (i == -1 || table[i] == null) {
            return null;
        }
        
        LLQueue<V> removedVals = table[i].values;
        table[i].key = null;
        table[i].values = null;
        return removedVals;
    }

    @Override
    @SuppressWarnings({"StringConcatenationInLoop"})
    public String toString() {
        String s = "[";
        for (int i = 0; i < table.length; i++) {
            if (table[i] == null) {
                s += "null";
            } else {
                String keys = "{";
                keys += table[i].key;
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
        OpenHashTable<Integer, String> hashTable0 = new OpenHashTable<>(10, 0);
        OpenHashTable<Integer, String> hashTable1 = new OpenHashTable<>(10, 1);
        OpenHashTable<Integer, String> hashTable2 = new OpenHashTable<>(10, 2);

        hashTable0.insert(1, "alpha");
        hashTable0.insert(77, "bravo");
        hashTable0.insert(3, "charlie");
        hashTable0.insert(4, "delta");
        hashTable0.insert(5, "echo");
        hashTable0.insert(6, "foxtrot");
        hashTable0.insert(7, "golf");
        hashTable0.insert(8, "hotel");
        hashTable0.insert(9, "india");
        hashTable0.insert(10, "juliet");

        System.out.println(hashTable0);

        hashTable1.insert(1, "alpha");
        hashTable1.insert(77, "bravo");
        hashTable1.insert(3, "charlie");
        hashTable1.insert(4, "delta");
        hashTable1.insert(5, "echo");
        hashTable1.insert(6, "foxtrot");
        hashTable1.insert(7, "golf");
        hashTable1.insert(8, "hotel");
        hashTable1.insert(9, "india");
        hashTable1.insert(10, "juliet");

        System.out.println(hashTable1);

        hashTable2.insert(1, "alpha");
        hashTable2.insert(77, "bravo");
        hashTable2.insert(3, "charlie");
        hashTable2.insert(4, "delta");
        hashTable2.insert(5, "echo");
        hashTable2.insert(6, "foxtrot");
        hashTable2.insert(7, "golf");
        hashTable2.insert(8, "hotel");
        hashTable2.insert(9, "india");
        hashTable2.insert(10, "juliet");

        System.out.println(hashTable2);
    }
}
