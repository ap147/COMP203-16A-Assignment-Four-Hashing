# COMP203-16A-Assignment-Four-Hashing


COMP203-16A — Assignment Four
Hashing
Due: Tuesday, June 14th, 2016 — 11.30pm

Description: This assignment is intended to give you experience with hashtables and hashing. You are required to implement three Java classes: a data class (objects to go into your hashtable), a hashtable class (to store data objects), and a testing class (a program object to test your hashtable) according to the specification below. You will use your hashing program to process the exact same sort of file of ATM transactions that you did for your third assignment. (Note that each class you define must go in a source file of its own.)

The data class: Your data class is just an extension of the Account class from assignment three. Simply override the default hashCode() method so that it returns the value of the key as the object's hashcode. (Note that we could implement a more thoughtful function if we wanted to spread Account codes around more, but this simple method will suffice for this assignment.)
The hashtable class: Your hashtable class must be called MyHashTable (defined in MyHashTable.java) and have five private member variables. Three are integers that keep 1) the capacity of the hashtable, 2) a count of how many slots in the table are currently used, and 3) a count of the number of collisions detected during use. The first two can be used to compute the load factor, and the third will offer an estimate of performance. The other two member variables are arrays that constitute the hashtable itself: one is an array of integers to hold keys and the other is an array of references to data objects associated with those keys. A single hashcode indexes both arrays, such that if the "key" found for a given index into the first array matches the key of the search then the other array has the reference to the associated data object at that same index. (Note that one could just use a single array of objects where each object holds both a key and a data reference, but for this assignment you can just use two arrays.)

The constructor for the hashtable must accept a positive integer parameter specifying the initial size (i.e. capacity) of the hashtable. The two arrays should be instantiated in the constructor so they are ready to be used, and the three integer member variables should be set accordingly.

This class should have three basic public methods: put(k,d) which puts the data object reference d in the hashtable at the location given by the hashcode computed for the key k. There must also be a get(k) method that returns a reference to the data object associated with the key k, or "null" if there is no object with that key. And your hashtable must have a remove(k) that removes the datum with key k. In addition to this, your hashtable must have a public method called getCollisions() that returns the number of collisions so far recorded by the hashtable. Thus every operation that probes into the table when looking to match a key must increment the collision-counting member variable. (Note that all data references for your program are to objects of type Account; whereas a more generally useful hashtable would handle any kind of Object.)
Hashing

When any key-based search operation occurs, the hashtable obtains an initial hashcode from the key object itself (using its hashCode method). The hashtable then uses that code to compute an index simply by mapping it into the allowable range given its capacity (i.e. zero to capacity-1) via the modulus operator. If this location isn't valid for this key (given the current operation) because of a collision, then the hashtable must invoke a collision resolution method. You must implement two collision resolution methods: linear probing, and key-offset probing. You will then compare how well each performs for a specific data processing task.

The testing class:

Create a program object called TestHash.java that can be used to see how well your hashing code works. It should take three arguments: a single letter "L" or "K" that indicates whether linear-probing or key-offset probing should be used; a positive integer specifying the initial capacity of the hashtable; and the name of a transaction file to be used as input.

The input file will be of the same format as it was for assignment three: a list of ATM transactions. On a deposit or withdrawal, if the Account is not in the hashtable then insert it assuming its initial balance was zero, then update it according to this first transaction. If the Account is aleady in the table, just update the balance. If a transaction closes an account, then remove it from the table.

Your program should stop when either the input file ends or if an insert into the table causes the load factor to exceed 80%. Its last line of standard output must be the label "COLLISIONS:" followed by the number of collisions that occurred during processing. Your program may produce other output prior to this, but such output should go to standard error.

Submission: Submit via Moodle you well-formatted and documented source code files (no .class or data files, please). If you want, you may include a plain text Readme.txt if you want to say anything more about your solution. Make sure your name and ID number form part of the header documentation in all of your source code files.

Tony C. Smith, 27/5/2016
