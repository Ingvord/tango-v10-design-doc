package org.tango.v10.api.client;

import org.tango.v10.client.event.EventData;
import org.tango.v10.client.event.EventSubscriber;
import org.tango.v10.client.event.EventSubscription;
import org.tango.v10.client.event.EventTransport;

import java.net.URI;

/**
 * @author ingvord
 * @since 8/6/18
 */
public interface DeviceProxy {
    URI asURI();
    EventSubscriber getEventSubscriber(String transport);
    EventSubscription<EventData.HeartbeatEventData> heartbeat();
    Iterable<EventSubscription> getSubscriptions();
}
