package org.tango.v10.service;

import java.net.URI;

/**
 * @author ingvord
 * @since 7/11/18
 */
public interface TangoServiceProvider {
    TangoHost lookupHost(URI uri) throws TangoServiceProviderException;
    TangoDevice lookupDevice(URI uri) throws TangoServiceProviderException;
    TangoAttribute lookupAttribute(URI uri) throws TangoServiceProviderException;
    TangoCommand lookupCommand(URI uri) throws TangoServiceProviderException;
    //etc

    class TangoServiceProviderException extends Exception {
    }
}
