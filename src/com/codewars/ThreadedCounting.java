package com.codewars;

import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * https://www.codewars.com/kata/threaded-counting
 */
public class ThreadedCounting {
    private static CyclicBarrier barrier;
    //Using AtomicInteger to avoid "Non atomic operation warning" when using primitive int
    private static volatile AtomicInteger num;
    private static volatile boolean done;
    private static Counter ctr;

    public static void countInThreads(Counter counter) {
        num = new AtomicInteger(1);
        done = false;
        ctr = counter;
        barrier = new CyclicBarrier(3, () -> {
            if (num.incrementAndGet() == 101) {
                done = true;
            }
        });
        //For each number, all three threads run. But only one prints (depends on what mod value it should act on)
        Thread t1 = new Thread(new Worker(0));
        Thread t2 = new Thread(new Worker(1));
        Thread t3 = new Thread(new Worker(2));

        t1.start();
        t2.start();
        t3.start();
        try {
            t1.join();
            t2.join();
            t3.join();
        } catch(InterruptedException ex) {
            System.out.println(ex.getMessage());
        }
    }
    private static class Worker implements Runnable {
        private int mod;
        Worker(int mod) {
            this.mod = mod;
        }

        @Override
        public void run() {
            while (!done) {
                if (num.get() % 3 == mod) {
                    ctr.count(num.get());
                }
                try {
                    barrier.await();
                } catch(InterruptedException | BrokenBarrierException ex) {
                    System.out.println(ex.getMessage());
                }
            }
        }
    }
    //Creating to compile
    class Counter {
        void count(int num) {

        }
    }
}


