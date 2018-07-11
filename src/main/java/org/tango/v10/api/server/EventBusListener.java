package org.tango.v10.api.server;

import org.tango.v10.server.EventContext;

/**
 * @author ingvord
 * @since 7/11/18
 */
public interface EventBusListener {
    void onBeforeEvent(EventContext context);

    void onEvent(EventContext context, Object message);

    void onAfterEvent(EventContext context);

    void onBeforeError(EventContext context);

    void onError(EventContext context, Exception clause);

    void onAfterError(EventContext context);
}
