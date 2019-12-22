package ru.otus.hashtable;

public class LinearHTable<K, V> extends AbstractOpenAddressingHTable<K, V> {

    public LinearHTable() {
        super();
    }

    public LinearHTable(float loadFactor) {
        super(loadFactor);
    }

    @Override
    protected int index(K key, int step, int n) {
        return (key.hashCode() % n + step) % n;
    }
}
