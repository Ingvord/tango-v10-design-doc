package org.tango.v10.protocol.pipeline;

import org.tango.v10.protocol.TangoRequest;

/**
 * @author ingvord
 * @since 7/11/18
 */
public interface TangoRequestFilter {
    boolean filter(TangoRequest resp);
}
