package com.example.canhbd.exercisethread1;

/**
 * Created by canhbd on 5/19/2017.
 */

public class ThreadUtils {
    public static void pause(double seconds) {
        try {
            Thread.sleep(Math.round(1000.0 * seconds));
        } catch (InterruptedException ie) {}
    }
    // Uninstantiable class: static methods only
    private ThreadUtils() {}
}
