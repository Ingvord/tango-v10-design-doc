package org.tango.v10.client.event;

import java.io.IOException;
import java.net.URI;

/**
 * @author ingvord
 * @since 8/6/18
 */
public interface EventSubscriber {
    URI getTarget();

    EventTransport getTransport();
    void startTransport();

    EventSubscription<? extends EventData> createOrGetSubscription(String attr, String event /*TODO replace with enum*/) throws IOException;
    /**
     *
     * @param event
     * @return subscription for a given event
     * @throws java.util.NoSuchElementException
     */
    EventSubscription<? extends EventData> getSubscription(EventData event);

    /**
     * Creates new EventSubscription specific for heartbeat i.e. has predefined EventConsumer
     *
     * @return new EventSubscription
     */
    EventSubscription<EventData.HeartbeatEventData> createHeartbeatSubscription();

    abstract class  ZmqEventSubscriber implements EventSubscriber {

    }

    abstract class   NotifdEventSubscriber implements EventSubscriber {
        
    }
}
