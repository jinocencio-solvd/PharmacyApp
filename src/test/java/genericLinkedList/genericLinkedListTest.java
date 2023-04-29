package genericLinkedList;

import static org.junit.jupiter.api.Assertions.*;

import java.util.NoSuchElementException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class GenericLinkedListTest {
    private static GenericLinkedList<Integer> genericLinkedList;
    @BeforeEach
    void setUp() {
        genericLinkedList = new GenericLinkedList<>();
    }

    @Test
    void testAddFirst() {
        genericLinkedList.addFirst(5);
        genericLinkedList.addFirst(3);
        assertEquals(3, genericLinkedList.getFirst());
        assertEquals(2, genericLinkedList.size());
    }

    @Test
    void addLast() {
        genericLinkedList.addLast(5);
        genericLinkedList.addLast(3);
        assertEquals(3, genericLinkedList.getLast());
        assertEquals(2, genericLinkedList.size());
    }

    @Test
    void removeFirst() {
        genericLinkedList.addFirst(5);
        genericLinkedList.addFirst(3);
        genericLinkedList.removeFirst();
        assertEquals(5, genericLinkedList.getFirst());
        assertEquals(1, genericLinkedList.size());
    }

    @Test
    void testThrowNoSuchElementException() {
        assertThrows(NoSuchElementException.class, ()-> genericLinkedList.removeFirst());
        assertThrows(NoSuchElementException.class, ()-> genericLinkedList.removeLast());
        genericLinkedList.addFirst(3);
        assertDoesNotThrow(()-> genericLinkedList.removeLast());
        assertThrows(NoSuchElementException.class, ()-> genericLinkedList.removeFirst());
        assertThrows(NoSuchElementException.class, ()-> genericLinkedList.removeLast());
    }

    @Test
    void removeLast() {
        genericLinkedList.addLast(5);
        genericLinkedList.addLast(3);
        genericLinkedList.removeLast();
        assertEquals(5, genericLinkedList.getLast());
        assertEquals(1, genericLinkedList.size());
    }

    @Test
    void getFirst() {
        genericLinkedList.addFirst(5);
        genericLinkedList.addFirst(3);
        assertEquals(3, genericLinkedList.getFirst());
        assertEquals(2, genericLinkedList.size());
    }

    @Test
    void getLast() {
        genericLinkedList.addLast(5);
        genericLinkedList.addLast(3);
        assertEquals(3, genericLinkedList.getLast());
        assertEquals(2, genericLinkedList.size());
    }

    @Test
    void size() {
        genericLinkedList.addFirst(2);
        genericLinkedList.addFirst(1);
        genericLinkedList.addLast(3);
        genericLinkedList.addLast(4);
        assertEquals(4, genericLinkedList.size());
    }

    @Test
    void printList() {
        genericLinkedList.addFirst(2);
        genericLinkedList.addFirst(1);
        genericLinkedList.addLast(3);
        genericLinkedList.addLast(4);
        assertEquals("head -> 1 -> 2 -> 3 -> 4 -> tail", genericLinkedList.printList());
    }
}