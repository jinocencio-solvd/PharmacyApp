package linkedList;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class LinkedListTest {
    private static LinkedList<Integer> linkedList;
    @BeforeEach
    void setUp() {
        linkedList = new LinkedList<>();
    }

    @Test
    void testAddFirst() {
        linkedList.addFirst(5);
        linkedList.addFirst(3);
        assertEquals(3, linkedList.getFirst());
        assertEquals(2, linkedList.size());
    }

    @Test
    void addLast() {
        linkedList.addLast(5);
        linkedList.addLast(3);
        assertEquals(3, linkedList.getLast());
        assertEquals(2, linkedList.size());
    }

    @Test
    void removeFirst() {
        linkedList.addFirst(5);
        linkedList.addFirst(3);
        linkedList.removeFirst();
        assertEquals(5, linkedList.getFirst());
        assertEquals(1, linkedList.size());
    }

    @Test
    void removeLast() {
        linkedList.addLast(5);
        linkedList.addLast(3);
        linkedList.removeLast();
        assertEquals(5, linkedList.getLast());
        assertEquals(1, linkedList.size());
    }

    @Test
    void getFirst() {
        linkedList.addFirst(5);
        linkedList.addFirst(3);
        assertEquals(3, linkedList.getFirst());
        assertEquals(2, linkedList.size());
    }

    @Test
    void getLast() {
        linkedList.addLast(5);
        linkedList.addLast(3);
        assertEquals(3, linkedList.getLast());
        assertEquals(2, linkedList.size());
    }

    @Test
    void size() {
        linkedList.addFirst(2);
        linkedList.addFirst(1);
        linkedList.addLast(3);
        linkedList.addLast(4);
        assertEquals(4, linkedList.size());
    }

    @Test
    void printList() {
        linkedList.addFirst(2);
        linkedList.addFirst(1);
        linkedList.addLast(3);
        linkedList.addLast(4);
        assertEquals("head -> 1 -> 2 -> 3 -> 4 -> tail", linkedList.printList());
    }
}