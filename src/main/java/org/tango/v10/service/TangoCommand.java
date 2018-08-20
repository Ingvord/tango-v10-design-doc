package org.tango.v10.service;

import org.tango.v10.protocol.TangoResource;

import java.util.concurrent.ExecutionException;

/**
 * @author ingvord
 * @since 7/11/18
 */
public interface TangoCommand<IN, OUT> extends TangoResource {
    OUT execute(IN input) throws ExecutionException;
}
