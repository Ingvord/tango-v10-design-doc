package org.tango.v10.transport;

/**
 * Responsible for TangoMessage (un)marshalling
 *
 * @param <T> typically will be byte[] but maybe something specific for the low level transport
 * @see TangoTransportFactory
 * @author ingvord
 * @since 7/11/18
 */
public interface TangoMarshaller<T> {
    T marshal(TangoMessage message) throws TangoMarshallerException;

    TangoMessage unmarshal(T message) throws TangoMarshallerException;

    class TangoMarshallerException extends Exception {
    }
}
