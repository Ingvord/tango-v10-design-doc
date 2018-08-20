package org.tango.v10.server.polling;

import java.util.concurrent.TimeUnit;

/**
 * Decorator for {@link ExecutionTask}. Measures time required for the inner task to be executed.
 *
 * @author ingvord
 * @since 8/19/18
 */
public class StopWatchTask implements ExecutionTask {
    private final ExecutionTask innerTask;
    private volatile long start;
    private volatile long stop;
    private volatile long executionTime;

    public StopWatchTask(ExecutionTask innerTask) {
        this.innerTask = innerTask;
    }

    /**
     *
     * @return nano
     */
    long getStartTime(){
        return start;
    }

    /**
     *
     * @return nano
     */
    long getEndTime(){
        return stop;
    }

    /**
     *
     * @return millis
     */
    long getExecutionTime(){
        return executionTime;
    }

    @Override
    public void run() {
        start = System.nanoTime();
        innerTask.run();
        stop = System.nanoTime();
        executionTime = TimeUnit.MILLISECONDS.convert(stop - start,TimeUnit.NANOSECONDS);
    }

    @Override
    public long getScheduledTime() {
        return innerTask.getScheduledTime();
    }

    @Override
    public void setScheduledTime(long scheduledTime) {
        innerTask.setScheduledTime(scheduledTime);
    }

    @Override
    public long getDelay() {
        return innerTask.getDelay();
    }

    @Override
    public void setDelay(long newDelay) {
        innerTask.setDelay(newDelay);
    }
}
