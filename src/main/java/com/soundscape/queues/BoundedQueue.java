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
 * A simple, thread-safe bounded queue implementation
 * <p/>
 * The general contract of {@code BoundedQueue} is:
 * <ul>
 * <li> A BoundedQueue is initialized with a fixed size</li>
 * <li> After initialization a BoundedQueue guarantees never to allocate additional memory</li>
 * <li> Multiple threads can safely manipulate queue</li>
 * <li> Dequeuing from an empty queue returns null</li>
 * <li> Enqueuing to a full queue returns false</li>
 * <li> Enqueuing null is a no-op</li>
 * <li> The content of an unused bucket is undefined</li>
 * <li>
 * </ul>
 */

public class BoundedQueue<T> implements MinimalQueue<T> {

    // Implement the bounded queue as an ArrayList
    private ArrayList<T> queueList;
    // size of queue (differs from size of ArrayList)
    private int size;
    // index of front of queue
    private int front;
    // index of back of queue
    private int back;

    // constructor
    public BoundedQueue(int size) {
        if (size <= 0) {
            throw new IllegalArgumentException();
        }
        this.size = size;
        // allocate extra cell in array for "back" (always empty)
        queueList = new ArrayList<T>(size + 1);
        for (int i = 0; i < size + 1; i++) {
            queueList.add(null);
        }
        front = 0;
        back = 0;
    }

    @Override
    // remove and return one item from the front of queue
    public T dequeue() {
        T item = null;

        // ensure only one consumer at a time can dequeue
        synchronized (this) {
            if (count() > 0) {
                item = queueList.get(front);
                if (--front < 0) {
                    front = size;
                }
            }
        }
        return item;
    }

    @Override
    // add new item to end of queue only if it's not null and if there's room for it
    public boolean enqueue(T obj) {
        if (obj == null) {
            return false;
        }

        boolean success = false;

        // ensure only one consumer at a time can enqueue
        synchronized (this) {
            // add element only if there's room for it
            if (count() < size) {
                queueList.set(back, obj);
                if (--back < 0) {
                    back = size;
                }
                success = true;
            }
        }
        return success;
    }

    @Override
    // count items in queue
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

    // return queue capacity, guaranteed invariant after initialization
    public int size() {
        return size;
    }
}
