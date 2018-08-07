package org.tango.v10.api;

import org.tango.v10.api.client.DeviceProxy;
import org.tango.v10.client.event.EventSubscription;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;

/**
 * @author ingvord
 * @since 8/7/18
 */
public class TangoControls {
    private static final TangoControlsContext CONTEXT = new TangoControlsContext();

    public static EventSubscription createEventSubscription(URI target){
        URI uri = URI.create(target.getSchemeSpecificPart());//omit tango:



        return null;
    }

    public static DeviceProxy createOrGetDeviceProxy(URI uri){
        return CONTEXT.proxies.get(uri);
    }

    public static class TangoControlsContext{
        private final Map<URI,DeviceProxy> proxies = new HashMap<>();
    }
}
