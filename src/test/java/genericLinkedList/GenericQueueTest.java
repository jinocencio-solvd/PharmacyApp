package genericLinkedList;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class GenericQueueTest {

    private static GenericQueue<Integer> genericQueue;

    @BeforeEach
    void setUp() {
        genericQueue = new GenericQueue<>();
    }

    @Test
    void enqueue() {
        genericQueue.enqueue(1);
        genericQueue.enqueue(2);
        genericQueue.enqueue(3);
        assertEquals(1, genericQueue.peek());
        assertEquals(3, genericQueue.size());

    }

    @Test
    void dequeue() {
        genericQueue.enqueue(1);
        genericQueue.enqueue(2);
        genericQueue.enqueue(3);
        assertEquals(1, genericQueue.dequeue());
        assertEquals(2, genericQueue.size());
        assertEquals(2, genericQueue.dequeue());
        assertEquals(1, genericQueue.size());


    }

    @Test
    void peek() {
        genericQueue.enqueue(1);
        genericQueue.enqueue(2);
        genericQueue.enqueue(3);
        assertEquals(1, genericQueue.peek());
        genericQueue.dequeue();
        assertEquals(2, genericQueue.peek());
    }
}