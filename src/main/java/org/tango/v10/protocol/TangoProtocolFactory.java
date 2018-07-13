package org.tango.v10.protocol;

/**
 * @author ingvord
 * @since 7/11/18
 */
public interface TangoProtocolFactory {
    TangoProtocolBackend newInstance(ProtocolVersion version);
}
