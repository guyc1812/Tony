# Atomic for Account

### Demo

```
public class AtomicAccount {

    private AtomicLong balance;

    public AtomicAccount(long money) {
        balance = new AtomicLong(money);
        System.out.println("Total Money:" + balance);
    }

    public void deposit(long money) {
        balance.addAndGet(money);
    }

    public void withdraw(long money) {
        // every thread can withdraw successfully with while (true)
        // only one thread will withdraw successfully when removing while (true)
        while (true) {
            long oldValue = balance.get();
            if (oldValue < money) {
                System.out.println(Thread.currentThread().getName() + " Insufficient! balance: " + balance);
                break;
            }
            try {
                Thread.sleep(500);
            } catch (Exception ignored) {
            }
            if (balance.compareAndSet(oldValue, oldValue - money)) {
                System.out.println(Thread.currentThread().getName() + " Withdraw " + money + " successfully!  balance: " + balance);
                break;
            }
            System.out.println(Thread.currentThread().getName() + " Blocked, try again");
        }
    }

}
```

### Demo Test

```
public static void main(String[] args) {
    final AtomicAccount account = new AtomicAccount(900);
    ExecutorService service = Executors.newFixedThreadPool(9);
    for (int i = 1; i < 10; i++) {
        service.execute(() -> account.withdraw(100));
    }
    service.shutdown();
}
```

### Output

```
Total Money:900
pool-1-thread-1 Blocked, try again
pool-1-thread-4 Withdraw 100 successfully!  balance: 800
pool-1-thread-6 Blocked, try again
pool-1-thread-5 Blocked, try again
pool-1-thread-3 Blocked, try again
pool-1-thread-2 Blocked, try again
pool-1-thread-9 Blocked, try again
pool-1-thread-8 Blocked, try again
pool-1-thread-7 Blocked, try again
pool-1-thread-1 Withdraw 100 successfully!  balance: 700
pool-1-thread-6 Blocked, try again
pool-1-thread-3 Blocked, try again
pool-1-thread-8 Blocked, try again
pool-1-thread-2 Blocked, try again
pool-1-thread-9 Blocked, try again
pool-1-thread-7 Blocked, try again
pool-1-thread-5 Blocked, try again
pool-1-thread-6 Blocked, try again
pool-1-thread-7 Blocked, try again
pool-1-thread-3 Blocked, try again
pool-1-thread-5 Withdraw 100 successfully!  balance: 600
pool-1-thread-8 Blocked, try again
pool-1-thread-2 Blocked, try again
pool-1-thread-9 Blocked, try again
pool-1-thread-2 Blocked, try again
pool-1-thread-7 Withdraw 100 successfully!  balance: 500
pool-1-thread-9 Blocked, try again
pool-1-thread-3 Blocked, try again
pool-1-thread-6 Blocked, try again
pool-1-thread-8 Blocked, try again
pool-1-thread-3 Blocked, try again
pool-1-thread-2 Blocked, try again
pool-1-thread-6 Blocked, try again
pool-1-thread-9 Blocked, try again
pool-1-thread-8 Withdraw 100 successfully!  balance: 400
pool-1-thread-9 Blocked, try again
pool-1-thread-2 Blocked, try again
pool-1-thread-3 Withdraw 100 successfully!  balance: 300
pool-1-thread-6 Blocked, try again
pool-1-thread-2 Blocked, try again
pool-1-thread-9 Withdraw 100 successfully!  balance: 200
pool-1-thread-6 Blocked, try again
pool-1-thread-6 Blocked, try again
pool-1-thread-2 Withdraw 100 successfully!  balance: 100
pool-1-thread-6 Withdraw 100 successfully!  balance: 0
```

[CODE](https://github.com/guyc1812/Tony/blob/master/src/main/java/com/avengers/tony/JavaBasic/atomic/code)
