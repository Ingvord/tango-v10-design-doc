package org.tango.v10.server.polling;

/**
 * @author ingvord
 * @since 8/19/18
 */
public interface ExecutionTask extends Runnable {
    /**
     * Next timestamp for this task to be executed
     *
     * @return timestamp in millis
     */
    long getScheduledTime();

    /**
     *
     * @param scheduledTime next execution time
     */
    void setScheduledTime(long scheduledTime);

    /**
     * For how long this task execution must be delayed
     *
     * @return delay in millis
     */
    long getDelay();

    /**
     * For how long this task execution must be delayed
     *
     * @param newDelay a new delay for this task
     */
    void setDelay(long newDelay);
}
