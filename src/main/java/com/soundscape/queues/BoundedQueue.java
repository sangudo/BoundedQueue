package com.soundscape.queues;

/**
 * Created by IntelliJ IDEA.
 * User: jdw
 * Date: 7/16/12
 * Time: 11:16 PM
 * Copyright 2012 John Wetherill
 */

import java.util.ArrayList;

/**
 * A simple, thread-safe bounded queue implementation using a circular array based on ArrayList.
 * <p/>
 * The general contract of {@code BoundedQueue} is:
 * <ul>
 * <li> A {@code BoundedQueue} is initialized with a fixed size</li>
 * <li> After initialization a {@code BoundedQueue} guarantees never to allocate additional memory</li>
 * <li> Multiple threads can safely manipulate queue</li>
 * <li> Dequeuing from an empty queue returns null</li>
 * <li> Enqueuing to a full queue returns false</li>
 * <li> Enqueuing null is a no-op</li>
 * <li> The content of an unused bucket is undefined and never referenced</li>
 * </ul>
 */

public class BoundedQueue<T> extends ArrayList<T> implements MinimalQueue<T> {

    // size of queue (differs from size of ArrayList)
    private int size;
    // index of front of queue
    private int front;
    // index of back of queue
    private int back;

    /**
     * Create a {@code BoundedQueue} of given size.
     * @param size
     */
    public BoundedQueue(int size) {
        super();
        if (size <= 0) {
            throw new IllegalArgumentException("queue size must be positive integer");
        }
        this.size = size;
        // allocate extra cell in array for bucket at back of queue which is always empty
        for (int i = 0; i < size + 1; i++) {
            add(null);
        }
        front = 0;
        back = 0;
    }

    @Override
    /**
     * Remove and return one item from the front of queue
     * @return item
     */
    public T dequeue() {
        T item = null;

        // ensure only one consumer at a time can dequeue
        synchronized (this) {
            if (count() > 0) {
                item = get(front);
                if (--front < 0) {
                    front = size;
                }
            }
        }
        return item;
    }

    @Override
    /**
     * add new item to end of queue if room is available.
     * Object cannot be null.
     * @param object to enqueue
     * @return boolean false if enqueue fails
     *
     */
    public boolean enqueue(T obj) {
        if (obj == null) {
            return false;
        }

        boolean success = false;

        // ensure only one consumer at a time can enqueue
        synchronized (this) {
            // add element only if there's room for it
            if (count() < size) {
                set(back, obj);
                if (--back < 0) {
                    back = size;
                }
                success = true;
            }
        }
        return success;
    }

    @Override
    /**
     *  count items in queue
     *  @return int count of occupied queue buckets
     */
    public int count() {
        int count = 0;

        if (front == back) {
            count = 0;
        } else if (front > back) {
            count = front - back;
        } else if (front < back) {
            count = size - back + front + 1;
        } else {
            assert false; // never happens
        }
        return count;
    }
}
