package ru.otus.hashtable;

public class HTableEntry<K, V> {
    final K key;
    V value;
    boolean deleted;

    HTableEntry(K key, V value) {
        this.key = key;
        this.value = value;
        this.deleted = false;
    }

    public K getKey() {
        return key;
    }

    public V getValue() {
        return value;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public V setValue(V newValue) {
        V oldValue = value;
        value = newValue;
        return oldValue;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    @Override
    public String toString() {
        return key + "=" + value;
    }
}