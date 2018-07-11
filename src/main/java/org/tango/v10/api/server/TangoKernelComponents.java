package org.tango.v10.api.server;

/**
 * @author ingvord
 * @since 7/11/18
 */
public abstract class TangoKernelComponents {
    abstract void registerComponent(TangoKernelComponent component) throws TangoKernelComponentException;

    private class TangoKernelComponentException extends Exception {
    }
}
