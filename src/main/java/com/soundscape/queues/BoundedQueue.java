package com.soundscape.queues;

/**
 * Created by IntelliJ IDEA.
 * User: jdw
 * Date: 7/16/12
 * Time: 11:16 PM
 * Copyright 2012 John Wetherill
 */

import java.util.ArrayList;
import java.util.List;

/**
 * A simple, thread-safe bounded queueList implementation
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

    // Here we implement the bounded queue as an ArrayList
    private ArrayList<T> queueList;
    // size of queue (differs from size of ArrayList)
    private int size;
    // index of front of queue
    private int front;
    // index of back of queue
    private int back;
    // count of items in queue
    private int count;

    // constructor
    public BoundedQueue(int size) {
        assert (size > 0);
        this.size = size;
        // allocate extra cell in array for "back" (always empty)
        queueList = new ArrayList<T>(size+1);
        front = 0;
        back = 0;
    }

    @Override
    // remove and return one item from the front of queue
    public T dequeue() {
        T item = null;
        if (count() > 0) {
            item = queueList.get(front);
            front = --front % (size+1);
        }
        return item;
    }

    @Override
    // enqueue new item only if it's not null and if there's room for it
    public boolean enqueue(T obj) {
        boolean success = false;
        if (obj != null) {
            // add element only if there's room for it
            if (count() < size) {
                queueList.set(back, obj);
                back = --back % (size + 1);
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
