package org.tango.v10.protocol;

import java.util.concurrent.Future;

/**
 * @author ingvord
 * @since 7/13/18
 */
public interface TangoProtocolFrontend {
    /**
     * Initial routine two establish connection between two Tango resources.
     * This is actually an aggregation of more fine grained protocol primitives e.g. discover and heartbeat
     *
     * @param req
     * @return
     * @throws TangoProtocolException
     */
    Future<TangoResponse> handShake(TangoRequest req) throws TangoProtocolException;

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
}
