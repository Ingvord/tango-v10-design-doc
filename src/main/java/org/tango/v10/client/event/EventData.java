package org.tango.v10.client.event;

import java.net.URI;

/**
 * @author ingvord
 * @since 8/6/18
 */
public interface EventData {
    URI getSource();
    long getTimestamp();
    boolean hasFailed();
    <T extends Throwable> T getException();

    interface HeartbeatEventData extends EventData {}
    interface ChangeEventData<T extends Number> extends EventData {
        T getPreviousValue();
        T getValue();
    }
    interface ArchiveEventData extends EventData {}
    interface PipeEventData extends EventData {}
    interface FailedEventData extends EventData {}
    //etc
}
