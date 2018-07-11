package org.tango.v10.transport;

import java.net.URI;

/**
 * Responsible for creating TangoTransport instances. Creating means initialization low level transport as well as injecting
 * (un)marshaller
 *
 * @author ingvord
 * @since 7/11/18
 */
public interface TangoTransportFactory {
    /**
     *
     * @param lowerLevelTransport aka corba, http, zmq etc
     * @return new TangoTransport instance
     * @throws TangoTransportException if initialization has failed
     */
    //TODO specialize transport exception
    TangoTransport newInstance(String lowerLevelTransport) throws LowLevelTransportIsNotSupported, TangoTransportException;

    /**
     * Convenient method - extracts TangoTransport from URI
     *
     * @param uri a Tango resource URI aka tango:corba://tango_host:port/devices/sys/tg_test/1
     * @return TangoTransport
     * @throws LowLevelTransportIsNotSupported
     * @throws TangoTransportException if initialization has failed
     */
    //TODO specialize transport exception
    TangoTransport fromURI(URI uri) throws LowLevelTransportIsNotSupported, TangoTransportException;

    class TangoTransportException extends Exception {
    }

    class LowLevelTransportIsNotSupported extends Exception {
    }
}
