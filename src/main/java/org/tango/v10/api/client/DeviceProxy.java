package org.tango.v10.api.client;

import org.tango.v10.client.event.EventSubscriber;

import java.net.URI;

/**
 * @author ingvord
 * @since 8/6/18
 */
public interface DeviceProxy {
    EventSubscriber getEventSubscriber(URI target);
}
