package org.tango.v10.client.event;

import org.tango.v10.api.client.DeviceProxy;

import java.io.IOException;
import java.net.URI;

/**
 * @author ingvord
 * @since 8/6/18
 */
public interface EventTransport extends Runnable {
    void connect(URI target) throws IOException;
    void disconnect() throws IOException, IllegalStateException;
    EventData nextEvent() throws InterruptedException;
    void send(EventData eventData) throws IOException;

    @Override
    default void run() {
        while(!Thread.currentThread().isInterrupted()){
            try {
                EventData event = this.nextEvent();

                EventSubscription subscription = EventSubscriptions.getFor(event);

                subscription.getEventConsumer().consume(event);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }

    interface ZmqEventTransport extends EventTransport{

    }

    interface NotifdEventTransport extends EventTransport{

    }
}
