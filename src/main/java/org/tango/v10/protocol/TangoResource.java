package org.tango.v10.protocol;

import java.net.URI;

/**
 * @author ingvord
 * @since 7/11/18
 */
public interface TangoResource {
    TangoProtocol getProtocol(ProtocolVersion version) throws UnsupportedProtocolVersion;
    URI toURI();
    //TODO ???
    TangoRequest asTangoRequest();
    TangoResponse asTangoResponse();

    class UnsupportedProtocolVersion extends Exception {
    }
}
