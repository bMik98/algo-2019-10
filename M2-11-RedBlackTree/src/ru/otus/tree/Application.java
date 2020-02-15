package ru.otus.tree;

import ru.otus.tree.binary.AVLTree;
import ru.otus.tree.binary.BSTree;
import ru.otus.tree.binary.RandomBSTree;
import ru.otus.tree.utils.Helper;

import java.util.Arrays;
import java.util.function.BiConsumer;

public class Application {

    private static final BiConsumer<IntTree, int[]> insertToTree = (tree, keys) -> {
        for (int key : keys) {
            tree.insert(key);
        }
    };

    private static final BiConsumer<IntTree, int[]> searchInTree = (tree, keys) -> {
        for (int key : keys) {
            tree.search(key);
        }
    };

    private static final BiConsumer<IntTree, int[]> removeFromTree = (tree, keys) -> {
        for (int key : keys) {
            tree.remove(key);
        }
    };

    public static void main(String[] args) {

        System.out.println("# 10. Performance of Trees\n");

        int size = 1_000_000;
        int searchQty = size / 10;
        int removeQty = size / 10;

        System.out.printf("elements to insert: %,10d%n", size);
        System.out.printf("random search for : %,10d%n", searchQty);
        System.out.printf("random remove of  : %,10d%n%n", removeQty);

        System.out.println("| Tree, init type                | insert, ms | search, ms | remove, ms |");
        System.out.println("|--------------------------------|-----------:|-----------:|-----------:|");

        int[] sequentialKeys = Helper.createSequentialArray(size);
        int[] randomKeys = Helper.createRandomArray(size);
        int[] keysToSearch = Arrays.copyOf(Helper.createRandomArray(size), searchQty);
        int[] keysToRemove = Arrays.copyOf(Helper.createRandomArray(size), removeQty);

        testTree("BSTree, init by random", new BSTree(), randomKeys, keysToSearch, keysToRemove);
        testTree("AVLTree, init by sequence", new AVLTree(), sequentialKeys, keysToSearch, keysToRemove);
        testTree("AVLTree, init by random", new AVLTree(), randomKeys, keysToSearch, keysToRemove);
        testTree("RandomTree, init by sequence", new RandomBSTree(), sequentialKeys, keysToSearch, keysToRemove);
        testTree("RandomTree, init by random", new RandomBSTree(), randomKeys, keysToSearch, keysToRemove);
    }

    private static void testTree(String name, IntTree tree, int[] keysToInsert, int[] keysToSearch, int[] keysToRemove) {
        System.out.printf("| %-30s | %,10d | %,10d | %,10d |%n", name,
                Helper.runAndMeasure(insertToTree, tree, keysToInsert),
                Helper.runAndMeasure(searchInTree, tree, keysToSearch),
                Helper.runAndMeasure(removeFromTree, tree, keysToRemove));
    }
}
