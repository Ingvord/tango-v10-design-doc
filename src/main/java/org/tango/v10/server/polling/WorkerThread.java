package org.tango.v10.server.polling;

/**
 * @author ingvord
 * @since 8/19/18
 */
public interface WorkerThread extends Runnable {
    void execute(ExecutionTask task);
}
