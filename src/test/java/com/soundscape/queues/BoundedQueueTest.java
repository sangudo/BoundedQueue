package com.soundscape.queues;

import junit.framework.TestCase;

/**
 * Unit tests for bounded queue
 */
public class BoundedQueueTest extends TestCase {

    public void testIsEmptyOnCreate() {
        BoundedQueue<String> boundedQueue = new BoundedQueue(1);
        assertTrue(boundedQueue.count() == 0);
    }

    public void testAddNull() {
        BoundedQueue<String> boundedQueue = new BoundedQueue(1);
        boolean success = boundedQueue.enqueue(null);
        assertTrue(!success);;
        assertTrue(boundedQueue.count() == 0);
    }

    public void testAddOneElement() {
        BoundedQueue<String> boundedQueue = new BoundedQueue(1);
        boundedQueue.enqueue("FIRST");
        assertTrue(boundedQueue.count() == 1);
    }

    public void testAddAndGetOneElement() {
        BoundedQueue<String> boundedQueue = new BoundedQueue(1);
        boundedQueue.enqueue("FIRST");
        String first = boundedQueue.dequeue();
        assertTrue(first.equals("FIRST"));
    }

    public void testFillAndEmpty() {
        int qsize = 10;
        BoundedQueue<String> boundedQueue = new BoundedQueue(qsize);
        for (int i = 0; i<qsize; i++) {
            boundedQueue.enqueue("element: " + i);
        }
        assertTrue(boundedQueue.count() == qsize);

        for (int i = 0; i < qsize; i++) {
            String s = boundedQueue.dequeue();
            assertTrue(s.equals("element: " + i));
        }
        assertTrue(boundedQueue.count() == 0);
    }

    public void testFillToCapacity() {
        int qsize = 100;
        BoundedQueue<String> boundedQueue = new BoundedQueue(qsize);
        for (int i = 0; i < qsize; i++) {
            boolean success = boundedQueue.enqueue("element: " + i);
            assertTrue(success);
        }
        assertTrue(boundedQueue.count() == qsize);
    }

    public void testOverFill() {
        int qsize = 100;
        BoundedQueue<String> boundedQueue = new BoundedQueue(qsize);
        for (int i = 0; i < qsize; i++) {
            boolean success = boundedQueue.enqueue("element: " + i);
            assertTrue(success);
        }
        boolean success = boundedQueue.enqueue("overfill");
        assertTrue(!success);
    }

    public void testDequeueFromEmptyQueue() {
        BoundedQueue<String> boundedQueue = new BoundedQueue(1);
        String s = boundedQueue.dequeue();
        assertNull(s);
    }


    public void testRepeatedlyFillAndEmpty() {
        int qsize = 37; // prime number
        BoundedQueue<String> boundedQueue = new BoundedQueue(qsize);
        for (int i = 0; i < 20; i++) {
            boundedQueue.enqueue("element: " + i);
        }
        assertTrue(boundedQueue.count() == 20);

        for (int i = 0; i < 5; i++) {
            boundedQueue.dequeue();
        }
        assertTrue(boundedQueue.count() == 15);

        for (int i = 1; i < 50; i++) {

            for (int j = 0; j < 5; j++) {
                boolean success = boundedQueue.enqueue("element: " + i);
                assertTrue(success);

            }

            for (int j = 0; j < 5; j++) {
                String s = boundedQueue.dequeue();
                assertNotNull(s);
            }
            assertTrue(boundedQueue.count() == 15);
        }
    }
}
