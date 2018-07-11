package org.tango.v10.protocol;

import org.tango.v10.transport.TangoMessage;

/**
 * @author ingvord
 * @since 7/11/18
 */
public interface TangoRequestBuilder {
    TangoRequestBuilder setProtocolVersion(ProtocolVersion version);
    TangoRequestBuilder setTarget(TangoResource resource);
    TangoRequestBuilder setSource(TangoResource resource);
    TangoRequestBuilder setMessage(TangoMessage message);
    TangoRequest build();
}
