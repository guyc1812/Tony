# Wait and Sleep

### Object methods

* wait

    Defined in Object Class. <br>
    Wait method tells the current thread to give up monitor and go to sleep.<br>
    Wait can only be called from a synchronized block. <br>
    
* notify
    
    Wakes up a single thread that is waiting on this object's monitor.
    
* notify
    
    Wakes up all the threads that called wait() on the same object.


### Thread method

* sleep 
    
    Thread.sleep() is a static method that can be called from any context. <br>
    Thread.sleep() pauses the current thread and does not release any locks.


### Why wait(), notify() and notifyAll() must be called inside a synchronized method or block.

Every object created in Java has one associated monitor (mutually exclusive lock). <br>
Only one thread can own a monitor at any given time.

Any method or a block of code, 
if not qualified with the keyword synchronized can be executed by more than one thread at any given time.
There is no monitor to wait or wake.


### Why wait and notify methods are defined in Object class not in Thread class.

wait() and notify() methods work at the monitor level, <br>
Wait method tells the current thread to give up monitor, <br>
notify Wakes up a single thread that is waiting on this object's monitor. <br>
Important point to note here is that monitor is assigned to an object not to a particular thread. 

wait(), notify() and notifyAll() are used for inter-thread communication. <br>
But threads themselves have no knowledge of each others status. <br>
It is the shared object among the threads that acts as a communicator among the threads.


### Compare the sleep() and wait() methods in Java.

sleep() is a blocking operation that keeps a hold on the monitor/lock of the shared object for the specified number of milliseconds.
sleep() is most commonly used for polling, or to check for certain results, at a regular interval. 

wait() simply pauses the thread until either <br>
* the specified number of milliseconds have elapsed or 
* it receives a desired notification from another thread (whichever is first), <br>
    without keeping a hold on the monitor/lock of the shared object.
    
wait() is generally used in multithreaded applications, <br>
in conjunction with notify()/notifyAll(), to achieve synchronization and avoid race conditions.


[CODE](https://github.com/guyc1812/Tony/blob/master/src/main/java/com/avengers/tony/JavaBasic/thread/code)