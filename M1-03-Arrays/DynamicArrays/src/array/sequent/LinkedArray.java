package array.sequent;

import array.IArray;

public class LinkedArray<T> implements IArray<T> {

    private Node<T> head;
    private Node<T> tail;
    private int size;

    @Override
    public int size() {
        return size;
    }

    @Override
    public void add(T item) {
        Node<T> node = new Node<>(item);
        if (isEmpty()) {
            head = node;
        } else {
            tail.setNext(node);
        }
        tail = node;
        size++;
    }

    @Override
    public void add(T item, int index) {
        if (index < 0 || index > size) {
            throw new ArrayIndexOutOfBoundsException(index);
        }
        if (index == 0) {
            head = new Node<>(item, head);
            size++;
        } else if (index == size) {
            add(item);
        } else {
            Node<T> previousNode = getNode(index - 1);
            Node<T> nodeToAdd = new Node<>(item, previousNode.getNext());
            previousNode.setNext(nodeToAdd);
            size++;
        }
    }

    @Override
    public T get(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayIndexOutOfBoundsException(index);
        }
        return getNode(index).getItem();
    }

    @Override
    public T remove(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayIndexOutOfBoundsException(index);
        }
        T deletedItem;
        if (index == 0) {
            deletedItem = head.getItem();
            head = head.getNext();
        } else {
            Node<T> previous = getNode(index - 1);
            Node<T> nodeToRemove = previous.getNext();
            previous.setNext(nodeToRemove.getNext());
            deletedItem = nodeToRemove.getItem();
        }
        size--;
        return deletedItem;
    }

    private boolean isEmpty() {
        return head == null;
    }

    private Node<T> getNode(int index) {
        Node<T> result = head;
        for (int i = 0; i < index; i++) {
            result = result.getNext();
        }
        return result;
    }
}
