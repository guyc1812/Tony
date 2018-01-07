package com.avengers.tony.JavaBasic.thread;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class multiThread_3 {

    ExecutorService executorService1 = Executors.newSingleThreadExecutor();

    ExecutorService executorService2 = Executors.newFixedThreadPool(10);

    ExecutorService executorService3 = Executors.newScheduledThreadPool(10);

    ExecutorService executorService4 = Executors.newCachedThreadPool();


    public static void main(String[] args) {

        // A Runnable
        Runnable run = new Runnable() {public void run() { System.out.println("Asynchronous task");}};

        // A Callable
        Callable call = new Callable(){public Object call() throws Exception{ return "Callable Result";}};

        // A list_linkedList of Callable
        Set<Callable<String>> callables = new HashSet<>();
        callables.add(() -> "Task 1");
        callables.add(() -> "Task 2");
        callables.add(() -> "Task 3");

        multiThread_3 thread = new multiThread_3();

        // execute(run)
        thread.executorService1.execute(run);

        // submit(Runnable)
        try{
            Future future1 = thread.executorService1.submit(run);
            System.out.println("future.get = " + future1.get());  //returns null if the task has finished correctly.
        }catch (Exception e){
            e.printStackTrace();
        }

        // submit(Callable)
        try{
            Future future2 = thread.executorService1.submit(call);
            System.out.println("future.get = " + future2.get());  //returns null if the task has finished correctly.
        }catch (Exception e){
            e.printStackTrace();
        }

        // invokeAny(Callable)
        try{
            String result = thread.executorService1.invokeAny(callables);
            System.out.println("result = " + result);
        }catch (Exception e){
            e.printStackTrace();
        }

        // invokeAll(callables)
        try{
            List<Future<String>> futures = thread.executorService1.invokeAll(callables);
            for(Future<String> future : futures){
                System.out.println("future.get = " + future.get());
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        thread.executorService1.shutdown();

    }

}
