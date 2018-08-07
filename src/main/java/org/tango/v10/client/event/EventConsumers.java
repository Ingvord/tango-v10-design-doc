package org.tango.v10.client.event;

/**
 * @author ingvord
 * @since 8/7/18
 */
public class EventConsumers {
    public static EventConsumer createSimpleCallbackConsumer(final EventCallback callback){
        return new EventConsumer() {
            @Override
            public void consume(EventData eventData) {
                if(eventData.hasFailed())
                    callback.onError(eventData);
                else
                    callback.onEvent(eventData);
            }

            @Override
            public <T extends EventData> void addCallback(EventCallback<T> callback) {
                throw new UnsupportedOperationException();
            }
        };
    }

}
