package org.tango.v10.api.server;

/**
 * @author ingvord
 * @since 7/11/18
 */
public interface TangoWorkerComponent extends TangoKernelComponent {
    void start();
    void stop();

}
