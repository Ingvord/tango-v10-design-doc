package org.tango.v10.protocol;

/**
 * Specifies low level exceptions e.g. IOException -> TangoProtocolException
 */
public abstract class TangoProtocolException extends Exception {
    abstract ProtocolVersion getProtocolVersion();
    abstract TangoRequest getRequest();
    abstract TangoRequest getResponse();
}
