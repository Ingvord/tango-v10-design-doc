package org.tango.v10.client.event;

/**
 * @author ingvord
 * @since 8/6/18
 */
public interface EventConsumer<T extends EventTransport> {
     T createEventSubscription();
     void consume(EventData eventData);

    interface ZmqEventConsumer extends EventConsumer<EventTransport.ZmqEventTransport> {
        @Override
        EventTransport.ZmqEventTransport createEventSubscription();
    }

    interface NotifdEventConsumer extends EventConsumer<EventTransport.NotifdEventTransport> {
        @Override
        EventTransport.NotifdEventTransport createEventSubscription();
    }
}
