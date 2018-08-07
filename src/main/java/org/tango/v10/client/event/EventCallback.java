package org.tango.v10.client.event;

/**
 * @author ingvord
 * @since 8/7/18
 */
public interface EventCallback<T extends EventData> {
    void onEvent(T data);
    void onError(T data);

    interface HeartbeatEventCallback extends EventCallback<EventData.HeartbeatEventData>{
        //onEvent -- reconnect
        //onError
    }
}
