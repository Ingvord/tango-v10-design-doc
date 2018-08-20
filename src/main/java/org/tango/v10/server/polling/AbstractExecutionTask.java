package org.tango.v10.server.polling;

import java.beans.ExceptionListener;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @author ingvord
 * @since 8/20/18
 */
public abstract class AbstractExecutionTask implements ExecutionTask {
    private final AtomicLong delay;
    private final AtomicLong scheduledTime;

    public AbstractExecutionTask(long delay, long scheduledTime) {
        this.delay = new AtomicLong(delay);
        this.scheduledTime = new AtomicLong(scheduledTime);
    }

    @Override
    public long getScheduledTime() {
        return scheduledTime.get();
    }

    @Override
    public long getDelay() {
        return delay.get();
    }

    @Override
    public void setScheduledTime(long scheduledTime) {
        this.scheduledTime.set(scheduledTime);
    }

    @Override
    public void setDelay(long newDelay) {
        this.delay.set(newDelay);
    }

    @Override
    public final void run() {
        innerRun();
        setScheduledTime(System.currentTimeMillis() + getDelay());
    }

    protected abstract void innerRun();
}
