package ru.otus.hashtable;

public interface HTable<K, V> {

    V get(K key);

    V put(K key, V value);

    V delete(K key);

    int allocatedSize();

    int size();
}
