package com.soundscape.queues;

/**
 * Created by IntelliJ IDEA.
 * User: jdw
 * Date: 7/17/12
 * Time: 12:06 AM
 * Copyright 2012 Glen Canyon Corporation
 */

/**
 * A stripped-down queue interface declaring only basic queue operations
 */
public interface MinimalQueue<T> {

    // remove and return first element in queue
    public T dequeue();

//FIXME: figure out proper way to notate this
    // add new element to end of queue
    public boolean enqueue(T obj);

    // number of elements in queue
    public int count();
}
