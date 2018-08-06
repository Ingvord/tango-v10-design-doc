package org.tango.v10.client.event;

/**
 * @author ingvord
 * @since 8/6/18
 */
public interface EventSubscriptionHandler {
    <T extends EventSubscription> void handle(T eventSubscription);
}
