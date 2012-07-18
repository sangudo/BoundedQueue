package com.soundscape.queues;

/**
 * Created by IntelliJ IDEA.
 * User: jdw
 * Date: 7/17/12
 * Time: 10:46 AM
 * Copyright 2012 Glen Canyon Corporation
 */

/**
 * A stripped-down queue interface declaring only basic queue operations
 */
public interface MinimalQueue<T> {

    /**
     * Remove and return one item from the front of queue
     * @return item (null if queue is empty)
     */
    public T dequeue();

    /**
     * add new item to end of queue if room is available.
     * Object cannot be null.
     * @return boolean true if enqueue succeeds
     *
     */
    public boolean enqueue(T obj);

    /**
     *  count items in queue
     *  @return int count of occupied queue buckets
     */
    public int count();
}
