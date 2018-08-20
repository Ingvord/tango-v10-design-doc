package org.tango.v10.server.polling;

import org.tango.v10.service.TangoDevice;

import java.util.Collection;
import java.util.Optional;
import java.util.PriorityQueue;

/**
 * Ordered by scheduled time queue
 *
 * @author ingvord
 * @since 8/19/18
 */
public interface ExecutionTasksQueue {
    /**
     *
     * @return a reference to the head element
     */
    Optional<ExecutionTask> head();

    <T extends ExecutionTask> void add(T task);
    <T extends ExecutionTask> void remove(T task);

    /**
     *
     * @return all polled attributes
     */
    Collection<PollingTask> getPollableAttributes();

    /**
     *
     * @param device
     * @return pollable attributes filtered by device
     */
    Collection<PollingTask> getPollableAttributes(TangoDevice device);
    Collection<PollingTask> getPollableCommands();
    Collection<PollingTask> getPollableCommands(TangoDevice device);
}
