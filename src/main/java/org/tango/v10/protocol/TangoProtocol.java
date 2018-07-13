package org.tango.v10.protocol;

import org.tango.v10.transport.TangoTransport;

import java.util.concurrent.Future;

/**
 * Implementation delegates transmission to {@link org.tango.v10.transport.TangoTransport} while providing basic
 *
 *
 * @author ingvord
 * @since 7/11/18
 */
//TODO (de)multiplex
public interface TangoProtocol {
    ProtocolVersion getVersion();

    /**
     * Initial routine two establish connection between two Tango resources.
     * This is actually an aggregation of more fine grained protocol primitives e.g. discover and heartbeat
     *
     * @param req
     * @return
     * @throws TangoProtocolException
     */
    Future<TangoResponse> handShake(TangoRequest req) throws TangoProtocolException;
    Future<TangoResponse> subscribe(TangoRequest req) throws TangoProtocolException;
    Future<TangoResponse> discover(TangoRequest req) throws TangoProtocolException;
    Future<TangoResponse> ping(TangoRequest req) throws TangoProtocolException;
    void heartbeat(TangoRequest req) throws TangoProtocolException;
    /**
     * Actual implementation will use specialized TangoRequest and TangoResponse
     *
     * @param req TangoRequest.ReadAttributes
     * @return TangoResponse.ReadAttributes
     * @throws TangoProtocolException
     * @see TangoRequest.ReadAttributes
     */
    Future<TangoResponse> readAttributes(TangoRequest req) throws TangoProtocolException;
    Future<TangoResponse> writeAttributes(TangoRequest req) throws TangoProtocolException;
    Future<TangoResponse> executeCommands(TangoRequest req) throws TangoProtocolException;
    Future<TangoResponse> readPipes(TangoRequest req) throws TangoProtocolException;

    boolean validate(TangoRequest req);
    boolean validate(TangoResponse res);

    TangoTransport getTransport();

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
