package genericLinkedList;

public interface IGenericLinkedList<T> {

    /**
     * Adds an item to the beginning of the list
     */
    void addFirst(T data);

    /**
     * Add an item to the end of the list
     */
    void addLast(T data);

    /**
     * Remove an item from the beginning of the list
     */
    void removeFirst();

    /**
     * Remove an item from the end of the list
     */
    void removeLast();

    /**
     * Get the item at the beginning of the list
     */
    T getFirst();

    /**
     * Get the item at the end of the list
     */
    T getLast();

    /**
     * Get size of linkedList
     *
     * @return size of linkedList
     */
    int size();

    /**
     * Return a string representation of the GenericLinkedList
     */
    String printList();

    /**
     * Returns true if linked list is empty, else false
     */
    boolean isEmpty();

    /**
     * Inserts an item at an index
     *
     * @param data The data to be inserted
     * @param idx  The index to insert at
     * @return true if successful, else false
     */
    boolean insertAt(T data, int idx);

    /**
     * Retrieves an item given an index
     *
     * @param idx The index to retrieve the item
     * @return The item
     */
    T get(int idx);
}
