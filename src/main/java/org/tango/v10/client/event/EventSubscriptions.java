package org.tango.v10.client.event;

import java.util.Optional;

/**
 * @author ingvord
 * @since 8/7/18
 */
public abstract class EventSubscriptions {
    /**
     *
     * @param event
     * @return
     * @throws java.util.NoSuchElementException
     */
    static EventSubscription<?> getFor(EventData event){
        return null;
    }
}
