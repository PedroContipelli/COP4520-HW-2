### Problem 1
Compile: **javac Minotaur.java**<br>
Run: **java Minotaur**

### Problem 2
Compile: **javac Minotaur2.java**<br>
Run: **java Minotaur2**

I implemented strategy 3 as I think it would work best for this scenario.

Some advantages of using a queue for this approach are:
- It ensures that any guest who wants to enter will eventually get to do so.
- It is more orderly, ensuring that guests will enter in the same order they got there.

Some disadvantages are:
- Guests are stuck waiting in line and cannot really do anything else while waiting in the queue.

### Sources:
Maurice Herlihy and Nir Shavit. 2008. The Art of Multiprocessor Programming. Morgan Kaufmann Publishers Inc., San Francisco, CA, USA.

Technical advantages of using a queue:
 Cache-coherence Traffic: All threads spin on the same shared location causing
cache-coherence traffic on every successful lock access (though less than with
the TASLock).
 Critical Section Underutilization: Threads delay longer than necessary, causing
the critical section to be underutilized.
