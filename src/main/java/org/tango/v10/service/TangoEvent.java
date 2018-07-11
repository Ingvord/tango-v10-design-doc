package org.tango.v10.service;

import org.tango.v10.protocol.TangoResource;

import java.util.stream.Stream;

/**
 * @author ingvord
 * @since 7/11/18
 */
public interface TangoEvent<T> extends TangoResource {
    Stream<T> read() throws TangoEventException;

    class TangoEventException extends Exception {
    }

    //TODO Change, Archive etc specialized events
}
