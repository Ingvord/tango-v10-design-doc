package org.tango.v10.client.event;

import java.io.IOException;
import java.net.URI;

/**
 * @author ingvord
 * @since 8/6/18
 */
public interface EventSubscriber {
    URI getTarget();
    EventSubscription<? extends EventData> createSubscription(String event /*TODO replace with enum*/) throws IOException;

    abstract class  ZmqEventSubscriber implements EventSubscriber {

    }

    abstract class   NotifdEventSubscriber implements EventSubscriber {
        
    }
}
