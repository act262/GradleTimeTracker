package io.micro.gradle

import java.util.concurrent.TimeUnit

/**
 * Compat gradle's Clock/Timer
 */
class Timer {

    private long startTime

    Timer() {
        reset()
    }

    long getElapsedMillis() {
        long elapsedNanos = System.nanoTime() - startTime;
        long elapsedMillis = TimeUnit.NANOSECONDS.toMillis(elapsedNanos);

        // System.nanoTime() can go backwards under some circumstances.
        // http://bugs.java.com/bugdatabase/view_bug.do?bug_id=6458294
        // This max() call ensures that we don't return negative durations.
        return Math.max(elapsedMillis, 0);
    }

    void reset() {
        startTime = System.nanoTime()
    }

}
