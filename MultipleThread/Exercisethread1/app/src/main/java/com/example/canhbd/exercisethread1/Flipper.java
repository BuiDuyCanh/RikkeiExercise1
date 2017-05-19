package com.example.canhbd.exercisethread1;

/**
 * Created by canhbd on 5/19/2017.
 */

public class Flipper implements Runnable {
    private final int mLoopLimit;
    public Flipper(int loopLimit) {
        this.mLoopLimit = loopLimit;
    }
    public void run() {
        for (int i = 0; i < mLoopLimit; i++) {
            String threadName =
                    Thread.currentThread().getName();
            System.out.printf("%s: %s%n", threadName, i);
// Sleep for up to 1 second
            ThreadUtils.pause(Math.random());
        }
    }
}
