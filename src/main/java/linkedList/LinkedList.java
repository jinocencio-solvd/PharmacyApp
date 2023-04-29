package linkedList;

import java.util.NoSuchElementException;

/**
 *LinkedList implementation of IlinkedList. Supports adding and removing elements from front and back, getting first and last elements, and get size of list. Linked list is generic and can be used to store any type of object
 * @param <T> the type of object to store in linked list
 */
public class LinkedList<T> implements IlinkedList<T> {

    private Node<T> head;
    private int size;

    public LinkedList() {
        this.head = null;
        size = 0;
    }

    /**
     * Adds an item to the beginning of the list
     */
    @Override
    public void addFirst(T data) {
        Node<T> newNode = new Node<>(data);
        // Set the next field of the new node to the current head of the list:
        newNode.next = head;
        // update the head of the list to point to the new node
        head = newNode;
        size++;
    }

    /**
     * Add an item to the end of the list
     */
    @Override
    public void addLast(T data) {
        Node<T> newNode = new Node<>(data);
        // if list is empty
        if (head == null) {
            head = newNode;
        } else {
            // Traverse the list to find the last node
            Node<T> curr = head;
            while (curr.next != null) {
                curr = curr.next;
            }
            // Reached last node --> set it's .next to newNode
            curr.next = newNode;
        }
        size++;
    }

    /**
     * Remove an item from the beginning of the list
     */
    @Override
    public void removeFirst() {
        // check if list is empty
        if (head == null) {
            throw new NoSuchElementException("Nothing to remove");
        }
        // Update the head ref to point to the node that the current head's next field points to
        head = head.next;
        size--;
    }

    /**
     * Remove an item from the end of the list
     */
    @Override
    public void removeLast() {
        // check if list is empty
        if (head == null) {
            throw new NoSuchElementException();
        }
        // check if there is only one node;
        if (head.next == null) {
            head = null;
        } else {
            Node<T> curr = head;
            while (curr.next.next != null) {
                curr = curr.next;
            }
            curr.next = null;
        }
        size--;
    }

    /**
     * Get the item at the beginning of the list
     */
    @Override
    public T getFirst() {
        // Check if list is empty
        if (head == null) {
            throw new NoSuchElementException();
        }
        return head.data;
    }

    /**
     * Get the item at the end of the list
     */
    @Override
    public T getLast() {
        // check if list is empty
        if (head == null) {
            throw new NoSuchElementException();
        }
        Node<T> curr = head;
        while (curr.next != null) {
            curr = curr.next;
        }
        return curr.data;
    }

    /**
     * Get the size of the linkedList
     *
     * @return size of linkedList
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * Return a string representation of the LinkedList
     */
    @Override
    public String printList() {
        return null;
    }
}
