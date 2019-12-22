package ru.otus.hashtable;

public class QuadraticHTable<K, V> extends AbstractOpenAddressingHTable<K, V> {

    public QuadraticHTable() {
        super();
    }

    public QuadraticHTable(float loadFactor) {
        super(loadFactor);
    }

    @Override
    protected int index(K key, int step, int max) {
        return (key.hashCode() % max + (step * step + step) / 2) % max;
    }
}
