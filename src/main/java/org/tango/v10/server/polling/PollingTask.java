package org.tango.v10.server.polling;

import java.util.concurrent.TimeUnit;

/**
 * @author ingvord
 * @since 8/19/18
 */
public class PollingTask<T extends Pollable<?>> extends AbstractExecutionTask {
    private final T pollable;

    public PollingTask(long delay, long scheduledTime, T pollable) {
        super(delay, scheduledTime);
        this.pollable = pollable;
    }

    T getPollable(){
        return pollable;
    }

    @Override
    public void innerRun() {
        pollable.poll();
    }
}
