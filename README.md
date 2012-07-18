BoundedQueue
============

A simple, thread-safe @{code Bounded Queue} implementation using a
circular array based on @{code java.util.ArrayList}.


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
  The general contract of @{code BoundedQueue} is:
 <ul>
 <li> A @{code BoundedQueue} is initialized with a fixed size</li>
 <li> After initialization a @{code BoundedQueue} guarantees never to allocate additional memory</li>
 <li> Multiple threads can safely manipulate queue</li>
 <li> Dequeuing from an empty queue returns null</li>
 <li> Enqueuing to a full queue returns false</li>
 <li> Enqueuing null is a no-op</li>
 <li> The content of an unused bucket is undefined and never referenced</li>
</ul>


