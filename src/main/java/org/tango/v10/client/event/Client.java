package org.tango.v10.client.event;

import org.tango.v10.api.client.DeviceProxy;

import java.net.URI;

/**
 * @author ingvord
 * @since 8/6/18
 */
public class Client {
    private final DeviceProxy proxy = null;//injected

    public void run() throws Exception {
        EventSubscription subscription = null;

        while (!Thread.currentThread().isInterrupted()) {
            EventData data = subscription.get();
            EventConsumer<?> consumer = subscription.getEventConsumer(data);
            consumer.consume(data);
        }
    }



    public void subscribe() throws Exception {
        EventSubscriber subscriber = proxy.getEventSubscriber(URI.create("tango://localhost:10000/sys/tg_test/1/long_scalar"));
        EventSubscription subscription = subscriber.createSubscription("change");

        subscription.setConsumer(/*EventConsumer*/ consumer);




    }
}
