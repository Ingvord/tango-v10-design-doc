package org.tango.v10.client.event;

import java.io.IOException;
import java.net.URI;

/**
 *
 *
 * @author ingvord
 * @since 8/6/18
 */
public interface EventSubscription<T extends EventData> {
    EventTransport getTransport();
    URI getTarget();//maybe used as id
    default String getId(){
        return getTarget().toString();
    }
    void setEndpoint(URI endpoint);
    URI getEndpoint();
    T get() throws InterruptedException;
    <V extends EventConsumer> void setEventConsumer(V consumer);


    /**
     * Invokes TangoAdmin.confirmSubscription
     *
     * @return true if subscription is till valid
     */
    boolean confirm() throws IOException;

    /**
     * By default every subscription is created with SimpleCallbackConsumer, with default callback -- stores events into buffer
     *
     * @param <V>
     * @return
     */
    <V extends EventConsumer> V getEventConsumer();
    default void cancel() throws IOException {
        getTransport().disconnect();
    }
    Iterable<T> getBuffer();


    interface ChangeEventSubscription extends EventSubscription<EventData.ChangeEventData> {
        @Override
        EventData.ChangeEventData get() throws InterruptedException;
    }

    interface PipeEventSubscription extends EventSubscription<EventData.PipeEventData> {
        @Override
        EventData.PipeEventData get() throws InterruptedException;
    }
}
