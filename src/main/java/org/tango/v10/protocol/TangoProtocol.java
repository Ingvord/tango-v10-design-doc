package org.tango.v10.protocol;

/**
 * Implementation delegates transmission to {@link org.tango.v10.transport.TangoTransport} while providing basic
 *
 *
 * @author ingvord
 * @since 7/11/18
 */
public interface TangoProtocol {
    ProtocolVersion getVersion();

    TangoResponse handShake(TangoRequest req) throws TangoProtocolException;
    /**
     * Actual implementation will use specialized TangoRequest and TangoResponse
     *
     * @param req TangoRequest.ReadAttributes
     * @return TangoResponse.ReadAttributes
     * @throws TangoProtocolException
     * @see TangoRequest.ReadAttributes
     */
    TangoResponse readAttributes(TangoRequest req) throws TangoProtocolException;
    TangoResponse writeAttributes(TangoRequest req) throws TangoProtocolException;
    TangoResponse executeCommands(TangoRequest req) throws TangoProtocolException;
    TangoResponse readPipes(TangoRequest req) throws TangoProtocolException;

    boolean validate(TangoRequest req);
    boolean validate(TangoResponse res);

    /**
     * Specifies low level exceptions e.g. IOException -> TangoProtocolException
     */
    abstract class TangoProtocolException extends Exception {
        abstract ProtocolVersion getProtocolVersion();
        abstract TangoRequest getRequest();
        abstract TangoRequest getResponse();
    }
    //etc
}
