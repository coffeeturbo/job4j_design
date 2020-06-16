package map;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class HashMap<K, V> implements Iterable<V> {

    private static final int CAPACITY = 16;
    private static final float CAPACITY_PROC = 0.5f;
    private Node<K, V>[] table;
    private int size = 0;
    private int modCounter = 0;

    HashMap(int size) {
        table = new Node[size];
    }

    public int getCapacity() {
        return table.length;
    }

    protected int overflowSize() {
        return (int) Math.ceil(table.length * CAPACITY_PROC);
    }

    public boolean insert(K key, V value) {
        int index = this.index(this.hash(key), this.table.length);
        Node<K, V> elem = new Node<>(key, value);

        resize();

        if (table[index] != null
            && key.equals(table[index].getKey())) {
            table[index] = elem;
            return false;
        }

        table[index] = elem;
        size++;
        modCounter++;
        return true;
    }
    
    public V get(K key) {
        V rsl = null;

        int index = index(key);
        if (table[index] != null && key.equals(table[index].getKey())) {
            Node<K, V> node = table[index];
            rsl = node.getValue();
        }

        return rsl;
    }

    public boolean delete(K key) {
        boolean rsl = true;

        int index = index(key);

        if (table[index] == null) {
            rsl = false;
        } else {
            table[index] = null;
            size--;
            modCounter++;
        }
        return rsl;
    }

    @Override
    public Iterator<V> iterator() {
        int initModCounter = modCounter;

        return new Iterator<>() {
            int pointer = 0;
            int size = table.length;

            @Override
            public boolean hasNext() {
                if (initModCounter < modCounter) {
                    throw new ConcurrentModificationException();
                }

                for (int i = pointer; i < size; i++) {
                    if (table[i] != null) {
                        return true;
                    }
                }
                return false;
            }

            @Override
            public V next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }

                for (int i = pointer; i < size; i++) {
                    if (table[i] != null) {
                        pointer = i;
                        break;
                    }
                }

                Node<K, V> node = table[pointer++];
                return node.getValue();
            }
        };
    }

    private static class Node<K, V> {
        private K key;
        private V value;

        Node(K key, V value) {
            this.key = key;
            this.value = value;
        }

        public K getKey() {
            return key;
        }

        public V getValue() {
            return value;
        }
    }

    private void resize() {
        if (size >= overflowSize()) {
            Node<K, V>[] newContainer = new Node[table.length + CAPACITY];
            System.arraycopy(table, 0, newContainer, 0, table.length);
            table = newContainer;
        }
    }

    private int hash(K key) {
        int hash = 0;
        if (key != null) {
            hash = key.hashCode();
            hash = hash ^ (hash >>> 16);
        }
        return hash;
    }

    private int index(int hash, int length) {
        return hash & (length - 1);
    }

    private int index(K key) {
        return this.index(this.hash(key), this.table.length);
    }
}
