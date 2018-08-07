package org.tango.v10.client.event;

import org.junit.Before;
import org.junit.Test;
import org.tango.v10.api.client.DeviceProxy;

import java.io.IOException;

import static org.mockito.Mockito.mock;

/**
 * @author ingvord
 * @since 8/6/18
 */
public class TestEventClient {
    private DeviceProxy proxy = null;//injected

    @Before
    public void before() throws Exception {
        proxy = mock(DeviceProxy.class);

    }

    public void run() throws Exception {
        EventTransport transport = null;

        while (!Thread.currentThread().isInterrupted()) {
            EventData event = transport.nextEvent();

            EventSubscription<?> subscription = EventSubscriptions.getFor(event);

            subscription.getEventConsumer().consume(event);
        }
    }

    @Test
    public void test_heartbeat() throws Exception {
        EventSubscriber subscriber = proxy.getEventSubscriber("zmq");
        EventSubscription subscription = subscriber.createHeartbeatSubscription();

        subscription.getTransport().connect(subscription.getEndpoint());
        subscriber.startTransport();
    }

    @Test
    public void test_subscribe() throws Exception {
//        TangoControls.createEventSubscription(URI.create("tango:zmq://localhost:10000/sys/tg_test/1/heartbeat"));
//        TangoControls.createEventSubscription(URI.create("tango:zmq://localhost:10000/sys/tg_test/1/interface"));
//        TangoControls.createEventSubscription(URI.create("tango:zmq://localhost:10000/sys/tg_test/1/float_scalar?event=change"));
//        TangoControls.createEventSubscription(URI.create("tango:notifd://localhost:10000/sys/tg_test/1/float_scalar?event=change"));

        EventSubscriber subscriber = proxy.getEventSubscriber("zmq");
        EventSubscription subscription = subscriber.createOrGetSubscription("float_scalar","change");

        try {
            subscriber.getTransport().connect(subscription.getEndpoint());

            subscription.setEventConsumer(EventConsumers.createSimpleCallbackConsumer(new EventCallback() {
                @Override
                public void onEvent(EventData data) {
                    System.out.println("onEvent");
                }

                @Override
                public void onError(EventData data) {
                    System.out.println("onError");
                }
            }));
        } catch (/*EventTransportConnectionFailed*/ IOException ex){
//            subscription.setEventConsumer(/*EventConsumer*/ consumer);
            throw ex;
        }




    }

    @Test
    public void test_unsubscribe() throws Exception {
        EventSubscriber subscriber = proxy.getEventSubscriber("zmq");
        EventSubscription subscription = subscriber.createOrGetSubscription("float_scalar","change");

        subscription.cancel();
    }
}
