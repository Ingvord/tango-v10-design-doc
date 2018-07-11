package org.tango.v10.protocol.pipeline;

import org.tango.v10.protocol.TangoResponse;

/**
 * @author ingvord
 * @since 7/11/18
 */
public interface TangoResponseFilter {
    boolean filter(TangoResponse resp);
}
