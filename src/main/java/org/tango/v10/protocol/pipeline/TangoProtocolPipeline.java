package org.tango.v10.protocol.pipeline;

/**
 * @author ingvord
 * @since 7/11/18
 */
public interface TangoProtocolPipeline {
    void registerRequestFilter(Priority priority, TangoRequestFilter filter);
    void registerResponseFilter(Priority priority, TangoResponseFilter filter);

    /**
     * @author ingvord
     * @since 7/11/18
     */
    enum Priority {
        //TODO
    }
}
