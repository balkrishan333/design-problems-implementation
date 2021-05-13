package design.impl;

import java.util.Objects;

public class MyHashMap<K, V> {

    private static final int INITIAL_CAPACITY = 16;

    private Node<K,V>[] elements;
    private int size;

    public MyHashMap() {
        elements = new Node[INITIAL_CAPACITY];
    }

    public V put(K key, V value) {
        if (key == null) {
            throw new IllegalArgumentException("Key can't be null");
        }
        int hash = hash(key);
        V retValue = value;

        checkCapacity();
        if (elements[hash] != null) {
            Node<K,V> node = elements[hash];

            while (node != null) {
                if(node.getKey().equals(key)) {
                    retValue = node.getValue();
                    node.value = value;
                    return retValue;
                }
                node = node.getNext();
            }

            //put the new element as 2nd element, as order does not matter and this requires less changes
            elements[hash].next = new Node<>(key, value, elements[hash].next);
            size++;

        } else {
            elements[hash] = new Node<>(key, value);
            size++;
        }
        return retValue;
    }

    private void checkCapacity() {
        if (size == elements.length) {
            Node<K,V>[] newArr = new Node[elements.length*2];

            System.arraycopy(elements, 0, newArr, 0, elements.length);
            elements = newArr;
        }
    }

    public V get(K key) {

        if (key == null) {
            throw new IllegalArgumentException("Key can't be null");
        }

        int hash = hash(key);
        Node<K,V> element = elements[hash];
        if (element == null) {
            return null;
        }

        if (element.key.equals(key)) {
            return element.value;
        }

        element = element.next;
        while (element != null) {
            if (element.key.equals(key)) {
                return element.value;
            }
            element = element.next;
        }
        return  null;
    }

    public V remove(K key) {
        if (key == null) {
            throw new IllegalArgumentException("Key can't be null");
        }

        int hash = hash(key);
        Node<K,V> element = elements[hash];
        if (element == null) {
            return null;
        }
        V retValue = null;

        if (element.key.equals(key)) {
            retValue = element.value;
            elements[hash] = element.next;
            size--;
            return retValue;
        }

        Node<K,V> prev = element;
        element = element.next;

        while (element != null) {
            if (element.key.equals(key)) {
                prev.next = element.next;
                size--;
                return element.value;
            }
            element = element.next;
        }
        return retValue;
    }

    public int size() {
        return size;
    }

    private int hash(K key) {
        int hash = Objects.hashCode(key);
        hash = hash >>> 1;

        return hash % elements.length;
    }

    class Node<K, V> {
        private K key;
        private V value;
        private Node<K,V> next;

        Node(K key, V value) {
            this.key = key;
            this.value = value;
        }

        Node(K key, V value, Node<K,V> next) {
            this.key = key;
            this.value = value;
            this.next = next;
        }

        K getKey() {
            return key;
        }

        V getValue() {
            return value;
        }

        Node<K,V> getNext() {
            return this.next;
        }
    }
}
