package com.weddingflow.util;

public class LinkedList<T> {
    private Node head;

    private class Node {
        T data;
        Node next;

        Node(T data) {
            this.data = data;
            this.next = null;
        }
    }

    public LinkedList() {
        head = null;
    }

    // Add a new node at the end
    public void add(T data) {
        Node newNode = new Node(data);
        if (head == null) {
            head = newNode;
            return;
        }
        Node current = head;
        while (current.next != null) {
            current = current.next;
        }
        current.next = newNode;
    }

    // Convert linked list to array
    @SuppressWarnings("unchecked")
    public T[] toArray(T[] array) {
        T[] result = array;
        int size = size();
        if (result.length < size) {
            result = (T[]) java.lang.reflect.Array.newInstance(
                    result.getClass().getComponentType(), size);
        }
        Node current = head;
        int index = 0;
        while (current != null) {
            result[index++] = current.data;
            current = current.next;
        }
        return result;
    }

    // Get size of linked list
    public int size() {
        int count = 0;
        Node current = head;
        while (current != null) {
            count++;
            current = current.next;
        }
        return count;
    }

    // Remove node with matching data
    public boolean remove(T data) {
        if (head == null) return false;

        if (head.data.equals(data)) {
            head = head.next;
            return true;
        }

        Node current = head;
        while (current.next != null) {
            if (current.next.data.equals(data)) {
                current.next = current.next.next;
                return true;
            }
            current = current.next;
        }
        return false;
    }

    // Get element at index (for sorting)
    public T get(int index) {
        if (index < 0 || index >= size()) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size());
        }
        Node current = head;
        for (int i = 0; i < index; i++) {
            current = current.next;
        }
        return current.data;
    }

    // Set element at index (for swapping in sorting)
    public void set(int index, T data) {
        if (index < 0 || index >= size()) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size());
        }
        Node current = head;
        for (int i = 0; i < index; i++) {
            current = current.next;
        }
        current.data = data;
    }

    // Update for AdminUtil (for string-based LinkedList)
    public boolean update(T oldData, T newData) {
        Node current = head;
        while (current != null) {
            if (current.data.equals(oldData)) {
                current.data = newData;
                return true;
            }
            current = current.next;
        }
        return false;
    }
}