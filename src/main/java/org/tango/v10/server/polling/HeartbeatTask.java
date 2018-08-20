package org.tango.v10.server.polling;

import org.tango.v10.server.event.EventSystem;

/**
 * @author ingvord
 * @since 8/20/18
 */
public class HeartbeatTask extends AbstractExecutionTask {

    private final EventSystem eventSystem;

    public HeartbeatTask(long delay, long scheduledTime, EventSystem eventSystem) {
        super(delay, scheduledTime);
        this.eventSystem = eventSystem;
    }

    @Override
    public void innerRun() {
        sendHeartbeat();
    }

    private void sendHeartbeat(){
        eventSystem.send();
    }
}
