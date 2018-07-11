package org.tango.v10.service;

import org.tango.v10.protocol.TangoResource;

import java.util.Collection;
import java.util.concurrent.Future;
import java.util.stream.Stream;

/**
 * @author ingvord
 * @since 7/11/18
 */
public interface TangoDevice extends TangoResource {
    Future<?> readAttribute(TangoAttribute<?> attr) throws TangoDeviceException;
    Stream<?> readAttributeAsStream(TangoAttribute<?> attr) throws TangoDeviceException;
    Future<?> readAttributes(Collection<TangoAttribute<?>> attrs) throws TangoDeviceException;
    Stream<Stream<?>> readAttributesAsStream(Collection<TangoAttribute<?>> attrs) throws TangoDeviceException;
    Future<TangoCommand> executeCommand(TangoCommand<?,?> cmd) throws TangoDeviceException;
    Future<Collection<TangoCommand>> executeCommands(Collection<TangoCommand> cmds) throws TangoDeviceException;
    //TODO etc

    /**
     * TODO specific exceptions e.g. TangoDeviceReadAttributeException
     */
    class TangoDeviceException extends Exception {
    }
}
