package org.tango.v10.server.polling;

import java.util.PriorityQueue;

/**
 * @author ingvord
 * @since 8/19/18
 */
public interface ControlThread extends Runnable {
    void add(Pollable<?> item);
    void remove(Pollable<?> item);
    PriorityQueue<Pollable<?>> getQueue();
}
