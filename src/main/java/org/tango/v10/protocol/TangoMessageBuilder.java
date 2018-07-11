package org.tango.v10.protocol;

import org.tango.v10.transport.TangoMessage;
import org.tango.v10.transport.TangoMessageBody;

/**
 * @author ingvord
 * @since 7/11/18
 */
public interface TangoMessageBuilder {
    TangoMessageBuilder setProtocolVersion(ProtocolVersion version);
    TangoMessageBuilder setKernelVersion(KernelVersion version);
    TangoMessageBuilder setType(TangoMessageType type);
    TangoMessageBuilder setDataType(TangoDataType type);
    TangoMessageBuilder setBody(TangoMessageBody body);
    TangoMessageBuilder multiplex(TangoMessage body);
    TangoMessage build();
}
