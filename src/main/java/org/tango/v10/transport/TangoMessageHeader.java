package org.tango.v10.transport;

/**
 * @author ingvord
 * @since 7/11/18
 */
public interface TangoMessageHeader<T> {
    T getHeaderValue();
}
