package design.impl;

class MyLinkedList {

    public static class Node {
        int val;
        Node next;
        Node prev;

        Node(int val) {
            this.val = val;
        }
    }

    private int size;
    private Node head;

    /** Initialize your data structure here. */
    public MyLinkedList() {
        head = null;
        size = 0;
    }

    /** Get the value of the index-th node in the linked list. If the index is invalid, return -1. */
    public int get(int index) {
        if(index < 0 || index > size-1) {
            return -1;
        }
        int count = 0;
        Node node = head;
        while(node.next != null && count < index) {
            node = node.next;
            count++;
        }
        System.out.print(node.val + " ,");
        return node.val;
    }

    /** Add a node of value val before the first element of the linked list. After the insertion, the new node will be the first node of the linked list. */
    public void addAtHead(int val) {
        System.out.print("null, ");
        Node newNode = new Node(val);
        if(head == null) {
            head = newNode;
        } else {
            newNode.next = head;
            head.prev = newNode;
            head = newNode;
        }
        size++;
    }

    /** Append a node of value val to the last element of the linked list. */
    public void addAtTail(int val) {
        System.out.print("null, ");
        Node newNode = new Node(val);
        Node node = head;
        if(node == null) {
            head = newNode;
        } else {
            while(node.next != null) {
                node = node.next;
            }
            node.next = newNode;
            newNode.prev = node;
        }
        size++;
    }

    /** Add a node of value val before the index-th node in the linked list. If index equals to the length of linked
     * list, the node will be appended to the end of linked list. If index is greater than the length, the node will
     * not be inserted. */
    public void addAtIndex(int index, int val) {
        System.out.print("null, ");
        if(index > size) {
            return;
        }

        if(index == 0) {
            addAtHead(val);
        } else if(index == size) {
            addAtTail(val);
        } else {
            int count = 0; Node node = head;
            Node newNode = new Node(val);
            while(count < index) {
                node  = node.next;
                count++;
            }
            Node temp = node.prev;
            node.prev.next = newNode;
            node.prev = newNode;
            newNode.next = node;
            newNode.prev = temp;
            size++;
        }
    }

    /** Delete the index-th node in the linked list, if the index is valid. */
    public void deleteAtIndex(int index) {
        System.out.print("null, ");
        if(index < 0 || index > size -1) {
            return;
        }
        if(index == 0) {
            head = head.next;
            head.prev = null;
        }

        Node node = head;
        int count = 0;
        while(count < index) {
            node = node.next;
            count++;
        }

        if(index == size-1) {
            node.prev.next = null;
            node.prev = null;
        } else {
            node.prev.next = node.next;
            node.next.prev = node.prev;
            node.next = null;
            node.prev = null;
        }
    }

    public static void main(String[] args) {
         MyLinkedList obj = new MyLinkedList();
         obj.addAtHead(1);
         obj.addAtTail(3);
         obj.addAtIndex(1,2);
         obj.get(1);
         obj.deleteAtIndex(1);
         obj.get(1);
    }
}


