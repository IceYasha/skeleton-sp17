package lab9;


import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class MyHashMap<K,V> implements Map61B<K, V> {
    private static int INIT_CAPACITY = 127;

    private int n;              // number of key-value pairs
    private int size;           // hash table size
    private double loadFactor;
    private Entry <K, V>[] ea;  // array of entries
    Set<K> keySet = new HashSet<>();


    public MyHashMap() {
        this(INIT_CAPACITY, 10);
    }
    public MyHashMap(int initialSize) {
        this(initialSize, 10);
    }

    public MyHashMap(int initialSize, double loadFactor) {
        this.n = 0;
        this.size = initialSize;
        this.loadFactor = loadFactor;
        ea = (Entry <K, V>[]) new Entry[initialSize];
        for (int i = 0; i < size; i++)
            ea[i] = new Entry<>();
    }

    /** Removes all of the mappings from this map. */
    @Override
    public void clear() {
        n = 0;
        for (int i = 0; i < this.size; i++)
            ea[i] = new Entry<>();
        keySet = null;
    }

    /** Returns true if this map contains a mapping for the specified key. */
    @Override
    public boolean containsKey(K key) {
        if (key == null) throw new IllegalArgumentException("argument to contains() is null");
        return get(key) != null;
    }

    /** Returns the value to which the specified key is mapped, or null if this
     * map contains no mapping for the key. */
    @Override
    public V get(K key) {
        if (key == null) throw new IllegalArgumentException("argument to get() is null");
        int i = hash(key);
        return ea[i].get(key);
    }

    /** Returns the number of key-value mappings in this map. */
    @Override
    public int size() {
        return n;
    }

    /** Associates the specified value with the specified key in this map. */
    @Override
    public void put(K key, V value) {
        if (key == null) throw new IllegalArgumentException("first argument to put() is null");

        // double table size if average length of list >= loadFactor
        if (n >= loadFactor*size) resize(2*size);

        int i = hash(key);
        if (!ea[i].contains(key)) n++;
        ea[i].put(key, value);
        keySet.add(key);
    }

    /** Returns a Set view of the keys contained in this map. */
    @Override
    public Set<K> keySet() {
        return keySet;
    }

    @Override
    public Iterator<K> iterator() {
        return keySet.iterator();
    }

    private class Entry<K, V> {
        K key;
        V val;
        Entry<K, V> next;

        Entry() {
            next = null;
        }

        /** Stores KEY as the key in this key-value pair, VAL as the value, and
         *  NEXT as the next node in the linked list. */
        Entry(K k, V v, Entry<K, V> next) {
            key = k; val = v; this.next = next;
        }

        /** Returns the Entry in this linked list of key-value pairs whose key
         *  is equal to KEY, or null if no such Entry exists. */
        V get(K key) {
            if (key != null && key.equals(this.key)) {
                return this.val;
            }
            if (next == null) {
                return null;
            }
            return next.get(key);
        }

        void put(K key, V val) {
            for (Entry x = next; x != null; x = x.next) {
                if (key.equals(x.key)) {
                    x.val = val;
                    return;
                }
            }
            next = new Entry<>(key, val, next);
        }

        boolean contains(K key) {
            return get(key) != null;
        }

        V delete(K key) {
            V result;
            if (key == null) throw new IllegalArgumentException("argument to delete() is null");
            result = next.get(key);
            next = delete(next, key);
            return result;
        }

        // delete key in linked list beginning at Entry x
        // warning: function call stack too large if table is large
        private Entry delete(Entry x, K key) {
            if (x == null) return null;
            if (key.equals(x.key)) {
                return x.next;
            }
            x.next = delete(x.next, key);
            return x;
        }

        Iterable<K> keys()  {
            HashSet<K> set = new HashSet<>();
            for (Entry<K, V> x = next; x != null; x = x.next)
                set.add(x.key);
            return set;
        }

    }

    // resize the hash table to have the given number of chains,
    // rehashing all of the keys
    private void resize(int chains) {
        MyHashMap<K, V> temp = new MyHashMap<K, V>(chains, loadFactor);
        for (int i = 0; i < size; i++) {
            for (K key : ea[i].keys()) {
                temp.put(key, ea[i].get(key));
            }
        }
        this.size = temp.size;
        this.ea = temp.ea;
    }

    // hash value between 0 and m-1
    private int hash(K key) {
        return (key.hashCode() & 0x7fffffff) % size;
    }

    /** Removes the mapping for the specified key from this map if present.
     * Not required for Lab 8. If you don't implement this, throw an
     * UnsupportedOperationException. */
    @Override
    public V remove(K key) {
        V value;
        if (key == null) throw new IllegalArgumentException("argument to delete() is null");

        int i = hash(key);
        if (ea[i].contains(key)) n--;
        value = ea[i].delete(key);

        // halve table size if average length of list <= 2
        if (size > INIT_CAPACITY && n <= 2*size) resize(size/2);
        return value;
    }

    /** Removes the entry for the specified key only if it is currently mapped to
     * the specified value. Not required for Lab 8. If you don't implement this,
     * throw an UnsupportedOperationException.*/
    @Override
    public V remove(K key, V value) {
        throw new UnsupportedOperationException();
    }
}
