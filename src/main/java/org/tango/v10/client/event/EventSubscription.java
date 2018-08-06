package org.tango.v10.client.event;

import java.net.URI;

/**
 *
 *
 * @author ingvord
 * @since 8/6/18
 */
public interface EventSubscription<T extends EventData> {
    EventTransport getTransport();
    URI getTarget();
    T get() throws InterruptedException;
    <V extends EventConsumer> V getEventConsumer(T eventData);
    void cancel();


    interface ChangeEventSubscription extends EventSubscription<EventData.ChangeEventData> {
        @Override
        EventData.ChangeEventData get() throws InterruptedException;
    }

    interface PipeEventSubscription extends EventSubscription<EventData.PipeEventData> {
        @Override
        EventData.PipeEventData get() throws InterruptedException;
    }
}
