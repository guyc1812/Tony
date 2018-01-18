# Thread

### States

![States](../../imgs/thread-life-cycle-in-java-flowchart.gif)

![States](../../imgs/thread_status.jpg)


### Summary

sleep(), from running to blocked, does not 让渡 lock, can let other priority thread execute.
wait(),  from running to blocked, waite() and notify() only in the Synchronized block
Yield(), from running to runnable, only allow same priority thread to be runnable.
join(),  from running to blocked, wait for the thread to finish, implemented by "wait()"
