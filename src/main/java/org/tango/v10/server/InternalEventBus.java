package org.tango.v10.server;

import org.tango.v10.api.server.EventBusListener;

/**
 * @author ingvord
 * @since 7/11/18
 */
public interface InternalEventBus {
    void subscribe(String event, EventBusListener listener);

    void publish(String event, Object message);//TODO specialize message
}
