package ru.otus.hashtable;

public class Application {

    public static void main(String[] args) {
        HTable<Integer, String> table = new LinearHTable<>();

        for (int i = 0; i < 10000; i++) {
            table.put(i, "-" + i);
        }
        for (int i = 0; i < 10000; i++) {
            table.put(i, "--" + i);
        }
        for (int i = 9999; i >= 0; i--) {
            System.out.println(table.get(i));
        }
        System.out.println(table.size());
        System.out.println(table.allocatedSize());
    }
}
