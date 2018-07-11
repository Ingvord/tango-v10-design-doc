package org.tango.v10.protocol;

import org.tango.v10.transport.TangoMessage;

import java.net.URI;
import java.util.Collection;

/**
 * High level wrapper for {@link TangoMessage}
 *
 * @author ingvord
 * @since 7/11/18
 */
public interface TangoRequest extends TangoMessage {

    interface ReadAttributes extends TangoRequest {
         void setAttributesURI(Collection<URI> attrs);
         void setAttributesValue(Collection<?> values);
    }

    //etc
}
