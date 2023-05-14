package genericLinkedList;

import java.util.NoSuchElementException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * GenericLinkedList implementation of IGenericLinkedList. Supports adding and removing elements
 * from front and back, getting first and last elements, and get size of list. Linked list is
 * generic and can be used to store any type of object
 *
 * @param <T> the type of object to store in linked list
 */
public class GenericLinkedList<T> implements IGenericLinkedList<T> {

    private static final Logger LOG = LogManager.getLogger(GenericLinkedList.class);

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

    /**
     * Inserts an item at an index
     *
     * @param data The data to be inserted
     * @param idx  The index to insert at
     * @return true if successful, else false
     */
    @Override
    public boolean insertAt(T data, int idx) {
        GenericNode<T> newNode = new GenericNode<>(data);
        // Edge case: empty list and incorrect idx
        if (head == null && idx != 0) {
            return false;
        }

        // Insert at beginning
        if (idx == 0) {
            newNode.next = head;
            head = newNode;
            size++;
            return true;
        }

        // iterate up until idx - 1 and insert newNode;
        GenericNode<T> curr = head;
        int currIdx = 0;
        while (curr != null && currIdx < idx - 1) {
            curr = curr.next;
            currIdx++;
        }
        // check if out of bounds
        if (curr == null) {
            return false;
        }

        newNode.next = curr.next;
        curr.next = newNode;
        size++;
        return true;
    }

    /**
     * Removes an item at an index
     *
     * @param idx  The index to remove at
     * @return true if successful, else false
     */
    public boolean removeAt(int idx) {
        // Edge case: empty list and incorrect idx
        if (head == null || idx < 0) {
            return false;
        }

        // Remove at beginning
        if (idx == 0) {
            head = head.next;
            size--;
            return true;
        }

        // iterate to find the node at idx
        int currIdx = 0;
        GenericNode<T> prev = null;
        GenericNode<T> curr = head;
        while (curr != null && currIdx < idx) {
            prev = curr;
            curr = curr.next;
            currIdx++;
        }
        // check if out of bounds
        if (curr == null) {
            return false;
        }

        prev.next = curr.next;
        size--;
        return true;
    }


    /**
     * Retrieves an item given an index
     *
     * @param idx The index to retrieve the item
     * @return The item at idx
     */
    @Override
    public T get(int idx) {
        GenericNode<T> curr = head;
        int currIdx = 0;

        while (curr != null && currIdx < idx) {
            curr = curr.next;
            currIdx++;
        }
        if (curr == null) {
            LOG.debug("Index out of bounds");
            throw new IndexOutOfBoundsException("Index out of bounds");
        }
        return curr.data;
    }
}
