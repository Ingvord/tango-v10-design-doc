package org.tango.v10.client.event;

/**
 * @author ingvord
 * @since 8/6/18
 */
public interface EventConsumer{
     void consume(EventData eventData);
     <T extends EventData> void addCallback(EventCallback<T> callback);
}
