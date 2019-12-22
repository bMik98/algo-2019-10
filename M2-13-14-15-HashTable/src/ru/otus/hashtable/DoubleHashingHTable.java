package ru.otus.hashtable;

public class DoubleHashingHTable<K, V> extends AbstractOpenAddressingHTable<K, V> {

    public DoubleHashingHTable() {
        super();
    }

    public DoubleHashingHTable(float loadFactor) {
        super(loadFactor);
    }

    @Override
    protected int index(K key, int step, int max) {
        int hash1 = hash1(key, max);
        int hash2 = hash2(key, max);
        return (hash1 + step * hash2) % max;
    }

    private int hash1(K key, int max) {
        return key.hashCode() % max;
    }

    private int hash2(K key, int max) {
        int x = key.hashCode() % (max / 2);
        return 2 * x + 1;
    }
}
