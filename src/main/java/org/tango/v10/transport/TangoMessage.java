package org.tango.v10.transport;

import java.util.Collection;

/**
 * @author ingvord
 * @since 7/11/18
 */
public interface TangoMessage {
    /**
     *
     * @return all headers
     */
    Collection<TangoMessageHeader<?>> getHeaders();

    /**
     *
     * @param header header
     * @param <T> header value type
     * @return corresponding header or null
     */
    <T> TangoMessageHeader<T> getHeader(String header);

    TangoMessageBody getBody();
}
