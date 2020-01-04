package ru.otus.tree;

import ru.otus.tree.binary.BinaryTree;
import ru.otus.tree.utils.Helper;

public class Application {

    public static void main(String[] args) {

        int size = 200_000;

        int[] keys = new int[size];
        for (int i = 0; i < size; i++) {
            keys[i] = i;
        }

        IntTree sequentialBinaryTree = new BinaryTree();
        long time = Helper.runAndMeasure(ints -> {
            for (int key : ints) {
                sequentialBinaryTree.insert(key);
            }
        }, keys);

        System.out.println(time);

        time = Helper.runAndMeasure(ints -> {
            for (int key : ints) {
                sequentialBinaryTree.search(key);
            }
        }, keys);

        System.out.println(time);

        time = Helper.runAndMeasure(ints -> {
            for (int i = ints.length - 1; i >= 0; i--) {
                sequentialBinaryTree.remove(ints[i]);
            }
        }, keys);

        System.out.println(time);


    }


}
