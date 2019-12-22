package ru.otus.hashtable;

import java.util.NoSuchElementException;

public abstract class AbstractOpenAddressingHTable<K, V> implements HTable<K, V> {

    public static final int INITIAL_SIZE = 1 << 4;
    public static final int MAXIMUM_SIZE = 1 << 30;
    public static final float DEFAULT_LOAD_FACTOR = 0.50f;

    private static final int INDEX_NOT_FOUND = -1;
    private final float loadFactor;
    private int size;
    private HTableEntry<K, V>[] buckets;

    @SuppressWarnings("unchecked")
    public AbstractOpenAddressingHTable() {
        this.size = 0;
        this.loadFactor = DEFAULT_LOAD_FACTOR;
        this.buckets = (HTableEntry<K, V>[]) new HTableEntry[INITIAL_SIZE];
    }

    @SuppressWarnings("unchecked")
    public AbstractOpenAddressingHTable(float loadFactor) {
        this.size = 0;
        this.loadFactor = loadFactor;
        this.buckets = (HTableEntry<K, V>[]) new HTableEntry[INITIAL_SIZE];
    }

    @Override
    public V get(K key) {
        int index = findIndexOfKey(key, buckets);
        return (index == INDEX_NOT_FOUND) ? null : buckets[index].getValue();
    }

    @Override
    public V delete(K key) {
        int index = findIndexOfKey(key, buckets);
        return (index == INDEX_NOT_FOUND) ? null : deleteByIndex(index);
    }

    private V deleteByIndex(int index) {
        size--;
        buckets[index].setDeleted(true);
        return buckets[index].getValue();
    }

    @Override
    public V put(K key, V value) {
        int index = findIndexOfKey(key, buckets);
        if (index != INDEX_NOT_FOUND) {
            return update(index, value);
        }
        if (size >= (int) (buckets.length * loadFactor)) {
            resize();
        }
        index = findIndexOfEmptyBucket(key, buckets);
        buckets[index] = new HTableEntry<>(key, value);
        size++;
        return null;
    }

    private V update(int index, V value) {
        V oldValue = buckets[index].getValue();
        buckets[index].setValue(value);
        return oldValue;
    }

    private int findIndexOfKey(K key, HTableEntry<K, V>[] buckets) {
        for (int i = 0; i < buckets.length; i++) {
            int index = index(key, i, buckets.length);
            HTableEntry<K, V> bucket = buckets[index];
            if (bucket == null) {
                return INDEX_NOT_FOUND;
            }
            if (!bucket.isDeleted() && bucket.getKey().equals(key)) {
                return index;
            }
        }
        return INDEX_NOT_FOUND;
    }

    @SuppressWarnings("unchecked")
    private void resize() {
        int newSize = buckets.length * 2;
        if (newSize > MAXIMUM_SIZE) {
            throw new IndexOutOfBoundsException("Hash table exceeded the maximum size");
        }
        HTableEntry<K, V>[] newBuckets = (HTableEntry<K, V>[]) new HTableEntry[newSize];
        for (HTableEntry<K, V> bucket : buckets) {
            if (bucket != null && !bucket.isDeleted()) {
                int index = findIndexOfEmptyBucket(bucket.getKey(), newBuckets);
                newBuckets[index] = bucket;
            }
        }
        buckets = newBuckets;
    }

    private int findIndexOfEmptyBucket(K key, HTableEntry<K, V>[] buckets) {
        for (int i = 0; i < buckets.length; i++) {
            int index = index(key, i, buckets.length);
            if (buckets[index] == null || buckets[index].isDeleted()) {
                return index;
            }
        }
        throw new NoSuchElementException("Free bucket not found");
    }

    @Override
    public int allocatedSize() {
        return buckets.length;
    }

    @Override
    public int size() {
        return size;
    }

    protected abstract int index(K key, int step, int n);
}
