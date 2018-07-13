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
public interface TangoProtocolBackend {
    ProtocolVersion getVersion();


    Future<TangoResponse> subscribe(TangoRequest req) throws TangoProtocolException;
    Future<TangoResponse> discover(TangoRequest req) throws TangoProtocolException;
    Future<TangoResponse> ping(TangoRequest req) throws TangoProtocolException;
    void heartbeat(TangoRequest req) throws TangoProtocolException;

    Future<TangoResponse> executeCommand(TangoRequest req) throws TangoProtocolException;


    boolean validate(TangoRequest req);
    boolean validate(TangoResponse res);

    TangoTransport getTransport();

    //etc
}
