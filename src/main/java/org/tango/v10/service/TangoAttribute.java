package org.tango.v10.service;

import org.tango.v10.protocol.TangoResource;

import java.util.concurrent.Future;
import java.util.stream.Stream;

/**
 * @author ingvord
 * @since 7/11/18
 */
public interface TangoAttribute<T> extends TangoResource {
    Stream<T> readAsStream() throws TangoAttributeException;
    Future<T> read()  throws TangoAttributeException;

    class TangoAttributeException extends Exception {
    }
    TangoEvent asChangeEvent()  throws TangoAttributeException;
    TangoEvent asPeriodicEvent()  throws TangoAttributeException;
    TangoEvent asArchiveEvent()  throws TangoAttributeException;
}
