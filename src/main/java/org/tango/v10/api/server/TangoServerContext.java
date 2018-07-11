package org.tango.v10.api.server;

import org.tango.v10.server.InternalEventBus;

/**
 * @author ingvord
 * @since 7/11/18
 */
public interface TangoServerContext {
    InternalEventBus getEventBus();
    //put
    //get
}
