package genericLinkedList;

import java.util.NoSuchElementException;

/**
 * GenericLinkedList implementation of IGenericLinkedList. Supports adding and removing elements
 * from front and back, getting first and last elements, and get size of list. Linked list is
 * generic and can be used to store any type of object
 *
 * @param <T> the type of object to store in linked list
 */
public class GenericLinkedList<T> implements IGenericLinkedList<T> {

    private GenericNode<T> head;
    private int size;

    public GenericLinkedList() {
        this.head = null;
        size = 0;
    }

    /**
     * Adds an item to the beginning of the list
     */
    @Override
    public void addFirst(T data) {
        GenericNode<T> newGenericNode = new GenericNode<>(data);
        // Set the next field of the new node to the current head of the list:
        newGenericNode.next = head;
        // update the head of the list to point to the new node
        head = newGenericNode;
        size++;
    }

    /**
     * Add an item to the end of the list
     */
    @Override
    public void addLast(T data) {
        GenericNode<T> newGenericNode = new GenericNode<>(data);
        // if list is empty
        if (head == null) {
            head = newGenericNode;
        } else {
            // Traverse the list to find the last node
            GenericNode<T> curr = head;
            while (curr.next != null) {
                curr = curr.next;
            }
            // Reached last node --> set it's .next to newGenericNode
            curr.next = newGenericNode;
        }
        size++;
    }

    /**
     * Remove an item from the beginning of the list
     *
     * @throws NoSuchElementException if the list is empty.
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
     *
     * @throws NoSuchElementException if the list is empty.
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
            GenericNode<T> curr = head;
            while (curr.next.next != null) {
                curr = curr.next;
            }
            curr.next = null;
        }
        size--;
    }

    /**
     * Get the item at the beginning of the list
     *
     * @throws NoSuchElementException if the list is empty.
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
     *
     * @throws NoSuchElementException if the list is empty.
     */
    @Override
    public T getLast() {
        // check if list is empty
        if (head == null) {
            throw new NoSuchElementException();
        }
        GenericNode<T> curr = head;
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
     * Return a string representation of the GenericLinkedList
     */
    @Override
    public String printList() {
        if (head == null) {
            return "[]";
        }

        StringBuilder sb = new StringBuilder();
        sb.append("head -> ");

        GenericNode<T> curr = head;
        while (curr != null) {
            sb.append(curr.data);
            sb.append(" -> ");

            // move to next node
            curr = curr.next;
        }

        sb.append("tail");
        return sb.toString();
    }

    /**
     * Returns true if linked list is empty, else false
     */
    @Override
    public boolean isEmpty() {
        return head == null && size == 0;
    }
}
