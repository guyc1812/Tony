# Node.js Event Loop

**Libuv is the library that provides the event loop to Node.js.**

![libuv](http://guyuchen.com/deadpool/github/node-libuv.png)

## Misconceptions

1. The event loop runs in a separate thread than the user code

    **Misconception**<br>
    There is a main thread where the JavaScript code of the user (userland code) runs in and another one that runs the event loop. <br>
    Every time an asynchronous operation takes place, the main thread will hand over the work to the event loop thread and once it is done, the event loop thread will ping the main thread to execute a callback.

    **Reality**<br>
    There is only one thread that executes JavaScript code and this is the thread where the event loop is running. <br>
    The execution of callbacks (know that every userland code in a running Node.js application is a callback) is done by the event loop. 

2. Everything that’s asynchronous is handled by a thread pool

    **Misconception**<br>
    Asynchronous operations, like working with the filesystems, doing outbound HTTP requests or talking to databases are always loaded off to a thread pool provided by libuv.

    **Reality**<br>
    Libuv by default creates a thread pool with four threads to offload asynchronous work to. <br>
    Today’s operating systems already provide asynchronous interfaces for many I/O tasks (e.g. AIO on Linux).<br>
    Whenever possible, libuv will use those asynchronous interfaces, avoiding usage of the thread pool. <br>
    The same applies to third party subsystems like databases. <br>
    Here the authors of the driver will rather use the asynchronous interface than utilizing a thread pool.<br>
    *In short: Only if there is no other way, the thread pool will be used for asynchronous I/O.*

3. The event loop is something like a stack or queue

    **Misconception**<br>
    The event loop continuously traverses a FIFO of asynchronous tasks and executes the callback when a task is completed.

    **Reality**<br>
    While there are queue-like structures involved, the event loop does not run through and process a stack.<br> 
    The event loop as a process is a set of phases with specific tasks that are processed in a round-robin manner.


## Understanding the phases of an event loop cycle

To really understand the event loop we have to understand which work is done in which phase. <br> 
Showing how the event loop works would be as follows:<br> 

![node-phases](http://guyuchen.com/deadpool/github/node-phases.png)

* Timers

    Everything that was scheduled via setTimeout() or setInterval() will be processed here.

* IO Callbacks

    Here most of the callbacks will be processed. <br> 
    As all userland code in Node.js is basically in callbacks (e.g a callback to an incoming http request triggers a cascade of callbacks), this is the userland code.

* IO Polling

    Polls for new events to be processed on the next run.

* Set Immediate
    
    Runs all callbacks registered via setImmediate().

* Close

    Here all on(‘close’) event callbacks are processed.

    ![node-loop](http://guyuchen.com/deadpool/github/node-loop.png)





## Threads in Node.js

![node-threads](http://guyuchen.com/deadpool/github/node-threads.png)










refer from [What you should know to really understand the Node.js Event Loop](https://medium.com/the-node-js-collection/what-you-should-know-to-really-understand-the-node-js-event-loop-and-its-metrics-c4907b19da4c)
