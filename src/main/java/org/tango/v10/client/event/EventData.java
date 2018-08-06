package org.tango.v10.client.event;

/**
 * @author ingvord
 * @since 8/6/18
 */
public interface EventData {
    interface ChangeEventData extends EventData {}
    interface ArchiveEventData extends EventData {}
    interface PipeEventData extends EventData {}
    interface FailedEventData extends EventData {}
    //etc
}
