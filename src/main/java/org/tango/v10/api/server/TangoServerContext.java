package org.tango.v10.api.server;

import org.tango.v10.protocol.TangoProtocolBackend;
import org.tango.v10.server.InternalEventBus;

/**
 * @author ingvord
 * @since 7/11/18
 */
public interface TangoServerContext {
    InternalEventBus getEventBus();
    TangoProtocolBackend getProtocol();
    TangoVersion getVersion();
    //put
    //get
}
