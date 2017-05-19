package com.example.canhbd.exercise3thread;

/**
 * Created by canhbd on 5/19/2017.
 */

public class Innerclass implements Runnable {
    private final int mLoopLimit;
    public Innerclass(int loopLimit) {
        this.mLoopLimit = loopLimit;
    }
    public void run() {
        for (int i = 0; i < mLoopLimit; i++) {
            String threadName =
                    Thread.currentThread().getName();
            System.out.printf("%s: %s%n", threadName, i);
            ThreadUtils.pause(Math.random());
        }
    }
}

