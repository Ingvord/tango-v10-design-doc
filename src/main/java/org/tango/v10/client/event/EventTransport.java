package org.tango.v10.client.event;

import java.io.IOException;
import java.net.URI;

/**
 * @author ingvord
 * @since 8/6/18
 */
public interface EventTransport {
    void connect(URI target) throws IOException;


    interface ZmqEventTransport extends EventTransport{

    }

    interface NotifdEventTransport extends EventTransport{

    }
}
