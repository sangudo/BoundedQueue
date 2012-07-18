BoundedQueue
============

A simple, thread-safe Bounded Queue implementation using a
circular array based on `java.util.ArrayList`.

See the [BoundedQueue API docs](http://wetherill.net/apidocs/ "BoundedQueue API docs")


### Problem Statement

<b>Bounded Queue</b>

*Using Java or Javascript write a queue that is initialized to a fixed
size and can be used by a number of readers and writers. It should not
allocate memory after the initialization.*


### Distilled Requirements


* Support basic queue operations (put/get)
* Allow multiple readers/writers (thread-safe)
* Datastructure must be fixed invariant size
* No memory allocation after initialization


### BoundedQueue Contract

  <p>
  The general contract of BoundedQueue is:
 <ul>
 <li> A BoundedQueue is initialized with a fixed size</li>
 <li> After initialization a BoundedQueue guarantees never to allocate additional memory</li>
 <li> Multiple threads can safely manipulate queue</li>
 <li> Dequeuing from an empty queue returns null</li>
 <li> Enqueuing to a full queue returns false</li>
 <li> Enqueuing null is a no-op</li>
 <li> The content of an unused bucket is undefined and never referenced</li>
</ul>


### Circular Array Implementation

<p>The graphic below shows a circular array implementation of a
bounded queue in various states.

Notes:

<ul>
<li>The contents of the cell at the back of the queue are
undefined. The cell at the front of the queue contains
the previously enqueued object or null if queue is empty.</li>

<li>With this design, one cell (the "back" cell) is always  empty and
thus the queue capacity is one less than the array size.
</li>

![BoundedQueue](http://www.wetherill.net/boundedqueue.png)
